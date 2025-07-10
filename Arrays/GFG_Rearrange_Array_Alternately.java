// LeetCode / GFG – Rearrange Array Alternately
// https://www.geeksforgeeks.org/problems/rearrange-array-alternately

// Problem:
// Given an array of positive integers, rearrange it so that the first element is the maximum,
// second is the minimum, third is the second maximum, fourth is the second minimum, and so on.
// Do this in-place (without using extra space).

// Example:
// Input : arr[] = [1, 2, 3, 4, 5, 6]
// Output: [6, 1, 5, 2, 4, 3]

// Constraints:
// 1 ≤ arr.length ≤ 10^6
// 1 ≤ arr[i] ≤ 10^6

// Expected Time Complexity: O(n log n)
// Expected Space Complexity: O(1)

import java.util.*;

class Solution {
    public void rearrange(int[] arr) {
        int n = arr.length;

        // Step 1: Sort the array in ascending order
        // This allows us to access smallest and largest elements by index
        Arrays.sort(arr);

        // Step 2: Set up two pointers:
        // - maxIdx points to the largest element
        // - minIdx points to the smallest element
        int maxIdx = n - 1;
        int minIdx = 0;

        // Step 3: Choose a number greater than the max element to encode two numbers
        int max = arr[n - 1] + 1;

        // Step 4: Traverse the array and encode two values into each index
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                // For even index → place maximum available element
                // Store it by encoding as: new_value * max + old_value
                arr[i] = (arr[maxIdx] % max) * max + arr[i];
                maxIdx--;
            } else {
                // For odd index → place minimum available element
                arr[i] = (arr[minIdx] % max) * max + arr[i];
                minIdx++;
            }
        }

        // Step 5: Decode new values by dividing by `max`
        // This removes the old value and retrieves the new one
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] / max;
        }
    }
}
