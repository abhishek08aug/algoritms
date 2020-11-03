package com.example.backtracking;

import java.util.*;

class PalindromePercentage {
    public static void main(String[] args) {
        String s = "aab";
        System.out.println("Percent of palindromes: " + getPercentage(s));

        s = "aaabbaaa";
        System.out.println("Percent of palindromes: " + getPercentage(s));
    }

    public static double getPercentage(String s) {
        if(s == null || s.length() == 0) {
            return 0.0;
        }

        Map<String, Boolean> permutationVsPalindrome = new HashMap<>();

        _permute(s, permutationVsPalindrome);

        int palindromeCount = 0;
        for(String key: permutationVsPalindrome.keySet()) {

            if(permutationVsPalindrome.get(key) == true) {
                palindromeCount++;
            }
        }
        return ((double)palindromeCount/permutationVsPalindrome.size())*100;
    }

    private static void _permute(String s, Map<String, Boolean> permutationVsPalindrome) {
        _permuteUtil(s, 0, s.length()-1, permutationVsPalindrome);
    }

    private static void _permuteUtil(String s, int start, int end, Map<String, Boolean> permutationVsPalindrome) {
        if(start > end) {
            permutationVsPalindrome.put(s, _isPalindrome(s));
            return;
        }

        for(int i=start; i<=end; i++) {
            String swapped = _swap(s, start, i);
            _permuteUtil(swapped, start+1, end, permutationVsPalindrome);
            _swap(swapped, start, i);
        }
    }

    private static boolean _isPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return true;
        }

        int start = 0;
        int end = s.length()-1;

        while(start <= end) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    private static String _swap(String s, int i, int j) {
        char[] chars = new char[s.length()];
        for(int index=0; index<s.length(); index++) {
            chars[index] = s.charAt(index);
        }

        char temp = chars[j];
        chars[j] = chars[i];
        chars[i] = temp;
        return new String(chars);
    }
}