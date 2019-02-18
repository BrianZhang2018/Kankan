package javabasic;

import java.util.Date;

/**
 * Created by brianzhang on 2/4/19.
 */
public class CircularArray {
    // function to print circular list
    // starting from given index ind.
    public static void print(char a[], int n, int ind) {

        // print from ind-th index to
        // (n+i)th index.
        for (int i = ind; i < n + ind; i++)
            System.out.print(a[(i % n)] + " ");


        System.out.println();
    }

    // driver code to check the above function

    public static void main(String[] args) {
        String input = "GeeksforGeeks";

        // getBytes() method to convert string
        // into bytes[].
        byte[] strAsByteArray = input.getBytes();

        byte[] result = new byte[strAsByteArray.length];

        // Store result in reverse order into the
        // result byte[]
        for (int i = 0; i < strAsByteArray.length; i++)
            result[i] = strAsByteArray[strAsByteArray.length - i - 1];

        System.out.println(new String(result));
    }
}
