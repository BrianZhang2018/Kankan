package category.greedy;

/**
 * https://www.lintcode.com/problem/pour-water/description
 * https://aaronice.gitbooks.io/lintcode/array/pour-water.html
 *
 * 模拟水滴的动态过程：设一个指针curr，让水滴向左，向右，最后回到K，最终停下的位置就是加水位的位置。
 * Created by brianzhang on 11/19/19.
 */
public class PourWater {

    public static void main(String[] args) {
    }
    /**
     * @param heights: the height of the terrain
     * @param V: the units of water
     * @param K: the index
     * @return: how much water is at each index
     */
    public static int[] solution(int[] heights, int V, int K) {

        if(heights == null || heights.length == 0)
            return heights;

        for(int i=0; i<V; i++){

            int curr = K;

            while(curr > 0 && heights[curr-1] <= heights[curr]){
                curr--;
            }

            while(curr<heights.length-1 && heights[curr+1] <= heights[curr]){
                curr++;
            }

            while(curr > K && heights[curr-1]<=heights[curr]){
                curr--;
            }

            heights[curr]++;
        }

        return heights;
    }
}
