package com.example.hdlitest.tool.timewheel;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author luyi
 * @date 2024/9/5 12:18
 */
public class HashedWheelTimerTest {


    public static void main(String[] args) throws Exception {
        testHashedWheelTimer();
    }

    public static void testHashedWheelTimer() throws Exception {

        HashedWheelTimer timer = new HashedWheelTimer(1, 1024, Runtime.getRuntime().availableProcessors() * 4);
        List<TimerFuture> futures = Lists.newLinkedList();

        for (int i = 0; i < 10; i++) {

            String name = "Task" + i;
            long nowMS = System.currentTimeMillis();
            int delayMS = ThreadLocalRandom.current().nextInt(60000);
            long targetTime = delayMS + nowMS;

            TimerTask timerTask = () -> {
                System.out.println("============= " + name + "============= ");
                System.out.println("ThreadInfo:" + Thread.currentThread().getName());
                System.out.println("expectTime:" + targetTime);;
                System.out.println("currentTime:" + System.currentTimeMillis());
                System.out.println("deviation:" + (System.currentTimeMillis() - targetTime));
                System.out.println("============= " + name + "============= ");
            };
            futures.add(timer.schedule(timerTask, delayMS, TimeUnit.MILLISECONDS));
        }

        // 随机取消
        futures.forEach(future -> {

            int x = ThreadLocalRandom.current().nextInt(2);
            if (x == 1) {
                future.cancel();
            }

        });

        Thread.sleep(10);

        // 关闭
        System.out.println(timer.stop().size());
        System.out.println("Finished！");

        Thread.sleep(27);
    }
}
