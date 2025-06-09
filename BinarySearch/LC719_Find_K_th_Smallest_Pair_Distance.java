// LeetCode 719 – Find K-th Smallest Pair Distance
// https://leetcode.com/problems/find-k-th-smallest-pair-distance/

// Problem:
// The distance of a pair (a, b) is defined as |a - b| (absolute difference).
// Given an array `nums`, return the k-th smallest distance among all pairs (i < j).
//
// Example:
// Input: nums = [1,3,1], k = 1
// Output: 0
// Explanation: All pair distances → [0, 2, 2]; 1st smallest is 0.
//
// Constraints:
// - 2 <= nums.length <= 10⁴
// - 0 <= nums[i] <= 10⁶
// - 1 <= k <= n * (n - 1) / 2
//
// Approach:
// 1. Sort the array to make pair distance calculation efficient.
// 2. Use Binary Search on the answer space (range of distances).
//    - min distance = 0
//    - max distance = max(nums) - min(nums)
// 3. For a given distance `mid`, count how many pairs have distance <= mid.
//    - Use sliding window two-pointer technique to count efficiently in O(n)
// 4. If count >= k, mid is a valid candidate → search in [low, mid - 1]
//    Else → search in [mid + 1, high]
// 5. The binary search will converge to the smallest distance with ≥ k pairs.
//
// Time Complexity: O(n log n + n log W), where W = max(nums) - min(nums)
// Space Complexity: O(1)

import java.util.Arrays;

class Solution {

    // Helper function to count how many pairs have distance <= given distance
    public int slidingWindow(int[] nums, int n, int distance) {
        int i = 0, j = 1;
        int pairCount = 0;

        while (j < n) {
            // Move i to maintain the window where nums[j] - nums[i] <= distance
            while (nums[j] - nums[i] > distance) {
                i++;
            }

            // All pairs (i...j-1, j) are valid
            pairCount += (j - i);
            j++;
        }

        return pairCount;
    }

    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;

        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Set binary search range on the possible distances
        int low = 0;
        int high = nums[n - 1] - nums[0];
        int result = 0;

        // Step 3: Binary search on the distance
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Count how many pairs have distance <= mid
            int pairCount = slidingWindow(nums, n, mid);

            if (pairCount >= k) {
                // We found at least k pairs → try smaller distances
                result = mid;
                high = mid - 1;
            } else {
                // Not enough pairs → try larger distances
                low = mid + 1;
            }
        }

        return result;
    }
}
