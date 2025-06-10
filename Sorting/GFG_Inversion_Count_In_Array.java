// GFG – Inversion Count in Array
// https://www.geeksforgeeks.org/counting-inversions/

// Problem:
// Given an array of integers arr[], find the Inversion Count in the array.
// Two elements arr[i] and arr[j] form an inversion if arr[i] > arr[j] and i < j.
//
// Inversion Count indicates how far the array is from being sorted.
// If the array is sorted, the inversion count is 0.
// If the array is sorted in reverse order, the count is maximum.
//
// Examples:
// Input: arr[] = [2, 4, 1, 3, 5] → Output: 3
// Input: arr[] = [2, 3, 4, 5, 6] → Output: 0
// Input: arr[] = [10, 10, 10] → Output: 0
//
// Constraints:
// 1 ≤ arr.length ≤ 10^5
// 1 ≤ arr[i] ≤ 10^4

// Approach:
// Use Merge Sort to count inversions efficiently in O(n log n) time.
// During the merge step, whenever an element from the right array is smaller,
// it contributes to inversions equal to the number of remaining elements in the left array.

class Solution {

    static int mergeSort(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0, inversionCount = 0;

        while (i <= mid && j <= end) {
            if (arr[i] > arr[j]) {
                temp[k++] = arr[j++];
                inversionCount += (mid - i + 1);
            } else {
                temp[k++] = arr[i++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= end) temp[k++] = arr[j++];

        for (int idx = 0; idx < temp.length; idx++) {
            arr[start + idx] = temp[idx];
        }

        return inversionCount;
    }

    static int merge(int[] arr, int start, int end) {
        if (start > end){
            int mid = start + (end - start) / 2;

            int leftCount = merge(arr, start, mid);
            int rightCount = merge(arr, mid + 1, end);

            int mergeCount = mergeSort(arr, start, mid, end);

            return leftCount + rightCount + mergeCount;
        }
        return 0;
    }

    static int inversionCount(int[] arr) {
        return merge(arr, 0, arr.length - 1);
    }
}
