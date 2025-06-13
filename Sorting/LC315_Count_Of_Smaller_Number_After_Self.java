// LeetCode 315 – Count of Smaller Numbers After Self
// https://leetcode.com/problems/count-of-smaller-numbers-after-self/

// Problem:
// Given an integer array nums, return an integer array counts where counts[i]
// is the number of smaller elements to the right of nums[i].

// Constraints:
// 1 <= nums.length <= 10^5
// -10^4 <= nums[i] <= 10^4

import java.util.*;

class Solution {

    public List<Integer> countSmaller(int[] nums){
        int n = nums.length;

        //Declaring Array : 1. indices[] to store the actual indexes of the elements
        //                  2. count[] to store the count of the elements smaller than self
        int[] indices = new int[n];
        int[] counts = new int[n];

        //Storing the indexes in indices array
        for(int i = 0; i < n; i++){
            indices[i] = i;
        }

        //This function will divide and merge the arrays from left to right and store out output in 'counts' array
        mergeSort(nums, 0, n - 1, indices, counts);

        //Declaring a list result & storing the output
        List<Integer> result = new ArrayList<>();

        for(int count : counts){
            result.add(count);
        }

        return result;
    }

    //Helper Function MergeSort
    public static void mergeSort(int[] nums, int left, int right, int[] indices, int[] counts){
        //Since we're using Recursion we have to have a base condition, which will stop recursion and start returning the values
        if(left >= right) return;

        int mid = left + (right - left) / 2;

        //Breaking the array in two parts, From (0 to mid) & From (mid + 1 to n - 1) using Recursion
        mergeSort(nums, left, mid, indices, counts);
        mergeSort(nums, mid + 1, right, indices, counts);

        //Although this merges the two arrays like normal Merge Sort but
        //Additionally it counts how many numbers smaller are to the right of the current number
        merge(nums, left, mid, right, indices, counts);
    }

    //Helper Function to Merge the Elements and count smaller numbers
    public static void merge(int[] nums, int left, int mid, int right, int[] indices, int[] counts){
        List<Integer> temp = new ArrayList<>();

        int i = left;       // pointer for left half
        int j = mid + 1;    // pointer for right half
        int rightCount = 0; // number of smaller elements from right half that have passed

        // Merge the two halves while counting how many smaller elements are encountered from the right
        while(i <= mid && j <= right){
            if(nums[indices[j]] < nums[indices[i]]){
                // element from right is smaller, it will move ahead of left[i]
                temp.add(indices[j]);
                rightCount++;
                j++;
            } else {
                // count how many smaller elements have moved before current left[i]
                counts[indices[i]] += rightCount;
                temp.add(indices[i]);
                i++;
            }
        }

        // Process remaining left elements
        while(i <= mid){
            counts[indices[i]] += rightCount;
            temp.add(indices[i]);
            i++;
        }

        // Process remaining right elements
        while(j <= right){
            temp.add(indices[j]);
            j++;
        }

        // Copy the sorted indices back into the original array
        for(int k = left; k <= right; k++){
            indices[k] = temp.get(k - left);
        }
    }
}
