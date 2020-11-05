package category.DynamicPlanning.DistinctWays.PathProblems;

/**
 * https://leetcode.com/problems/knight-probability-in-chessboard/
 *
 * 实际上是求到达某点有多少种方法, the ways of curr node  += the ways of previous node
 * https://www.youtube.com/watch?v=MyJvMydR2G4
 *
 * 一样的题：https://leetcode.com/problems/knight-dialer/discuss/189271/Java-Top-Down-Memo-DP-O(N)
 *
 * Created by brianzhang on 2/25/19.
 */
public class KnightProbabilityInChessboard {

    public double knightProbability(int N, int K, int r, int c) {

        int[][] dires  = new int[][]{{2,1},{-2,1},{2,-1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};
        double[][] prev = new double[N][N];
        prev[r][c] = 1;

        for(int k=1; k<=K; k++){
            double[][] curr = new double[N][N];
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    for(int[] dire : dires){
                        int oldR =  i - dire[0];
                        int oldC =  j - dire[1];
                        if(oldR>=0 && oldC>=0 && oldR<N && oldC <N){
                            curr[i][j] += prev[oldR][oldC];
                        }
                    }
                }
            }

            prev = curr;
        }

        double sum =  0;
        for(int i=0; i<N;++i){
            for(int j=0; j<N;++j){
                sum+=prev[i][j];
            }
        }

        return sum/Math.pow(8, K);
    }
}
