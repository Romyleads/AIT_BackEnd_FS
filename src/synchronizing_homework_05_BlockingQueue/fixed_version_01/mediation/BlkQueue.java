package synchronizing_homework_05_BlockingQueue.fixed_version_01.mediation;

/**
 * This interface represents Blocking Queue for usage
 * as communication buffer between Producers and Consumers
 */
public interface BlkQueue<T> {
    void push(T message);
    T pop();
}
