package category.backtracingDFS;

/**
 *  Same idea with me for your reference
 *  https://leetcode.com/problems/n-queens-ii/discuss/20048/Easiest-Java-Solution-(1ms-98.22
 *  Created by brianzhang on 4/19/19.
 */
public class NQueensII {
    private boolean[] cols;     // columns   |
    private boolean[] digl1;    // diagonals \
    private boolean[] digl2;    // diagonals /
    private int res = 0;

    public int totalNQueens(int n) {
        if(n ==0) return 0;
        cols = new boolean[n];
        digl1 = new boolean[2*n-1];
        digl2 = new boolean[2*n-1];
        backtracking(0, n);
        return res;
    }

    /**
     * the question means that can't has the another queen in the same row, col and diagonals
     * so, we do row by row, and each row only put one queen. We just need check the col and diagonals as we only allow put one queen in one row.
     */
    public void backtracking(int row, int n){
        if(row == n){
            res++;
            return;
        }
        //我们只要看列和对角线就可以了, 因为我们是row by row的dfs，一行放一个queen，因为同行不能有其他queen.
        for(int col=0; col<n; col++){
            if(cols[col] || digl1[row+col] || digl2[row-col+n-1]){
                continue;
            }
            cols[col] = true;
            digl1[row+col] = true;
            digl2[row-col+n-1] = true;
            backtracking(row+1, n);
            cols[col] = false;
            digl1[row+col] = false;
            digl2[row-col+n-1] = false;
        }
    }
}