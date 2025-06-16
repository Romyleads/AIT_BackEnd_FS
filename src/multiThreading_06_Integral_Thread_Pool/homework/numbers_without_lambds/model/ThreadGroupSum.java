package multiThreading_06_Integral_Thread_Pool.homework.numbers_without_lambds.model;

import multiThreading_06_Integral_Thread_Pool.homework.numbers_without_lambds.task.OneGroupSum;

import java.util.Arrays;

public class ThreadGroupSum extends GroupSum {
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups); // pass to parent constructor
    }

    @Override
    public int computeSum() {
        int n = numberGroups.length; // group count

        Thread[] threads = new Thread[n]; // thread array
        OneGroupSum[] tasks = new OneGroupSum[n]; // task array

        for (int i = 0; i < n; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]); // create task for group
            threads[i] = new Thread(tasks[i]); // wrap task in thread
            threads[i].start(); // run
        }

        for (int i = 0; i < n; i++) {
            try {
                threads[i].join(); // wait thread
            } catch (InterruptedException e) {
                throw new RuntimeException(e); // error
            }
        }

        return Arrays.stream(tasks) // stream over tasks
                .mapToInt(OneGroupSum::getSum) // get each result
                .sum(); // total sum
    }
}
