package category.String;

/**
 * "baaabbabbb" -> "aabbabb" (7)
 *
 * Created by brianzhang on 10/20/19.
 */
public class FindConsecutiveSubstringWithLessThanTwoConsecutiveCharacters {
    public static void main(String[] args) {
        System.out.println(solution("baaabbabbb"));
    }

    public static int solution(String s) {
        if (s.length() < 3) return s.length();
        // Initialize temporary and final ans to 2 as this is the minimum length of substring when length of the given string is greater than 2
        int temp = 2;
        int ans = 2;
        // Traverse the string from the third character to the last
        for (int i = 2; i < s.length(); i++) {
            // If no three consecutive characters are same then increment temporary count
            if (s.charAt(i) != s.charAt(i - 1) || s.charAt(i) != s.charAt(i - 2))
                temp++;
            else  // Else update the final ans and reset the temporary count
            {
                ans = Math.max(temp, ans);
                temp = 2;
            }
        }

        ans = Math.max(temp, ans);
        return ans;
    }
}
