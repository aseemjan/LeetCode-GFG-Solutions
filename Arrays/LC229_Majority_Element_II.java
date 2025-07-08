// LeetCode 229 – Majority Element II
// https://leetcode.com/problems/majority-element-ii/

// Problem:
// Given an integer array `nums`, return all the elements that appear more than ⌊n / 3⌋ times.
// You may return the answer in **any order**.

// Constraints:
// - 1 <= nums.length <= 5 * 10^4
// - -10^9 <= nums[i] <= 10^9

// Follow-up: Can you solve the problem in O(n) time and O(1) space?

// -------------------------------------------------------------------------------------------------
// Approach: Extended Boyer-Moore Voting Algorithm
// Intuition:
// - An element must appear more than ⌊n/3⌋ times to be considered a majority element.
// - There can be **at most 2 such elements** in any array.
//   - Because more than 3 elements appearing more than n/3 times would exceed the array size.
// - We use two counters and two candidate elements.
//   - In the first pass, find potential candidates.
//   - In the second pass, verify if they actually occur > n/3 times.

// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;

        // Step 1: Find up to two potential majority candidates
        int count1 = 0, element1 = 0;
        int count2 = 0, element2 = 0;

        for (int num : nums) {
            if (num == element1) {
                count1++; // Increment count for candidate 1
            } else if (num == element2) {
                count2++; // Increment count for candidate 2
            } else if (count1 == 0) {
                element1 = num;
                count1 = 1; // Assign new candidate to element1
            } else if (count2 == 0) {
                element2 = num;
                count2 = 1; // Assign new candidate to element2
            } else {
                // Reduce count for both candidates
                count1--;
                count2--;
            }
        }

        // Step 2: Verify the actual frequencies of the two candidates
        int freq1 = 0, freq2 = 0;

        for (int num : nums) {
            if (num == element1) freq1++;
            else if (num == element2) freq2++;  // Use `else if` to avoid double-counting when element1 == element2
        }

        List<Integer> result = new ArrayList<>();

        if (freq1 > n / 3) result.add(element1);

        // Why check `element2 != element1`?
        // It's possible that both `element1` and `element2` were set to the same number
        // (especially in arrays with repeated numbers like [2,2,2,2,2]).
        // This check ensures that we do not add the same number twice to the result.
        if (element2 != element1 && freq2 > n / 3) result.add(element2);

        return result;
    }
}
