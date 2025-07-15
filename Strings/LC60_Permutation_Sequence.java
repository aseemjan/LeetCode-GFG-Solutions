// LeetCode 60 – Permutation Sequence
// https://leetcode.com/problems/permutation-sequence/

// Problem:
// The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
// Given `n` and `k`, return the k-th permutation sequence of these numbers (1-indexed).

// Constraints:
// - 1 <= n <= 9
// - 1 <= k <= n!

// Approach:
// ✅ Optimized Mathematical Approach using Factorials (No recursion or backtracking)
// The key idea is to use factorials to "jump" over entire blocks of permutations
// to directly find the k-th permutation without generating all previous ones.
//
// Example:
// n = 4 → Numbers: [1, 2, 3, 4]
// Total permutations: 4! = 24
// If k = 9, we want the 9th permutation in lexicographic order.
//
// At each digit position, we know (n-1)! permutations start with each number.
// So we can calculate which digit to place at each position by dividing k with (n-1)!
// and reducing k accordingly.
//
// Time Complexity: O(n^2) due to list removals in ArrayList
// Space Complexity: O(n)

class Solution {
    public String getPermutation(int totalDigits, int targetPosition) {
        // Initialize list of available digits [1, 2, ..., totalDigits]
        List<Integer> availableDigits = new ArrayList<>();
        for (int i = 1; i <= totalDigits; i++) {
            availableDigits.add(i);
        }

        // Precompute factorials for positions 0 to totalDigits - 1
        int[] factorial = new int[totalDigits];
        factorial[0] = 1;
        for (int i = 1; i < totalDigits; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // Convert to 0-based index for simpler math
        targetPosition--;

        // Use StringBuilder to build the result
        StringBuilder result = new StringBuilder();

        // Loop from totalDigits to 1 → filling from leftmost to rightmost digit
        for (int digitsLeft = totalDigits; digitsLeft >= 1; digitsLeft--) {
            // Determine which digit to pick by finding the correct block
            int blockSize = factorial[digitsLeft - 1];
            int indexToPick = targetPosition / blockSize;

            // Add selected digit to result
            result.append(availableDigits.get(indexToPick));

            // Remove selected digit from available list
            availableDigits.remove(indexToPick);

            // Update targetPosition for the next digit (inside the new block)
            targetPosition = targetPosition % blockSize;
        }

        return result.toString();
    }
}

/*
    Other Approaches :

    1. Backtracking / Recursion with Permutations (Brute-force)
        Generate all n! permutations using backtracking, sort, and return the k-th.
        Very inefficient for larger n.
        Time Complexity: O(n!)
        Space Complexity: O(n!) to store all permutations.

    2. Next-Permutation k–1 times
        Start from the first permutation [1, 2, ..., n], and apply the next permutation logic k-1 times.
        Time Complexity: O(k × n)
        Space Complexity: O(n)
        Better than brute-force, but still slow for large k.

 */