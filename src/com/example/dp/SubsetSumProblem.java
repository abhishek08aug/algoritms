package com.example.dp;

public class SubsetSumProblem {
    public static void main(String[] args) {
        int [] values = new int [] {100, 7, 3, 4, 10};
        int sum = 114;

        System.out.println("Subset sum present (Recursive): " + isSubsetSumPresentRecursive(values, values.length, sum));
        System.out.println("Max profit (Memoized): " + isSubsetSumPresentMemoized(values, values.length, sum));
        System.out.println("Max profit (Tabular): " + isSubsetSumPresentTabular(values, values.length, sum));

        values = new int [] { 60, 100, 120, 4 };
        sum = 65;

        System.out.println("Subset sum present (Recursive): " + isSubsetSumPresentRecursive(values, values.length, sum));
        System.out.println("Max profit (Memoized): " + isSubsetSumPresentMemoized(values, values.length, sum));
        System.out.println("Max profit (Tabular): " + isSubsetSumPresentTabular(values, values.length, sum));
    }

    public static boolean isSubsetSumPresentRecursive(int [] values, int n, int sum) {
        if(sum == 0) {
            return true;
        }

        if(n == 0) {
            return false;
        }

        if(values[n-1] > sum) {
            return isSubsetSumPresentRecursive(values, n-1, sum);
        }

        return isSubsetSumPresentRecursive(values, n-1, sum) || isSubsetSumPresentRecursive(values, n-1, sum-values[n-1]);
    }

    public static boolean isSubsetSumPresentMemoized(int [] values, int n, int sum) {
        int [][] table = new int[n+1][sum+1];
        for(int i=0; i<n+1; i++) {
            for(int j=0; j<sum+1; j++) {
                table[i][j] = -1;
            }
        }
        return _isSubsetSumPresentMemoized(values, n, sum, table);
    }

    private static boolean _isSubsetSumPresentMemoized(int [] values, int n, int sum, int [][] table) {
        if(sum == 0) {
            return true;
        }

        if(n == 0) {
            return false;
        }

        if(table[n][sum] != -1) {
            return table[n][sum]==1? true : false;
        }

        if(values[n-1] > sum) {
            table[n][sum] = _isSubsetSumPresentMemoized(values,n-1, sum, table)? 1 : 0;
            return table[n][sum]==1 ? true : false;
        }
        table[n][sum] = (_isSubsetSumPresentMemoized(values,n-1, sum, table) || _isSubsetSumPresentMemoized(values, n-1, sum-values[n-1], table))? 1 : 0;
        return table[n][sum] == 1? true : false;
    }

    public static boolean isSubsetSumPresentTabular(int [] values, int n, int sum) {
        if(sum == 0) {
            return true;
        }

        if(n == 0) {
            return false;
        }

        boolean [][] table = new boolean[n+1][sum+1];
        for(int i=0; i<=n; i++) {
            table[i][0] = true;
        }
        for(int j=1; j<=sum; j++) {
            table[0][j] = false;
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=sum; j++) {
                if(values[i-1] > j) {
                    table[i][j] = table[i-1][j];
                } else {
                    table[i][j] = table[i-1][j-values[i-1]] || table[i-1][j];
                }
            }
        }
        return table[n][sum];
    }
}
