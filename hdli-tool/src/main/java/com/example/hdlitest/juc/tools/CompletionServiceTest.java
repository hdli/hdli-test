package com.example.hdlitest.juc.tools;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiFunction;

/**
 * @author luyi
 * @date 2021/12/11 6:18 下午
 */
public class CompletionServiceTest {


    /**
     * https://segmentfault.com/a/1190000023603639
     *
     * 可以看出，在示例 1 中，虽然 Task 4 执行时间只有 400ms，但因为我们是按照 1-2-3-4 的顺序依次取结果，
     * 因此 Task 4 完成后并没有马上打印出结果来。而在示例 2 中，对每个 Task 都是在完成时立刻就将结果打印出来了。
     * 这就是 CompletionService 的优势所在
     *
     *
     * 原理解释:
     * CompletionService 之所以能够做到这点，是因为它没有采取依次遍历 Future 的方式，
     * 而是在中间加上了一个结果队列，任务完成后马上将结果放入队列，那么从队列中取到的就是最早完成的结果。
     *
     * 如果队列为空，那么 take() 方法会阻塞直到队列中出现结果为止。此外 CompletionService 还提供一个 poll() 方法，
     * 返回值与 take() 方法一样，不同之处在于它不会阻塞，如果队列为空则立刻返回 null。这算是给用户多一种选择。
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        ExecutorService executor;
        CompletionService<String> completionService;

        // 创建一个指定执行时长的任务的方法
        BiFunction<Integer, Integer, Callable<String>> createTask = (id, duration) -> () -> {
            System.out.println("Task " + id + " started, duration=" + duration);
            Thread.sleep(duration);
            System.out.println(("Task " + id + " completed."));
            return "Result of task " + id;
        };

        ///////////////////////////////////////////////////////////////////
        System.out.println("// 示例1：像使用 ExecutorService 一样使用 CompletionService");

        // 初始化 executor 和 completionService
        executor = Executors.newFixedThreadPool(4);
        completionService = new ExecutorCompletionService<>(executor);

        // 提交任务
        List<Future<String>> results = Arrays.asList(
                completionService.submit(createTask.apply(1, 1000)),
                completionService.submit(createTask.apply(2, 800)),
                completionService.submit(createTask.apply(3, 600)),
                completionService.submit(createTask.apply(4, 400))
        );

        // 取结果
        for (Future<String> result : results) {
            System.out.println((result.get()));
        }

        executor.shutdown();

        ///////////////////////////////////////////////////////////////////
        System.out.println("// 示例2：按标准方式使用 CompletionService");

        // 初始化 executor 和 completionService
        executor = Executors.newFixedThreadPool(4);
        completionService = new ExecutorCompletionService<>(executor);

        // 提交任务
        completionService.submit(createTask.apply(5, 1000));
        completionService.submit(createTask.apply(6, 800));
        completionService.submit(createTask.apply(7, 600));
        completionService.submit(createTask.apply(8, 400));

        // 取结果
        for (int i = 0; i < 4; i++) {
            System.out.println((completionService.take().get()));
        }

        ///////////////////////////////////////////////////////////////////
        executor.shutdown();
    }
}
