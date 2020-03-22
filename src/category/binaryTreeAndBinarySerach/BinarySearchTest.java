package category.binaryTreeAndBinarySerach;

/**
 * Created by brianzhang on 1/21/20.
 */
public class BinarySearchTest {

    public static void main(String[] args) {

        System.out.println(bs(new int[]{1,4,7,8,9,23,54}, 10));
    }

    public static int bs(int[] arr, int target){

        int lo = 0;
        int hi = arr.length;


        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            if (arr[mid] == target){
                return mid;
            }
            if(arr[mid] > target){
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }

        return lo;
    }
}
