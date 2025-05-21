// LeetCode 33 – Search in Rotated Sorted Array
// https://leetcode.com/problems/search-in-rotated-sorted-array/

// Problem:
// Given a sorted array (with distinct integers) that has been rotated at an unknown pivot,
// search for a target value and return its index. If not found, return -1.
// Must run in O(log n) time.

// Approach:
// 1. First, find the pivot index where rotation occurred using binary search.
// 2. Then apply binary search on the left and right subarrays split by the pivot.
// 3. Return the index if found, otherwise return -1.

// Time Complexity: O(log n)
// Space Complexity: O(1)

class Solution {
    public int findPivot(int[] nums, int n){
        int left = 0;
        int right = n - 1;

        while(left < right){
            int mid = left + (right - left) / 2;

            if(nums[mid] > nums[right]){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return right;
    }

    public int binarySearch(int[] nums, int target, int start, int end){
        while(start <= end){
            int mid = start + (end - start) / 2;

            if(nums[mid] == target){
                return mid;
            }else if (nums[mid] < target) start = mid + 1;
            else end = mid - 1;
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        int idx = -1;

        int pivotIdx = findPivot(nums, n);

        idx = binarySearch(nums, target, 0, pivotIdx - 1);

        if(idx != -1){
            return idx;
        }

        idx = binarySearch(nums, target, pivotIdx, n - 1);

        return idx;
    }
}
