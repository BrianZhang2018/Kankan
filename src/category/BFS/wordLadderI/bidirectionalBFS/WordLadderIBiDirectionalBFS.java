package category.BFS.wordLadderI.bidirectionalBFS;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/solution/
 *
 * Bidirectional BFS search
 *
 * time complexity: O(n*l*26) -> O(n*l),l = len(word), n=|wordList|
 *
 * Created by brian Zhang on 8/21/18.
 */
public class WordLadderIBiDirectionalBFS {
    public static void main(String[] args) {
        System.out.println(ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot", "hat", "dot", "dog", "lot", "log", "cog"))));
        System.out.println(ladderLengthRecursive("hit", "log", new ArrayList<>(Arrays.asList("hot", "hat", "dot", "dog", "lot", "log", "cog"))));
        //System.out.println(ladderLengthRecursive("nanny", "aloud", new ArrayList(largeTestData)));
    }

    // Solution-1: Bidirectional BFS
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || !wordList.contains(endWord)) return 0;

        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        int steps = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // optimization, 选小的queue去扩展
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
                System.out.println("beginSet: " + beginSet);
                System.out.println("endSet: " + endSet);
            }

            Set<String> temp = new HashSet<>();
            // time complexity: O(n*l*26) -> O(n*l),l = len(word), n=|wordList|
            for (String word : beginSet) { // O(n)
                char[] chs = word.toCharArray();
                for (int i = 0; i < chs.length; i++) {  // O(l)
                    for (char c = 'a'; c <= 'z'; c++) { // O(26)
                        char old = chs[i];
                        chs[i] = c;
                        String newWord = String.valueOf(chs);
                        if (endSet.contains(newWord)) {
                            return steps + 1;
                        }
                        if (wordList.contains(newWord)) {
                            temp.add(newWord);
                            wordList.remove(newWord);
                        }
                        chs[i] = old; //backtracking
                    }
                }
            }
            beginSet = temp;
            steps++;
        }
        return 0;
    }

    // Solution-2: recursive bfs - same idea with above one
    public static int ladderLengthRecursive(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return 0;
        }
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;

        Set<String> bSet = new HashSet<>(Arrays.asList(beginWord));
        Set<String> eSet = new HashSet<>(Arrays.asList(endWord));
        return bfs(bSet, eSet, dict, 1);
    }

    public static int bfs(Set<String> bSet, Set<String> eSet, Set<String> dict, int level) {
        if (bSet.size() == 0 || eSet.size() == 0) return 0; // last temp is empty means not able to find word, so exit with 0 value.
        dict.removeAll(bSet);

        Set<String> temp = new HashSet<>();
        for (String s : bSet) {
            char[] ch = s.toCharArray();
            for (int i = 0; i < ch.length; i++) {
                char old = ch[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    ch[i] = c;
                    String word = new String(ch);
                    if (dict.contains(word)) {
                        if (eSet.contains(word))
                            return level + 1;
                        else
                            temp.add(word);
                    }
                }
                ch[i] = old;
            }
        }
        return temp.size() != 0 && temp.size() > eSet.size() ? bfs(eSet, temp, dict, level + 1) : bfs(temp, eSet, dict, level + 1);
    }
}