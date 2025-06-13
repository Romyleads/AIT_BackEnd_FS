package synchronizing_homework_05_BlockingQueue.fixed_version_01.mediation;

import java.util.LinkedList;

public class BlkQueueImpl<T> implements BlkQueue<T> {

    // list to store items
    private final LinkedList<T> queue = new LinkedList<>();

    // max number of items
    private final int maxSize;

    // create the queue with max size
    public BlkQueueImpl(int maxSize) {
        this.maxSize = maxSize;
    }

    // producer adds message; one thread at a time
    @Override
    public synchronized void push(T message) {

        // wait if full
        while (queue.size() >= maxSize) {
            try {
                wait(); // pause
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        queue.add(message); // producer puts message in queue
        // System.out.print("*".repeat(queue.size()));
        notifyAll(); // wake up consumers
    }

    @Override
    public synchronized T pop() {

        while (queue.isEmpty()) {
            try {
                wait(); // waiting for a message
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        T msg = queue.remove(); // take a message from the queue
        // System.out.print("*".repeat(queue.size()) );
        notifyAll(); // wake up all producers
        return msg;
    }
}
