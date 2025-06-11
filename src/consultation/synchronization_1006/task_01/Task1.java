package consultation.synchronization_1006.task_01;

public class Task1 {
    public static void main(String[] args) {

        // Два базовыз способа создания потоков
        // 1. Наследование от класса Thread
        // 2. Реализация интерфейса Runnable

        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();

        //  myThread1.run();
        // myThread2.run();

        // При создании наследования - просто вызываем метод start();
        myThread1.start();

        // Сначала нужно создать объект класса Thread
        // В конструктор мы можем передать реализацию Runnable

        Thread thread = new Thread(myThread2);

        thread.setDaemon(true); // сделать поток Демон - можно только до его старта

        thread.start();



        // поток 2 запущен выше другим способом


        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() +" - "+ i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
