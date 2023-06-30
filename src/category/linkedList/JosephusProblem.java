package category.linkedList;
import java.util.*;
/**
 * https://www.geeksforgeeks.org/josephus-problem/
 */
public class JosephusProblem {
    public static void main(String [] args)
    {
        int n = 5, k = 2;
        int index = 0;
        List<Integer> person = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            person.add(i);
        }
        k--; // skip k-1 people
        josh(person, k, index);
    }
    static void josh(List<Integer> person, int k, int index)
    {
        // Base case , when only one person is left
        if (person.size() == 1) {
            System.out.println(person.get(0));
            return;
        }
        // find the index of first person which will die
        index = ((index + k) % person.size());
        System.out.println("index: " + index);

        // remove the first person which is going to be killed
        person.remove(index);

        // recursive call for n-1 persons
        josh(person, k, index);
    }
}
