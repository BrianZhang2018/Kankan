package category.DataStructure.stack;
import java.unit.*;

/**
 * https://leetcode.com/submissions/detail/261100706/
 */
public class DailyTemperature{
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<Integer>();
        int n = temperatures.length;
        int[] res = new int[n];
       
       for(int i=0; i<n; i++){
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int idx = stack.pop();
                res[idx] = i - idx;
            }
           stack.push(i);
           
       }
      return res;
    }
}