// LeetCode 378 – Kth Smallest Element in a Sorted Matrix
// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

// Problem:
// Given an n x n matrix where each of the rows and columns is sorted in ascending order,
// return the kth smallest element in the matrix.

// Note that it is the kth smallest element in the sorted order, not the kth distinct element.

// You must find a solution with a memory complexity better than O(n^2).

// Example:
// Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
// Output: 13

// Constraints:
// n == matrix.length == matrix[i].length
// 1 <= n <= 300
// -10^9 <= matrix[i][j] <= 10^9
// All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
// 1 <= k <= n^2

// Approach:
// Use binary search on the value range in the matrix.
// 1. Set low = smallest element, high = largest element.
// 2. For each mid, count how many elements are <= mid.
// 3. If count < k, move right (low = mid + 1).
// 4. If count >= k, move left (high = mid).
// This ensures we converge to the k-th smallest number efficiently.

// Time Complexity: O(n * log(max - min))
// Space Complexity: O(1)

class Solution {
    public int kthSmallest(int[][] matrix, int k) {

        int m = matrix.length;
        int n = matrix[0].length;

        //Binary Search On Range
        int low = matrix[0][0];
        int high = matrix[m - 1][n - 1];

        while(low < high){
            int mid = low + (high - low) / 2;

            int count = countLessEqual(matrix, mid);

            if(count < k){ // If the count of elements less than mid is less than 'k', then we search for element more than mid.
                low = mid + 1;
            }else {
                high = mid; // if the count is >= k We’ve found at least k numbers ≤ mid, so mid might be the answer.
                // But maybe there’s a smaller number that’s still valid → search left
            }
        }
        return low;
    }
    /*

    ## For the below helper function ##

    Q - How the count is "count += row + 1" ?
    A - You're at matrix[row][col], and you know that this column (col) is sorted from top to bottom.
        So all values in this column from row 0 to current row are ≤ mid.
        That means:
        matrix[0][col], matrix[1][col], ..., matrix[row][col] are all ≤ mid.

    */
    public int countLessEqual(int[][] matrix, int mid){
        int M = matrix.length;
        int N = matrix[0].length;

        int row = M - 1;
        int col = 0;
        int count = 0;

        while(row >= 0 && col < N){
            if(matrix[row][col] <= mid){
                count += row + 1;
                col++; // We're searching for the next bigger value
            }else{
                row--; // if "matrix[row][col] > mid"  we search for the value lesser than this.
            }
        }
        return count;
    }
}
