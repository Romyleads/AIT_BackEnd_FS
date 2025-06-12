package synchronizing_atomic.elevator.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Elevator {
    private String name;
    //public int currentVolume;
    private AtomicInteger currentVolume = new AtomicInteger(0);

    public Elevator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    //public int getCurrentVolume() {return currentVolume;}
    public int getCurrentVolume() {
        return currentVolume.get();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(int portion) {
        //currentVolume = currentVolume + portion;
        currentVolume.addAndGet(portion);

    }
}
