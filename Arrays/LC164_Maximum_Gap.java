// LeetCode 164 – Maximum Gap
// https://leetcode.com/problems/maximum-gap/

// Problem:
// Given an integer array nums, return the maximum difference between two successive elements in its sorted form.
// If the array contains less than two elements, return 0.
// You must write an algorithm that runs in linear time and uses linear extra space.

// Other approaches (not implemented here):
// 1. Sorting-based: Sort the array and check adjacent differences → O(n log n)
// 2. Radix Sort: O(n), but implementation-heavy
// We use Bucket Sort here (Pigeonhole Principle), which achieves O(n) time and space.

// Approach:
// 1. Find global minimum and maximum in nums.
// 2. Compute the ideal minimum possible maximum gap = (max - min) / (n - 1)
//    Question you asked: "Why divide by (n - 1)?"
//    → Because in sorted form, there are (n - 1) gaps. This gives us the tightest lower bound for max gap.
// 3. Create buckets of this ideal size. Total buckets = (range / bucketSize) + 1
//    Question you asked: "Why +1 in bucket count?"
//    → To safely accommodate maxVal at the upper bound and avoid index out-of-bounds.
// 4. For each number, find its bucketIndex = (num - minVal) / bucketSize
//    Question you asked: "Why this formula?"
//    → It tells how many bucketSize steps we need to go from minVal to place this number in the right bucket.
// 5. Track only the min and max of each bucket.
//    Question you asked: "Why not store full elements in buckets?"
//    → Because max gap can only occur between buckets, not within — bucket size limits internal gaps.
// 6. Scan through buckets and compute max gap between non-empty buckets.

class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;

        // Base case: need at least two elements to have a gap
        if (n < 2) return 0;

        // Step 1: Find global minimum and maximum
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;

        for (int num : nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }

        // If all elements are same → no gap
        if (minVal == maxVal) return 0;

        // Step 2: Compute ideal gap (minimum possible maximum gap)
        int bucketSize = Math.max(1, (maxVal - minVal) / (n - 1));

        // Step 3: Compute total number of buckets
        int bucketCount = ((maxVal - minVal) / bucketSize) + 1;

        // Step 4: Prepare buckets to store only min and max in each
        int[] bucketMins = new int[bucketCount];
        int[] bucketMaxs = new int[bucketCount];
        Arrays.fill(bucketMins, Integer.MAX_VALUE);
        Arrays.fill(bucketMaxs, Integer.MIN_VALUE);

        // Step 5: Place each number into its corresponding bucket
        for (int num : nums) {
            int bucketIndex = (num - minVal) / bucketSize;

            // Update min and max of that bucket
            bucketMins[bucketIndex] = Math.min(bucketMins[bucketIndex], num);
            bucketMaxs[bucketIndex] = Math.max(bucketMaxs[bucketIndex], num);
        }

        // Step 6: Compute max gap between non-empty buckets
        int maxGap = 0;
        int previousBucketMax = minVal;

        for (int bucketIndex = 0; bucketIndex < bucketCount; bucketIndex++) {

            // Skip empty buckets
            if (bucketMins[bucketIndex] == Integer.MAX_VALUE &&
                    bucketMaxs[bucketIndex] == Integer.MIN_VALUE) continue;

            // Compute gap between current bucket's min and previous non-empty bucket's max
            maxGap = Math.max(maxGap, bucketMins[bucketIndex] - previousBucketMax);

            // Update previous non-empty bucket's max
            previousBucketMax = bucketMaxs[bucketIndex];
        }

        return maxGap;
    }
}
