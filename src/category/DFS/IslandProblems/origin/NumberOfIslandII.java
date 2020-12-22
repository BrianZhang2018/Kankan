package category.DFS.IslandProblems.origin;

import java.util.*;

/**
 * https://www.lintcode.com/problem/number-of-islands-ii/description
 *
 * UnionFind solution
 */
public class NumberOfIslandII {

    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> res = new ArrayList<>();
        if(operators == null || operators.length == 0) return res;
           
        int[] parents = new int[n*m];
        Arrays.fill(parents, -1);

        int num = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for(Point p : operators){
           int po = p.x * m + p.y;
           if(parents[po] != -1){
               res.add(num);
               continue;
           }
           //assign the value to mark it has been visited
           parents[po] = po;
           num++;

           for(int[] dir : dirs){
               int px = p.x + dir[0];
               int py = p.y + dir[1];

               if(px < 0 || py < 0 || px >=n || py>=m){
                   continue;
               }

               int pn = px * m +py;
               if(parents[pn] == -1){
                   continue;
               }

               if(find(parents, po) != find(parents, pn)){
                   parents[find(parents, po)] = find(parents, pn);
                   num--;
               }
           }

           res.add(num);
        }
        return res;
   }

    public int find(int[] parents, int d) {
        while (parents[d] != d) {
            parents[d] = parents[parents[d]];
            d = parents[d];
        }
        return d;
    }
}

class Point {
    int x;
    int y;
    Point() {x = 0;y = 0;}
    Point(int a, int b) {x = a;y = b;}
}