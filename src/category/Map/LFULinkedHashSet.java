package category.Map;

import java.util.LinkedHashSet;

/**
 * Created by brianzhang on 6/16/20.
 */
public class LFULinkedHashSet {

    public static void main(String[] args) {
        LinkedHashSet<Integer> lhs = new LinkedHashSet<>();
        lhs.add(1);
        lhs.add(2);
        lhs.add(3);

        for (int i : lhs) System.out.println(i);
    }
}
