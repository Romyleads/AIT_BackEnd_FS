package consultation.synchronization_1006.task_01;

public class MyThread2 implements Runnable{

    @Override
    public void run() {
        for (int i = 10_000; i <10_010 ; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " - " + i);
        }
    }
}
