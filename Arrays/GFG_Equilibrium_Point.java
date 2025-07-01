// GFG – Equilibrium Point
// https://www.geeksforgeeks.org/problems/equilibrium-point-1587115620/

// Problem:
// Given an array of integers arr[], find the first equilibrium index.
// An equilibrium index is one where the sum of all elements before it
// is equal to the sum of all elements after it.
// If no such index exists, return -1.

// Examples:

// Input: arr[] = [1, 2, 0, 3]
// Output: 2
// Explanation: Left sum = 1 + 2 = 3, Right sum = 3
//
// Input: arr[] = [1, 1, 1, 1]
// Output: -1
// Explanation: No index where left sum = right sum
//
// Input: arr[] = [-7, 1, 5, 2, -4, 3, 0]
// Output: 3
// Explanation: Left sum = -7 + 1 + 5 = -1, Right sum = -4 + 3 + 0 = -1

// Constraints:
// 3 ≤ arr.length ≤ 10^5
// -10^4 ≤ arr[i] ≤ 10^4

// Expected Time Complexity: O(n)
// Expected Auxiliary Space: O(1)

// Approach:
// 1. First, calculate the total sum of the array.
// 2. Traverse the array and keep track of left sum.
// 3. At each index `i`, calculate right sum as: totalSum - leftSum - arr[i].
// 4. If left sum equals right sum, return the index.
// 5. If loop finishes without match, return -1.

// Time Complexity: O(n) – One pass to compute total, another to find index.
// Space Complexity: O(1) – Only constant variables used.

class Solution {
    // Function to find the first equilibrium index in the array
    public static int findEquilibrium(int[] arr) {
        int n = arr.length;
        int totalSum = 0;

        // Step 1: Calculate total sum of array
        for (int num : arr) {
            totalSum += num;
        }

        int leftSum = 0;

        // Step 2: Traverse to find equilibrium point
        for (int i = 0; i < n; i++) {
            // Right sum = total - left - current
            int rightSum = totalSum - leftSum - arr[i];

            // If left and right sum match, return current index
            if (leftSum == rightSum) {
                return i;
            }

            // Update left sum for next index
            leftSum += arr[i];
        }

        // Step 3: No equilibrium index found
        return -1;
    }
}

