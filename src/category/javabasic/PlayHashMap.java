package category.javabasic;

import java.util.*;

/*
 *  1. object's default hashcode function is that converted the object reference's memory address to integer
 *
 *  2. list hashcode function:
 *
 *      public int hashCode() {
             int hashCode = 1;
             for (E e : this)
                 hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
             return hashCode;
        }

    3. Integer hashcode:

     public static int hashCode(int value) {
        return value;
     }
 *
 * Created by brianzhang on 9/5/20.
 */
public class PlayHashMap {

    public static void main(String[] args) {
        List<Integer> t1 = new ArrayList<>();
        t1.add(10);
        System.out.println("hashcode: " + t1.hashCode());
        List<Integer> t2 = new ArrayList<>();
        t2.add(1);
        System.out.println("hashcode: " + t2.hashCode());

        Map<List<Integer>, String> map = new HashMap<>();
        map.put(t1, "aa");
        map.put(t2, "bb");

        System.out.println(map.size());

        for (Map.Entry<List<Integer>, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        People p = new People("zhang", "dong");
        System.out.println("hashcode: " + p.hashCode());
        People s = new People("zhang", "dong");
        int hc = s.hashCode();
        System.out.println("hashcode: " + s.hashCode());
        System.out.println("hashcode: " + s.equals(p));

        Map<People, String> m1 = new HashMap<>();
        m1.put(p, "aa");
        m1.put(s, "bb");

        for (Map.Entry<People, String> entry : m1.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

class People {
    public String firstName;
    public String secondName;

    public People(String f, String s) {
        this.firstName = f;
        this.secondName = s;
    }

/*    public boolean equals(Object obj){

        return this.firstName.equals(((People)obj).firstName);
    }*/

}

