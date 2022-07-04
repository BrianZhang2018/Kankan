package category.Sort;

import java.util.*;

/**
 * https://leetcode.com/problems/insert-interval/discuss/21600/Short-java-code
 */
public class InsertInterval{
    public static void main(String[] args){
        for(int[] res : insert(new int[][]{{6,9},{11,13}}, new int[]{7,8}))
            System.out.println(Arrays.toString(res));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        for(int[] i : intervals){
            if(newInterval == null || i[1] < newInterval[0]){
                result.add(i);
            }else if(i[0] > newInterval[1]){
               // be careful the sequence here
                result.add(newInterval);
                result.add(i);
                newInterval = null;
            }else{
                newInterval[0] = Math.min(newInterval[0], i[0]); // get min
                newInterval[1] = Math.max(newInterval[1], i[1]); // get max
            }
        }
       
       if(newInterval != null)
           result.add(newInterval);

       return result.toArray(new int[result.size()][]);
   }
}