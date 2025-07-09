// LeetCode 152 – Maximum Product Subarray
// https://leetcode.com/problems/maximum-product-subarray/

// Problem:
// Given an integer array `nums`, find the contiguous subarray (containing at least one number)
// which has the largest product, and return the product.

// Constraints:
// - 1 <= nums.length <= 2 * 10^4
// - -10 <= nums[i] <= 10
// - The product of any subarray is guaranteed to fit in a 32-bit integer.

// Brute-force Approaches (Too slow for large inputs):
// 1. O(n^3): Try all subarrays using three nested loops, calculating product each time.
// 2. O(n^2): Use two loops and maintain running product for each subarray.
// These will give TLE (Time Limit Exceeded) on large test cases.

// Efficient Approach (Prefix & Suffix Traversal):
// - We traverse the array from both left-to-right (prefix) and right-to-left (suffix).
// - At each step, we calculate:
//     1. The running product from the start (prefix).
//     2. The running product from the end (suffix).
// - If the prefix or suffix becomes zero, we reset it to 1, since zero breaks the subarray.
// - We take the maximum of all prefix and suffix products at each step.
//
// Why this works:
// - A negative number can flip product signs — a large negative can become a large positive from the other end.
// - Zeros act as boundaries between valid subarrays, so we reset after hitting them.
// - Scanning from both ends ensures we don’t miss any possible max product due to sign flips.
//
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;

        int result = Integer.MIN_VALUE;  // Stores the global maximum product

        int prefix = 1;  // Running product from left to right
        int suffix = 1;  // Running product from right to left

        for (int i = 0; i < n; i++) {

            // Reset prefix/suffix to 1 if product chain is broken (due to zero)
            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;

            // Multiply current number to running products
            prefix *= nums[i];
            suffix *= nums[n - i - 1];

            // Update the global max product with the best of prefix or suffix
            result = Math.max(result, Math.max(prefix, suffix));
        }

        return result;
    }
}
