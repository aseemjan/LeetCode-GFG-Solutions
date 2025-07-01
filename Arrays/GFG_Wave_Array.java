// GFG – Wave Array
// https://www.geeksforgeeks.org/problems/wave-array-1587115621/

// Problem:
// Given a sorted array arr[] of integers, convert it into a wave-like array in-place.
// That is, arrange the elements so that:
//     arr[0] >= arr[1] <= arr[2] >= arr[3] <= arr[4] ...
// The array should remain in-place, and among all valid solutions, the lexicographically smallest should be returned.

// Note:
// - The array is initially sorted in increasing order.
// - Only adjacent elements can be swapped.
// - Modify the given array directly (in-place).

// Examples:
//
// Input:  arr[] = [1, 2, 3, 4, 5]
// Output: [2, 1, 4, 3, 5]
//

//
// Input:  arr[] = [1]
// Output: [1]
//
// Constraints:
// 1 ≤ arr.length ≤ 10^6
// 0 ≤ arr[i] ≤ 10^9

// Expected Time Complexity: O(n)
// Expected Auxiliary Space: O(1)

// Approach:
// - Since the array is already sorted in increasing order, we can swap every adjacent pair starting from index 0.
// - Specifically, we swap arr[i] and arr[i+1] for all even indices (i = 0, 2, 4, ...).
// - This guarantees that: arr[0] >= arr[1], arr[2] >= arr[3], and so on.
// - Since the array is sorted, this produces the **lexicographically smallest** wave-like array.
//
// Example:
// Input: [1, 2, 3, 4, 5]
// Step-by-step:
//  -> swap(0,1) => [2,1,3,4,5]
//  -> swap(2,3) => [2,1,4,3,5]
// Final: [2,1,4,3,5]

class Solution {
    public void sortInWave(int[] arr) {
        int n = arr.length;

        // No need to do anything for single element
        if (n == 1) return;

        // Swap adjacent elements in pairs starting from index 0
        for (int i = 0; i < n - 1; i += 2) {
            // Swap only if arr[i] <= arr[i+1] to maintain the wave pattern
            if (arr[i] <= arr[i + 1]) {
                swap(arr, i, i + 1);
            }
        }
    }

    // Helper function to swap two elements at indices i and j
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
