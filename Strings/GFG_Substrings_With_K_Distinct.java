// GFG: Count substrings with exactly K distinct characters
// Link: https://www.geeksforgeeks.org/problems/count-number-of-substrings4528/1

// Given a string s consisting of lowercase characters and an integer k,
// count all possible substrings (not necessarily distinct) that have exactly k distinct characters.

// Example:
// Input: s = "aba", k = 2
// Output: 3
// Explanation: Substrings are ["ab", "ba", "aba"]

// Constraints:
// - 1 ≤ s.length ≤ 10^6
// - 1 ≤ k ≤ 26
// Expected Time Complexity: O(n)
// Expected Auxiliary Space: O(1) — i.e., no extra space that grows with input size

// Approach:
// ✅ Optimal Sliding Window Technique using:
//    countAtMostK(s, k) - countAtMostK(s, k - 1)
//
// Reasoning:
// - Total substrings with **exactly K distinct** characters can be found by subtracting:
//   all substrings with at most (K - 1) distinct chars from those with at most K distinct chars.
// - This trick allows us to avoid generating substrings explicitly.
// - We use a sliding window to count substrings with at most K distinct characters in O(n).

// Time Complexity: O(n)
// Space Complexity: O(1) — we use a fixed 26-length array for lowercase characters.

class Solution {
    public int countSubstr(String s, int k) {
        return countAtMostKDistinct(s, k) - countAtMostKDistinct(s, k - 1);
    }

    // Helper function to count substrings with at most K distinct characters
    private int countAtMostKDistinct(String s, int k) {
        int n = s.length();
        int[] freq = new int[26]; // Frequency array for lowercase letters
        int left = 0;
        int count = 0;
        int distinct = 0;

        for (int right = 0; right < n; right++) {
            int idx = s.charAt(right) - 'a';

            // If character is new to the current window, increase distinct count
            if (freq[idx] == 0) {
                distinct++;
            }
            freq[idx]++;

            // Shrink the window until we have at most k distinct characters
            while (distinct > k) {
                int leftIdx = s.charAt(left) - 'a';
                freq[leftIdx]--;

                // If character is completely removed from window, decrease distinct count
                if (freq[leftIdx] == 0) {
                    distinct--;
                }
                left++;
            }

            // All substrings ending at 'right' with valid window contribute (right - left + 1)
            count += right - left + 1;
        }

        return count;
    }
}

/*
Other Approaches :
1. Brute Force (O(n³)):
   Generate all substrings and for each, count number of distinct characters using a Set.
   Too slow for large inputs.

2.Optimized Brute Force (O(n²)):
    Use a frequency map inside a nested loop, count distinct chars, and break early if > k.
    Still too slow for n = 10^6.

 */
