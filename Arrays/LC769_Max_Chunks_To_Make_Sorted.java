// LeetCode 769 – Max Chunks To Make Sorted
// https://leetcode.com/problems/max-chunks-to-make-sorted/

// Problem:
// Given an array `arr` representing a permutation of integers from 0 to n - 1,
// return the maximum number of chunks you can split the array into so that
// sorting each chunk individually and concatenating them results in a sorted array.
//
// Constraints:
// - 1 <= arr.length <= 10
// - 0 <= arr[i] < arr.length
// - All elements are unique

// ---------------------------------------------------------------
// ✅ Approach 1: Prefix Max + Suffix Min Comparison
// Time Complexity: O(n)
// Space Complexity: O(n)
// Idea:
// For each index, check if we can split between index i-1 and i.
// A valid split is possible if the max of the left part < min of the right part.
//
// How we came up with it:
// - If max(left) < min(right), then all elements to the left are smaller
// - So sorting the left and right parts separately keeps final order correct.
// ---------------------------------------------------------------

class Solution1 {
    public int maxChunksToSorted(int[] arr) {
        int len = arr.length;
        int[] leftMax = arr.clone();
        int[] rightMin = arr.clone();

        // Compute prefix max array
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], leftMax[i]);
        }

        // Compute suffix min array
        for (int i = len - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], rightMin[i]);
        }

        int chunkCount = 0;

        for (int i = 0; i < len; i++) {
            int maxLeft = (i > 0) ? leftMax[i - 1] : -1;
            int minRight = rightMin[i];

            if (maxLeft < minRight) {
                chunkCount++;
            }
        }

        return chunkCount;
    }
}

// ---------------------------------------------------------------
// ✅ Approach 2: Cumulative Sum Comparison
// Time Complexity: O(n)
// Space Complexity: O(1)

// Idea:
// - Since arr is a permutation of 0 to n-1, its sorted version has a known pattern.
// - So, if the sum of arr[0..i] equals sum of 0..i (i.e., natural numbers),
//   then those elements are a complete subarray — we can safely cut here.
// ---------------------------------------------------------------

class Solution2 {
    public int maxChunksToSorted(int[] arr) {
        int totalSum = 0;      // Sum of actual array elements
        int expectedSum = 0;   // Sum of natural numbers 0 to i
        int parts = 0;

        for (int i = 0; i < arr.length; i++) {
            totalSum += arr[i];
            expectedSum += i;

            if (totalSum == expectedSum) {
                parts++;
            }
        }

        return parts;
    }
}

// ---------------------------------------------------------------
// ✅ Approach 3: Prefix Maximum Check
// Time Complexity: O(n)
// Space Complexity: O(1)

// Idea:
// - Keep track of the max value seen so far (maxTillNow)
// - If maxTillNow == current index, then all elements so far are in correct range (0 to i)
// - So we can make a cut here.
//
// Why this works:
// - Since arr is a permutation of [0, n-1], maxTillNow == i means all numbers 0..i are present
// - Sorting this chunk will naturally place all those values in correct order.
// ---------------------------------------------------------------

class Solution3 {
    public int maxChunksToSorted(int[] arr) {
        int highest = -1;
        int validChunks = 0;

        for (int i = 0; i < arr.length; i++) {
            highest = Math.max(highest, arr[i]);

            if (highest == i) {
                validChunks++;
            }
        }

        return validChunks;
    }
}
