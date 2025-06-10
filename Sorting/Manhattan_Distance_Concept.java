// Manhattan Distance – Sum of All Pairwise Manhattan Distances
// Custom Problem

// Problem:
// Given `n` points on a 2D plane, represented as integer coordinates (x, y),
// return the **sum of Manhattan distances** between all pairs of points.

// Manhattan Distance between points (x1, y1) and (x2, y2) is defined as:
// |x1 - x2| + |y1 - y2|

// Approach:
// Manhattan Distance is **additive in each dimension**, so we can break it into two parts:
// - Total contribution from x-coordinates
// - Total contribution from y-coordinates

// For each dimension separately:
// 1. Sort the coordinates.
// 2. For each coordinate at index `i` in sorted array, its total contribution is:
//    coord[i] * i - sum of all coord[0..i-1]

// Why this works (Analogy):
// Think of the distance calculation like a tug-of-war between left and right.
// Every element on the right side pulls positively (coord[i] * i),
// and everything left of it contributes negatively (the sum of smaller coords).

// Time Complexity: O(n log n) for sorting
// Space Complexity: O(n) for storing x[] and y[]

import java.util.*;

class Solution {
    public long totalManhattanDistance(int[][] points) {
        int n = points.length;

        int[] x = new int[n];
        int[] y = new int[n];

        // Extract x and y coordinates into separate arrays
        for (int i = 0; i < n; i++) {
            x[i] = points[i][0];
            y[i] = points[i][1];
        }

        Arrays.sort(x);
        Arrays.sort(y);

        return computeSum(x) + computeSum(y);
    }

    // Helper function to compute 1D Manhattan sum
    private long computeSum(int[] coord) {
        long sum = 0;
        long prefixSum = 0;

        for (int i = 0; i < coord.length; i++) {
            sum += ((long) coord[i] * i) - prefixSum;
            prefixSum += coord[i];
        }

        return sum;
    }

    // Driver method to test the example
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] points = {
                {1, 2},   // P0
                {2, 0},   // P1
                {3, 1},   // P2
                {4, 3},   // P3
                {5, 4}    // P4
        };

        long result = sol.totalManhattanDistance(points);
        System.out.println("Total Manhattan Distance: " + result);  // Output: 44
    }
}
