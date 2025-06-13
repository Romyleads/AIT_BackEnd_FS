package synchronizing_homework_05_BlockingQueue.buggy_version;

import synchronizing_homework_05_BlockingQueue.buggy_version.actors.MsgConsumer;
import synchronizing_homework_05_BlockingQueue.buggy_version.actors.MsgProducer;
import synchronizing_homework_05_BlockingQueue.buggy_version.mediation.BlkQueue;
import synchronizing_homework_05_BlockingQueue.buggy_version.mediation.BlkQueueImpl;

public class ProducerConsumeAppl {
    private static final int N_MESSAGES = 50;
    private static final int N_CONSUMERS = 5;
    private static final int MSG_SEND_INTERVAL_MILLIS = 100;
    private static final int MSG_HANDLING_TIME_MILLIS = 1000;
    private static final int QUEUE_MAX_SIZE = 10;

    public static void main(String[] args) throws InterruptedException {
        BlkQueue<String> blkQueue = new BlkQueueImpl<>(QUEUE_MAX_SIZE);
        MsgProducer sender = new MsgProducer(blkQueue, N_MESSAGES, MSG_SEND_INTERVAL_MILLIS);
        sender.start();
        for (int i = 0; i < N_CONSUMERS; i++) {
            new MsgConsumer(blkQueue, MSG_HANDLING_TIME_MILLIS).start();
        }

        Thread.sleep(N_MESSAGES / N_CONSUMERS * MSG_HANDLING_TIME_MILLIS + 1000);

    }
}
