// Sum of All Submatrices of a Square Matrix
// Problem: Given a square matrix of size n x n, calculate the sum of all possible submatrices.
// Approach 1: Naive - Brute-force O(n^6) [see below for explanation]
// Approach 2: Contribution-based O(n^2)

// Example:
// Input:
// matrix = {
//   {1, 2, 3},
//   {4, 5, 6},
//   {7, 8, 9}
// }
// Output: 500

public class MatrixSubmatrixSum {

    // Optimized Approach using Contribution technique: O(n^2)
    public static int matrixSum(int[][] matrix) {
        int n = matrix.length;
        int totalSum = 0;

        // Traverse each element and calculate its contribution
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // (i+1) choices for top-left row, (j+1) for top-left col
                int topLeft = (i + 1) * (j + 1);

                // (n-i) choices for bottom-right row, (n-j) for bottom-right col
                int bottomRight = (n - i) * (n - j);

                // Total number of submatrices containing matrix[i][j]
                int totalSubmatrices = topLeft * bottomRight;

                // Contribution of current element
                totalSum += matrix[i][j] * totalSubmatrices;
            }
        }

        return totalSum;
    }

    // Brute-force Naive approach: O(n^6)
    // Calculates the sum of all submatrices by generating them explicitly
    public static int naiveMatrixSum(int[][] matrix) {
        int n = matrix.length;
        int totalSum = 0;

        // Iterate over all possible top-left corners
        for (int i1 = 0; i1 < n; i1++) {
            for (int j1 = 0; j1 < n; j1++) {

                // Iterate over all possible bottom-right corners
                for (int i2 = i1; i2 < n; i2++) {
                    for (int j2 = j1; j2 < n; j2++) {

                        // For each submatrix defined by (i1,j1) to (i2,j2), sum its elements
                        for (int x = i1; x <= i2; x++) {
                            for (int y = j1; y <= j2; y++) {
                                totalSum += matrix[x][y];
                            }
                        }
                    }
                }
            }
        }

        return totalSum;
    }
}
