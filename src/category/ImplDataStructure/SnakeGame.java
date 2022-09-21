package category.ImplDataStructure;

import java.util.*;

/**
 * https://leetcode.com/problems/design-snake-game/
 *
 * Created by brianzhang on 2/12/21.
 */
public class SnakeGame {

    static class Point {
        private int r;
        private int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean equals(Object o) {
            if (o instanceof Point) {
                Point p = (Point)o;
                return this.r == p.r && this.c == p.c;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    int rows, cols;
    Deque<Point> snake;
    int[][] food;
    int score;

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.rows = height;
        this.cols = width;
        this.food = food;
        this.score = 0;

        this.snake = new LinkedList<>();
        snake.addFirst(new Point(0, 0)); // =snake.push(..)=stack.push(..)
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        Point head = snake.peekFirst();
        Point next = null;
        switch(direction){
            case "U":
                next = new Point(head.r-1, head.c);break;
            case "D":
                next = new Point(head.r+1, head.c);break;
            case "L":
                next = new Point(head.r, head.c-1);break;
            case "R":
                next = new Point(head.r, head.c+1);break;
        }
        // Check if out of bound
        if (next.r < 0 || next.r == rows || next.c < 0 || next.c == cols) {
            return -1;
        }
        // Check if it bites itself
        // NOTE: If next is equal to last point, it's considered as valid
        if (!next.equals(snake.peekLast()) && snake.contains(next)) {
            return -1;
        }

        snake.addFirst(next);

        // Check if it reaches food
        if (score < food.length && next.r == food[score][0] && next.c == food[score][1]) {
            score++;
        } else {
            snake.removeLast();

        }
        return score;
    }
}
