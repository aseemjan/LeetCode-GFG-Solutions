// LeetCode 31 – Next Permutation
// https://leetcode.com/problems/next-permutation/

// Problem:
// A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
//
// The next permutation of an array is the next lexicographically greater permutation of its integers.
// If such arrangement is not possible, rearrange the array to the lowest possible order (i.e., sorted in ascending order).
//
// The replacement must be in-place and use only constant extra memory.
//
// Example 1:
// Input: nums = [1,2,3]
// Output: [1,3,2]
//
// Example 2:
// Input: nums = [3,2,1]
// Output: [1,2,3]
//
// Example 3:
// Input: nums = [1,1,5]
// Output: [1,5,1]
//
// Constraints:
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 100

class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;

        int golaIdx = -1;

        // Step 1: Find the first decreasing index from the end
        for (int i = n - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                golaIdx = i - 1;
                break;  // Only the 'first' such index is required
            }
        }

        // Step 2: If we found such an index, swap it with the next greater element from the right
        if (golaIdx != -1) {
            int swapIdx = golaIdx;

            for (int j = n - 1; j >= golaIdx + 1; j--) {
                if (nums[j] > nums[golaIdx]) {
                    swapIdx = j;
                    break; // Only the 'first' such index is required
                }
            }

            // Fix: pass indices to the swap method, not the values
            swap(nums, golaIdx, swapIdx);
        }

        // Step 3: Reverse the suffix
        reverse(nums, golaIdx + 1, n - 1);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }
}
