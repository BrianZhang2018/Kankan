package category.bst;

/**
 * https://leetcode.com/problems/first-bad-version/
 * <p>
 * Created by brianzhang on 2/18/19.
 */
public class FirstBadVersionIterative {
    public int firstBadVersion(int n) {
        int start = 1, end = n;
        while (start < end) { // compare the below comment part to understand the condition
            int mid = start + (end - start) / 2;
            if (!isBadVersion(mid))
                start = mid + 1;
            else
                end = mid;
        }
        // if condition is (start <= end), you should do end = mid-1. if (start < end), you do end= mid to avoid missing last element
        /*while (start <= end) {
            int mid = start + (end - start) / 2;
            if (!isBadVersion(mid))
                start = mid + 1;
            else
                end = mid-1;
        }*/

        return start;
    }

    //Leetcode give the implementation of this method, here just give a mock method, no real meaning
    public boolean isBadVersion(int number) {
        return true;
    }
}