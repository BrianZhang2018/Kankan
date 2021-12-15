package category.DFSBacktracing;

import java.util.*;

/**
 * https://leetcode.jp/problemdetail.php?id=254
 * https://www.lintcode.com/problem/factor-combinations/description
 *
 * Created by brianzhang on 3/6/19.
 */
public class FactorCombination {

    public static void main(String[] args) {
        FactorCombination fc = new FactorCombination();
        for(List<Integer> l :  fc.getFactors(12)){
            System.out.println(l.toString());
        }
    }

    public List<List<Integer>> getFactors(int n) {
        return dfs(n, 2, new ArrayList<>(), new ArrayList<>());
    }

    private List<List<Integer>> dfs(int n, int start, List<Integer> temp, List<List<Integer>> res){
        if(n == 1){
            if(temp.size() > 1){
                res.add(new ArrayList<>(temp));
            }
        }

        for(int i=start; i<=n; i++){
            if(n % i != 0) continue;

            temp.add(i);
            dfs(n/i, i, temp, res);
            temp.remove(temp.size()-1);
        }

        return res;
    }
}
