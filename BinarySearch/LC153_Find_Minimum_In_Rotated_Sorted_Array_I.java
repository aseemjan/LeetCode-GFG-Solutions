// LeetCode 153 – Find Minimum in Rotated Sorted Array I
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

// Problem:
// Given the sorted rotated array nums of unique elements,
// Return the minimum element of this array..
// Must run in O(log n) time.

// Approach:
// 1. Use binary search to find the index of the minimum element (pivot) in the rotated sorted array.
// 2. Compare mid with right to decide which half to search next.
// 3. The loop ends when left == right, pointing to the minimum element.


// Time Complexity: O(log n)
// Space Complexity: O(1)

class Solution{
    public int findMin(int[] nums) {
        int n = nums.length;

        int l = 0, r = n - 1;

        while(l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] > nums[r]){
                l = mid + 1;
            }else if (nums[mid] < nums[r]){
                r = mid;
            }
        }
        return nums[r]; //nums[r] can also be returned since they'll be at the same index at the end;
    }
}