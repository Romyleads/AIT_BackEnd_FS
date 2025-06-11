package synchronizing3_block.task;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer implements Runnable{
    private int clockPeriod=3;

    public void setClockPeriod(int clockPeriod) {
        this.clockPeriod = clockPeriod;
    }

    public int getClockPeriod() {
        return clockPeriod;
    }

    @Override
    public void run(){
        while (true){

            System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));

            try{
                Thread.sleep(clockPeriod*1000);

            } catch (InterruptedException e){ // проверяемая ошибка

                //throw new RuntimeException(e); // непроверяемая ошибка

                System.out.println(Thread.interrupted()); // флаг тут возвращается в фалз
                System.out.println(Thread.currentThread().isInterrupted());

                System.out.println("Thread имя: " + Thread.currentThread().getName());

                System.out.println("Timer say bye, bye");
                break;

            }

        }

    }
}
