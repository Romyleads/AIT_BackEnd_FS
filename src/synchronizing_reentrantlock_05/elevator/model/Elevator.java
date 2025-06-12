package synchronizing_reentrantlock_05.elevator.model;

public class Elevator {
    private String name;
    public int currentVolume;


    public Elevator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized void add(int portion){
        currentVolume = currentVolume + portion;

    }
}
