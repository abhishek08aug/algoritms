package com.example.dp;

public class ZeroOneKnapsack {

    public static void main(String[] args) {
        int [] value = new int [] {100, 7, 3, 4, 10};
        int [] weight = new int [] {13, 2, 8, 4, 9};
        int knapsackCapacity = 12;

        System.out.println(getMaximumProfitRecursive(value, weight, value.length, knapsackCapacity));

        int [][] table = new int[value.length+1][knapsackCapacity+1];
        for(int i=0; i<value.length+1; i++) {
            for(int j=0; j<knapsackCapacity+1; j++) {
                table[i][j] = -1;
            }
        }
        System.out.println(getMaximumProfitMemoized(value, weight, value.length, knapsackCapacity, table));
    }

    public static int getMaximumProfitRecursive(int [] value, int [] weight, int n, int knapsackCapacity) {
        if(knapsackCapacity == 0 || n == 0) {
            return 0;
        }

        if(weight[n-1] > knapsackCapacity) {
            return getMaximumProfitRecursive(value, weight, n-1, knapsackCapacity);
        }

        return Math.max(value[n-1] + getMaximumProfitRecursive(value, weight, n-1, knapsackCapacity-weight[n-1]), getMaximumProfitRecursive(value, weight, n-1, knapsackCapacity));
    }

    public static int getMaximumProfitMemoized(int [] value, int [] weight, int n, int knapsackCapacity, int [][] table) {
        if(knapsackCapacity == 0 || n == 0) {
            return 0;
        }

        if(table[n][knapsackCapacity] != -1) {
            return table[n][knapsackCapacity];
        }

        if(weight[n-1] > knapsackCapacity) {
            table[n][knapsackCapacity] = getMaximumProfitRecursive(value, weight, n-1, knapsackCapacity);
            return table[n][knapsackCapacity];
        }

        table[n][knapsackCapacity] = Math.max(value[n-1] + getMaximumProfitRecursive(value, weight, n-1, knapsackCapacity-weight[n-1]), getMaximumProfitRecursive(value, weight, n-1, knapsackCapacity));
        return table[n][knapsackCapacity];
    }
}
