package category.String;

/**
 * https://leetcode.com/problems/string-without-aaa-or-bbb/
 *
 * Created by brianzhang on 2/17/20.
 */
public class StringWithoutAAAOrBBB {
    public static void main(String[] args) {
        System.out.println(strWithout3a3b(2, 3));
    }

    public static String strWithout3a3b(int A, int B) {

        StringBuilder sb = new StringBuilder(A+B);
        while(A + B>0){
            String s = sb.toString();

            if(s.endsWith("aa")){
                sb.append("b");
                B--;
            }else if(s.endsWith("bb")){
                sb.append("a");
                A--;
            }else if(A>B){
                sb.append("a");
                A--;
            }else{     // else if(B>=A)
                sb.append("b");
                B--;
            }
        }

        return sb.toString();
    }
}
