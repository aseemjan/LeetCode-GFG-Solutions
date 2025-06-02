// LeetCode 162 – Find Peak Element
// https://leetcode.com/problems/find-peak-element/

// Problem:
// A peak element is an element that is strictly greater than its neighbors.
// Given an integer array `nums`, find a peak element and return its index.
// You may assume that `nums[-1] = nums[n] = -∞`.
// The array may contain multiple peaks; return the index of any of them.

// You must write an algorithm that runs in O(log n) time.

// Approach:
// 1. Use binary search to find a peak.
// 2. At each iteration:
//    - Check if nums[mid] is a peak by comparing it with neighbors (careful of boundaries).
//    - If it's a peak, return mid.
//    - If nums[mid] < nums[mid + 1], then a peak exists to the right → low = mid + 1.
//    - Else, a peak exists to the left → high = mid - 1.
// 3. The loop will always converge to a peak element due to the properties of the array.

// Time Complexity: O(log n)
// Space Complexity: O(1)

class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;

        // Base case: only one element
        if (n == 1) return 0;

        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if nums[mid] is a peak
            boolean leftIsSmaller = (mid == 0 || nums[mid] > nums[mid - 1]);
            boolean rightIsSmaller = (mid == n - 1 || nums[mid] > nums[mid + 1]);

            if (leftIsSmaller && rightIsSmaller) {
                return mid;
            }

            // If mid is less than right neighbor, move right
            else if (mid < n - 1 && nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            }

            // Otherwise, move left
            else {
                high = mid - 1;
            }
        }

        return -1; // Unreachable due to problem guarantees
    }
}
