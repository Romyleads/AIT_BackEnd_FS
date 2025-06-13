package multiThreading_06_Integral_Thread_Pool;

import multiThreading_06_Integral_Thread_Pool.task.SumRectangles;

import java.util.Arrays;

public class integralThreadAppl {

    // Количество потоков (задач), которые будут считать площадь
    private static final int N_TASKS = 8;

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
        }

        long t1 = System.currentTimeMillis();

        // Массив потоков (Thread), по одному для каждой задачи
        Thread[] threads = new Thread[N_TASKS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(tasks[i]); // создаём поток
            threads[i].start();                // запускаем поток
        }

        // Ждём завершения всех потоков
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        long t2 = System.currentTimeMillis();

        System.out.println("Duration = " + (t2-t1));
        // Складываем все частичные результаты от задач
        double res = Arrays.stream(tasks)
                .mapToDouble(SumRectangles::getResult)
                .sum();

        // Выводим итоговый результат
        System.out.println("Result = " + res);
    }
}
