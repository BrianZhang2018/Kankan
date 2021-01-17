package category.twoPointer.FastSlowPointers;

import java.util.HashSet;

/**
 * https://leetcode.com/problems/happy-number/
 * <p>
 * The key logic here for this problem is: if the same sum appear twice, which means it's not a happy number.
 * <p>
 * Created by brianzhang on 4/5/20.
 */
public class HappyNumber {

    int digitSquareSum(int n) {
        int sum = 0, tmp;
        while (n > 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }

    boolean isHappy(int n) {
        int slow =n, fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(fast);
            fast = digitSquareSum(fast);
        } while (slow != fast);
        if (slow == 1)
            return true;
        else
            return false;
    }

    //solution-2:
    public boolean isHappy1(int n) {
        if (n == 0)
            return false;

        if (n == 1)
            return true;

        HashSet<Integer> set = new HashSet<>();
        while (set.add(n)) {
            int count = 0;
            while (n > 0) {
                int temp = n % 10;
                count += temp * temp;
                n /= 10;
            }
            if (count == 1) {
                return true;
            }
            n = count;
        }

        return false;
    }
}
