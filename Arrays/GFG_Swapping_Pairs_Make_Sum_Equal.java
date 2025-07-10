// Problem:
// Given two arrays a[] and b[], check if there exists a pair (x from a, y from b)
// such that swapping x and y will make the sum of both arrays equal.

// Constraints:
// 1 ≤ a.length, b.length ≤ 10^6
// 1 ≤ a[i], b[i] ≤ 10^3

// Time Constraints: Arrays can be large, so an O(n + m) or O(n log n + m log m) approach is preferred.

import java.util.*;

class Solution {

    // --------------------------------------------------------------------------------
    // ✅ Approach 1: HashSet + Math (Optimal Time: O(n + m), Space: O(m))
    //
    // Mathematical Insight:
    // Let sumA = sum of a[], sumB = sum of b[]
    // Suppose we swap x from a[] and y from b[].
    // After swapping:
    //    newSumA = sumA - x + y
    //    newSumB = sumB - y + x
    //
    // For sums to become equal:
    //    sumA - x + y == sumB - y + x
    // => 2(y - x) == sumB - sumA
    // => y - x = (sumB - sumA) / 2   ← let this be target
    // => y = x + target
    //
    // Goal:
    // For each x in a[], check if y = x + target exists in b[].
    //
    // Edge Case:
    // If (sumB - sumA) is odd → No valid swap can make the sums equal.
    // --------------------------------------------------------------------------------

    public boolean findSwapValuesHashSet(int[] a, int[] b) {
        long sumA = 0, sumB = 0;

        for (int num : a) sumA += num;
        for (int num : b) sumB += num;

        long diff = sumB - sumA;

        // If the difference is odd, we can't divide evenly → no valid swap possible
        if (diff % 2 != 0) return false;

        long target = diff / 2;  // We need y - x = target → y = x + target

        // Add all elements of b[] to a HashSet for quick lookup
        Set<Integer> setB = new HashSet<>();
        for (int num : b) setB.add(num);

        for (int x : a) {
            long y = x + target;
            // Check if y exists in b[]
            if (y >= Integer.MIN_VALUE && y <= Integer.MAX_VALUE && setB.contains((int) y)) {
                return true;
            }
        }

        return false;
    }

    // --------------------------------------------------------------------------------
    // ✅ Approach 2: Sorting + Two Pointers (Time: O(n log n + m log m), Space: O(1))
    //
    // Mathematical Insight:
    // Same as above:
    //    y - x = (sumB - sumA) / 2  →  target
    // Goal: After sorting both arrays, use two pointers to find a pair (x, y)
    // such that b[j] - a[i] == target.
    //
    // Logic:
    // - If b[j] - a[i] < target → move j++
    // - If b[j] - a[i] > target → move i++
    // - If equal → found a valid pair → return true
    //
    // This avoids extra space but takes more time due to sorting.
    // --------------------------------------------------------------------------------

    public boolean findSwapValuesTwoPointers(int[] a, int[] b) {
        long sumA = 0, sumB = 0;
        for (int num : a) sumA += num;
        for (int num : b) sumB += num;

        long diff = sumB - sumA;

        if (diff % 2 != 0) return false;

        long target = diff / 2;

        Arrays.sort(a);
        Arrays.sort(b);

        int i = 0, j = 0;

        while (i < a.length && j < b.length) {
            long delta = (long) b[j] - a[i];

            if (delta == target) {
                return true;  // Found x and y such that y - x == target
            } else if (delta < target) {
                j++;  // Increase y to get closer to target
            } else {
                i++;  // Increase x to reduce delta
            }
        }

        return false;
    }
}
