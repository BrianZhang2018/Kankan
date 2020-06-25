package category.DFS;

import category.model.NestedInteger;

import java.util.List;

/**
 * https://www.lintcode.com/problem/nested-list-weight-sum
 *
 * facebook, google 店面
 * Created by brianzhang on 5/26/20.
 */
public class NestedListWeightSum {

    public static void main(String[] args) {
        NestedListWeightSum test = new NestedListWeightSum();
        //test.depthSum();
    }

    public int depthSum(List<NestedInteger> nestedList) {
        // Write your code here
        return dfs(nestedList, 1);
    }

    public int dfs(List<NestedInteger> nestedList, int depth){
        if(nestedList == null || depth == nestedList.size())
            return 0;

        int sum = 0;
        for(NestedInteger ni : nestedList){
           if(ni.isInteger()){
               sum += ni.getInteger() * depth;
           }else{
               sum += dfs(ni.getList(), depth +1);
           }
        }

        return sum;
    }
}
