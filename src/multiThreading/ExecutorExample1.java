package multiThreading;

import java.util.*;
import java.util.stream.Collectors;

class A {
    String name;
     A child;
    public A(A a) {
        this.name = a.name;
        child = a;
    }
    public A(String a) {
        this.name = a;
    }
}
public class ExecutorExample1 {

    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        return strs.stream().map(str -> {if(str.equals("")) return "empty"; else return str;}).collect(Collectors.joining(","));
       // return String.join(",", strs);
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        String[] arr = s.split(",");

        return null;
    }
    public static void main(String[] args) {
    /*    StringBuilder sb = new StringBuilder("aaa");
        StringBuilder nSb = new StringBuilder("bbb");
        sb = nSb;
        nSb = new StringBuilder();
        nSb.append("bianle");
        System.out.println(sb);*/

        decode(",,");
        System.out.println(encode(Arrays.asList("", "")));

        A a0 = new A("hua");
        A a = new A("2222");
        a0 = a;
        System.out.println(a0.name);
        a = new A("3333");
        System.out.println(a0.name);



        /*ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<Integer>> listOfCallable = Arrays.asList(
                () -> 1,
                () -> 2,
                () -> 3);
        try {
            List<Future<Integer>> futures = executor.invokeAll(listOfCallable);
            int sum = futures.stream().map(f -> {
                try {
                    return f.get();
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }).mapToInt(Integer::intValue).sum();

            System.out.println(sum);

        } catch (InterruptedException e) {// thread was interrupted
            e.printStackTrace();
        } finally {
            // shut down the executor manually
            executor.shutdown();
        }*/
    }
}
