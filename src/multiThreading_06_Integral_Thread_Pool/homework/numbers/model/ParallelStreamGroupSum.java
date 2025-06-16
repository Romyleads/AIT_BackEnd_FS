package multiThreading_06_Integral_Thread_Pool.homework.numbers.model;

import java.util.Arrays;

public class ParallelStreamGroupSum extends GroupSum {
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        return Arrays.stream(numberGroups) // stream of groups
                .parallel() // make parallel
                .mapToInt(group -> Arrays.stream(group).sum()) // sum row
                .sum(); // total sum

    }
}
