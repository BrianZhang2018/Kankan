package category.TwoPointer;

/**
 * Created by brianzhang on 9/4/18.
 */
public class IncreasingTripletSubsequence {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 5, 0, 4, 6};
        System.out.println(increasingTriplet(arr));
    }

    public static boolean increasingTriplet(int[] nums) {
        for (int i = 0; i <= nums.length - 3; i++) {
            int temp = i;
            for (int j = temp + 1; j < nums.length; j++) {

                if (nums[temp] < nums[j]) {
                    if (j - i + 1 == 3)
                        return true;
                    else {
                        temp++;
                        continue;
                    }
                } else {
                    break;
                }
            }
        }
        return false;
    }
}
