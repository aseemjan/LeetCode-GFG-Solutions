// LeetCode 540 – Single Element in a Sorted Array
// https://leetcode.com/problems/single-element-in-a-sorted-array/

// Problem:
// You are given a sorted array consisting of only integers where every element appears exactly twice,
// except for one element which appears exactly once.
// Return the single element that appears only once.

// Your solution must run in O(log n) time and O(1) space.

// Example 1:
// Input: nums = [1,1,2,3,3,4,4,8,8]
// Output: 2

// Example 2:
// Input: nums = [3,3,7,7,10,11,11]
// Output: 10

// Constraints:
// 1 <= nums.length <= 10^5
// 0 <= nums[i] <= 10^5

// Approach:
// 1. Use binary search on the index range.
// 2. In a correctly paired subarray, the first occurrence of a pair is always at an even index,
//    and the second occurrence is at an odd index.
// 3. When you find that nums[mid] == nums[mid + 1]:
//    - If the remaining length on the right side (including mid) is even, the single element is on the right → move left to mid + 2.
//    - If the remaining length is odd, the single element is on the left → move right to mid - 1.
// 4. Else (nums[mid] != nums[mid + 1]):
//    - If the remaining length is even, the single element is on the left → move right to mid.
//    - If odd, the single element is on the right → move left to mid + 1.
// 5. The loop ends when low == high, which will be the index of the unique element.

// Time Complexity: O(log n)
// Space Complexity: O(1)

class Solution {
    public int singleNonDuplicate(int[] arr) {
        int n = arr.length;

        int l = 0, h = n - 1;

        while(l < h){
            int mid = l + (h - l) / 2;

            boolean isEven = false;

            if((h - mid) % 2 == 0){
                isEven = true;
            }else{
                isEven = false;
            }

            if(arr[mid] == arr[mid + 1]){
                if(isEven){
                    l = mid + 2;
                }else{
                    h = mid - 1;
                }
            }else{
                if(isEven){
                    h = mid;
                }else{
                    l = mid + 1;
                }
            }
        }
        return arr[h];
    }
}