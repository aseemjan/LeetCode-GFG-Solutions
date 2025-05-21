// LeetCode 35 – Search Insert Position
// https://leetcode.com/problems/search-insert-position/

// Problem:
// Given a sorted array of distinct integers and a target value,
// return the index if the target is found. If not, return the index where it would be inserted.
// Must run in O(log n) time.

// Approach:
// Apply binary search to locate the target.
// If found, return its index.
// If not found, 'low' will point to the correct insertion position.


// Time Complexity: O(log n)
// Space Complexity: O(1)

class Solution {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;

        // Binary search to find the position of target or its insertion index
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid; // target found
            } else if (nums[mid] < target) {
                low = mid + 1; // search right half
            } else {
                high = mid - 1; // search left half
            }
        }

        // If not found, 'low' is the correct insertion position
        return low;
    }
}
