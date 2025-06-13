// LeetCode 878 – Nth Magical Number
// https://leetcode.com/problems/nth-magical-number/

// Problem:
// A positive integer is magical if it is divisible by either a or b.
// Given the three integers n, a, and b, return the nth magical number.
// Since the answer may be very large, return it modulo 10^9 + 7.

// Example:
// Input: n = 4, a = 2, b = 3
// Output: 6

// Constraints:
// 1 <= n <= 10^9
// 2 <= a, b <= 4 * 10^4

class Solution {
    public int nthMagicalNumber(int n, int a, int b) {

        long A = a;
        long B = b;

        //We'll keep : 1. low = minimum of a & b   &&  2. high = n * min(a,b). Because its the max of the range
        //where we can find the element.
        long low = (long) Math.min(a, b);
        long high = (long) n * low;

        //Declaring MOD
        long mod = (long) (Math.pow(10,9) + 7);

        //Now, 'A' stores GCD of a & b. To calculate the LCM
        while(B > 0){
            long temp = A;
            A = B;
            B = temp % A;
        }

        //Formula of LCM(a,b) = (a * b) / GCD(a,b)
        long lcm = (a * b) / A;

        //Declaring the result variable
        long ans = 0;

        //Calculating the value using Binary Search
        while(low <= high){
            long mid = low + (high - low) / 2;

            long val = (mid / a) + (mid / b) - (mid / lcm);

            /*
            Ques. What does 'val' stores (in plain english) ?
            Ans.  It tells us "How many magical numbers are there upto 'mid' i.e. '<=mid'".

                Example : -
                mid / a → number of multiples of a ≤ mid
                → e.g. if a = 3 and mid = 10, then multiples of 3 are: 3, 6, 9 → count = 3

                Similiarly for 'b'.


                Ques. Why have we subtracted (mid / lcm) ?
                Ans.
                    Because: some numbers are divisible by both a and b
                    → e.g. if a = 2, b = 3, then 6 is divisible by both
                    → So we’re double-counting numbers like 6, 12, etc.

                    Therefore : mid / lcm → subtract these common multiples to fix the overcount

            */

            if(val >= n){        // If true : “mid is a candidate where the count of magical numbers has reached n or more.”
                ans = mid;       // store the possible answer
                high = mid - 1;  // Try for better answer or lower value of n
            }else{
                low = mid + 1;
            }
        }
        return (int) (ans % mod);
    }
}
