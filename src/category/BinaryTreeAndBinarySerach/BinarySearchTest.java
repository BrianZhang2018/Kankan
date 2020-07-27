package category.BinaryTreeAndBinarySerach;

/**
 * Created by brianzhang on 1/21/20.
 */
public class BinarySearchTest {

    public static void main(String[] args) {

        System.out.println(bs(new int[]{1,4,7,8,9,23,54}, 10));
    }

    public static int bs(int[] arr, int target){

        int left = 0;
        int right = arr.length;

        while(left<right){
            int mid = left + (right-left)/2;
            if (arr[mid] == target){
                return mid;
            }
            if(arr[mid] > target){
                right = mid;
            }else{
                left = mid+1;
            }
        }

        return left;
    }
}
