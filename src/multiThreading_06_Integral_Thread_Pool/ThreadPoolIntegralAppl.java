package multiThreading_06_Integral_Thread_Pool;

import multiThreading_06_Integral_Thread_Pool.task.SumRectangles;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolIntegralAppl {

    // Количество потоков (задач), которые будут считать площадь
    //private static final int N_TASKS = 100;

    // эксперимент
    private static final int N_TASKS = Runtime.getRuntime().availableProcessors() * 1800;

    // Левая и правая границы интегрирования (например, от 0 до 3)
    public static final int A = 0;
    public static final int B = 3;

    // Общее количество прямоугольников (точек разбиения)
    private static final int N_PARTS = 1_000_000;

    public static void main(String[] args) throws InterruptedException {

        // Массив задач — каждая считает часть площади
        SumRectangles[] tasks = new SumRectangles[N_TASKS];

        for (int i = 0; i < tasks.length; i++) {
            // Передаём параметры: границы, функцию f(x)=x², кол-во прямоугольников, номер потока
            tasks[i] = new SumRectangles(A, B, x -> x * x, N_PARTS, N_TASKS, i);
            // простыми словами: разбиваем участок на много прямоугольников, каждый поток считает свою часть
        }

        long t1 = System.currentTimeMillis(); // засекаем время старта

        // ExecutorService executorService = Executors.newFixedThreadPool(10);
        // создаём пул из 10 рабочих потоков (фиксированное количество)

        // int cpus = Runtime.getRuntime().availableProcessors();
        // System.out.println(cpus);
        // можно узнать, сколько ядер доступно

        ExecutorService executorService = Executors.newWorkStealingPool();
        // создаём продвинутый пул: он может перекидывать задачи между потоками,
        // чтобы не было простаивающих потоков и всё выполнялось быстрее

        // Массив потоков (Thread), по одному для каждой задачи
        // Thread[] threads = new Thread[N_TASKS];

        for (int i = 0; i < tasks.length; i++) {
            //threads[i] = new Thread(tasks[i]); // создаём поток вручную (если без пула)
            //threads[i].start();                // запускаем поток вручную
            executorService.execute(tasks[i]);   // передаём задачу в пул потоков
        }

        executorService.shutdown(); // больше задач не будет — можно закрывать пул

        // executorService.execute(tasks[0]);
        // ❗ Не нужно вызывать после shutdown, иначе будет ошибка

        /*
        // Ждём завершения всех потоков вручную (если использовались обычные потоки)
        for (int i = 0; i < tasks.length; i++) {
            //threads[i].join();
            executorService.execute(tasks[i]); // лишнее, не нужно дублировать
        }
        */

        executorService.awaitTermination(1, TimeUnit.MINUTES);
        // Ждём максимум 1 минуту, чтобы все задачи завершились.
        // После этого всё, что не завершилось — будет остановлено.

        long t2 = System.currentTimeMillis(); // время окончания

        System.out.println("Duration = " + (t2 - t1)); // сколько времени всё заняло

        // Складываем все частичные результаты от задач (каждая считала свой кусочек площади)
        double res = Arrays.stream(tasks)
                .mapToDouble(SumRectangles::getResult)
                .sum();

        // Выводим итоговый результат — это и есть приближённая площадь по методу прямоугольников
        System.out.println("Result = " + res);
    }
}
