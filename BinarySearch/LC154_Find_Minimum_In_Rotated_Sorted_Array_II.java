// LeetCode 153 – Find Minimum in Rotated Sorted Array I
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

// Problem:
// Given a sorted and rotated array `nums` of unique elements,
// return the minimum element in the array.
// You must write an algorithm that runs in O(log n) time.

// Approach:
// 1. Use binary search to find the index of the minimum element (pivot).
// 2. Compare nums[mid] with nums[right] to decide which half to discard:
//    - If nums[mid] > nums[right], the minimum is in the right half → l = mid + 1
//    - If nums[mid] < nums[right], the minimum is in the left half → r = mid
// 3. Loop continues until l == r, which points to the minimum element.

// Note (for duplicates – LeetCode 154):
// If duplicates are allowed (e.g., nums[mid] == nums[right]),
// we can't determine the correct half, so we do r-- to safely reduce the search space.

// Time Complexity: O(log n)
// Space Complexity: O(1)

class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                r--; // duplicates case
            }
        }

        return nums[l];
    }
}
