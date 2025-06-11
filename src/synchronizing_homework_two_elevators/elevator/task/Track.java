package synchronizing_homework_two_elevators.elevator.task;

import synchronizing_homework_two_elevators.elevator.model.Elevator;

public class Track implements Runnable {
    private int nRaces;
    private int capacity;
    //private Elevator elevator;
    private Elevator elevator1;
    private Elevator elevator2;
    private static final Object lock = new Object(); // create a separate monitor object

    public Track(int nRaces, int capacity, Elevator elevator1, Elevator elevator2) {
        this.nRaces = nRaces;
        this.capacity = capacity;
        this.elevator1 = elevator1;
        this.elevator2 = elevator2;
    }

    @Override
    public void run() {
        for (int i = 0; i < nRaces; i++) {
            int half = capacity / 2;
            synchronized (lock) {
                elevator1.add(half);
                elevator2.add(capacity - half);
            }
        }
    }
}
