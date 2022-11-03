package category.Unionfind.simpleVersion;

/**
 * https://leetcode.com/problems/friend-circles/
 *
 * Simplified UnionFind solution without ranking
 *
 * Created by brianzhang on 11/2/20
 */
public class FriendCircleSimpleUnionFind {
    public static void main(String[] args) {
       // int[][] matrix = new int[][]{{1,1,0}, {1,1,0}, {0,0,1}};
       int[][] matrix = new int[][]{{1,1,0}, {1,1,0}, {0,1,1}};
       System.out.println(findCircleNum(matrix));
    }

    public static int findCircleNum(int[][] M) {
        int m = M.length, n = M[0].length;
        int[] root = new int[m];
        for (int i = 0; i < m; i++) root[i] = i;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(M[i][j] == 1) {  // only unite the two if they are connected
                    union(root, i, j);
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < m; i++)
            if (i == root[i]) cnt++;

        return cnt;
    }

    // if the two connected, we unite the two under same root
    public static void union(int[] root, int i, int j){
        while(root[i] != i) i = root[i];  // find "i" root (can be improved with flat binaryTree)
        while(root[j] != j) j = root[j];  // find v2's root

        if(root[i] != root[j]) root[i] = j;  // unite the 2 subtrees
    }
}
