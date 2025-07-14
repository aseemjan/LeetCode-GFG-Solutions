// LeetCode 151 – Reverse Words in a String
// https://leetcode.com/problems/reverse-words-in-a-string/

// Problem:
// Given a string s, reverse the order of the words.
// A word is defined as a sequence of non-space characters.
// Return a string with the words in reverse order, separated by a single space.
// The result should not contain leading or trailing spaces.

// Example:
// Input:  "  the sky  is blue  "
// Output: "blue is sky the"

// Constraints:
// - 1 <= s.length <= 10^4
// - s contains English letters (upper-case and lower-case), digits, and spaces ' '
// - There is at least one word in s

// -----------------------------------------------------------------------------------
// ✅ Approach 1: Two Pointer + Reverse Word by Word (In-place on char[])
// 1. Reverse the whole string
// 2. For each word: copy into a compacted version, reverse it, insert one space
// 3. Skip extra spaces between words
// 4. Trim trailing space from final output
// Time Complexity: O(n)
// Space Complexity: O(n) for the char array
// -----------------------------------------------------------------------------------

class Solution {
    public String reverseWords(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        // Step 1: Reverse the full array
        reverse(arr, 0, n - 1);

        int i = 0;
        int left = 0, right = 0; // will write cleaned and reversed words here

        while (i < n) {
            // Skip and copy non-space characters
            while (i < n && arr[i] != ' ') {
                arr[right++] = arr[i++];
            }

            // If a word was copied, reverse that word
            if (left < right) {
                reverse(arr, left, right - 1);

                // Add a space only if there's more to process
                if (right < n) {
                    arr[right++] = ' ';
                }

                // Update left pointer for next word
                left = right;
            }

            i++; // skip space or move forward
        }

        // Trim trailing space if present
        int end = (right > 0 && arr[right - 1] == ' ') ? right - 1 : right;

        return new String(arr, 0, end);
    }

    // Helper to reverse part of a char array
    private void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }

    // -----------------------------------------------------------------------------------
    // ✅ Approach 2: Using Split + StringBuilder (Clean & Simple)
    // 1. Trim the string to remove leading/trailing spaces
    // 2. Split using "\\s+" to remove multiple internal spaces
    // 3. Iterate from the end and rebuild string
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // -----------------------------------------------------------------------------------

    public String reverseWordsSplit(String s) {
        String[] tokens = s.trim().split("\\s+");

        StringBuilder sb = new StringBuilder();

        for (int i = tokens.length - 1; i >= 0; i--) {
            sb.append(tokens[i]).append(" ");
        }

        return sb.toString();
    }
}
