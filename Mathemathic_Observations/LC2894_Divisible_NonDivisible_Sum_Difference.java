/*
Leetcode 2894 : Divisible Non-Divisible Sum Difference
https://leetcode.com/problems/divisible-and-non-divisible-sums-difference/description/

Problem :
You are given positive integers n and m.
num1 : The sum of all integers in the range [1, n] (both inclusive) that are not divisible by m.
num2 : The sum of all integers in the range [1, n] (both inclusive) that are divisible by m.
return num1 - num2


**We're not gonna write the iterative-simulation based approach. Only the Mathemathical Approach will be described.**


Idea & Observations:
    ##
        Num2 : Elements in Range which ARE Divisible

        Purely from math standpoint --> numbers divible by 'm' would be : "m, 2m, 3m, ..... , km"

        num2 = m * (1 + 2 + 3 + .... + k)     <--(Sum of k Natural numbers)
             = m * (k * (k + 1) / 2)

        Now, WHAT is 'k' ? : Its  -->  "How many Numbers from '1 to n' that can be divisible by 'm' "

        Simple formula for 'k' is : k =  n / m

        num1 = TotalSum - (DivisibleSum i.e. num2)
    ##

TC - O(1)
SC - O(1)

 */

class Solution {
    public int differenceOfSums(int n, int m) {

        int num1 = 0, num2 = 0;

        int totalSum = n * (n + 1) / 2;

        //As per explanation, Substituting all the values

        int k = n / m;

        num2 = m * (k * (k + 1) / 2);

        num1 = totalSum - num2;

        return num1 - num2;

    }
}
