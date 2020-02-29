package com.example.hdlitest.redis;

import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 *
 * 使用redis实现分布式锁
 * @author 李会东
 * @version 1.0
 * @date 2019-12-10 17:55
 */
@Component
public class RedisLocakTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String test(){
        String locakKey = "locked";
        //标记当前线程
        String clientId = UUID.randomUUID().toString();
        try {
            //在springboot 2.2.0 以后的高版本中才有的 保证 加锁 和 超时 原子操作
            boolean result = stringRedisTemplate.opsForValue().setIfAbsent(locakKey,"1",10L, TimeUnit.SECONDS);
            //TODO 保证重入 若加锁失败 查询是不是当前线程已经加过锁了，若是当前线程加的锁 value = value+1 加锁成功
            if (!result){
                return "lock error";
            }
            //TODO 添加异步线程 每隔锁超时间的1/3的时间检查线程是否存活 如果存活重置重置锁过期时间

            //TODO 执行业务代码
            return "success";
        }finally {
            //保证自己加锁 自己解锁
            if (clientId.equals(stringRedisTemplate.opsForValue().get(locakKey))){
                //TODO 获取线程锁的值 如果减1都小于等于0 执行那个删除 （实现锁重入）
                stringRedisTemplate.delete(locakKey);
            }
        }
    }

    /**
     * 编写一个ScheduledThreadPoolExecutor 按某个频率去检查 某个线程的状态
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"开始");
        String locakKey = "locked";
        String clientId = UUID.randomUUID().toString();

        ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
        Item item = new Item(thread,clientId,10L,TimeUnit.SECONDS,locakKey,scheduledExecutorService);
        scheduledExecutorService.scheduleAtFixedRate(item,10/3,10/3,TimeUnit.SECONDS);

        Thread.sleep(40000);
        System.out.println(thread.getName()+"结束");
    }

    static class Item implements Runnable{

        private Thread thread;

        private String threadId;

        private long timeOut;

        private TimeUnit unit;

        private String lockKey;

        private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

        public Item(Thread thread, String threadId, long timeOut, TimeUnit unit, String lockKey,ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
            this.thread = thread;
            this.threadId = threadId;
            this.timeOut = timeOut;
            this.unit = unit;
            this.lockKey = lockKey;
            this.scheduledThreadPoolExecutor = scheduledThreadPoolExecutor;
        }

        @Override
        public void run() {
            if (thread.isAlive()){
                System.out.println(Thread.currentThread().getName()+" "+thread.getName()+"还活着");
                //TODO 重置锁过期日期
            }else {
                System.out.println(Thread.currentThread().getName()+" "+thread.getName()+"已经死了");
                scheduledThreadPoolExecutor.shutdown();
            }
        }
    }

}
