package category.binaryTreeAndBinarySerach;

/**
 * Binary Search for Rotated array
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * Created by brianzhang on 2/27/19.
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        SearchInRotatedSortedArray test = new SearchInRotatedSortedArray();
        System.out.println(test.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 7));
    }

    int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        while(start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] == target)
                return mid;
            
            //'<=' also belong to 左边有序递增
            if(nums[start] <= nums[mid]){
                if(target >= nums[start] && target < nums[mid]){
                    end = mid -1;
                }else{
                    start = mid +1;
                }
            }else{
                if(target > nums[mid] && target <= nums[end]){
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
                
            }
        }
        
        return -1;
    }
}
