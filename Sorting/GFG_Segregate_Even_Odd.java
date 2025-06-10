// GFG – Segregate Even and Odd Numbers
// https://www.geeksforgeeks.org/sort-even-placed-elements-in-increasing-and-odd-placed-in-decreasing-order/

// Problem:
// Given an array `arr`, segregate even and odd numbers such that:
// 1. All even numbers appear first in sorted order.
// 2. Then all odd numbers appear in sorted order.
// 3. Modify the array in-place (no extra space).

// Constraints:
// - 1 ≤ arr.length ≤ 10^6
// - 0 ≤ arr[i] ≤ 10^5

// Example:
// Input:  [12, 34, 45, 9, 8, 90, 3]
// Output: [8, 12, 34, 90, 3, 9, 45]

// Input:  [0, 1, 2, 3, 4]
// Output: [0, 2, 4, 1, 3]

// Input:  [10, 22, 4, 6]
// Output: [4, 6, 10, 22]  // Sorted even numbers, no odds

// Approach:
// 1. Use two pointers (left and right) to partition the array in-place.
//    - Left pointer stops at first odd number.
//    - Right pointer stops at first even number from the end.
//    - Swap them until left < right.
// 2. Scan from start to find the first odd index = partitionIdx.
// 3. Sort even part: arr[0..partitionIdx-1]
// 4. Sort odd part: arr[partitionIdx..n-1]

// Time Complexity: O(n + e*log(e) + o*log(o))
//   - n for partitioning
//   - e for number of evens, o for number of odds
// Space Complexity: O(1) – in-place sorting

import java.util.Arrays;

class Solution {
    void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    void segregateEvenOdd(int arr[]) {
        int n = arr.length;
        int left = 0, right = n - 1;

        // Step 1: Move evens to left, odds to right using two-pointer technique
        while (left < right) {
            while (left < right && arr[left] % 2 == 0) left++;
            while (left < right && arr[right] % 2 == 1) right--;

            if (left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }

        // Step 2: Find partition index — first odd number's index
        int partitionIdx = 0;
        while (partitionIdx < n && arr[partitionIdx] % 2 == 0) {
            partitionIdx++;
        }

        // Step 3: Sort both halves separately
        Arrays.sort(arr, 0, partitionIdx);      // Sort evens
        Arrays.sort(arr, partitionIdx, n);      // Sort odds
    }
}
