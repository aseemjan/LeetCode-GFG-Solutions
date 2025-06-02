// GFG – Find All Duplicates in an Array
// https://www.geeksforgeeks.org/problems/array-duplicates/0

// Problem:
// Given an array `arr[]` of integers, find all elements that appear more than once in the array.
// Return them in a list. If no elements repeat, return an empty list.

// Constraints:
// 1 <= arr.length <= 10^6
// 0 <= arr[i] <= 10^6

// Approach:
// 1. Sort the array so duplicates are adjacent.
// 2. Traverse the sorted array.
// 3. If adjacent elements are equal and haven't already been added, add them to the result list.
// 4. Use a check: `list.isEmpty() || list.getLast() != arr[i]` to avoid duplicate entries.

// Time Complexity: O(n log n) — due to sorting
// Space Complexity: O(1) extra (excluding output list)

import java.util.*;

class Solution {
    public List<Integer> findDuplicates(int[] arr) {
        List<Integer> list = new ArrayList<>();
        int n = arr.length;

        // Edge case: array with only 1 element has no duplicates
        if (n == 1) return list;

        // Sort the array to group duplicates together
        Arrays.sort(arr);

        // Traverse and collect duplicates
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                // Only add if not already added
                if (list.isEmpty() || list.get(list.size() - 1) != arr[i]) {
                    list.add(arr[i]);
                }
            }
        }

        return list;
    }
}
