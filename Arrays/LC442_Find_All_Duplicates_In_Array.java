// LeetCode 442 – Find All Duplicates in an Array
// https://leetcode.com/problems/find-all-duplicates-in-an-array/

// Problem:
// Given an integer array `nums` of length `n` where all integers are in the range [1, n]
// and each integer appears once or twice, return an array of all the integers that appear twice.

// You must solve it in O(n) time and use only constant extra space (excluding output list).

// -----------------------------------------------------------------------------------------------
// ✅ Optimized Approach: In-Place Marking Using Negation
//
// Intuition:
// - Each number is in the range [1, n], so it maps directly to a valid index (num - 1).
// - We'll use the sign of the value at that index as a "visited marker".
// - If we visit an index whose value is already negative, it means we've seen this number before.
//
// Steps:
// 1. Iterate through the array.
// 2. For each number, get its absolute value (since we may have negated it already).
// 3. Use (num - 1) as an index. If the value at that index is negative → duplicate found.
// 4. If not, mark it visited by negating the number at that index.
//
// Time Complexity: O(n)
// Space Complexity: O(1) — no extra space except the output list

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);     // Use abs in case it was marked negative
            int idx = num - 1;               // Map number to index (1-based to 0-based)

            // If the value at idx is already negative, it's a duplicate
            if (nums[idx] < 0) {
                list.add(num);
            } else {
                nums[idx] *= -1; // Mark the number as visited by negating
            }
        }

        return list;
    }
}
