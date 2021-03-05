package category.BacktracingDFS;

import java.util.*;

/**
 * 我首先follow花花酱的解法，然后refactor根据下面的reference
 * for reference:
 * https://leetcode.com/problems/n-queens/discuss/19932/Clean-back-tracking-Java-solution-with-simple-explaination
 *
 * Created by brianzhang on 4/16/19.
 */
public class NQueens {
    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        List<List<String>> res = nQueens.solveNQueens(4);
        for(List<String> list : res){
            for(int i=0; i<list.size(); i++){
                System.out.println(list.get(i));
            }
            System.out.println("/n");
        }
    }

    private boolean[] cols;
    private boolean[] dign1; //正对角线
    private boolean[] dign2; //反对角线
    private List<List<String>> res = new ArrayList<>();
    private List<String> board = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        if(n ==0){
            return res;
        }

        this.cols = new boolean[n];
        this.dign1 = new boolean[2*n-1];
        this.dign2 = new boolean[2*n-1];

        nQueen(0, n);
        return res;
    }

    public void nQueen(int row, int n){
        if(row == n){
            res.add(new ArrayList<>(board));
            return;
        }
        for(int col=0; col<n; col++){
            if(!available(row, col, n)){
                continue;
            }
            char[] r = new char[n];
            Arrays.fill(r, '.');
            r[col] = 'Q';
            board.add(new String(r));
            updateBoard(row, col, n, true);
            nQueen(row + 1, n);
            board.remove(board.size() - 1);
            updateBoard(row, col, n, false);
        }
    }

    public void updateBoard(int row, int col, int n, boolean isTaken){
        //列
        cols[col] = isTaken;
        //正对角线
        dign1[row + col] = isTaken;
        //反对角线
        dign2[row-col+n-1] = isTaken;
    }

    public boolean available(int row, int col, int n){
        return !cols[col] && !dign1[row + col] && !dign2[row-col+n-1];
    }
}
