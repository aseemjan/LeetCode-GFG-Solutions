// LeetCode 242 – Valid Anagram
// https://leetcode.com/problems/valid-anagram/

// Problem:
// Given two strings `s` and `t`, return true if `t` is an anagram of `s`, and false otherwise.
//
// An Anagram is a word formed by rearranging the letters of another word.
// Example: "anagram" → "nagaram" is valid, but "rat" → "car" is not.
//
// Constraints:
// - 1 <= s.length, t.length <= 5 * 10^4
// - s and t consist of lowercase English letters only
//
// Follow-up:
// - How would you handle Unicode characters (beyond lowercase English)?
//   → Use a HashMap<Character, Integer> instead of fixed-size arrays

// Approach:
// - Convert both strings to character arrays
// - Sort both arrays
// - Compare if sorted arrays are equal
//
// Time Complexity: O(n log n) due to sorting
// Space Complexity: O(1) (ignoring sorting space which can be O(n) depending on implementation)

public class Solution {
    public boolean isAnagram(String s, String t) {
        // If lengths are not equal, cannot be anagrams
        if (s.length() != t.length()) return false;

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }
}

// Approach:
// - Count frequency of each character in s and subtract while iterating over t
// - At the end, if all counts are 0, strings are anagrams
//
// Time Complexity: O(n)
// Space Complexity: O(1) — fixed size 26-element array (for lowercase letters only)

public class Solution {
    public boolean isAnagram(String s, String t) {
        // If lengths don't match, can't be anagrams
        if (s.length() != t.length()) return false;

        int[] count = new int[26]; // For 'a' to 'z'

        // Increment count for each character in s
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        // Decrement count for each character in t
        for (char ch : t.toCharArray()) {
            count[ch - 'a']--;
        }

        // Check if all counts are zero
        for (int c : count) {
            if (c != 0) return false;
        }

        /*

        // Can Also Use Lambda Operator i.e. - Arrays.Stream method

            boolean allZeroes = Arrays.stream(count).allMatch(element -> element == 0);

            return allZeroes;

         */
        return true;
    }
}
