package category.BFS.wordLadderI;

import java.util.*;
/**
 * Pre-processed adjacent nodes + bidirectional bfs
 *
 * Time complexity:
 * Created by brianzhang on 12/3/20.
 */
public class WordLadderIBiBFSAndPreProcessing {
    public static void main(String[] args) {
        System.out.println(ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Since all words are of same length.
        int L = beginWord.length();

        // Pre-processing: get all adjacent wildcard words by changing one letter to "*"
        Map<String, List<String>> allComboDict = new HashMap<>();
        wordList.forEach(word -> {
            for (int i = 0; i < L; i++) {
                // Key is the generic word (wildcard word)
                // Value is a list of words which have the same intermediate generic word.
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                allComboDict.putIfAbsent(newWord, new ArrayList<>());
                allComboDict.get(newWord).add(word);
            }
        });

        // Queue for BFS
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        // Visited to make sure we don't repeat processing same word.
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);
        int steps=1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            Set<String> temp = new HashSet<>();
            for(String word : beginSet){
                for (int i = 0; i < L; i++) {
                    // Intermediate words for current word
                    String wildCardWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                    // get all the words which share the same intermediate state, e.g. h*t.
                    for (String adjacentWord : allComboDict.getOrDefault(wildCardWord, new ArrayList<>())) {
                        if (adjacentWord.equals(endWord)) {
                            return steps + 1;
                        }
                        // Otherwise, add it to the BFS Queue. Also mark it visited
                        if (!visited.containsKey(adjacentWord)) {
                            visited.put(adjacentWord, true);
                            temp.add(adjacentWord);
                        }
                    }
                }
            }
            beginSet = temp;
            steps++;
        }
        return 0;
    }
}
