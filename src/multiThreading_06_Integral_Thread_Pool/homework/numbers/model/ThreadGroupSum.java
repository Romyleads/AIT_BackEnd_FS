package multiThreading_06_Integral_Thread_Pool.homework.numbers.model;

import java.util.Arrays;

public class ThreadGroupSum extends GroupSum {
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        int n = numberGroups.length; // group count

        Thread[] threads = new Thread[n]; // thread array
        int[] res = new int[n]; // result array

        for (int i = 0; i < n; i++) {
            final int index = i; // index for array

            threads[i] = new Thread(() -> {
                res[index] = Arrays.stream(numberGroups[index])
                        .sum(); // sum group
            });

            threads[i].start(); // run
        }

        for (int i = 0; i < n; i++) {
            try {
                threads[i].join(); // wait thread
            } catch (InterruptedException e) {
                throw new RuntimeException(e); // error
            }
        }

        return Arrays.stream(res).sum(); // total sum
    }
}

