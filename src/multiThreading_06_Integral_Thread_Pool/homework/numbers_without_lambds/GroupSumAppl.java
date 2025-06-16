package multiThreading_06_Integral_Thread_Pool.homework.numbers_without_lambds;

import multiThreading_06_Integral_Thread_Pool.homework.numbers_without_lambds.model.*;
import multiThreading_06_Integral_Thread_Pool.homework.numbers_without_lambds.test.GroupSumPerfomanceTest;

import java.util.Random;

public class GroupSumAppl {
    private static final int N_GROUPS = 10_000;
    private static final int NUMBERS_PER_GROUPS = 10_000;
    private static final int[][] numbers = new int[N_GROUPS][NUMBERS_PER_GROUPS];
    private static Random random = new Random();

    public static void main(String[] args) {
        fillArray();
        GroupSum threadGroupSum = new ThreadGroupSum(numbers);
        GroupSum executorGroupSum = new ExecutorGroupSum(numbers);
        GroupSum streamGroupSum = new ParallelStreamGroupSum(numbers);
        GroupSum streamGroupSumOpt = new ParallelStreamGroupSumOpt(numbers);
        new GroupSumPerfomanceTest("ThreadGroupSum", threadGroupSum).runTest();
        new GroupSumPerfomanceTest("ExecutorGroupSum", executorGroupSum).runTest();
        new GroupSumPerfomanceTest("ParallelStreamGroupSum", streamGroupSum).runTest();
        new GroupSumPerfomanceTest("ParallelStreamGroupSumOpt", streamGroupSumOpt).runTest();
    }

    private static void fillArray() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                numbers[i][j] = random.nextInt();
            }
        }
    }
}
