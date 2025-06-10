package synchronizing3.elevator.task;

import synchronizing3.elevator.model.Elevator;

public class Track implements Runnable{
    private final static Object monitor = new Object();
    private int nRaces;
    private int capacity;
    private Elevator elevator;

    public Track(int nRaces, int capacity, Elevator elevator) {
        this.nRaces = nRaces;
        this.capacity = capacity;
        this.elevator = elevator;
    }

    @Override
    public void run() {
        // synchronized (elevator) { // добавили синхронизацию к элеватору, но есть проблема - медленнее чем внутри цикла
            for (int i = 0; i < nRaces; i++) {

                synchronized (monitor) { // хороший стардарт
                    //synchronized (elevator) { // плохой стандарт
                    // synchronized (Track.class) { // так тоже можно
                    // synchronized ("mutex") { // так тоже можно
                    elevator.add(capacity);

                }

            //}
        }
    }
}
