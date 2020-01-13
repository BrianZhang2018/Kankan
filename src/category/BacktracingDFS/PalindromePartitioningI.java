package category.BacktracingDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 *
 * 和subset问题解法类似
 * Created by brianzhang on 1/12/20.
 */
public class PalindromePartitioningI {

    public static void main(String[] args) {
        PalindromePartitioningI test = new PalindromePartitioningI();
        test.partition("adb");
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        dfs(s,0,list,res);
        return res;
    }

    public void dfs(String s, int pos, List<String> list, List<List<String>> res){
        if(pos==s.length()) res.add(new ArrayList<>(list));
        else{
            for(int i=pos;i<s.length();i++){
                if(isPal(s,pos,i)){
                    list.add(s.substring(pos,i+1));
                    dfs(s,i+1,list,res);
                    list.remove(list.size()-1);
                }
            }
        }
    }

    public boolean isPal(String s, int low, int high){
        while(low<high) if(s.charAt(low++)!=s.charAt(high--)) return false;
        return true;
    }
}
