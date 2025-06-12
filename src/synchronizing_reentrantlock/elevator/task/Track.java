package synchronizing_reentrantlock.elevator.task;

import synchronizing_reentrantlock.elevator.model.Elevator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Track implements Runnable{
    private int nRaces;
    private int capacity;
    private Elevator elevator;
    private static Lock mutex = new ReentrantLock();

    public Track(int nRaces, int capacity, Elevator elevator) {
        this.nRaces = nRaces;
        this.capacity = capacity;
        this.elevator = elevator;
    }

    @Override
    public void run() {

        for (int i = 0; i <nRaces ; i++) {
            mutex.lock();
            try{
                elevator.add(capacity);

            } finally {
                mutex.unlock();
            }


        }

    }
}
