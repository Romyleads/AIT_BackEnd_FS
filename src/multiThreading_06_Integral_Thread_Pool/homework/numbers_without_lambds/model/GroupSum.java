package multiThreading_06_Integral_Thread_Pool.homework.numbers_without_lambds.model;

public abstract class GroupSum {
    protected int[][] numberGroups;

    public GroupSum(int[][] numberGroups) {
        this.numberGroups = numberGroups;
    }

    public abstract int computeSum();
}
