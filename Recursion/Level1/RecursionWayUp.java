import java.util.*;

public class RecursionWayUp {

    public static int printSS(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        
        int count = 0;  // Have this as a practice, (Count of no of solutions)
        char ch = str.charAt(0);
        String restStr = str.substring(1);
        count += printSS(restStr, ans + ch); // Yes
        count += printSS(restStr, ans);      //No
        
        return count;
    }

    public static int printKPC(String str, String ans, String[] map) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        
        int count = 0; // Have this as a practice, (Count of no of solutions)
        int num = str.charAt(0)-'0';
        String key = map[num];
        String restStr = str.substring(1);
        
        for (int i = 0;i < key.length();i++) {
            count += printKPC(restStr, ans + key.charAt(i), map);
        }
        
        return count;
    }

    public static int printStairPaths(int n, String path) {
        if (n == 0) {
            System.out.println(path);
            return 1;
        }
        
        int count = 0; // Have this as a practice, (Count of no of solutions)
        for (int jump = 1;jump <= 3 && n-jump >= 0;jump++) {
            count+= printStairPaths(n-jump, path + jump);
        }
        
        return count;
    }

    public static int printBoardPath(int n, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int dice = 1;dice <= 6 && n-dice >= 0;dice++) {
            count += printBoardPath(n-dice, ans + dice);
        }

        return count;
    }

    public static int printBoardPath2(int[] arr, int n, String ans) {
        if (n == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int jump : arr) {
            if (n-jump >= 0)
                count += printBoardPath2(arr, n-jump, ans + jump);
        }

        return count;
    }

    public static int printMazePaths(int sr, int sc, int dr, int dc, String ans) {
        if (sr == dr && sc == dc) {
            System.out.println(ans);
            return 1;
        }
        
        int count = 0;
        if (sc+1 <= dc) 
            count += printMazePaths(sr, sc+1, dr, dc, ans + "h");
        if (sc+1 <= dc && sr+1 <= dr)
            count += printMazePaths(sr+1, sc+1, dr, dc, ans + "d");
        if (sr+1 <= dr)
            count += printMazePaths(sr+1, sc, dr, dc, ans + "v");

        return count;
    }

    public static int printMazePaths2(int sr, int sc, int dr, int dc, String ans) {
        if (sr == dr && sc == dc) {
            System.out.println(ans);
            return 1;
        }
        
        int count = 0;
        for (int jump = 1;sc+jump <= dc;jump++)
            count += printMazePaths2(sr, sc+jump, dr, dc, ans + "h" + jump);
        for (int jump = 1;sc+jump <= dc && sr+jump <= dr;jump++)
            count += printMazePaths2(sr+jump, sc+jump, dr, dc, ans + "d" + jump);
        for (int jump = 1;sr+jump <= dr;jump++)
            count += printMazePaths2(sr+jump, sc, dr, dc, ans + "v" + jump);

        return count;
    }

    public static int printMazePath_(int i, int j, String ans) {
        if (i < 0 || j < 0) return 0;
        if (i == 0 && j == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        count += printMazePath_(i-1, j, ans + "V");
        count += printMazePath_(i, j-1, ans + "H");
        count += printMazePath_(i-1, j-1, ans + "D");

        return count;
    }

    // With duplicates
    public static int printPermutationsWithDuplicates(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0;i < str.length();i++) {
            String nextStr = str.substring(0, i) + str.substring(i+1, str.length());
            char ch = str.charAt(i);
            count += printPermutationsWithDuplicates(nextStr, ans + ch);
        }

        return count;
    }

    // Without duplicates
    public static int printPermutationsWithoutDuplicates(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        char p = '$';      // Some dummy char
        // p -> prev, i -> curr
        for (int i = 0;i < str.length();i++) {
            if (p != str.charAt(i)) {
                String nextStr = str.substring(0, i) + str.substring(i+1);
                char ch = str.charAt(i);
                count += printPermutationsWithoutDuplicates(nextStr, ans + ch);
            }
            p = str.charAt(i);
        }

        return count;
    }

    public static void manipulateStringForPermutationsWithoutDuplicates(String str) {
        int[] freq = new int[26];
        for (int i = 0;i < str.length();i++) {
            freq[str.charAt(i) - 'a']++;
        }

        String sortedString = "";
        for (int i = 0;i < 26;i++) {
            for (int j = 0;j < freq[i];j++) {
                sortedString += (char)('a' + i);
            }
        }

        System.out.println(printPermutationsWithoutDuplicates(sortedString, ""));
    }

    public static int printEncodings(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        if (str.charAt(0) == '0') {
            return 0;
        }

        int count = 0;
        char ch1 = str.charAt(0);
        char key1 = (char)('a' + ch1 - '1');

        count += printEncodings(str.substring(1), ans + key1);

        if (str.length() > 1) {
            char ch2 = str.charAt(1);
            int num2 = (ch1 - '0') * 10 + (ch2 - '0');
            if (num2 <= 26) {
                char key2 = (char)('a' + num2 - 1);
                count += printEncodings(str.substring(2), ans + key2);
            }
        }

        return count;
    }

    // LeetCode 46 -> Permutations
    public static void permutations(int[] nums, int count, List<Integer> ans, List<List<Integer>> res) {
        if (count == nums.length) {
            List<Integer> baseAns = new ArrayList<>(ans);
            res.add(baseAns);
            return;
        }
        
        
        for (int i = 0;i < nums.length;i++) {
            if (nums[i] != Integer.MIN_VALUE) {
                int cur_val = nums[i];
                nums[i] = Integer.MIN_VALUE;
                ans.add(cur_val);
                permutations(nums, count+1, ans, res);
                ans.remove(ans.size()-1);
                nums[i] = cur_val;
            }
        }
    }


    public static void main(String[] args) {
        // String str = "abcd";
        // // Subsequence
        // System.out.println(printSS(str, ""));

        // String[] map = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};
        // String str = "476";
        // System.out.println(printKPC(str, "", map));

        Scanner sc = new Scanner(System.in);
        // int n = sc.nextInt();       // 4
        // System.out.println(printStairPaths(n, ""));

        // int n = sc.nextInt();    // 10
        // System.out.println(printBoardPath(n, ""));

        // int[] arr = {4, 1, 5, 2, 1, 7};
        // int n = sc.nextInt();    // 10
        // System.out.println(printBoardPath2(arr, n, ""));

        

        // int n = sc.nextInt();
        // int m = sc.nextInt();
        // System.out.println(printMazePaths(0, 0, n-1, m-1, ""));

        // int n = sc.nextInt();   // 3
        // int m = sc.nextInt();   // 3
        // System.out.println(printMazePaths2(n-1, m-1, ""));

        // int n = sc.nextInt();   // 3
        // int m = sc.nextInt();   // 3
        // System.out.println(printMazePath_(n-1, m-1, ""));

        // String str = "aba";
        // System.out.println(printPermutationsWithDuplicates(str, ""));
        
        // String str = "aba";
        // manipulateStringForPermutationsWithoutDuplicates(str);

        // String str = "1206";
        // System.out.println(printEncodings(str, ""));

        // List<List<Integer>> res = new ArrayList<>();
        // List<Integer> ans = new ArrayList<>();
        // int[] nums = {1, 2, 3};
        // permutations(nums, 0, ans, res);
    }
}
