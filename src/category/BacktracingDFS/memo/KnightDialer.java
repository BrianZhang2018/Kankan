package category.BacktracingDFS.memo;

/**
 * https://leetcode.com/problems/knight-dialer/
 * https://leetcode.com/problems/knight-dialer/discuss/189271/Java-Top-Down-Memo-DP-O(N)
 *
 * DFS + Memo = DP
 *
 * Created by brianzhang on 1/20/21.
 */
public class KnightDialer {

    public static void main(String[] args) {
        System.out.println(new KnightDialer().knightDialer(2));
    }

    private static int mod = 1000000007;

    public int knightDialer(int n) {
        if(n == 1) return 10;
        // build graph - step-1
        int[][] graph = new int[][]{{4,6}, {6,8}, {7,9}, {4,8}, {3,9,0}, {}, {1,7,0}, {2,6}, {1,3}, {4, 2}};
        int[][] memo = new int[n][10]; // 0-9 dial number

        int cnt = 0;
        // dfsHelper backTracking + memo - step-2
        for(int num=0; num<=9; num++){
            cnt = (cnt + helper(n-1, num, memo, graph)) % mod;
        }

        return cnt;
    }

    // dfsHelper backTracking + memo
    public int helper(int n, int num, int[][] memo, int[][] graph){
        if(n == 0) return 1;

        if(memo[n][num] != 0){
            return memo[n][num];
        }

        int cnt=0;
        for(int next : graph[num]){
            cnt = (cnt + helper(n-1, next, memo, graph)) % mod;
        }

        memo[n][num] = cnt;
        return cnt;
    }
}
