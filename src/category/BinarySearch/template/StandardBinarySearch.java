package category.BinarySearch.template;

/**
 * check this:
 * https://en.wikipedia.org/wiki/Binary_search_algorithm
 *
 * My practise summary:
 * the disadvantage of "left <=right ... right = mid-1":
 * skip the mid (which is need to considered, like find leftmost or rightmost)
 *
 * while(left < or != right) ...:
 * left and right are the same at the end
 *
 * Created by brianzhang on 1/21/20.
 */
public class StandardBinarySearch {
    public static void main(String[] args) {
          System.out.println(binarySearch1(new int[]{4,5,6, 7, 8}, 8));
          //System.out.println(binarySearch(new int[]{4,5,6,7,9,10}, 9));
      //  System.out.println(binarySearch(new int[]{1,2,5,6}, 4));
     //   System.out.println(findMin(new int[] {3,4,5,6,2}));
    }

    // standard binary search
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                System.out.println("mid: " + mid);
                break;
            }
            if (target > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("left: " + left);
        System.out.println("right " + right);
        return left;
    }

    // alternative way - I like this way
    public static int binarySearch1(int[] arr, int target) {
        int left = 0;
        int right = arr.length; // this trick is different with the standard way arr.length-1, left <=right, but act the same and specifically used when find leftmost/rightmost
        while (left < right) { // or left != right
            int mid = left + (right - left) / 2;
            // comment out the if (arr[mid] == target), when we find the left/rightmost one
            if (arr[mid] == target) {
                System.out.println("mid: " + mid);
                break;
            }
            if (target > arr[mid]) {
                left = mid + 1;
            } else { // 隐含了, if found first occurrence of the target, then move left since right = mid
                right = mid;
            }
        }
        System.out.println("left: " + left);
        System.out.println("right " + right);
        // "left and right is the same" at the end when use "left < right" in while condition,
        // but right = (left)target-position-1 if use "left <= right ... right=mid-1", since when "equal" will do right=mid-1.
        return left;
    }

    // for find left/right-most problem, we should use below left<right way since "mid can be the target", but we need left-most/right-most one.
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println("left: " + left);
        System.out.println("right " + right);
        return nums[right];
    }
}
