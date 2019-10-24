package javabasic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianzhang on 11/24/18.
 */
public class PassReferenceAsValue {
    public static void main(String[] args) {
        //example 1: pass reference as value, so original list will be changed
        List<Integer> jj = new ArrayList<>();
        changeList(jj);
        for (Integer i : jj) {
            System.out.println(i);
        }

        //example 2: primitive value won't be changed as it pass value as value,
        //doesn't like the object will pass reference as value
        int a = 1;
        changeValue(a);
        System.out.println(a);
    }

    public static void changeList(List<Integer> list) {
        List test = list;
        //test = new ArrayList(list); //deep copy will avoid change the original list
        test.add(5);
    }

    public static void changeValue(int a) {
        a = 100;
    }
}
