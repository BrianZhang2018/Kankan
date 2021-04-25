package category.tree.BinaryTree;

/**
 * https://leetcode.com/problems/first-bad-version/
 *
 * Note: binary search condition comparison
 * 
 * Created by brianzhang on 2/18/19.
 */
public class FirstBadVersionIterative {

    public static void main(String[] args) {
        FirstBadVersionIterative test = new FirstBadVersionIterative();
        System.out.println(test.firstBadVersion(5));
    }

    public int firstBadVersion(int n) {
        int start = 1, end = n;

// compare with the below comment part to understand the comparison condition between start and end
        while (start <= end) { 
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid))
                end = mid - 1;
            else
                start = mid + 1;
        }
        // so, if condition is (start <= end), you should do "end = mid-1".
        // but, if (start < end), you do "end = mid" to avoid missing last element
        /*
            while (start < end) { 
                int mid = start + (end - start) / 2;
                if (isBadVersion(mid))
                    end = mid;
                else
                    start = mid + 1;
            }
       */
        return start;
    }

    //write as recursive way, just different way, no difference with above solution
    public int firstBadVersionRecursive(int n) {
        if (n == 0)
            return 0;

        return bs(1, n);
    }

    public int bs(int left, int right) {
        if (left == right)
            return left;

        int pivot = left + (right - left) / 2;
        if (isBadVersion(pivot)) {
            return bs(left, pivot);
        } else {
            return bs(pivot + 1, right);
        }
    }


    //Leetcode has the built-in implementation for this method, here is my mock method
    public boolean isBadVersion(int number) {
        if(number >= 4)
            return true;
        else
            return false;
    }
}
