package category.DFS;

import category.model.NestedInteger;
import java.util.List;

/**
 * https://www.lintcode.com/problem/nested-list-weight-sum
 *
 * facebook, google, linkedin店面
 * Time complexity : O(N).
 * Let Nbe the total number of nested elements in the input list. For example, the list [ [[[[1]]]], 2 ] contains 44 nested lists and 22 nested integers (11 and 22), so N = 6N=6 for that particular case.
 * Created by brianzhang on 5/26/20.
 */
public class NestedListWeightSum {
    public static void main(String[] args) {}

    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    public int dfs(List<NestedInteger> nestedList, int depth){
        int sum = 0;
        for(NestedInteger ni : nestedList){
           if(ni.isInteger()){
               sum += ni.getInteger() * depth;
           }else{
               sum += dfs(ni.getList(), depth +1); // here is "depth+1", can't use depth++ since it's inside a "for" loop which can result in wrongly increase the depth for others at the same level
           }
        }

        return sum;
    }
}
