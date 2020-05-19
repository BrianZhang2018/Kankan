package category.MonotoneQueueStack.descrasing.stack;

import java.util.Arrays;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
/**
 * https://leetcode.com/problems/next-greater-element-i/
 * https://leetcode.com/problems/next-greater-element-i/discuss/97595/java-10-lines-linear-time-complexity-on-with-explanation
 *
 * Monotone stack
 */
public class NextGreaterElement{
    public static void main(String[] args){
        NextGreaterElement test = new NextGreaterElement();
        System.out.println(Arrays.toString(test.nextGreaterElement1(new int[]{4, 1, 2}, new int[]{1,3,4,2})));
        //System.out.println(Arrays.toString(test.nextGreaterElement2(new int[]{4, 1, 2}, new int[]{1,3,4,2})));
    }

    //in-sequence traversal (start from the left of array)
    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int num : nums2){ //Monotone decrease stack
            while(!stack.isEmpty() && num > stack.peek()){
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        // above code get the next greater element for num2. Since nums1 is subset of nums2, so just need get value from reuslt map.
        
        int[] res = new int[nums1.length];
        for(int i=0; i<nums1.length; i++){
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        
        return res;
    }

     //reverse-sequence traversal (start from the end of array)
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        
        int n = nums2.length;
        for(int i=n-1; i>=0; i--){  //reverse-sequence
            while(!stack.isEmpty()){
               if(nums2[i] < stack.peek()){
                   map.put(nums2[i], stack.peek());
                   break;
               }else{
                   stack.pop();
               }
            }
            
            stack.push(nums2[i]);
        }
        int[] res = new int[nums1.length];
        for(int i=0; i<nums1.length; i++){
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        
        return res;
    }
}