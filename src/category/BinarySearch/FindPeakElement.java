package category.BinarySearch;

/**
 * https://leetcode.com/problems/find-peak-element/editorial/
 *
 * Here is three cases for this question:
 * 1.   /     2.  \         3.  / \
 *    /            \           /   \
 *  /               \         /
 * /                 \       /
 * Created by brianzhang on 3/14/19.
 */
public class FindPeakElement {
    public static void main(String[] args) {
        FindPeakElement test = new FindPeakElement();
        System.out.println(test.findPeakElement(new int[]{1,2,3,1}));
    }

    // solution-1: O(logN)
    public int findPeakElement(int[] nums) {
        int l=0, r = nums.length-1;
        while(l<r) {
            int mid = l + (r - l) /2;
            if(nums[mid] > nums[mid+1]) { // ***, can refer the idea from below O(n) solution
                r = mid;
            }else {
                l = mid+1;
            }
        }
        return l; // or return r;
    }

    // solution-2: O(n)
    public int findPeakElement1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) { // this implicitly said the nums[i-1] < nums[i], otherwise won't reach here.
                return i;
            }
        }
        return nums.length - 1;
    }

}
