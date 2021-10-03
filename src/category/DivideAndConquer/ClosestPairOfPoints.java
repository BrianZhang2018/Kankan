package category.DivideAndConquer;

import java.util.*;

/**
 * https://www.lintcode.com/problem/966/solution/22651
 *
 * https://www.geeksforgeeks.org/closest-pair-of-points-using-divide-and-conquer-algorithm/
 *
 * snowflake phones screening: https://www.1point3acres.com/bbs/thread-788245-1-1.html
 *
 * Created by brianzhang on 9/26/21.
 */
public class ClosestPairOfPoints {

    public static void main(String[] args) {
        System.out.println(new ClosestPairOfPoints().getClosestDistance(
                new double[]{1.0,2.0,3.0,4.0,5.0}, new double[]{2.0,3.0,1.0,2.0,7.0}));
    }
    /**
     * @param x: the list of coordinate x
     * @param y: the list of coordinate x
     * @return: find the closest pair of points and return the distance
     */
    public double getClosestDistance(double[] x, double[] y) {
        List<Point> xSort = new ArrayList<>();
        int n = x.length;
        for (int i = 0; i < n; i++) xSort.add(new Point(x[i], y[i]));

        Collections.sort(xSort, (a, b) -> (int)(a.x - b.x)); // sort by X coordinate
        return getCloseHelper(xSort, 0, n - 1);
    }

    public double getCloseHelper(List<Point> xSort, int left, int right) {
        if(left == right) return Integer.MAX_VALUE;
        if(left+1 == right) return getDistance(xSort.get(left), xSort.get(right));

        int mid = (left + right) / 2;
        Point midPoint = xSort.get(mid);
        // consider left & left or right & right point
        double minL = getCloseHelper(xSort, left, mid);
        double minR = getCloseHelper(xSort, mid + 1, right);
        double d = Math.min(minL, minR);

        // consider left & right point
        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            Point p = xSort.get(i);
            if (Math.abs(p.x - midPoint.x) < d) {
                strip.add(p);
            }
        }
        Collections.sort(strip, (a,b) -> (int)(a.y-b.y));
        return stripMin(strip, d);
    }

    public double stripMin(List<Point> strip, double d) {
        int n = strip.size();
        double min = d;
        for (int i = 0; i < n; i++) {
            Point a = strip.get(i);
            for (int j = i + 1; j < n; j++) {
                Point b = strip.get(j);
                if (b.y - a.y >= d) { // prune
                    break;
                }
                min = Math.min(min, getDistance(a, b));
            }
        }
        return min;
    }

    public double getDistance(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }
}

class Point {
    double x;
    double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}