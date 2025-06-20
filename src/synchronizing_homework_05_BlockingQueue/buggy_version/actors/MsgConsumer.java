package synchronizing_homework_05_BlockingQueue.buggy_version.actors;

import synchronizing_homework_05_BlockingQueue.buggy_version.mediation.BlkQueue;

public class MsgConsumer extends Thread{
    BlkQueue<String> blkQueue;
    int msgHandlingTimeMillis;

    public MsgConsumer(BlkQueue<String> blkQueue, int msgHandlingTimeMillis) {
        super();
        this.blkQueue = blkQueue;
        this.msgHandlingTimeMillis = msgHandlingTimeMillis;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            String message = blkQueue.pop();
            System.out.println(message + " ==> consumer " + getId());
            try { // simulate message handling
                Thread.sleep(msgHandlingTimeMillis);
            } catch (InterruptedException e) {
                // noop
            }
        }
    }
}
