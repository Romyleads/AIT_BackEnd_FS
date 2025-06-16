package multiThreading_06_Integral_Thread_Pool.homework.numbers_without_lambds.model;

import multiThreading_06_Integral_Thread_Pool.homework.numbers_without_lambds.task.OneGroupSum;

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
        OneGroupSum[] tasks = new OneGroupSum[n]; // array of tasks

        for (int i = 0; i < n; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]); // task for one group
            service.execute(tasks[i]); // run task
        }

        service.shutdown(); // stop accepting tasks
        try {
            service.awaitTermination(1, TimeUnit.MINUTES); // wait all
        } catch (InterruptedException e) {
            throw new RuntimeException(e); // error
        }

        return Arrays.stream(tasks) // stream of tasks
                .mapToInt(OneGroupSum::getSum) // get sum
                .sum(); // total sum
    }
}
