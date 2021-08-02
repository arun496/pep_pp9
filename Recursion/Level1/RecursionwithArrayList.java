import java.util.ArrayList;

public class RecursionwithArrayList {
    public static ArrayList<String> getSubsequence(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        
        char ch = str.charAt(0);
        ArrayList<String> recAns = getSubsequence(str.substring(1));
        
        ArrayList<String> myAns = new ArrayList<>();
        for (String s : recAns) {
            myAns.add(s);
        }
        for (String s : recAns) {
            myAns.add(ch + s);
        }
        
        return myAns;
    }

    public static ArrayList<String> getKPC(String str, ArrayList<String> keys) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        
        int key = str.charAt(0)-'0';
        ArrayList<String> recAns = getKPC(str.substring(1), keys);
        
        String pattern = keys.get(key);
        ArrayList<String> myAns = new ArrayList<>();
        for (int i = 0;i < pattern.length();i++) {
            char ch = pattern.charAt(i);
            for (String ans : recAns) {
                myAns.add(ch + ans);
            }
        }
        
        return myAns;
    }

    public static ArrayList<String> decodeWays1(String str, char[] map) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        if (str.length() == 1) {
            ArrayList<String> base = new ArrayList<>();
            base.add(map[Integer.parseInt(str)-1] + "");
            return base;
        }

        ArrayList<String> ans = new ArrayList<>();

        String str1 = str.substring(0, 1);
        String str2 = str.substring(0, 2);

        // Check validity for key1
        if (str1.charAt(0) != '0') {
            ArrayList<String> recAns1 = decodeWays1(str.substring(1), map);
            char key1 = map[Integer.parseInt(str1)-1];
            for (String s : recAns1) {
                ans.add(key1 + s);
            }
        }
        
        // Check validity for key2
        if (str2.charAt(0) != '0' && Integer.parseInt(str2) <= 26) {
            ArrayList<String> recAns2 = decodeWays1(str.substring(2), map);
            char key2 = map[Integer.parseInt(str2)-1];
            for (String s : recAns2) {
                ans.add(key2 + s);
            }
        }

        return ans;
    }

    // V.Imp, Make note how we map without Integer.parseInt()
    public static ArrayList<String> decodeWays2(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        if (str.charAt(0) == '0') {
            return new ArrayList<>();
        }

        ArrayList<String> myAns = new ArrayList<>();

        char ch1 = str.charAt(0);
        ArrayList<String> recAns1 = decodeWays2(str.substring(1));
        for (String s : recAns1) {
            char key = (char)('a' + ch1 - '1');
            myAns.add(key + s);
        }

        if (str.length() > 1) {
            char ch2 = str.charAt(1);
            int num = (ch1 - '0') * 10 + (ch2 - '0');

            if (num <= 26) {
                ArrayList<String> recAns2 = decodeWays2(str.substring(2));
                for (String s : recAns2) {
                    char key = (char)('a' + num - 1);
                    myAns.add(key + s);
                }
            }
        }

        return myAns;
    }

    // This is additional problem (combination of keypad & decodeWays problem)
    public static ArrayList<String> decodeWays3(String str, String[] map) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        if (str.charAt(0) == '0') {
            return new ArrayList<>();
        }

        ArrayList<String> myAns = new ArrayList<>();

        char ch1 = str.charAt(0);
        ArrayList<String> recAns1 = decodeWays3(str.substring(1), map);
        String key1 = map[ch1-'0'];
        for (int i = 0;i < key1.length();i++) {
            char keyChar = key1.charAt(i);
            for (String s : recAns1) {
                myAns.add(keyChar + s);
            }
        }

        if (str.length() > 1) {
            char ch2 = str.charAt(1);
            int num = (ch1 - '0') * 10 + (ch2 - '0');
            if (num <= 11) {
                ArrayList<String> recAns2 = decodeWays3(str.substring(2), map);
                String key2 = map[num];
                for (int i = 0;i < key2.length();i++) {
                    char keyChar = key2.charAt(i);
                    for (String s : recAns2) {
                        myAns.add(keyChar + s);
                    }
                }
            }
        }

        return myAns;
    }


    public static void main(String[] args) {
        // String str = "abcd";
        // System.out.println(getSubsequence(str));

        // Map below keys in an array for easier code
        // String str = "789";
        // ArrayList<String> keys = new ArrayList<>();
        // keys.add(".;");
        // keys.add("abc");
        // keys.add("def");
        // keys.add("ghi");
        // keys.add("jkl");
        // keys.add("mno");
        // keys.add("pqrs");
        // keys.add("tu");
        // keys.add("vwx");
        // keys.add("yz");
        // System.out.println(getKPC(str, keys));

        // char[] map = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        // String str = "11106";
        // String str = "11283";
        // System.out.println(decodeWays1(str, map));
        // System.out.println(decodeWays2(str));
        
        // String[] map = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wx", "yz", "+-*", "<>/%"};
        // String str = "11106";
        // System.out.println(decodeWays3(str, map));

    }
}
