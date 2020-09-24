package com.example.dp;

public class EqualSumPartition {
    public static void main(String[] args) {
        int [] values = new int [] {2, 6, 8};

        System.out.println("Equal sum partition (Recursive): " + hasEqualSumPartitionsRecursive(values, values.length));
        System.out.println("Equal sum partition (Memoized): " + hasEqualSumPartitionsMemoized(values, values.length));
        System.out.println("Equal sum partition (Tabular): " + hasEqualSumPartitionsTabular(values, values.length));

        values = new int [] {2, 6, 9};

        System.out.println("Equal sum partition (Recursive): " + hasEqualSumPartitionsRecursive(values, values.length));
        System.out.println("Equal sum partition (Memoized): " + hasEqualSumPartitionsMemoized(values, values.length));
        System.out.println("Equal sum partition (Tabular): " + hasEqualSumPartitionsTabular(values, values.length));
    }

    public static boolean hasEqualSumPartitionsRecursive(int [] values, int n) {
        if(values == null) {
            return true;
        }

        int sum = 0;
        for(int val : values) {
            sum += val;
        }

        if(sum%2 == 1) {
            return false;
        }
        return SubsetSumProblem.isSubsetSumPresentRecursive(values, n, sum/2);
    }

    public static boolean hasEqualSumPartitionsMemoized(int [] values, int n) {
        if(values == null) {
            return true;
        }

        int sum = 0;
        for(int val : values) {
            sum += val;
        }

        if(sum%2 == 1) {
            return false;
        }
        return SubsetSumProblem.isSubsetSumPresentMemoized(values, n, sum/2);
    }

    public static boolean hasEqualSumPartitionsTabular(int [] values, int n) {
        if(values == null) {
            return true;
        }

        int sum = 0;
        for(int val : values) {
            sum += val;
        }

        if(sum%2 == 1) {
            return false;
        }
        return SubsetSumProblem.isSubsetSumPresentTabular(values, n, sum/2);
    }

}
