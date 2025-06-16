/*
 * LeetCode 2016 – Maximum Difference Between Increasing Elements
 * https://leetcode.com/problems/maximum-difference-between-increasing-elements/
 *
 * Problem:
 * Given a 0-indexed integer array nums of size n, find the maximum difference
 * between nums[i] and nums[j] (i.e., nums[j] - nums[i]), such that 0 <= i < j < n
 * and nums[i] < nums[j].
 *
 * Return the maximum difference. If no such i and j exists, return -1.
 *
 * Example 1:
 * Input  : nums = [7,1,5,4]
 * Output : 4
 * Explanation:
 * The maximum difference occurs with i = 1 and j = 2,
 * nums[j] - nums[i] = 5 - 1 = 4.
 *
 * Constraints:
 * n == nums.length
 * 2 <= n <= 1000
 * 1 <= nums[i] <= 1e9
 *
 * Approach:
 * 1. Maintain the smallest element seen so far as we move from left to right.
 * 2. For each element nums[j], if nums[j] > minEl, compute the difference and update the answer.
 * 3. Otherwise, update minEl to nums[j].
 *
 * Intuition (Analogy):
 * Imagine tracking the lowest stock price you've seen so far; every day you compare today's price
 * against that lowest price to see how much profit you could make if you bought at that lowest point
 * and sold today. The task is to find the biggest possible profit.
 *
 * Complexity:
 * Time  : O(n) – single pass through the array.
 * Space : O(1) – constant extra space.
 */

class Solution {
    /**
     * Calculates the maximum difference between two increasing elements.
     *
     * @param nums an array of integers
     * @return the maximum difference or -1 if no increasing pair exists
     */
    public int maximumDifference(int[] nums) {
        int n = nums.length;

        int minEl = nums[0];

        // Note : We are considering minimum elements as our nums[i]

        int maxDiff = -1;

        for(int j = 1; j < n; j++){ // Since we have considered nums[0] as our minEl
            // If current number is larger than the minimum seen so far,
            // we have a valid increasing pair (minEl, nums[j]).
            if(nums[j] > minEl){
                maxDiff = Math.max(maxDiff, nums[j] - minEl);
            }else {
                // Current number becomes the new minimum candidate for future comparisons.
                minEl = nums[j];
            }
        }
        return maxDiff;
    }
}
