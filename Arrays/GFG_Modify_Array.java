// Problem: Rearrange array so that if arr[i] = j, then arr[j] = i
// All elements are in the range [0, n-1] and are unique (i.e., it's a valid mapping)

// GFG Link: https://www.geeksforgeeks.org/dsa/rearrange-array-arrj-becomes-arri-j/

// Approaches:
// 1. Extra Array (Naive, O(n) space):
//      - Create a new array 'res' of size n.
//      - For every i: res[arr[i]] = i
//      - Copy 'res' back to arr.
//      - Very readable but uses extra space.
//
// 2. Cyclic Approach (O(n) time, O(n) space):
//      - Use a visited[] array to avoid cycles.
//      - Traverse cycles and place i at arr[j] carefully.
//
// 3. Modulo-n Encoding Approach (O(n) time, O(1) space):
//      - Encode both old and new values into a single integer using modulo & multiplication.

public class ReverseMapping {

    // 👉 Approach 1: Modulo-n Encoding Trick (In-place, No Extra Space)
    public static void rearrangeModuloN(int[] arr) {
        int n = arr.length;

        // Step 1: Encode both old and new value into arr[j]
        for (int i = 0; i < n; i++) {
            // arr[i] % n ensures we get the *original* value even after modification
            // We add i * n to arr[arr[i] % n] to encode that arr[j] = i
            arr[arr[i] % n] += i * n;
        }

        // Step 2: Extract the new value by dividing each element by n
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] / n;
        }
    }

    // 👉 Approach 2: Cyclic Replacement with Visited Array
    public static void rearrangeCyclic(int[] arr) {
        int n = arr.length;
        boolean[] visited = new boolean[n]; // Keeps track of which elements we've modified

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int val = i;
                int prev = i;

                // Follow the cycle until we come back to a visited element
                while (!visited[val]) {
                    int next = arr[val];    // Save original value before overwriting
                    arr[val] = prev;        // Place current index at the value position
                    visited[val] = true;    // Mark this index as processed
                    prev = val;             // Update previous for next step
                    val = next;             // Move to next node in the cycle
                }
            }
        }
    }
}
