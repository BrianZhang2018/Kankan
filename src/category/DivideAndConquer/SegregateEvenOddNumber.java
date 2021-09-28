package category.DivideAndConquer;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/segregate-even-and-odd-numbers/
 *
 * Created by brianzhang on 7/22/20.
 */
public class SegregateEvenOddNumber {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(segregate(new int[]{12, 34, 45, 9, 8, 90, 3})));
        System.out.println(Arrays.toString(segregateQuickSort(new int[]{12, 34, 45, 9, 8, 90, 3})));
    }

    // less swap
    public static int[] segregate(int[] arr){
        int left = 0, right = arr.length-1;
        int count = 0;

        while(left < right){

            while(arr[left] %2 ==0 && left <right){
                left++;
            }

            while(arr[right] %2 !=0 && left <right){
                right--;
            }

            if(left < right){
                count++;
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }

            left++;right--;
        }

        System.out.println(count);

        return arr;
    }

    //more swap
    public static int[] segregateQuickSort(int[] arr){

        int j=-1;

        // quick-sort solution
        for(int i=0; i<arr.length; i++){
            if(arr[i] % 2 == 0){
                j++;

                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }
}
