package category.BitManipulation;

/**
 * https://leetcode.com/problems/single-number-ii/discuss/43297/Java-O(n)-easy-to-understand-solution-easily-extended-to-any-times-of-occurance
 * <p>
 * Created by brianzhang on 2/24/19.
 */
public class SingleNumberII {

    public static void main(String[] args) {

        System.out.println(8 >> 1 & 1);
        System.out.println(singleNumber(new int[]{2, 2, 3, 2}));
    }

    public static int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((nums[j] >> i) & 1) == 1) {
                    sum++;
                    sum %= 3;
                }
            }
            if (sum != 0) {
                ans |= sum << i;
            }
        }
        return ans;
    }
}
