package multiThreadindIntro.homework.test;

public class ThreadsExampleApp {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Старт главного потока->");

        // Создаём первый поток
        Runnable task1 = new Task("Поток 1");
        Thread thread1 = new Thread(task1);

        // Создаём второй поток
        Runnable task2 = new Task("Поток 2");
        Thread thread2 = new Thread(task2);

        // Запускаем оба потока
        thread1.start();
        thread2.start();

        // Главный поток тоже считает
        for (int i = 0; i < 5; i++) {
            System.out.println("Главный поток — шаг " + i);
            Thread.sleep(100);
        }

        // Ждём окончания других потоков
        thread1.join();
        thread2.join();

        System.out.println("Все потоки завершили выполнение!");
    }
}