// LeetCode 5 – Longest Palindromic Substring
// https://leetcode.com/problems/longest-palindromic-substring/

// Problem:
// Given a string `s`, return the longest palindromic substring in `s`.

// ----------------------------------------------------------
// 🔴 Brute Force Approach (O(n^3)) – Not implemented
// ----------------------------------------------------------
// - Try all substrings `s[i..j]`.
// - Check if each one is a palindrome (O(n) for each).
// - Total time: O(n^3) → too slow for large strings.
// ----------------------------------------------------------

// ✅ Approach: Dynamic Programming with Memoization (Top-Down)
// ----------------------------------------------------------
// 🎯 Intuition:
// - A substring `s[i..j]` is a palindrome if:
//   - s.charAt(i) == s.charAt(j)
//   - and the substring `s[i+1..j-1]` is also a palindrome
//
// 🧠 Optimization:
// - Use a DP table to remember results of subproblems (i.e., whether `s[i..j]` is a palindrome).
// - Avoids recomputation of overlapping subproblems.
//
// 🔁 How It Works:
// - Use recursion to check if `s[i..j]` is palindrome.
// - Memoize result in a 2D Boolean table `dp[i][j]`.
// - If palindrome and longer than previously found, update result.
//
// 🧮 Time Complexity: O(n^2)
// 🧮 Space Complexity: O(n^2) for memo table + call stack depth

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 1) return s;

        // dp[i][j] stores whether s[i..j] is a palindrome
        Boolean[][] dp = new Boolean[n][n];

        int maxLength = 0;    // Length of the longest palindromic substring found
        int startIdx = 0;     // Starting index of that substring

        // Try all substrings from i to j
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(s, i, j, dp)) {
                    // If current palindrome is longer than max seen so far, update result
                    if (j - i + 1 > maxLength) {
                        maxLength = j - i + 1;
                        startIdx = i;
                    }
                }
            }
        }

        // Extract and return the longest palindromic substring
        return s.substring(startIdx, startIdx + maxLength);
    }

    // 🔁 Helper function to check if s[i..j] is a palindrome using recursion + memoization
    private boolean isPalindrome(String s, int i, int j, Boolean[][] dp) {
        // Base case: single character or empty substring is always a palindrome
        if (i >= j) return true;

        // If already computed, return memoized result
        if (dp[i][j] != null) return dp[i][j];

        // Check the characters at both ends
        if (s.charAt(i) == s.charAt(j)) {
            // Recurse on the inner substring
            dp[i][j] = isPalindrome(s, i + 1, j - 1, dp);
        } else {
            // If mismatch, it's not a palindrome
            dp[i][j] = false;
        }

        return dp[i][j];
    }
}
