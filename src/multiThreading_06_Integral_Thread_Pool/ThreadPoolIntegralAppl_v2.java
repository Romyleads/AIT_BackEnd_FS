package multiThreading_06_Integral_Thread_Pool;

import multiThreading_06_Integral_Thread_Pool.task.SumRectangles;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolIntegralAppl_v2 {

    // Левая и правая границы интегрирования (например, от 0 до 3)
    public static final int A = 0;
    public static final int B = 3;

    // Общее количество прямоугольников (точек разбиения)
    private static final int N_PARTS = 1_000_000;

    public static void main(String[] args) throws InterruptedException {

        int cores = Runtime.getRuntime().availableProcessors(); // сколько ядер
        int N_TASKS = cores * 10; // много задач, но не много потоков

        SumRectangles[] tasks = new SumRectangles[N_TASKS];
        for (int i = 0; i < N_TASKS; i++) {
            tasks[i] = new SumRectangles(A, B, x -> x * x, N_PARTS, N_TASKS, i);
        }

        long t1 = System.currentTimeMillis();

        // Work-Stealing-пул — сам эффективно использует ядра
        ExecutorService executor = Executors.newWorkStealingPool();

        for (SumRectangles task : tasks) {
            executor.execute(task);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES); // ждём максимум 1 минуту

        long t2 = System.currentTimeMillis();

        double res = Arrays.stream(tasks)
                .mapToDouble(SumRectangles::getResult)
                .sum();

        System.out.println("Duration = " + (t2 - t1) + " ms");
        System.out.println("Result = " + res);
    }
}
