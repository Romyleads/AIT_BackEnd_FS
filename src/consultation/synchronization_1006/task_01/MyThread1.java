package consultation.synchronization_1006.task_01;

public class MyThread1 extends Thread{

    @Override
    public void run() {
        for (int i = 100; i <110 ; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " - " + i);
        }
    }

}
