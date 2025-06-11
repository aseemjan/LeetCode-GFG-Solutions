// LeetCode 668 – Kth Smallest Number in Multiplication Table
// https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/

// Problem:
// You're given a multiplication table of size m x n.
// Find the k-th smallest number in this table in sorted order.
// You must do this in better than O(m * n) time.

// Example:
// Input: m = 3, n = 3, k = 5
// Output: 3
// Explanation: The table is:
// 1 2 3
// 2 4 6
// 3 6 9
// Sorted elements: [1,2,2,3,3,4,6,6,9] → 5th smallest is 3

// Constraints:
// 1 <= m, n <= 3 * 10^4
// 1 <= k <= m * n

class Solution {

    // Step 1: Count how many numbers are ≤ candidateValue in the m x n multiplication table
    public int count(int m, int n, int candidateValue){
        int total = 0;

        for(int i = 1; i <= m; i++){
            // In row i → values are: i, 2i, 3i, ..., ni
            // We count how many of them are ≤ candidateValue using:
            //    candidateValue / i → gives how many multiples of i fit ≤ candidateValue
            // But there are only 'n' elements in each row, so we take the minimum of both
            total += Math.min(candidateValue / i, n);
        }

        return total;
    }

    // Step 2: Binary Search on the value range [1, m * n]
    public int findKthNumber(int m, int n, int k) {
        int low = 1, high = m * n;
        int answer = -1;

        while(low <= high){
            int mid = low + (high - low) / 2;

            int elementCount = count(m, n, mid);

            if(elementCount >= k){
                answer = mid;        // mid might be the answer, but let's check smaller values
                high = mid - 1;
            }else {
                low = mid + 1;       // Too few numbers ≤ mid, try higher
            }
        }
        return answer;
    }
}

/*

📌 Why we use Math.min(candidateValue / i, n):

→ The i-th row of the multiplication table contains values:
     i, 2i, 3i, ..., n*i

→ To count how many numbers in this row are ≤ candidateValue:
     candidateValue / i  → gives how many multiples of i fit into candidateValue

→ But we can’t count more than 'n' numbers in any row (because there are only 'n' columns),
   so we take:
     Math.min(candidateValue / i, n)

🔍 Example:
Let’s say m = 3, n = 3, candidateValue = 5

Row 1: [1, 2, 3] → 5 / 1 = 5 → min(5, 3) = 3
Row 2: [2, 4, 6] → 5 / 2 = 2 → min(2, 3) = 2
Row 3: [3, 6, 9] → 5 / 3 = 1 → min(1, 3) = 1

Total count of elements ≤ 5 = 3 + 2 + 1 = 6

*/
