package category.DFS;

/**
 * https://leetcode.com/problems/24-game/
 *
 * time complexity O(1):
 *
 *   Time complexity is O(9216) reduced to O(1)
     There are 4 numbers to choose from: 4
     After choosing the 1st number, there are 3 numbers to choose from: 3
     after picking a pair of numbers, you have 4 operations to choose from: 4
     Then you have 3 numbers to choose from: 3
     Then you have 2 numbers to choose from: 2
     after picking a pair of numbers, you have 4 operations to choose from: 4
     Then you have 2 numbers to choose from: 2
     Then you have 1 number1 to choose from: 1
     after picking a pair of numbers, you have 4 operations to choose from: 4
     4x3x4x3x2x4x2x1x4 = O(9216) = O(1)
 *
 * Created by brianzhang on 7/9/21.
 */
public class Game24 {

    public static void main(String[] args) {

    }

    public boolean judgePoint24(int[] cards) {
        double[] array = new double[]{cards[0], cards[1], cards[2], cards[3]};
        return dfs(array);
    }

    // 每次dfs都是选取两张牌
    private boolean dfs(double[] cards) {
        if (cards.length == 1) {
            return Math.abs(cards[0] - 24) < 0.01;
        }
        for (int i = 0; i < cards.length; i++) {
            for (int j = i + 1; j < cards.length; j++) {

                double nextRound[] = new double[cards.length - 1];
                for (int k = 0, index = 0; k < cards.length; k++) {
                    if (k != i && k != j) {
                        nextRound[index++] = cards[k];
                    }
                }
                // 对于每下一个可能的产生的组合
                for (double res : compute(cards[i], cards[j])) {
                    nextRound[nextRound.length - 1] = res;
                    if (dfs(nextRound)) { // dfs
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 计算下一个可能产生的组合
    private double[] compute(double a, double b) {
        return new double[]{a + b, b + a, a - b, b - a, a * b, b * a, a / b, b / a};
    }
}
