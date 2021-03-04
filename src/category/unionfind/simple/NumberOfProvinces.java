package category.unionfind.simple;

/**
 * https://leetcode.com/problems/number-of-provinces/
 *
 * Created by brianzhang on 2/8/21.
 */
public class NumberOfProvinces {

    // Solution-1: Union-Find
    public int findCircleNum(int[][] M) {
        int m = M.length, cnt = 0;
        int[] root = new int[m];
        for (int i = 0; i < m; i++) root[i] = i;

        for (int i = 0; i < m; i++)
            for (int j = i + 1; j < m; j++)
                if (M[i][j] == 1) unionFind(root, i, j);

        for (int i = 0; i < m; i++)
            if (i == root[i]) cnt++;
        return cnt;
    }

    void unionFind (int[] root, int v1, int v2) {
        while (root[v1] != v1) v1 = root[v1]; // find v1's root
        while (root[v2] != v2) v2 = root[v2]; // find v2's root
        if (root[v1] != root[v2]) root[v2] = v1; // unite the 2 subtrees
    }

    // Solution-2: dfs
    public int findCircleNumDFS(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int cnt = 0;
        for(int i=0; i<M.length; i++){
            if(visited[i]) continue;

            if(!visited[i]){ // dfs traverse the direct friends of "i" person
                visited[i] = true;
                dfs(M, i, visited);
                cnt++; // always count++ here as himself also is a group even though no other friends
            }
        }

        return cnt;
    }

    public void dfs(int[][] M, int i, boolean[] visited) {
        for(int j=0; j<M.length; j++){
            if(visited[j]) continue;

            if(M[i][j] == 1 && !visited[j]){
                visited[j] = true;
                dfs(M, j, visited); //  dfs traverse the direct friends of "j" person which is the indirect friend of "i"
            }
        }
    }
}
