// LeetCode 1614 – Maximum Nesting Depth of the Parentheses
// https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/

// Problem:
// A valid parentheses string (VPS) is given.
// Return the **maximum nesting depth** of the parentheses.
// The nesting depth is the maximum number of parentheses opened at any point.

// Examples:
// Input: "(1+(2*3)+((8)/4))+1" → Output: 3
// Input: "(1)+((2))+(((3)))" → Output: 3
// Input: "()(())((()()))" → Output: 3

// Constraints:
// - 1 <= s.length <= 100
// - s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'
// - The string is guaranteed to be a valid parentheses string (VPS)

// Approach:
// 1. Use a counter `openBrackets` to track the current depth as you traverse the string.
// 2. Each time you see '(', increment `openBrackets`.
// 3. Each time you see ')', decrement `openBrackets`.
// 4. At each step, update `result` to hold the maximum value of `openBrackets` seen so far.
// 5. This will give the maximum nesting depth.

// Time Complexity: O(n), where n = length of string
// Space Complexity: O(1)

class Solution {
    public int maxDepth(String s) {
        int result = 0;         // Tracks the maximum nesting depth
        int openBrackets = 0;   // Tracks current open parentheses depth

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                openBrackets++; // Increase depth when we open a bracket
                result = Math.max(result, openBrackets); // Update max depth
            } else if (ch == ')') {
                openBrackets--; // Decrease depth when we close a bracket
            }
        }

        return result;
    }
}
