package consultation.synchronization_0612.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterDemo {

    // Создаём атомарный счётчик — используется CAS (Compare-And-Set), потокобезопасен
    static AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        // Задача для потока: 100 000 раз увеличить счётчик на 1
        Runnable task = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                // Атомарное увеличение счётчика
                counter.incrementAndGet();
                counter.addAndGet(2); // атомарная операция прибавления

            }
        };

        // Создаём два потока, выполняющих одну и ту же задачу
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        // Запускаем оба потока
        t1.start();
        t2.start();

        // Ожидаем завершения обоих потоков
        t1.join();
        t2.join();

        // Выводим итоговое значение счётчика
        // Ожидаемое значение: 200 000 (100 000 + 100 000)
        System.out.println("Final counter value: " + counter.get());
    }
}
