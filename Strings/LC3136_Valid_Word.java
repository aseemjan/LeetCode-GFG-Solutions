// LeetCode 3136 – Valid Word
// https://leetcode.com/problems/valid-word/

// ✅ Problem:
// A word is valid if ALL of the following conditions are satisfied:
// 1. It has at least 3 characters
// 2. It contains only lowercase/uppercase letters and digits (no special characters)
// 3. It has at least one vowel (a, e, i, o, u – case-insensitive)
// 4. It has at least one consonant (any alphabet that is not a vowel)

// Return true if the given string is a valid word, otherwise false.

// ✅ Example:
// Input: word = "a3i"
// Output: false → no consonant
//
// Input: word = "Ab3"
// Output: true

// ✅ Time Complexity: O(n)
// ✅ Space Complexity: O(1)

class Solution {
    public boolean isValid(String word) {
        int n = word.length();

        // Step 1: Length must be at least 3
        if (n < 3) return false;

        boolean hasVowel = false;
        boolean hasConsonant = false;

        // Step 2: Iterate through each character
        for (char ch : word.toCharArray()) {

            if (Character.isLetter(ch)) {
                // Convert to lowercase for uniform vowel check
                ch = Character.toLowerCase(ch);

                // Step 3: Check if it's a vowel
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    hasVowel = true;
                } else {
                    // Step 4: If not a vowel, it’s a consonant
                    hasConsonant = true;
                }

            } else if (!Character.isDigit(ch)) {
                // Step 5: If not a letter or digit, invalid character
                return false;
            }
        }

        // Step 6: Must have at least one vowel and one consonant
        return hasVowel && hasConsonant;
    }
}
