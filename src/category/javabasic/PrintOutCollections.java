package category.javabasic;

import java.util.*;

/**
 * Created by brianzhang on 7/26/20.
 */
public class PrintOutCollections {

    public static void main(String[] args) {
        Map<Integer, String> customers = new HashMap<>();
        customers.put(1, "Jhon");
        customers.put(2, "Smith");
        customers.put(3, "Sally");

        customers.forEach((id, name) -> System.out.println("Key : " + id + " value : " + name));
        customers.entrySet().forEach(e -> System.out.println("Key : " + e.getKey() + " value : " + e.getValue()));

        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);

        list.forEach(i -> System.out.println(i));
    }
}
