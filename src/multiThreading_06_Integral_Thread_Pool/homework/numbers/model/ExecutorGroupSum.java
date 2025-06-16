package multiThreading_06_Integral_Thread_Pool.homework.numbers.model;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorGroupSum extends GroupSum {
    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        int n = numberGroups.length; // number of groups

        ExecutorService service = Executors.newWorkStealingPool(); // create optimized thread pool
        int[] res = new int[n]; // result array

        for (int i = 0; i < n; i++) {
            final int index = i;
            service.execute(() -> {
                res[index] = Arrays.stream(numberGroups[index]).sum(); // sum one group
            });
        }

        service.shutdown(); // stop new tasks
        try {
            service.awaitTermination(1, TimeUnit.MINUTES); // wait all tasks
        } catch (InterruptedException e) {
            throw new RuntimeException(e); // error
        }

        return Arrays.stream(res).sum(); // return result

    }
}