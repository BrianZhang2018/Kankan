package category.BinaryTreeAndBinarySerach.binarySearch;

/**
 * https://leetcode.com/problems/sqrtx/
 *
 * Created by brianzhang on 11/8/20.
 */
public class Sqrt {
    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }
    public static int mySqrt(int x) {
        if(x == 0) return 0;

        int left=1, right = x; // careful: left = 1
        while(left < right){
            int mid = left + (right - left) /2;
            if(mid <= x / mid && (mid+1) > x/(mid +1)) // Main logic here.
                return mid;

            if(mid > x/mid){
                right = mid;
            }else{
                left= mid+1;
            }
        }

        return left;
    }
}
