package synchronizing2_method.elevator.task;

import synchronizing2_method.elevator.model.Elevator;

public class Track implements Runnable{
    private int nRaces;
    private int capacity;
    private Elevator elevator;

    public Track(int nRaces, int capacity, Elevator elevator) {
        this.nRaces = nRaces;
        this.capacity = capacity;
        this.elevator = elevator;
    }

    @Override
    public synchronized void run() { // добавили синхронизацию

        for (int i = 0; i <nRaces ; i++) {
            elevator.add(capacity);
        }

    }
}
