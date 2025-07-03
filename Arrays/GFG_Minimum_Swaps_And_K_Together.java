// GFG – Minimum Swaps and K Together
// https://www.geeksforgeeks.org/minimum-swaps-required-bring-elements-less-equal-k-together/

// Problem:
// Given an array `arr[]` and a number `k`. You can swap any two elements any number of times.
// Find the minimum number of swaps required to bring all the numbers ≤ k together in a contiguous subarray.

// Examples:
//
// Input:  arr[] = [2, 1, 5, 6, 3], k = 3
// Output: 1
// Explanation: To bring 2, 1, 3 together, swap index 2 with 4 → arr = [2, 1, 3, 6, 5]
//
// Input:  arr[] = [2, 7, 9, 5, 8, 7, 4], k = 6
// Output: 2
// Explanation: To bring 2, 5, 4 together, swap index 2 with 6, and 4 with 5
//
// Input:  arr[] = [2, 4, 5, 3, 6, 1, 8], k = 6
// Output: 0
// Explanation: All elements ≤ k are already together

// Constraints:
// 1 ≤ arr.length ≤ 10^6
// 1 ≤ arr[i] ≤ 10^6
// 1 ≤ k ≤ 10^6

// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    // Function for finding maximum and value pair
    int minSwap(int[] arr, int k) {
        // Complete the function
        int n = arr.length;

        // Counting the Good Numbers in Array as in numbers <= k
        // This is going to be our window size
        int countGood = 0;
        for(int num : arr){
            if(num <= k){
                countGood++;
            }
        }

        //Edge Case : If All elements are good OR No elements are good
        if(countGood == 0 || countGood == n){
            return 0;
        }

        // We'll count the Number of Bad elements in first window
        // We counted bad elements in the first window separately because :
        // 1. We can start with Index 1 from in the main loop, and we'd have to back check i.e. do [start - 1]
        // 2. It reduces the margin of error, also makes it look cleaner
        int bad = 0;
        for(int i = 0; i < countGood; i++){
            if(arr[i] > k){
                bad++;
            }
        }

        // Now, we'll start iterating in our array counting the remaining bad elements
        // For us to swap them
        // Also, we'll keep a track of the Minimum Bad elements , as per our question requirements

        int minSwaps = bad;
        for(int start = 1; start <= n - countGood; start++){ // The last Position of Start pointer should not exceed n - countGood. Since only this way,
            //End pointer will be pointing at the last index of the array.

            int end = start + countGood - 1; // End will be at the last of the Window Size

            if(arr[start - 1] > k){ // Reduce bad count if the leaving index element is > k
                bad--;
            }

            if(arr[end] > k){ // Increase the Bad count if the upcoming index elements > k
                bad++;
            }

            minSwaps = Math.min(minSwaps, bad);
        }
        return minSwaps;
    }
}
