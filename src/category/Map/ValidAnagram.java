package category.Map;

public class ValidAnagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram("rat", "car"));
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;

        int[] bucket = new int[128];
        for (char c : s.toCharArray()) {
            bucket[c]++;
        }

        for (char c : t.toCharArray()) {
            if (--bucket[c] < 0)
                return false;
        }

        return true;
    }
}
