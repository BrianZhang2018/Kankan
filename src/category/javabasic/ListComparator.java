package category.javabasic;

import category.model.Pair;

import java.util.*;
/**
 * Created by brianzhang on 7/28/21.
 */
public class ListComparator {
    public static void main(String[] args) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        list.add(new Pair<>(1, 2));
        list.add(new Pair<>(2, 3));
        list.add(new Pair<>(3, 5));
        list.add(new Pair<>(3, 4));
        list.add(new Pair<>(3, 2));
        Collections.sort(list, (a, b) -> {
            if (a.getKey() == b.getKey()) {
                return a.getValue() - b.getValue();
            }
            return a.getKey() - b.getKey();
        });

        for (Pair<Integer, Integer> p : list) {
            System.out.println(p);
        }
    }
}
