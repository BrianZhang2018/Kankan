package category.BinarySearch;

/**
 * Created by brianzhang on 3/14/19.
 */
public class FindPeakElement {

    public static void main(String[] args) {
        FindPeakElement test = new FindPeakElement();
        System.out.println(test.findPeakElement(new int[]{1,2,3,1}));
    }

    public int findPeakElement(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;

        return binarySearch(0, nums.length-1, nums);
    }

    public int binarySearch(int start, int end, int[] nums){

        if(start == end){
            return start;
        }else if(start + 1 == end){
            return nums[start]>nums[end] ? start : end;
        }else{
            int mid = (start + end)/2;
            //int res = 0;
            if(nums[mid-1] < nums[mid] && nums[mid] > nums[mid+1]){
                return mid;
            }else if(nums[mid-1] > nums[mid] && nums[mid] > nums[mid+1]){
                return binarySearch(start, mid-1, nums);
            }else{
                return binarySearch(mid+1, end, nums);
            }
        }
    }
}
