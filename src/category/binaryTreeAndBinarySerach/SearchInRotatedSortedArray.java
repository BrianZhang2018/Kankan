package category.binaryTreeAndBinarySerach;

/**
 * Binary Search for Rotated array
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Created by brianzhang on 2/27/19.
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        SearchInRotatedSortedArray test = new SearchInRotatedSortedArray();
        System.out.println(test.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 7));
    }

    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        while(start <= end){
            
            int mid = start + (end - start)/2;
            if(nums[mid] == target)
                return mid;
        
            if(nums[start] <= nums[mid]){
               // start -> mid is ordered increasingly, so check whether target falls in this range by compare the number on 'start' and 'mid' position
                //if not, which means the number falls in another half (mid -> end), so "start = mid +1"
                if(target >= nums[start] && target < nums[mid])
                    end = mid -1;
                else
                    start = mid+1;
            }else{
                //mid -> end is ordered increasingly, so check whether target falls in this range by comparing the number on 'mid' and 'end' position
                //if not, which means the number falls in another half (start - mid), so end = mid - 1
                if(target > nums[mid] && target <= nums[end])
                    start = mid +1;
                else
                    end = mid -1;
            }
        }
        
        return -1;
    }
}
