package category.Map;

import java.util.*;

/**
 * https://leetcode.com/problems/brick-wall/
 *
 * Created by brianzhang on 1/1/21.
 */
public class BrickWall {

    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap();
        int count=0;
        for(List<Integer> l : wall){
            int prefixSum = 0;
            for(int i = 0; i < l.size() - 1; i++){ // 解题思路: don't include the brick on edge since we can't draw a line on edge, if you include it, the win key-pair will be (wallWidth, rows) since all row has the equal width, so the result will be the rows of wall
                prefixSum += l.get(i);
                map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
                count = Math.max(count, map.get(prefixSum));
            }
        }

        return wall.size()-count;
    }
}
