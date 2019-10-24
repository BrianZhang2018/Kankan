package specificeProblems.string;

/**
 * Microsoft OA
 * Fucked up on this question as not familiar with the string delete operation
 * Created by brianzhang on 10/20/19.
 */
public class GetStringWithNoThreeIdenticalConsecutiveLetter {

    public static void main(String[] args) {
        System.out.println(solution("uuuggguuuaaaggggguuu"));
    }

    //delete the letter to make a string that no three identical consecutive letters
    public static String solution(String S) {

        if (S.length() <= 2) {
            return S;
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append(S.charAt(0));

        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i - 1) == S.charAt(i)) {
                count++;
            } else {
                while (count > 2) {
                    sb.deleteCharAt(sb.length() - 1);
                    count--;
                }
                count = 1;
            }

            sb.append(S.charAt(i));   //重点: incrementally append the letter into a string
        }

        while (count > 2) {
            sb.deleteCharAt(sb.length() - 1);
            count--;
        }

        return sb.toString();
    }

}
