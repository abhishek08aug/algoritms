package com.example.dp;

public class KthPermutation {
    public static void main(String[] args) {
        int n = 3;
        int k = 4;

        String s = getKthPermutation(n, k);
        System.out.println("Result: " + s);
    }

    public static String getKthPermutation(int n, int k) {
        if(n < 0 || k < 0 || n > 9 || k > _fact(n)) {
            return null;
        }
        String s = prepareString(n);
        StringBuilder sb = new StringBuilder();
        _getKthPermutation(s, n, k, sb);
        return sb.toString();
    }

    private static void _getKthPermutation(String s, int n, int k, StringBuilder sb) {
        System.out.println("s: " + s + " n: " + n + " k: " + k + " sb: " + sb.toString());
        if(n == 0 || k == 0) {
            return;
        }
        for(int i=1; i<=n; i++) {
            if(k <= i*_fact(n-1)) {
                String next = s.substring(0, i-1) + s.substring(i, s.length());
                sb.append(s.charAt(i-1));
                _getKthPermutation(next, n-1, k-(i-1)*_fact(n-1), sb);
                break;
            }
        }
    }

    private static int _fact(int n) {
        if(n == 0 || n ==1) {
            return 1;
        }
        return n*_fact(n-1);
    }

    private static String prepareString(int n) {
        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=n; i++) {
            sb.append(i);
        }
        return sb.toString();
    }
}
