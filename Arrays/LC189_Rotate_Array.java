// LeetCode 189 – Rotate Array
// https://leetcode.com/problems/rotate-array/

// Problem:
// Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

// Example 1:
// Input: nums = [1,2,3,4,5,6,7], k = 3
// Output: [5,6,7,1,2,3,4]
// Explanation:
// rotate 1 step to the right: [7,1,2,3,4,5,6]
// rotate 2 steps to the right: [6,7,1,2,3,4,5]
// rotate 3 steps to the right: [5,6,7,1,2,3,4]

// Example 2:
// Input: nums = [-1,-100,3,99], k = 2
// Output: [3,99,-1,-100]

// Constraints:
// 1 <= nums.length <= 10^5
// -2^31 <= nums[i] <= 2^31 - 1
// 0 <= k <= 10^5

// Follow-up:
// - Try to come up with as many solutions as you can.
// - Could you do it in-place with O(1) extra space?

class Solution {

    // ✅ Approach 1: Brute Force – Rotate one by one (Time: O(n*k), Space: O(1))
    // Not efficient for large inputs, but works.
    public void rotateBruteForce(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // To avoid redundant full rotations (e.g., k = 10 for n = 5 → same as k = 0)

        for (int step = 0; step < k; step++) {
            int last = nums[n - 1]; // Save last element

            // Shift all elements one step to the right
            for (int i = n - 1; i > 0; i--) {
                nums[i] = nums[i - 1];
            }

            nums[0] = last; // Place saved element at index 0
        }
    }

    // ✅ Approach 2: Extra Array – Use a temp array (Time: O(n), Space: O(n))
    // Fast and easy, but not in-place.
    public void rotateExtraArray(int[] nums, int k) {
        int n = nums.length;
        int[] rotated = new int[n];
        k = k % n; // To prevent out-of-bound issues

        // Place each element at its rotated position
        for (int i = 0; i < n; i++) {
            rotated[(i + k) % n] = nums[i];
        }

        /*
         Question - So why i + k?
         Answer - Because each element is shifted k positions to the right.
                  So you just add k to its original index.
                  Then take % n to wrap around the end of the array.

          Example : -
          Original Index (i):  0  1  2  3  4  5  6
          Original Array:      1  2  3  4  5  6  7

            We are rotating right by k = 3, so:

            Element at index 0 (1) → should go to index 3
            Element at index 1 (2) → should go to index 4
            Element at index 2 (3) → should go to index 5
            ...
            Element at index 4 (5) → should go to index (4 + 3) = 7, but array ends at index 6 →
            we wrap around using % n

         */


        // Copy back to original array
        for (int i = 0; i < n; i++) {
            nums[i] = rotated[i];
        }
    }

    // ✅ Approach 3: Reversal Method – In-place rotation (Time: O(n), Space: O(1))
    // Most optimal – uses 3 reverse operations
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // In case k >= n, rotating k times is same as rotating k % n times

        // Step 1: Reverse the entire array
        reverse(nums, 0, n - 1);

        // Step 2: Reverse the first k elements
        reverse(nums, 0, k - 1);

        // Step 3: Reverse the remaining n-k elements
        reverse(nums, k, n - 1);
    }

    // Helper function to reverse array from index 'start' to 'end'
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            // Swap elements at start and end
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }
}
