package com.example.hdlitest.juc.collections.queue.BlockingQueue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-5 22:05
 */
public class DelayQueueTest {
    /**
     * 是一个支持延时获取元素的无界阻塞队列。队列使用 PriorityQueue 来实现。队列中的元素必须实
     * 现 Delayed 接口，在创建元素时可以指定多久才能从队列中获取当前元素。只有在延迟期满时才
     * 能从队列中提取元素
     */
    private static DelayQueue<Model> queue = new DelayQueue();
    public static void main(String[] args) throws InterruptedException {

        Model model1 = new Model("model1",5,TimeUnit.SECONDS);
        Model model2 = new Model("model2",10,TimeUnit.SECONDS);
        Model model3 = new Model("model3",20,TimeUnit.SECONDS);
        Model model4 = new Model("model4",15,TimeUnit.SECONDS);
        queue.put(model1);
        queue.put(model2);
        queue.put(model3);
        queue.put(model4);
        System.out.println(queue.size());
        System.out.println("begin time:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        for (int i = 0; i < 4; i++) {
            Model take = queue.take();
            System.out.format("name:{%s}, time:{%s}\n",take.name, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        }
    }


    static class Model implements Delayed{
        private String name;
        private long delayTime;

        public Model(String name, long delayTime,TimeUnit unit) {
            this.name = name;
            this.delayTime = System.currentTimeMillis() + (delayTime > 0? unit.toMillis(delayTime): 0);
        }


        @Override
        public long getDelay(TimeUnit unit) {
            return delayTime - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            Model model = (Model) o;

            long diff = this.delayTime - model.delayTime;
            if (diff <= 0) {// 改成>=会造成问题
                return -1;
            }else {
                return 1;
            }
        }

        @Override
        public String toString() {
            return "Model{" +
                    "name='" + name + '\'' +
                    ", delayTime=" + delayTime +
                    '}';
        }
    }
}
