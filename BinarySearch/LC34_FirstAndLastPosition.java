package BinarySearch;

/**
 * LeetCode 34: Find First and Last Position of Element in Sorted Array
 *
 * 📌 Problem Statement:
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending
 * position of a given target value. If target is not found in the array, return [-1, -1].
 *
 * Constraints:
 * - 0 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 * - nums is a non-decreasing array.
 * - -10^9 <= target <= 10^9
 *
 * Example:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * 💡 Approach:
 * - Use binary search twice:
 *     1. First to find the first occurrence of the target.
 *     2. Second to find the last occurrence of the target.
 * - Time Complexity: O(log n)
 * - Space Complexity: O(1)
 *
 * 🧠 Explanation:
 * - The helper method findIndex performs a binary search.
 * - It takes a boolean flag firstIdx:
 *     → If true: searches for the first occurrence.
 *     → If false: searches for the last occurrence.
 * - In searchRange, we call findIndex twice to get both the start and end positions.
 * - If the target doesn't exist, both calls return -1.
 */

public class LC34_FirstAndLastPosition {
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int[] res = {-1, -1};
            res[0] = findIndex(nums, target, true);   // First occurrence
            res[1] = findIndex(nums, target, false);  // Last occurrence
            return res;
        }

        private int findIndex(int[] nums, int target, boolean firstIdx) {
            int idx = -1;
            int low = 0, high = nums.length - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (nums[mid] == target) {
                    idx = mid;
                    if (firstIdx) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                } else if (nums[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return idx;
        }
    }
}
