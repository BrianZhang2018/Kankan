package category.Map.treeMap;

import java.util.*;

/**
 * https://leetcode.com/problems/max-points-on-a-line/
 *
 * https://www.youtube.com/watch?v=7FPL7nAi9aM
 *
 *         y2-y1
 * slope = -----
 *         x2-x1
 * Created by brianzhang on 2/9/21.
 */
public class MaxPointsOnALine {

    public int maxPoints(int[][] points) {
        int m = points.length;
        int res = 0;

        for (int i = 0; i < m; i++) { // 解题思路：计算一个点到所有其他点的slope（斜率），相同的slope说明在一条直线上.
            int duplicate = 0, currMax = 0;
            Map<String, Integer> map = new HashMap();
            for (int j = i + 1; j < m; j++) {
                int x1 = points[i][0], x2 = points[j][0];
                int y1 = points[i][1], y2 = points[j][1];
                if (x1 == x2 && y1 == y2) {
                    duplicate++;
                    continue;
                }
                int deltaX = x2 - x1, deltaY = y2 - y1;
                int gcd = gcd(deltaX, deltaY);
                String slope = (deltaX / gcd) + ":" + (deltaY / gcd);
                map.put(slope, map.getOrDefault(slope, 0) + 1); // count 相同斜率的点
                currMax = Math.max(currMax, map.get(slope)); // 计算当前loop，最多有少点在一条直线上
            }
            res = Math.max(res, currMax + duplicate + 1);
        }

        return res;
    }

    // 求最大公约数 (greatest common divisor)
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a%b);
    }
}