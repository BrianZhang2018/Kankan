package category.algorithms;

/**
 * https://leetcode.com/problems/majority-element/
 *
 * Created by brianzhang on 2/18/19.
 */
public class MajorityElementMooreVotingAlgorithm {

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{2,2,1,1,1,2,2,2,3}));
    }

    public static int majorityElement(int[] num) {
        int major = num[0], count = 1;
        for (int i = 1; i < num.length; i++) {
            if (count == 0) {
                count++;
                major = num[i];
            } else if (major == num[i]) {
                count++;
            } else count--;

        }
        return major;
    }
}
