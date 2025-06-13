package consultation.synchronization_0612;

/*

Требуется симулировать старт гонки, где несколько машин должны стартовать одновременно.

Каждая машина финиширует через случайный промежуток времени

После того как все финишируют программа должна завершиться.

CountDownLatch - работает как задвижка
await() - поток будет заблокирован (приостановлен) - пока значение задвижки не станет равно 0
latch.countDown() - уменьшает значение счетчика на -1.
Когда счетчик достигает нуля - задвиджка открывается и все ждущие потоки продолжают выполнение кода.

 */


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class CarRace {

    public static void main(String[] args) throws InterruptedException {

        int carCount = 3;
        CountDownLatch readyLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(carCount);

        for (int i = 1; i <= carCount ; i++) {
            String name = "Car-" + i;

            new Thread(()->{


                try {
                    System.out.println(name + " на старте");
                    readyLatch.await();

                // генерируем время гонки и засыпаем поток

                    int timeToFinish = ThreadLocalRandom.current().nextInt(1000, 3000);
                    Thread.sleep(timeToFinish);

                    System.out.println(name +" финишировал за "+ timeToFinish + "мс");

                    // Сообщает о финише
                    finishLatch.countDown();


                } catch (InterruptedException e){

                    e.printStackTrace();
                }



            }).start();



        }

        Thread.sleep(2000);
        System.out.println("START!");

        readyLatch.countDown(); // Открываем стартовую, защелку

        // Делаем в потоке main, но ждали открытия защелки другие потоки

        finishLatch.await(); // Ждем  пока все финишируют

        System.out.println("Все машины финишировали");

    }

}
