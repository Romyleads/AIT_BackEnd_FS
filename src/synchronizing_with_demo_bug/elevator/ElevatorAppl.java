package synchronizing_with_demo_bug.elevator;

import synchronizing_with_demo_bug.elevator.model.Elevator;
import synchronizing_with_demo_bug.elevator.task.Track;

public class ElevatorAppl {

    private static final int N_TRUCK = 10_000;

    private static final int N_RACES = 10;

    private static final int CAPACITY = 20;

    public static void main(String[] args) throws InterruptedException {
        Elevator elevator = new Elevator("V.I. Lenon");

        Track[] trucks = new Track[N_TRUCK];

        for (int i = 0; i < trucks.length; i++) {
            trucks[i] = new Track(N_RACES, CAPACITY, elevator);

        }

        Thread[] threads = new Thread[trucks.length];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(trucks[i]);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("Elevator "+ elevator.getName() + " has " + elevator.getCurrentVolume());

    }
}
