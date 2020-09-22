package com.example.dp;

public class ZeroOneKnapsack {

    public static void main(String[] args) {
        int [] value = new int [] {100, 7, 3, 4, 10};
        int [] weight = new int [] {13, 2, 8, 4, 9};
        int knapsackCapacity = 12;

        System.out.println("Max profit (Recursive): " + getMaximumProfitRecursive(value, weight, value.length, knapsackCapacity));
        System.out.println("Max profit (Memoized): " + getMaximumProfitMemoized(value, weight, value.length, knapsackCapacity));
        System.out.println("Max profit (Tabular): " + getMaximumProfitTabular(value, weight, value.length, knapsackCapacity));

        value = new int [] { 60, 100, 120 };
        weight = new int [] { 10, 20, 30 };
        knapsackCapacity = 50;

        System.out.println("Max profit (Recursive): " + getMaximumProfitRecursive(value, weight, value.length, knapsackCapacity));
        System.out.println("Max profit (Memoized): " + getMaximumProfitMemoized(value, weight, value.length, knapsackCapacity));
        System.out.println("Max profit (Tabular): " + getMaximumProfitTabular(value, weight, value.length, knapsackCapacity));
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

    public static int getMaximumProfitMemoized(int [] value, int [] weight, int n, int knapsackCapacity) {
        if(knapsackCapacity == 0 || n == 0) {
            return 0;
        }

        int [][] table = new int[n+1][knapsackCapacity+1];
        for(int i=0; i<n+1; i++) {
            for(int j=0; j<knapsackCapacity+1; j++) {
                table[i][j] = -1;
            }
        }
        return _getMaximumProfitMemoized(value, weight, n, knapsackCapacity, table);
    }

    private static int _getMaximumProfitMemoized(int [] value, int [] weight, int n, int knapsackCapacity, int [][] table) {
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

    public static int getMaximumProfitTabular(int [] value, int [] weight, int n, int knapsackCapacity) {
        if(knapsackCapacity == 0 || n == 0) {
            return 0;
        }

        int [][] table = new int[n+1][knapsackCapacity+1];
        for(int i=0; i<=n; i++) {
            table[i][0] = 0;
        }
        for(int j=0; j<=knapsackCapacity; j++) {
            table[0][j] = 0;
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=knapsackCapacity; j++) {
                if(weight[i-1] > j) {
                    table[i][j] = table[i-1][j];
                } else {
                    table[i][j] = Math.max(value[i-1] + table[i-1][j-weight[i-1]], table[i-1][j]);
                }
            }
        }
        return table[n][knapsackCapacity];
    }
}
