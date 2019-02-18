package category.bst;

/**
 * Created by brianzhang on 2/18/19.
 */
public class FirstBadVersionRecursive {

    public int firstBadVersion(int n) {
        if (n == 0)
            return 0;

        return bs(1, n, n);
    }

    public int bs(int left, int right, int n) {
        if (left == right)
            return left;

        int pivot = left + (right - left) / 2;
        if (isBadVersion(pivot)) {
            return bs(left, pivot, n);
        } else {
            return bs(pivot + 1, right, n);
        }
    }

    //Leetcode give the implementation of this method, here just give a mock method, no real meaning
    public boolean isBadVersion(int number) {
        return true;
    }
}

