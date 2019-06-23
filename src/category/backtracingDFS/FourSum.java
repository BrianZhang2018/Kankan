package category.BacktracingDFS;
import java.util.*;
/**
 * this can be turned into the K Sum.
 * 
 */
public class FourSum{
    public static void main(String[] args){
        FourSum test = new FourSum();
        int[] data = {1,0,-1,0,-2,2};
        System.out.println(test.fourSum(data, 0));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        
        if(nums == null || nums.length ==0)
            return Collections.EMPTY_LIST;
        
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        backtrack(set, new ArrayList<Integer>(), nums, target, 0);
        return new ArrayList<>(set);
    }
    
    public void backtrack(Set<List<Integer>> set, List<Integer> tempList, int[] nums, int target, int index){ 
        if(tempList.size() == 4) { // 4 can be K to turn this to K sum
            if(target == 0){
                set.add(new ArrayList<>(tempList));
            }
            return;
        }
            
        for(int i=index; i<nums.length; i++){
            tempList.add(nums[i]);
            target -= nums[i];
            backtrack(set, tempList, nums, target, i+1);
            target += nums[i];
            tempList.remove(tempList.size()-1);
        }
    }

}