// LeetCode 205 – Isomorphic Strings
// https://leetcode.com/problems/isomorphic-strings/

// Problem:
// Given two strings s and t, determine if they are isomorphic.
//
// Two strings are isomorphic if the characters in s can be replaced to get t,
// with the following conditions:
// - Each character in s must map to exactly one character in t.
// - No two characters in s may map to the same character in t.
// - Characters may map to themselves.
//
// Examples:
// Input: s = "egg", t = "add"     → Output: true
// Input: s = "foo", t = "bar"     → Output: false
// Input: s = "paper", t = "title" → Output: true

// Constraints:
// - 1 <= s.length <= 5 * 10^4
// - t.length == s.length
// - s and t consist of any valid ASCII characters

// Approach 1: Using Two Arrays (ASCII mapping)
// Time Complexity: O(n)
// Space Complexity: O(1) – We use two fixed-size arrays of size 256 (ASCII limit)
//
// Explanation:
// - We use two arrays:
//     mp1[ch1] stores the character in t that ch1 from s maps to
//     mp2[ch2] stores the character in s that ch2 from t maps to
// - Both directions must agree for the strings to be isomorphic
// - Initialize all entries to -1 (meaning "unmapped")

class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] mp1 = new int[256];
        int[] mp2 = new int[256];
        Arrays.fill(mp1, -1);
        Arrays.fill(mp2, -1);

        int n = s.length();

        for (int i = 0; i < n; i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            // If already mapped, check consistency
            if ((mp1[ch1] != -1 && mp1[ch1] != ch2) || (mp2[ch2] != -1 && mp2[ch2] != ch1))
                return false;

            // Record new mapping
            mp1[ch1] = ch2;
            mp2[ch2] = ch1;
        }

        return true;
    }
}

// Approach 2: Using HashMaps
// Time Complexity: O(n)
// Space Complexity: O(1) – At most 256 entries (ASCII character mappings)
//
// Explanation:
// - Use two HashMaps:
//     mp1 maps s → t
//     mp2 maps t → s
// - For each character pair:
//     - If already mapped, check for consistency
//     - Otherwise, store the new mapping

public class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> mp1 = new HashMap<>();
        Map<Character, Character> mp2 = new HashMap<>();

        int n = s.length();

        for (int i = 0; i < n; i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            // Check if mapping already exists and is consistent
            if ((mp1.containsKey(ch1) && mp1.get(ch1) != ch2) ||
                    (mp2.containsKey(ch2) && mp2.get(ch2) != ch1)) {
                return false;
            }

            // Create new mappings in both directions
            mp1.put(ch1, ch2);
            mp2.put(ch2, ch1);
        }

        return true;
    }
}
