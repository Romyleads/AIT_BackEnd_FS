package multiThreading_06_Integral_Thread_Pool.homework.numbers_without_lambds.model;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ParallelStreamGroupSumOpt extends GroupSum {
    public ParallelStreamGroupSumOpt(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {

        ForkJoinPool customPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors()); // create optimized pool
        Future<Integer> future = customPool.submit( // submit parallel task to custom pool
                () ->
                        Arrays.stream(numberGroups) // stream rows
                                .parallel()           // use parallel stream
                                .mapToInt(group -> Arrays.stream(group).sum()) // sum row
                                .sum()                // total
        );

        try {
            return future.get(); // wait and return result
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e); // handle error
        } finally {
            customPool.shutdown(); // shutdown pool
        }
    }
}
