package category.DynamicPlanning;

import java.util.*;

/**
 * https://leetcode.com/problems/pascals-triangle/
 *
 * Created by brianzhang on 11/29/18.
 */
public class PascalTriangle {

    public static void main(String[] args) {
        generate(5);
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows == 0) return res;

        for(int i=0; i<numRows; i++){
            List<Integer> list = new ArrayList();
            for(int j=0; j<=i; j++){
                if(j == 0 || j==i){
                    list.add(1);
                }else{
                    list.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
                }
            }
            res.add(list);
        }
        return res;
    }
}
