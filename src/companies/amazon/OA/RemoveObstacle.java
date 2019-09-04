package companies.amazon.OA;

public class RemoveObstacle{

    int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot) {
        if (numRows == 0 || numColumns == 0 || lot == null) {
            return -1;
        }
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        HashSet<Point> visited = new HashSet<>();
        visited.add(new Point(0, 0));

        int step = 0;
        int[] deltax = new int[]{0, 1, 0, -1};
        int[] deltay = new int[]{1, 0, -1, 0};
        while (!queue.isEmpty()){
            Point current = queue.poll();
            if (lot.get(current.x).get(current.y) == 9) {
                return step;
            }
            for (int diff = 0; diff < 4; diff++) {
                int nextX = current.x + deltax[diff];
                int nextY = current.y + deltay[diff];
                if (isValiad(nextX, nextY, numRows, numColumns, lot, visited)) {
                    ((LinkedList<Point>) queue).add(new Point(nextX, nextX));
                    visited.add(new Point(nextX, nextY));
                }
            }
            step++;

        }
        return -1;
    }

    private boolean isValiad(int nextX, int nextY, int numRows, int numColumns, List<List<Integer>> lot, HashSet<Point> visited) {
        if (nextX < 0 || nextX >= numRows || nextY < 0 || nextY >= numColumns || lot.get(nextX).get(nextY) == 1 || visited.contains(new Point(nextX, nextY))) {
            return false;
        }
        return true;
    }
}