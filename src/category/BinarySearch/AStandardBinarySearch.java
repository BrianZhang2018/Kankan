package category.BinarySearch;

/**
 * Created by brianzhang on 1/21/20.
 */
public class AStandardBinarySearch {

    public static void main(String[] args) {
        System.out.println(bs(new int[]{1,3}, 3));
        System.out.println(bs1(new int[]{1,4,7,8,9,9,23,54}, 9));
        System.out.println(bs2(new int[]{1,4,7,8,9,9,23,54}, 9));
    }

    public static int bs(int[] arr, int target){

        int left = 0, right = arr.length;

        while(left < right){
            int mid = left + (right-left)/2;
            if (arr[mid] == target){
                return mid;
            }
            if(target > arr[mid]){
                left = mid+1;
            }else{ // target <= arr[mid]
                right = mid;
            }
        }

        return left;
    }

    // 不需要 (arr[mid] == target) check, find leftmost target, FindFirstAndLastPositionOfElement.java的解法
    public static int bs1(int[] arr, int target){

        int left = 0, right = arr.length;

        while(left<right){
            int mid = left + (right-left)/2;
            if(target > arr[mid]){
                left = mid+1;
            }else{ // target <= arr[mid]
                right = mid;
            }
        }

        return left; // or right
    }

    // 不需要 (arr[mid] == target) check, find rightmost target, FindFirstAndLastPositionOfElement.java的解法
    public static int bs2(int[] arr, int target){

        int left = 0, right = arr.length;

        while(left<right){
            int mid = left + (right-left)/2;
            if(target < arr[mid]){
                right = mid;
            }else{ // target >= arr[mid]
                left = mid+1;
            }
        }

        return left-1; // or right
    }
}
