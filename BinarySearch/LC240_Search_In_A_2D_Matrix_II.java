// LeetCode 240 – Search a 2D Matrix II
// https://leetcode.com/problems/search-a-2d-matrix-ii/

// Problem:
// Write an efficient algorithm that searches for a value `target` in an m x n integer matrix `matrix`.
// This matrix has the following properties:
// - Integers in each row are sorted in ascending from left to right.
// - Integers in each column are sorted in ascending from top to bottom.

// Example 1:
// Input: matrix = [
//   [1, 4, 7, 11, 15],
//   [2, 5, 8, 12, 19],
//   [3, 6, 9, 16, 22],
//   [10,13,14,17,24],
//   [18,21,23,26,30]
// ], target = 5
// Output: true

// Example 2:
// Input: matrix = [
//   [1, 4, 7, 11, 15],
//   [2, 5, 8, 12, 19],
//   [3, 6, 9, 16, 22],
//   [10,13,14,17,24],
//   [18,21,23,26,30]
// ], target = 20
// Output: false

// Constraints:
// - m == matrix.length
// - n == matrix[i].length
// - 1 <= m, n <= 300
// - -10^9 <= matrix[i][j], target <= 10^9
// - Integers in each row are sorted in ascending from left to right.
// - Integers in each column are sorted in ascending from top to bottom.

// Approach:
// 1. Start from the bottom-left corner of the matrix.
// 2. At each step:
//    - If current element is equal to the target, return true.
//    - If current element is greater than the target, move up.
//    - If current element is less than the target, move right.
// 3. This works because:
//    - Every element to the right of current is larger.
//    - Every element above is smaller.
//    - So at each step we eliminate a row or column.

// Time Complexity: O(m + n)
// Space Complexity: O(1)

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Start at bottom-left
        int i = m - 1;
        int j = 0;

        while (i >= 0 && j < n) {
            int curr = matrix[i][j];

            if (curr == target) {
                return true;
            } else if (curr > target) {
                i--; // Move up
            } else {
                j++; // Move right
            }
        }

        return false;
    }
}
