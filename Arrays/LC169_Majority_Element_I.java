// LeetCode 169 – Majority Element
// https://leetcode.com/problems/majority-element/

// Problem:
// Given an array `nums` of size `n`, return the majority element.
// The majority element is the element that appears more than ⌊n / 2⌋ times.
// You may assume that the majority element always exists in the array.

// Constraints:
// - n == nums.length
// - 1 <= n <= 5 * 10^4
// - -10^9 <= nums[i] <= 10^9

// Follow-up: Could you solve the problem in O(n) time and O(1) space?

// -------------------------------------------------------------------------------------------------
// Approach: Boyer-Moore Voting Algorithm
// Intuition:
// - Since the majority element appears more than n/2 times, it will "survive" any cancellation.
// - We maintain a `count` and a `candidate`.
//   - If `count == 0`, we pick the current number as the candidate.
//   - If the number matches the candidate, we increment the count.
//   - Otherwise, we decrement the count.
// - In the end, the candidate will be the majority element.

// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;     // Count of the current candidate
        int element = 0;   // Current candidate for majority element

        for (int num : nums) {
            if (count == 0) {
                // Pick a new candidate
                element = num;
                count = 1;
            } else if (num == element) {
                // Same as current candidate → increment count
                count++;
            } else {
                // Different element → cancel out
                count--;
            }
        }

        // Since the problem guarantees that a majority element exists,
        // we can return the candidate directly.
        return element;
    }
}
