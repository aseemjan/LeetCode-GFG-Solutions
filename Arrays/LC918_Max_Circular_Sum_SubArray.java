// LeetCode 918 – Maximum Sum Circular Subarray
// https://leetcode.com/problems/maximum-sum-circular-subarray/

// Problem:
// Given a circular array nums, return the maximum possible sum of a non-empty subarray of nums.
// A subarray may only include each element of the array at most once.
// (The circular nature means that the subarray may wrap around from end to start.)

// Approach:
// 1. Use Kadane’s Algorithm to find:
//    - maxSum: Maximum subarray sum (normal, non-circular case)
//    - minSum: Minimum subarray sum (to help compute circular case)
// 2. totalSum = sum of all elements
// 3. circularSum = totalSum - minSum → removes the "worst" segment in the middle, and keeps prefix + suffix
// 4. Edge Case: If all elements are negative, circularSum becomes 0 (invalid), so we return maxSum instead.

// Time Complexity: O(n)
// Space Complexity: O(1)

import java.util.Arrays;

class Solution {

    // Kadane's algorithm to find the minimum subarray sum
    static int kadanesMin(int[] nums, int n) {
        int sum = nums[0];
        int minSum = nums[0];

        for (int i = 1; i < n; i++) {
            sum = Math.min(sum + nums[i], nums[i]);  // Extend or restart
            minSum = Math.min(minSum, sum);          // Track the smallest subarray sum
        }

        return minSum;
    }

    // Kadane's algorithm to find the maximum subarray sum
    static int kadanesMax(int[] nums, int n) {
        int sum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < n; i++) {
            sum = Math.max(sum + nums[i], nums[i]);  // Extend or restart
            maxSum = Math.max(maxSum, sum);          // Track the largest subarray sum
        }

        return maxSum;
    }

    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;

        int totalSum = Arrays.stream(nums).sum();   // Total sum of the array
        int minSum = kadanesMin(nums, n);           // Minimum subarray sum
        int maxSum = kadanesMax(nums, n);           // Maximum subarray sum (non-circular)

        int circularSum = totalSum - minSum;        // Max circular subarray sum

        // If all numbers are negative, circularSum = 0 (invalid), so return maxSum
        if (maxSum > 0) {
            return Math.max(maxSum, circularSum);
        }

        return maxSum;
    }
}
