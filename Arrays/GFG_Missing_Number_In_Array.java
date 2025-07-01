// GFG – Missing Number in Array
// https://www.geeksforgeeks.org/problems/missing-number-in-array1416/

// Problem:
// You are given an array `arr[]` of size `n - 1` that contains distinct integers from 1 to n (inclusive).
// One number is missing. Find and return that missing number.

// Examples:
//
// Input:  arr[] = [1, 2, 3, 5]
// Output: 4
//
// Input:  arr[] = [8, 2, 4, 5, 3, 7, 1]
// Output: 6
//
// Input:  arr[] = [1]
// Output: 2

// Constraints:
// 1 ≤ arr.length ≤ 10^6
// 1 ≤ arr[i] ≤ arr.length + 1

// Expected Time Complexity: O(n)
// Expected Auxiliary Space: O(1)

class Solution {

    // ✅ Approach 1: XOR Method
    // Time: O(n), Space: O(1)
    //
    // Idea:
    // - XOR all numbers from 1 to n
    // - XOR the result with all elements in the array
    // - Since XOR cancels out duplicates, only the missing number remains
    int missingNumXOR(int[] nums) {
        int n = nums.length + 1; // Total numbers should be n

        int xor = 0;

        // XOR all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }

        // XOR all elements in the array
        for (int num : nums) {
            xor ^= num;
        }

        return xor;
    }

    // ✅ Approach 2: Sum Formula Method
    // Time: O(n), Space: O(1)
    //
    // Idea:
    // - Sum of first n natural numbers is (n * (n + 1)) / 2
    // - Subtract the sum of elements in the array to get the missing number
    int missingNum(int[] arr) {
        long n = arr.length;

        // Sum of first n+1 natural numbers (since one number is missing)
        long sumOfn = ((n + 1) * (n + 2)) / 2;

        // Subtract each element from the total sum
        for (long num : arr) {
            sumOfn -= num;
        }

        return (int) sumOfn;
    }
}
