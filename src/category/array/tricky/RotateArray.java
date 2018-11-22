package category.array.tricky;

/**
 * Created by brianzhang on 11/12/18.
 */
public class RotateArray {

    public static void main(String[] args) {
        System.out.println("quick".substring(0, "quick".length()));
    }
    public void rotate(int[] nums, int k) {

        if (nums == null || nums.length < 2 || k == 0)
            return;

        k %= nums.length;

        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    public void reverse(int[] nums, int s, int e) {

        int temp = 0;
        while (s < e) {

            temp = nums[s];
            nums[s] = nums[e];
            nums[e] = temp;

            s++;
            e--;
        }
    }
}
