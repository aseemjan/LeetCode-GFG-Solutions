// LeetCode 8 – String to Integer (atoi)
// https://leetcode.com/problems/string-to-integer-atoi/

// Problem:
// Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (like C/C++'s atoi).
// The function discards leading whitespace characters until the first non-whitespace character is found.
// Then takes an optional '+' or '-' sign, followed by as many numerical digits as possible, and interprets them as a numerical value.
// The string can contain additional characters after those digits which are ignored.
// If the converted integer overflows the range of a 32-bit signed integer, clamp the value within [-2^31, 2^31 - 1].

// Example:
// Input: "   -42"
// Output: -42

// Input: "4193 with words"
// Output: 4193

// Input: "words and 987"
// Output: 0

// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int myAtoi(String s) {

        // Step 1: Trim leading and trailing whitespace
        s = s.trim();

        int n = s.length();
        if (n == 0) return 0;

        // Step 2: Initialize variables
        boolean neg = false; // Track if the number is negative
        long ans = 0;        // Use long to safely handle overflow during computation
        int i = 0;

        // Step 3: Handle optional '+' or '-' sign
        if (i < n && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            neg = s.charAt(i) == '-'; // Set neg = true if '-' is found
            i++; // Move to the next character
        }

        // Step 4: Parse digits and build the number
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            ans = ans * 10 + digit;

            // Step 5: Check for overflow and clamp if necessary
            if (!neg && ans > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (neg && -ans < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++;
        }

        // Step 6: Apply the sign to the final result
        if (neg) ans = -ans;

        return (int) ans;
    }
}
