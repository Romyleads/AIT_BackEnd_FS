package consultation.synchronization_0612.wash;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class CarWash {
    public static void main(String[] args) {

        int carCount = 7;

        Semaphore semaphore = new Semaphore(2); // 2 бокса в мойке свободно

        for (int i = 1; i <= carCount ; i++) {

            String name = "Car-" + i;

            new Thread(

                    ()-> {

                        try {

                            System.out.println(name + "ждет очередь на мойку");
                            semaphore.acquire(); // запрашивает вход в автомойку

                            // Если места нет -поток будет остановлен

                            System.out.println(name + " моется...");

                            int washingTime = ThreadLocalRandom.current().nextInt(1000, 3000);

                            Thread.sleep (washingTime); // Симуляция мытья

                            System.out.println(name + " выехала из мойки спустя " + washingTime + " мс");

                        } catch (InterruptedException e){
                            e.printStackTrace();
                        } finally {

                            semaphore.release(); // Освобождаю место
                        }

                    }



            ).start();
        }

    }

}
