package com.example.dp;

public class EditDistance {
    public static void main(String[] args) {
        String s1 = "CAT";
        String s2 = "CTA";

        int dist = findEditDistance(s1, s2);

        if(dist <= 1) {
            System.out.println("Its a match: " + dist);
        } else {
            System.out.println("Words are not same: " + dist);
        }
    }

    public static int findEditDistance(String s1, String s2) {
        if(s1 == null && s2 == null) {
            return 0;
        }

        if(s1 == null) {
            return s2.length();
        }

        if(s2 == null) {
            return s1.length();
        }

        int m = s1.length();
        int n = s2.length();

        int [][] table = new int [m][n];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                table[i][j]  = -1;
            }
        }

        return _findEditDistance(s1, s2, m-1, n-1, m, n, table);
    }

    private static int _findEditDistance(String s1, String s2, int i, int j, int m, int n, int [][] table) {
        if(i < 0 && j < 0) {
            return 0;
        }

        if(i < 0) {
            return j+1;
        }

        if(j < 0) {
            return i+1;
        }

        if(table[i][j] != -1) {
            return table[i][j];
        }

        if(s1.charAt(i) == s2.charAt(j)) {
            table[i][j] = _findEditDistance(s1, s2, i-1, j-1, m, n, table);
        } else {
            int add1 =  _findEditDistance(s1, s2, i, j-1, m, n, table) ;
            int add2 =  _findEditDistance(s1, s2, i-1, j, m, n, table);

            int del1 = _findEditDistance(s1, s2, i-1, j, m, n, table);
            int del2 = _findEditDistance(s1, s2, i, j-1, m, n, table);

            int rep1 = _findEditDistance(s1, s2, i-1, j-1, m, n, table);

            table[i][j] = Math.min(add1, Math.min(add2, Math.min(del1, Math.min(del2, rep1)))) + 1;
        }
        return table[i][j];
    }
}
