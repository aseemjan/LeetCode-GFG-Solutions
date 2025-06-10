// LeetCode 3442 – Maximum Difference Between Even and Odd Frequency I
// https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/

// Problem:
// You are given a string s consisting of lowercase English letters.
//
// Your task is to find the maximum difference diff = freq(a1) - freq(a2)
// between the frequency of characters a1 and a2 in the string such that:
//
// - a1 has an odd frequency in the string.
// - a2 has an even frequency in the string.
//
// Return this maximum difference.
//
// Example 1:
// Input: s = "aaaaabbc"
// Output: 3
// Explanation:
// - 'a' has an odd frequency = 5
// - 'b' has an even frequency = 2
// - Maximum difference = 5 - 2 = 3
//
// Example 2:
// Input: s = "abcabcab"
// Output: 1
// Explanation:
// - 'a' has an odd frequency = 3
// - 'c' has an even frequency = 2
// - Maximum difference = 3 - 2 = 1
//
// Constraints:
// - 3 <= s.length <= 100
// - s consists only of lowercase English letters.
// - s contains at least one character with an odd frequency and one with an even frequency.

// Approach:
// 1. Count the frequency of each character using an int[26] array.
// 2. Traverse the frequency array:
//    - If the frequency is even, track the minimum even frequency (minEven).
//    - If the frequency is odd, track the maximum odd frequency (maxOdd).
// 3. Return the difference maxOdd - minEven.

// Time Complexity: O(n), where n = length of the string.
// Space Complexity: O(1), since we're only using a fixed-size array for 26 letters.

class Solution {
    public int maxDifference(String s) {
        int n = s.length();

        int[] freq = new int[26];

        for(char ch : s.toCharArray()){
            freq[ch - 'a']++;
        }

        int minEven = n + 1;  // No freq can be greater than this. You can also take Math.Max .
        int maxOdd = 0;

        for(int i = 0; i < 26; i++){
            if (freq[i] == 0) continue;

            if (freq[i] % 2 == 0){
                minEven = Math.min(minEven, freq[i]);
            }else {
                maxOdd = Math.max(maxOdd, freq[i]);
            }
        }

        return maxOdd - minEven;
    }
}
