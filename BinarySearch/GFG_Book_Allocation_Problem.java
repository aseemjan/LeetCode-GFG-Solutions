// LeetCode/GFG – Book Allocation Problem
// https://practice.geeksforgeeks.org/problems/allocate-minimum-number-of-pages/0

// Problem:
// You are given an array arr[] of integers, where each element arr[i] represents the number of pages in the ith book.
// You also have an integer k representing the number of students.
// The task is to allocate books to each student such that:
//   - Each student receives at least one book.
//   - Each student is assigned a contiguous sequence of books.
//   - No book is assigned to more than one student.
// The objective is to minimize the maximum number of pages assigned to any student.

// Note: Return -1 if a valid assignment is not possible. Allocation must be in contiguous order.

// Examples:
// Input: arr[] = [12, 34, 67, 90], k = 2
// Output: 113
// Explanation: Allocation can be done in following ways:
// [12] and [34, 67, 90] -> Max = 191
// [12, 34] and [67, 90] -> Max = 157
// [12, 34, 67] and [90] -> Max = 113 (minimum possible maximum)

// Input: arr[] = [15, 17, 20], k = 5
// Output: -1

// Input: arr[] = [22, 23, 67], k = 1
// Output: 112

// Constraints:
// 1 <= arr.size() <= 10^6
// 1 <= arr[i] <= 10^3
// 1 <= k <= 10^3

// Time Complexity: O(n * log(sum(arr[])))
// Space Complexity: O(1)

class Solution {

    //Althought Its A Helper Function But Stores The Crux Of Our Code
    public static boolean isValid(int[] arr, int n, int k, int maxPages){
        int student = 1, numberOfPages = 0;

        for(int i = 0; i < n; i++){
            if(numberOfPages + arr[i] <= maxPages){
                numberOfPages += arr[i];
            }else{
                student++;
                numberOfPages = arr[i];

                if(student > k){
                    return false;
                }
            }
        }
        return true;
    }

    public static int findPages(int[] arr, int k) {
        // code here
        int n = arr.length, result = -1;

        //If No. of Students is More than No. of Books
        if (k > n) return -1;

        //Assigning low and high pointers on the Range of Values
        int low = Integer.MIN_VALUE;
        int high = 0;

        for(int num : arr){
            low =  Math.max(low, num);
            high += num;
        }

        //Binary Search to find the least of the Maximum Pages
        while(low <= high){
            int mid = low + (high - low) / 2;

            if(isValid(arr, n, k, mid)){
                result = mid;
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return result;
    }
}
