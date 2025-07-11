import java.util.*;

public class CatalanNumber {
    public static void main(String[] args){
        int n = 6;
        int res = findWays(n);
        System.out.println(res);
    }
    public static int findWays(int n ){
        if(n % 2 == 1){
            return -1;
        }
        return catalan(n / 2);
    }
    public static int catalan(int n){
        int c = binomialCoeff(2*n, n);

        int res = (c / (n + 1));

        return res;
    }
    public static int binomialCoeff(int n, int k){
        int res = 1;
        if(k > n - k){
            k = n - k;
        }

        for(int i = 0; i < k; i++){
            res *= (n - i);
            res /= (i + 1);
        }

        return res;
    }
    /*
===========================
🌟 CATALAN NUMBERS Q&A SET
===========================

🔹 Q1: What is the Catalan number formula?
------------------------------------------
Catalan(n) = C(2n, n) / (n + 1)

Where:
- C(2n, n) is the binomial coefficient = (2n)! / (n! * n!)

🔹 Q2: What does Catalan number represent in parentheses problems?
------------------------------------------------------------------
It counts the number of valid balanced parentheses expressions of length n.
Note: n must be even.

🔹 Q3: Why must n be even for valid parentheses expressions?
------------------------------------------------------------
Each '(' must be matched with a ')'. So if n is odd, you can't form valid expressions.

🔹 Q4: Why do we call catalan(n / 2) in code?
----------------------------------------------
Because n is the total number of brackets.
Valid expressions of length n have n/2 pairs of '()'.

🔹 Q5: Why do we compute C(2n, n) / (n + 1) ?
---------------------------------------------
It’s the closed-form formula of Catalan numbers derived combinatorially.

🔹 Q6: Why do we optimize binomialCoeff() with:
      if (k > n - k) k = n - k;
------------------------------------------------
Because C(n, k) = C(n, n - k). Using the smaller k reduces the number of iterations
and avoids overflow.

🔹 Q7: Can k > n - k ever happen in Catalan computation?
---------------------------------------------------------
No. Because we always call binomialCoeff(2n, n), where n == k,
so k == n - k → optimization never triggers.

🔹 Q8: Can this optimization be useful elsewhere?
-------------------------------------------------
Yes! For any general binomial coefficient computation like C(10, 8),
this optimization converts it to C(10, 2) which is faster.

🔹 Q9: How does this code compute C(n, k)?
------------------------------------------
int res = 1;
for (int i = 0; i < k; i++) {
    res *= (n - i);   // Multiply numerator
    res /= (i + 1);   // Divide denominator
}

This builds:
C(n, k) = [n * (n - 1) * ... * (n - k + 1)] / [1 * 2 * ... * k]

🔹 Q10: Why does the numerator go down to (n - k + 1)?
-------------------------------------------------------
Because we cancel (n - k)! from the factorial form of n!, leaving only
k terms starting from n down to (n - k + 1)

🔹 Q11: Example:
---------------
C(10, 7) → optimization applied → C(10, 3)
res = 1
res *= 10 / 1 = 10
res *= 9 / 2 = 45
res *= 8 / 3 = 120

So, C(10, 7) = 120

🔹 Q12: Full Catalan number formula implementation?
---------------------------------------------------
Catalan(n) = binomialCoeff(2 * n, n) / (n + 1)

*/

}
