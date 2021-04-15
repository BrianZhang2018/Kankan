package category.BacktracingDFS;

import java.util.*;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 *
 * 和subset问题解法类似
 * Created by brianzhang on 1/12/20.
 */
public class PalindromePartitioningI {

    public static void main(String[] args) {
        PalindromePartitioningI test = new PalindromePartitioningI();
        for(List<String> list : test.partition("aab")){
            System.out.println(Arrays.toString(list.toArray()));
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(s,0,new ArrayList<>(),res);
        return res;
    }

    public void dfs(String s, int pos, List<String> temp, List<List<String>> res){
        if(pos == s.length()) {
            res.add(new ArrayList<>(temp));
        }
        else{
            for(int i=pos; i<s.length(); i++){
                if(isPalindrome(s, pos, i)){
                    temp.add(s.substring(pos, i+1));
                    dfs(s,i+1, temp, res);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }

    public boolean isPalindrome(String s, int low, int high){
        while(low<high){
            if(s.charAt(low++)!=s.charAt(high--)) return false;
        }

        return true;
    }
}
