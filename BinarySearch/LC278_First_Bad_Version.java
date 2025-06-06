/*
Leetcode 278: First Bad Version
Link: https://leetcode.com/problems/first-bad-version/

You are a product manager and currently leading a team to develop a new product. 
Unfortunately, the latest version of your product fails the quality check. 
Since each version is developed based on the previous version, all the versions 
after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, 
which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which returns whether version is bad. 
Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example 1:
Input: n = 5, bad = 4
Output: 4
Explanation:
call isBadVersion(3) -> false  
call isBadVersion(5) -> true  
call isBadVersion(4) -> true  
Then 4 is the first bad version.

Example 2:
Input: n = 1, bad = 1
Output: 1

Constraints:
1 <= bad <= n <= 2^31 - 1
*/

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int low = 0, high = n;
        int result = -1; // Store the potential first bad version

        // We use 'low <= high' because we want to include both boundaries in our search.
        // If we used 'low < high', we might miss the case where low == high is the bad version.
        while (low <= high) {
            // To prevent integer overflow, use: mid = low + (high - low) / 2
            int mid = low + (high - low) / 2;

            if (isBadVersion(mid)) {
                // If mid is bad, this could be the first bad version,
                // but there might be an earlier bad version, so we go left.
                result = mid;
                high = mid - 1;
            } else {
                // If mid is not bad, then all previous versions are good,
                // so the first bad one must be on the right.
                low = mid + 1;
            }
        }

        return result;
    }
}
