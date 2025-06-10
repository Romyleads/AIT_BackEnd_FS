package multiThreadindIntro.homework;

import java.util.Random;

public class CockroachRunner implements Runnable {

    private static final Random random = new Random();
    private static final int MIN_SLEEP_MS = 2;
    private static final int MAX_SLEEP_MS = 5;

    private final int cockroachNumber;
    private final int distance;

    public CockroachRunner(int cockroachNumber, int distance) {
        this.cockroachNumber = cockroachNumber;
        this.distance = distance;
    }

    @Override
    public void run() {
        for (int step = 1; step <= distance; step++) {
            System.out.println("Cockroach #" + cockroachNumber + " ran step " + step);
            try {
                int sleepTime = MIN_SLEEP_MS + random.nextInt(MAX_SLEEP_MS - MIN_SLEEP_MS);
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("Cockroach #" + cockroachNumber + " was stopped");
                return;
            }
        }

        // callback
        TarakansRaceApp.trySetWinner(cockroachNumber);
    }
}