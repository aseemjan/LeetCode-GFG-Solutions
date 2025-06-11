// GFG – Sorting Elements of an Array by Frequency
// https://practice.geeksforgeeks.org/problems/sorting-elements-of-an-array-by-frequency/0

// Problem:
// Given an array A[] of integers, sort the array according to the frequency of elements.
// Elements with higher frequency come first.
// If frequencies of two elements are same, the smaller number comes first.
//
// Constraints:
// 1 ≤ T ≤ 70
// 1 ≤ N ≤ 130
// 1 ≤ Ai ≤ 60
//
// Example:
// Input:
// 2
// 5
// 5 5 4 6 4
// 5
// 9 9 9 2 5
//
// Output:
// 4 4 5 5 6
// 9 9 9 2 5
//
// Explanation:
// Testcase1: 5 and 4 both have frequency 2, but 4 < 5 → 4 4 5 5, then 6
// Testcase2: 9 occurs 3 times, then 2 and 5 once each → 9 9 9 2 5

// Approach:
// 1. Build frequency map for each test case.
// 2. Copy elements to a list for sorting.
// 3. Use custom comparator:
//    - If frequencies differ → sort by frequency descending.
//    - If frequencies equal → sort by value ascending.
// 4. Print the sorted list.
// Time Complexity: O(N log N) per test case
// Space Complexity: O(N) per test case

import java.util.*;

class GFG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt(); // Number of test cases

        for (int i = 0; i < T; i++) {
            int N = sc.nextInt(); // Size of array
            int[] arr = new int[N];
            Map<Integer, Integer> freq = new HashMap<>();

            // Step 1: Build frequency map
            for (int j = 0; j < N; j++) {
                arr[j] = sc.nextInt();
                freq.put(arr[j], freq.getOrDefault(arr[j], 0) + 1);
            }

            // Step 2: Copy elements to a list
            List<Integer> list = new ArrayList<>();
            for (int num : arr) {
                list.add(num);
            }

            // Step 3: Sort using lambda comparator
            Collections.sort(list, (a, b) -> {
                int freqA = freq.get(a);
                int freqB = freq.get(b);
                if (freqA != freqB) {
                    return freqB - freqA; // Higher frequency first
                } else {
                    return a - b; // If same frequency, smaller number first
                }
            });

            // Step 4: Output the result
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}

//////////////////////////////////////////////////////
// 👇 BELOW ARE THE CONCEPTUAL EXPLANATIONS & FAQs 👇 //
//////////////////////////////////////////////////////

/*
📌 How does the comparator work?

Collections.sort(list, (a, b) -> {
    int freqA = freq.get(a);
    int freqB = freq.get(b);
    if (freqA != freqB) {
        return freqB - freqA; // Higher frequency first
    } else {
        return a - b; // If same frequency, smaller number first
    }
});

🔹 If comparator returns negative → a comes before b
🔹 If comparator returns positive → b comes before a

Examples:
- freq[a] = 3, freq[b] = 5 → return 5 - 3 = 2 → b before a
- freq[a] = 5, freq[b] = 5, a = 1, b = 2 → return 1 - 2 = -1 → a before b

---

❓Q: Where does it say to put one number before other if one of them is smaller or greater?

✔️ A: That's defined by how the comparator works:
- Java’s `Collections.sort()` uses your comparator’s return value.
- If the return is **negative**, it keeps the order as `a` before `b`.
- If the return is **positive**, it places `b` before `a`.

---

❓Q: Is there any other way to achieve this without using lambda or anonymous comparator?

✔️ A: Yes. You can manually sort the list using nested loops and swap:

```java
for (int i = 0; i < list.size(); i++) {
    for (int j = 0; j < list.size() - i - 1; j++) {
        int a = list.get(j), b = list.get(j + 1);
        int freqA = freq.get(a), freqB = freq.get(b);
        if (freqA < freqB || (freqA == freqB && a > b)) {
            list.set(j, b);
            list.set(j + 1, a);
        }
    }
}
