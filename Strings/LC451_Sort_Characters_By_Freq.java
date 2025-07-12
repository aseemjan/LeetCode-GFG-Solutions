// LeetCode 451 – Sort Characters By Frequency
// https://leetcode.com/problems/sort-characters-by-frequency/

// Problem:
// Given a string s, sort it in decreasing order based on the frequency of the characters.
// Return the sorted string. If there are multiple answers, return any of them.

// Example:
// Input: s = "tree"
// Output: "eert" or "eetr"

// Constraints:
// - s consists of uppercase and lowercase English letters and digits.
// - Length of s is in the range [1, 5 * 10^5]

// -----------------------------------------------------------------------------------
// Approach 1: ASCII Array + Custom Pair + Sorting
// -----------------------------------------------------------------------------------
// 1. Create an array of 123 elements to cover all ASCII characters up to 'z' (ASCII 122).
// 2. Use a custom Pair class to store each character and its frequency.
// 3. Count the frequency of each character by indexing into the array with its ASCII value.
// 4. Sort the array in descending order based on frequency.
// 5. Build the result string by appending each character `freq` times.
//
// Time Complexity: O(n + k log k), where n = input length, k = 123 (constant)
// Space Complexity: O(1), since array size is fixed

class Solution {
    public String frequencySort(String s) {
        Pair[] vec = new Pair[123];

        // Create an object of class vec
        // Each Pair is of type {char, int} where characters are in order of ASCII table
        // and initially their freq is kept 0
        // Example: (a, 0), (b, 0), (c, 0), ...
        for (int i = 0; i < 123; i++) {
            vec[i] = new Pair((char) i, 0);
        }

        // Now, we'll increase the freq of the characters present in the input string
        for (char ch : s.toCharArray()) {
            vec[ch].freq++;
        }

        // Now, we'll sort all character-frequency pairs in decreasing order of freq
        Arrays.sort(vec, (a, b) -> b.freq - a.freq);

        // Create a StringBuilder to build our result string
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 123; i++) {
            if (vec[i].freq > 0) {
                for (int j = 0; j < vec[i].freq; j++) {
                    result.append(vec[i].ch);
                }
            }
        }

        return result.toString();
    }

    public class Pair {
        char ch;
        int freq;

        Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }
}

// -----------------------------------------------------------------------------------
// Approach 2: HashMap + Sorting Entry List
// -----------------------------------------------------------------------------------
// 1. Use HashMap<Character, Integer> to count frequency of each character.
// 2. Convert map.entrySet() to a list.
// 3. Sort the list by frequency in descending order.
// 4. Build the output string by appending each character `freq` times.
//
// Time Complexity: O(n log k), where n = input length, k = number of unique characters
// Space Complexity: O(k), for HashMap and result construction

class Solution2 {
    public String frequencySort(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();

        // Step 1: Count frequency of each character
        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Convert to list of entries and sort by frequency
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(freqMap.entrySet());
        entryList.sort((a, b) -> b.getValue() - a.getValue());

        // Step 3: Build the output string
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : entryList) {
            char ch = entry.getKey();
            int freq = entry.getValue();
            for (int i = 0; i < freq; i++) {
                result.append(ch);
            }
        }

        return result.toString();
    }
}
