package synchronizing2_method.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TimerAppl {
    public static void main(String[] args) throws IOException {

        Timer timer = new Timer();
        Thread thread = new Thread(timer);

        // thread.setDaemon(true); // before start

        thread.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){

            System.out.println("Enter time interval, or press q for exit");
            String str = br.readLine();

            if ("q".equalsIgnoreCase(str)){
//                thread.stop();
                thread.interrupt();
//                System.out.println("In Main thread: " + thread.isInterrupted()); ловим тру
                break;
            } else {

                timer.setClockPeriod(Integer.parseInt(str));

            }
        }

        System.out.println("Main thread finished");

    }
}
