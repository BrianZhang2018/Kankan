package category.javabasic.strobogrammatic;

import java.util.*;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Find all strobogrammatic numbers that are of length = n.
 *
 * Input:  n = 2
 * Output: ["11","69","88","96"]
 *
 * Created by brianzhang on 2/2/21.
 */
public class StrobogrammaticII {

    public static void main(String[] args) {
        for(String str : new StrobogrammaticII().findStrobogrammaticIterative(4)){
            System.out.println(str);
        }
    }

    public List<String> findStrobogrammaticIterative(int n) {
        List<String> cur, ans;
        ans = new ArrayList<>((n %2) == 0 ? Arrays.asList("") : Arrays.asList("0", "1", "8"));
        if (n < 2) return ans;

        for (;n > 1; n -= 2) {
            cur = ans;
            ans = new ArrayList<>();
            for (String s : cur) { // "s" is Strobogrammatic
                if (n > 3)
                    ans.add('0' + s + '0');

                ans.add('1' + s + '1');
                ans.add('8' + s + '8');
                ans.add('6' + s + '9');
                ans.add('9' + s + '6');
            }
        }

        return ans;
    }

    public List<String> findStrobogrammaticRecursive(int n) {
        return helper(n, n);
    }

    List<String> helper(int n, int m) {
        if (n == 0) return new ArrayList<>(Arrays.asList(""));
        if (n == 1) return new ArrayList<>(Arrays.asList("0", "1", "8"));

        List<String> list = helper(n - 2, m);

        List<String> res = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);

            if (n != m) res.add("0" + s + "0");

            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }

        return res;
    }
}
