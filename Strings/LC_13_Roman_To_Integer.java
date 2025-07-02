// LeetCode 13 – Roman to Integer
// https://leetcode.com/problems/roman-to-integer/

// Problem:
// Convert a Roman numeral string into an integer.
//
// Roman numerals use the following symbols:
// Symbol   Value
//   I        1
//   V        5
//   X        10
//   L        50
//   C        100
//   D        500
//   M        1000
//
// Special subtraction rules apply:
// - I before V or X → 4 or 9
// - X before L or C → 40 or 90
// - C before D or M → 400 or 900
//
// Examples:
// Input: "III"        → Output: 3
// Input: "LVIII"      → Output: 58 (L=50, V=5, III=3)
// Input: "MCMXCIV"    → Output: 1994 (M=1000, CM=900, XC=90, IV=4)
//
// Constraints:
// - 1 <= s.length <= 15
// - s contains only valid Roman numerals representing numbers from 1 to 3999

// Approach:
// 1. Use an array of size 128 to store integer values for Roman characters.
// 2. Loop through the string from left to right.
// 3. If the current character has a smaller value than the next, subtract it.
//    Else, add it to the result.
// 4. This works because subtraction cases only occur when a smaller symbol precedes a larger one.
//
// Time Complexity: O(n) where n = length of the string
// Space Complexity: O(1)

class Solution {
    public int romanToInt(String s) {
        int n = s.length();

        // ASCII-based lookup table for Roman numeral values
        int[] values = new int[128];
        values['I'] = 1;
        values['V'] = 5;
        values['X'] = 10;
        values['L'] = 50;
        values['C'] = 100;
        values['D'] = 500;
        values['M'] = 1000;

        int result = 0;

        // Traverse each character in the Roman numeral string
        for (int i = 0; i < n; i++) {
            int currVal = values[s.charAt(i)];

            // If the next symbol is larger, subtract current (handles cases like IV, IX, etc.)
            if (i < n - 1 && currVal < values[s.charAt(i + 1)]) {
                result -= currVal;
            } else {
                result += currVal;
            }
        }

        return result;
    }
}
