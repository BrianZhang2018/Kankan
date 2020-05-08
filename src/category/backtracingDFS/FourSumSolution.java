package category.BacktracingDFS;
import java.util.*;
/**
 * this can be turned into the K Sum.

 */
public class FourSumSolution{
    public static void main(String[] args){
        FourSumSolution test = new FourSumSolution();
        int[] data = {1,0,1,0,-2,2};
        System.out.println(test.fourSum(data, 0));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return Collections.EMPTY_LIST;
        }
       
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> subList = new ArrayList<>();
        findCombinations(set, subList, nums, 0, 0, target);
        return new ArrayList(set);
    }
    
    private void findCombinations(Set<List<Integer>> set, List<Integer> subList, int[] nums, int sum, int start, int target) {
        if (subList.size() == 4) {
            if (sum == target) {
                set.add(new ArrayList<>(subList));
            }          
            return;
        }
        
        if (start == nums.length) return;
        
        for (int index = start; index < nums.length; index++) {
            
            //I don't think this condition should be used here.
            if (index > start && nums[index] == nums[index-1])
                continue;
            
           //nums[i] is too small   (maxVal * the number of available slot in the subList (size = 4 as 4sum) - 1 (is the num[index]))
            if (sum + nums[index] + nums[nums.length-1] * (4 - subList.size() -1) < target)
            	continue;  
            
            //nums[i] is too big (this is more effective compare to above two conditions)
            if (sum + nums[index] * (4 - subList.size()) > target)
            	return;
            
            subList.add(nums[index]);
            findCombinations(set, subList, nums, sum + nums[index], index+1, target);
            subList.remove(subList.size() - 1);
        }
    }

}