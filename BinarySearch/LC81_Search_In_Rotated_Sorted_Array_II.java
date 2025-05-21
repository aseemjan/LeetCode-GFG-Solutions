// LeetCode 81 – Search in Rotated Sorted Array II
// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

// Problem:
// Given a rotated sorted array (that may contain duplicates), return true if the target exists, false otherwise.

// Approach:
// 1. First, handle duplicates while finding the pivot using binary search.
// 2. Once the pivot is found, use binary search on both sides of the pivot to check for the target.

// Time Complexity: Worst-case O(n) due to duplicates during pivot search
// Space Complexity: O(1)

// Follow-up:
// Q: How do duplicates affect the runtime complexity?
// A: Duplicates may cause the worst-case time complexity to degrade from O(log n) to O(n),
//    because when elements at left, mid, and right are equal, we can't determine the sorted half and must shrink the bounds linearly.

// Only difference from LeetCode 33: This problem allows **duplicate** elements, which affects pivot detection.

class Solution {
    public int findPivot(int[] nums, int n){
        int left = 0;
        int right = n - 1;

        while(left < right){
            // Skip duplicates on both ends to avoid ambiguity
            while(left < right && nums[left] == nums[left + 1]){
                left++;
            }

            while(left < right && nums[right] == nums[right - 1]){
                right--;
            }

            int mid = left + (right - left) / 2;

            // Pivot is in the right half
            if(nums[mid] > nums[right]){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return right; // Pivot index
    }

    public boolean binarySearch(int[] nums, int target, int start, int end){
        while(start <= end){
            int mid = start + (end - start) / 2;

            if(nums[mid] == target){
                return true;
            }else if (nums[mid] > target) end = mid - 1;
            else start = mid + 1;
        }
        return false;
    }

    public boolean search(int[] nums, int target) {
        int n = nums.length;

        int pivotIdx = findPivot(nums, n);

        // Try left half
        if(binarySearch(nums, target, 0, pivotIdx - 1)){
            return true;
        }

        // Try right half
        return binarySearch(nums, target, pivotIdx, n - 1);
    }
}
