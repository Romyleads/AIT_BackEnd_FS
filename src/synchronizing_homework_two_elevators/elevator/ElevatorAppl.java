package synchronizing_homework_two_elevators.elevator;

import synchronizing_homework_two_elevators.elevator.model.Elevator;
import synchronizing_homework_two_elevators.elevator.task.Track;

public class ElevatorAppl {

    private static final int N_TRUCK = 10_000;

    private static final int N_RACES = 10;

    private static final int CAPACITY = 20;

    public static void main(String[] args) throws InterruptedException {
        //Elevator elevator = new Elevator("V.I. Lenon");
        Elevator elevator1 = new Elevator("Lenon1");
        Elevator elevator2 = new Elevator("Lenon2");

        Track[] trucks = new Track[N_TRUCK];

        for (int i = 0; i < trucks.length; i++) {
            trucks[i] = new Track(N_RACES, CAPACITY, elevator1, elevator2);

        }

        Thread[] threads = new Thread[trucks.length];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(trucks[i]);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("Elevator " + elevator1.getName() + " has " + elevator1.getCurrentVolume());
        System.out.println("Elevator " + elevator2.getName() + " has " + elevator2.getCurrentVolume());

    }
}
