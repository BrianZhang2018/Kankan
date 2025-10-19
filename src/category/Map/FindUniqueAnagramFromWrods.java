package category.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindUniqueAnagramFromWrods {

    public static List<String> findAnagramWords(String[] words) {
        Map<String, List<String>> map = new HashMap<>();

        // Group by normalized pattern
        for (String word : words) {
            String normalized = normalizeWord(word);
            map.computeIfAbsent(normalized, k -> new ArrayList<>()).add(word);
        }

        // Return words that have anagrams (group size > 1)
        List<String> result = new ArrayList<>();
        for (List<String> group : map.values()) {
            if (group.size() > 1) {
                result.addAll(group); // Add all anagram words
            }
        }

        return result;
    }

    private static String normalizeWord(String word) {
        // Convert to lowercase and sort characters
        char[] chars = word.toLowerCase().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        // Example 1
        String[] words1 = { "listen", "silent", "hello", "world", "enlist" };
        System.out.println("Input: " + Arrays.toString(words1));
        System.out.println("Unique words: " + findAnagramWords(words1));
        // Output: [listen, hello, world]

        // Example 2
        String[] words2 = { "cat", "act", "dog", "god", "tac", "bird" };
        System.out.println("\nInput: " + Arrays.toString(words2));
        System.out.println("Unique words: " + findAnagramWords(words2));
        // Output: [cat, dog, bird]

        // Example 3
        String[] words3 = { "apple", "pale", "leap", "peal", "banana" };
        System.out.println("\nInput: " + Arrays.toString(words3));
        System.out.println("Unique words: " + findAnagramWords(words3));
        // Output: [apple, banana]
    }
}
