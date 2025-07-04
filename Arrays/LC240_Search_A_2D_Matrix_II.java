// LeetCode 240 – Search a 2D Matrix II
// https://leetcode.com/problems/search-a-2d-matrix-ii/

// Problem:
// Write an efficient algorithm that searches for a value target in an m x n integer matrix.
// This matrix has the following properties:
// - Integers in each row are sorted in ascending from left to right.
// - Integers in each column are sorted in ascending from top to bottom.

// Example 1:
// Input: matrix = [
//     [1, 4, 7, 11, 15],
//     [2, 5, 8, 12, 19],
//     [3, 6, 9, 16, 22],
//     [10, 13, 14, 17, 24],
//     [18, 21, 23, 26, 30]
// ], target = 5
// Output: true

// Example 2:
// Input: matrix = same as above, target = 20
// Output: false

// Constraints:
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 300
// -10^9 <= matrix[i][j], target <= 10^9
// Each row is sorted left to right, each column is sorted top to bottom

// Approach:
// 1. Start from the bottom-left corner of the matrix.
// 2. At each step:
//    - If the current value equals the target, return true.
//    - If the current value is greater than the target, move up (decrease row index).
//    - If the current value is smaller than the target, move right (increase column index).
// 3. This works because the matrix is sorted row-wise and column-wise.
// 4. Time Complexity: O(m + n)
// 5. Space Complexity: O(1)

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;       // Total rows
        int n = matrix[0].length;    // Total columns

        // 'i' is kept at the last row
        int i = m - 1;

        // 'j' is kept at the first column
        int j = 0;

        // Start the search from bottom-left corner
        while (i >= 0 && j < n) {
            int curr = matrix[i][j];

            if (curr == target) {
                return true; // Target found
            } else if (curr > target) {
                i--; // Move up
            } else {
                j++; // Move right
            }
        }

        // Target not found
        return false;
    }
}
