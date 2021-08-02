public class Revision {
    public static void printIncreasing(int a, int b) {
        if (a > b) return;

        System.out.println(a);
        printIncreasing(a+1, b);
    }
    
    public static void printDecreasing(int a, int b) {
        if (a > b) return;

        printDecreasing(a+1, b);
        System.out.println(a);
    }

    public static void printIncreasingDecreasing(int a, int b) {
        if (a > b) return;

        System.out.println(a);
        printIncreasingDecreasing(a+1, b);
        System.out.println(a);
    }

    public static void oddEven(int a, int b) {
        if (a > b) return;
        if (a%2 != 0) System.out.println(a);
        oddEven(a+1, b);
        if (a%2 == 0) System.out.println(a);
    }

    public static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n-1);
    }

    public static int power(int a, int b) {
        if (b == 0) return 1;
        return a * power(a, b-1);
    }

    public static int powerBtr(int a, int b) {
        if (b == 0) return 1;

        int ans = power(a, b/2);
        ans *= ans;

        return b%2 == 0 ? ans : ans * a;
    }

    public static void printArray(int[] arr, int idx) {
        if (idx == arr.length) return;
        
        System.out.println(arr[idx]);
        printArray(arr, idx+1);
    }

    public static void printArrayReverse(int[] arr, int idx) {
        if (idx == arr.length) return;

        printArrayReverse(arr, idx+1);
        System.out.println(arr[idx]);
    }

    public static int maximum(int[] arr, int idx) {
        if (idx == arr.length) return Integer.MIN_VALUE;

        int curmax = maximum(arr, idx+1);
        int ans = Math.max(curmax, arr[idx]);

        return ans;
    }

    public static int minimum(int[] arr, int idx) {
        if (idx == arr.length) return Integer.MAX_VALUE;

        int curmin = minimum(arr, idx+1);
        int ans = Math.min(curmin, arr[idx]);

        return ans;
    }

    public static boolean find(int[] arr, int data, int idx) {
        if (idx == arr.length) return false;
        
        if (arr[idx] == data) return true;
        boolean ans = find(arr, data, idx+1);

        return ans;
    }

    public static int firstIndex(int[] arr, int data, int idx) {
        if (idx == arr.length) return -1;

        if (arr[idx] == data) return idx;
        int ans = firstIndex(arr, data, idx+1);

        return ans;
    }

    public static int lastIndex(int[] arr, int data, int idx) {
        if (idx == arr.length) return -1;

        int ans = lastIndex(arr, data, idx+1);
        if (arr[idx] == data && ans == -1) return idx;

        return ans;
    }

    // public static int[] allIndex(int[] arr, int data, int idx) {

    // }
    
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 2, -10, 4, 4, 50, 4, 5};

        // printIncreasing(1, 5);
        // printDecreasing(1, 5);
        // printIncreasingDecreasing(1, 5);
        // oddEven(1, 5);
        // System.out.println(factorial(5));
        // System.out.println(power(2, 5));
        // System.out.println(powerBtr(2, 5));
        // printArray(arr, 0);
        // printArrayReverse(arr, 0);
        System.out.println(maximum(arr, 0));
        System.out.println(minimum(arr, 0));
        // System.out.println(find(arr, -1, 0));
        // System.out.println(firstIndex(arr, 4, 0));
        // System.out.println(lastIndex(arr, 2, 0));
    }
}