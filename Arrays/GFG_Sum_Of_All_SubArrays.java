// GFG – Sum of All Subarrays
// https://www.geeksforgeeks.org/problems/sum-of-all-subarrays/1

// Problem:
// Given an array `arr`, find the sum of all possible subarrays.
// Since the sum can be very large, return it modulo 10^9 + 7.

// Examples:
// Input:  arr = [1, 2, 3]
// Output: 20
// Explanation: All subarray sums → [1]=1, [2]=2, [3]=3, [1,2]=3, [2,3]=5, [1,2,3]=6
// Total sum = 1 + 2 + 3 + 3 + 5 + 6 = 20

// Input: arr = [1, 3]
// Output: 8
// Explanation: [1]=1, [3]=3, [1,3]=4 → 1+3+4 = 8

// Constraints:
// 1 ≤ arr.length ≤ 10^6
// 1 ≤ arr[i] ≤ 10^9

class Solution {

    // ✅ Optimized Contribution Method – O(n) Time | O(1) Space
    // Each arr[i] appears in (i + 1) * (n - i) subarrays
    // So its total contribution is arr[i] * (i + 1) * (n - i)
    public long subarraySum(int[] arr) {
        int n = arr.length;
        int mod = 1_000_000_007;

        long total = 0;

        for (int i = 0; i < n; i++) {
            long contribution = ((long) arr[i] * (i + 1) % mod * (n - i) % mod) % mod;
            total = (total + contribution) % mod;
        }

        return total;
    }

    // ✅ Approach 2: Prefix-Sum Based – O(n^2) Time | O(1) Space
    // Uses two loops, avoids recomputing sum from scratch
    public long subarraySumPrefix(int[] arr) {
        int n = arr.length;
        int mod = 1_000_000_007;
        long total = 0;

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum = (sum + arr[j]) % mod;
                total = (total + sum) % mod;
            }
        }

        return total;
    }

    // ✅ Approach 1: Brute Force – O(n^3) Time | O(1) Space
    // Generates all subarrays and sums each manually
    public long subarraySumBruteForce(int[] arr) {
        int n = arr.length;
        int mod = 1_000_000_007;
        long total = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int k = i; k <= j; k++) {
                    total = (total + arr[k]) % mod;
                }
            }
        }

        return total;
    }
}
