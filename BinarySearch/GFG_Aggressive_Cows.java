// GFG – Aggressive Cows
// https://practice.geeksforgeeks.org/problems/aggressive-cows/1

// Problem:
// You are given an array `stalls[]` of size `n` where `stalls[i]` represents the position of the i-th stall.
// You have to place `k` cows in these stalls such that the minimum distance between any two cows is maximized.

// Return the maximum possible minimum distance between the cows.

// Approach:
// 1. Sort the stalls.
// 2. Use Binary Search on Answer to find the largest minimum distance possible.
//    - The search space is [1, max distance between stalls].
// 3. For a candidate distance `mid`, check if it's possible to place all cows with at least `mid` distance apart.
//    - If possible, try for a larger distance (move right).
//    - Otherwise, try smaller distance (move left).

// Time Complexity: O(n * log(maxDistance))
// Space Complexity: O(1)

import java.util.Arrays;

class Solution {

    // Helper function to check if it’s possible to place k cows
    // such that each cow is at least `minimumAllowedDistance` apart.
    public static boolean isValid(int[] stalls, int n, int k, int minimumAllowedDistance) {
        int lastPosition = stalls[0]; // Place first cow at the first stall
        int cows = 1; // One cow placed

        for (int i = 1; i < n; i++) {
            // If current stall is at least `minimumAllowedDistance` away from last placed cow
            if ((stalls[i] - lastPosition) >= minimumAllowedDistance) {
                cows++; // Place a cow here
                lastPosition = stalls[i]; // Update last placed cow’s position
            }

            // If all cows are placed successfully
            if (cows == k) {
                return true;
            }
        }

        // Not enough cows could be placed with the given minimum distance
        return false;
    }

    public static int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls); // Sort stall positions

        int n = stalls.length;

        int low = 1; // Minimum possible distance
        int high = stalls[n - 1] - stalls[0]; // Maximum possible distance
        int ans = -1;

        // Binary Search on Answer
        while (low <= high) {
            int mid = low + (high - low) / 2; // Mid is the candidate minimum distance

            if (isValid(stalls, n, k, mid)) {
                ans = mid;        // Store current distance as a possible answer
                low = mid + 1;    // Try for a larger minimum distance
            } else {
                high = mid - 1;   // Try for a smaller minimum distance
            }
        }

        return ans; // Maximum possible minimum distance
    }
}
