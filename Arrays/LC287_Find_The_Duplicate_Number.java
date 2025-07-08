// LeetCode 287 – Find the Duplicate Number
// https://leetcode.com/problems/find-the-duplicate-number/

// Problem:
// Given an array `nums` of length n + 1 where each number is in the range [1, n],
// there is exactly one repeated number, which may appear more than once.
// Find and return that duplicate number **without modifying the array** and using **only constant extra space**.

// Constraints:
// - Must not modify the input array.
// - Must use only constant extra space.
// - Time complexity must be better than O(n²).

// -------------------------------------------------------------------------------------------------
// ❌ Brute Force (O(n²)) - Not Implemented
// - For every element, scan the rest of the array to find duplicates.
// - Time Complexity: O(n²), Space: O(1)
// - Too slow for large inputs.

// -------------------------------------------------------------------------------------------------
// ✅ Optimized Approach: Floyd’s Tortoise and Hare (Cycle Detection)
// Intuition:
// - Treat the array like a linked list where `i → nums[i]` forms the next pointer.
// - Due to the duplicate, the "linked list" will have a cycle.
// - First, detect the cycle using fast and slow pointers.
// - Then, reset one pointer to the start and move both at the same speed to find the start of the cycle,
//   which corresponds to the duplicate number.

// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        // Phase 1: Detect intersection point in the cycle
        slow = nums[slow];             // move 1 step
        fast = nums[nums[fast]];       // move 2 steps

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // Phase 2: Find the entry point of the cycle (duplicate number)
        slow = nums[0]; // reset one pointer to the start

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return fast; // or slow, both point to the duplicate
    }
}
