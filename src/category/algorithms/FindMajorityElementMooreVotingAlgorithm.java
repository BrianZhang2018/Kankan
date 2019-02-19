package category.algorithms;

/**
 * Created by brianzhang on 2/18/19.
 */
public class FindMajorityElementMooreVotingAlgorithm {

    public int majorityElement(int[] num) {
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
