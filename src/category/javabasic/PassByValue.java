package category.javabasic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianzhang on 11/5/18.
 */
public class PassByValue {
    public static void main(String[] args) {
        String bb = "bb";
        test(bb);
        System.out.println(bb);

        List<String> ls = new ArrayList<>();
        ls.add("aa");
        ls.add("bb");

        testReference(ls);
        System.out.println(ls);
    }

    //pass primitive type as value, so the value of original variable didn't change
    public static void test(String test) {
        test = "abc";
        test.concat("aa");
    }

    //pass the reference as value so the original list got changed
    public static void testReference(List<String> list) {
        list.add("cc");
    }

}
