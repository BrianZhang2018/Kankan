package category.javabasic;

import java.util.*;

public class InterestingCode {
/*    public static void main(String[] args) {
        int a = 1, b = 1;
        if (a  > 1 && b-- > 0){ // won't execute the second condition if first condition failed
            System.out.println(11);
        }
        else
            System.out.println(b);
    }*/

/*    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        List<Map.Entry> list = new ArrayList<>(map.entrySet());

        for(int i=0; i<5; i++) {
            for(Map.Entry entry : map.entrySet()) {
                System.out.println(entry.getKey());
            }
        }
*//*        for(int i= 0; i< list.size(); i++) {
            System.out.println(list.get(i).getKey());
        }*//*
    }*/


    public static void main(String[] args) {
        // java pass reference as value, so original object will be changed if referred one changed except primitive type, like Integer, String ...
        List<People1> ppl = new ArrayList();
        People1 p1 = new People1();
        p1.name = "hanhan";
        ppl.add(p1);
        System.out.println(ppl.get(0).name);
        test(ppl);
        System.out.println(ppl.get(0).name);

        List<Integer> tmp3 = new ArrayList(); // Integer is immutable in java, so the original list won't be changed
        tmp3.add(1);
        test1(tmp3);
        System.out.println(Arrays.toString(tmp3.toArray()));
    }

    public static void test(List<People1> list) {
        List<People1> ha = new ArrayList<>(list);
        ha.get(0).name = "dongdong";
        System.out.println(ha.get(0).name);
    }

    public static void test1(List<Integer> list) {
        List<Integer> ha = new ArrayList<>(list);
        ha.add(3);
        System.out.println(Arrays.toString(ha.toArray()));
    }
}

class People1 {
    String name;
}
