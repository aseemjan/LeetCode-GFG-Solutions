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
    public String getPermutation(int n, int k) {
        // Create a list of numbers from 1 to n
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        // Precompute factorials up to (n - 1)!
        // fact[i] will hold i! for quick access
        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = fact[i - 1] * i;
        }

        // Convert k to 0-based index to simplify calculations
        k = k - 1;

        StringBuilder sb = new StringBuilder();

        // Loop from n down to 1 because we are placing digits from left to right
        // i.e., for position 1 → (n) choices, position 2 → (n-1) choices, ..., last position → 1 choice
        for (int i = n; i >= 1; i--) {
            // Determine which block of permutations the k-th one falls into
            // Each block is of size (i-1)! and corresponds to a specific starting digit
            int idx = k / fact[i - 1];

            // Append the selected digit to our result
            sb.append(nums.get(idx));

            // Remove the used digit from the list (so we don't use it again)
            nums.remove(idx);

            // Update k to be the offset within the new block
            k = k % fact[i - 1];
        }

        return sb.toString();
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