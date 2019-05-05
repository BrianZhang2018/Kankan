package category.backtracingDFS;

import java.util.HashMap;
import java.util.Map;

/**
 * 知识点: DFS + Recover array
 * Company: Tableau
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=499470&highlight=Tableau
 *
 * Created by brianzhang on 4/15/19.
 */
public class ZumaGame {

    public static void main(String[] args) {
        ZumaGame zumaGame = new ZumaGame();
        System.out.println(zumaGame.findMinStep("WWRRBBWW", "WRBRW"));
    }
    public int findMinStep(String board, String hand) {
        if(board == null)
            return 0;
        HashMap<Character, Integer> hands = new HashMap<Character, Integer>();
        for(int i=0; i<hand.length(); i++){
            hands.put(hand.charAt(i), hands.getOrDefault(hand.charAt(i), 0) +1);
        }
        return dfs(board, hands);
    }

    public int dfs(String board, Map<Character, Integer> hands){
        if(board.isEmpty())
            return 0;

        int i=0;
        int j=0;
        int ans = Integer.MAX_VALUE;
        while(i<board.length()){ //loop color ball in the board
            while(j<board.length() && (board.charAt(i) == board.charAt(j))){
                ++j;
            }
            int ballNeed = 3 - (j-i);
            if(hands.get(board.charAt(i)) != null && hands.get(board.charAt(i)) >= ballNeed){
                //create a new board after clean the color ball (a sequence and more than 3)
                //e.g frist loop: i=0, color=W, WWRRBBWW -> RRBBWW, j=2
                //    second loop: i=2, color=R, WWRRBBWW -> WWBBWW, j=5 ...
                String newBoard = updateBoard(board.substring(0, i) + board.substring(j));
                hands.put(board.charAt(i), hands.get(board.charAt(i)) - ballNeed);
                int r = dfs(newBoard, hands);
                if(r != -1){
                    ans = Math.min(ans, r + ballNeed);
                }
                //Recover hands array by add the removed ballNeed back  *****
                hands.put(board.charAt(i), hands.get(board.charAt(i)) + ballNeed);
            }
            i=j; //go to the next different color ball
        }
        return ans == Integer.MAX_VALUE? -1 : ans;
    }

    // Update the board by removing all consecutive 3+ balls.
    // "YWWRRRWWYY" -> "YWWWWYY" -> "YYY" -> ""
    public String updateBoard(String newBoard){
        int i=0;
        while(i<newBoard.length()){
            int j= i;
            while(j<newBoard.length() && (newBoard.charAt(i) == newBoard.charAt(j)))
                ++j;

            if(j-i >=3){
                newBoard = newBoard.substring(0, i) + newBoard.substring(j);
                i=0;
            }else{
                ++i;
            }
        }
        return newBoard;
    }
}
