package category.BinarySearch.template;

/**
 * Important use case to understand the binary search and finally be able to flexibility use it.
 */
public class FindLeftRightMostPositionOfElement {
    public static void main(String[] args) {
        System.out.println(FindLeftMostPositionOfElement(new int[]{1, 4, 7, 8, 9, 9, 23, 54}, 9));
        System.out.println(FindRightMostPositionOfElement(new int[]{1, 4, 7, 8, 9, 9, 23, 54}, 9));
    }

    // omit (arr[mid] == target) check in while, find "leftmost" target
    public static int FindLeftMostPositionOfElement(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > arr[mid]) {
                left = mid + 1;
            } else { // when equal do "right = mid" so that move left to find the leftmost
                right = mid;
            }
        }
        return left;
    }

    // find "rightmost" target
    public static int FindRightMostPositionOfElement(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target < arr[mid]) {
                right = mid;
            } else { // when equal do "left = mid+1" so that move right to find the rightmost
                left = mid + 1;
            }
        }
        return right - 1; // or left - 1
    }
}
