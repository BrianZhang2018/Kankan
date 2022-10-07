package category.Sort.bucketSort;

public class FindFirstNonRepeatCharacter {
    public static void main(String[] args) {
        System.out.println(firstUniqChar("leetcode"));
    }

    public static int firstUniqChar(String s) {
        int[] bucket = new int[26];
        for (char c : s.toCharArray())
            bucket[c - 'a']++;

        for (int i = 0; i < s.length(); i++) {
            if (bucket[s.charAt(i) - 'a'] == 1)
                return i;
        }

        return -1;
    }
}
