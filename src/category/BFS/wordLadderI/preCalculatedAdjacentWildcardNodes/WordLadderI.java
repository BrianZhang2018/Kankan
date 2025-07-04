package category.BFS.wordLadderI.preCalculatedAdjacentWildcardNodes;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/, Return the length of the shortest
 * transformation sequence
 * linkedIn 高频
 * Problem: BFS traverse undirected and unweighted graph
 *
 * We will essentially be working with an undirected and unweighted graph with
 * words as nodes and edges between words which differ by just "one letter".
 * The problem boils down to finding the shortest path from a start node to a
 * destination node, if there exists one.
 * Hence, it can be solved using Breadth First Search approach.
 *
 * Time Complexity: O(N*L), where L is the length of each word and N is the
 * total number of words in the input word list.
 * check the detailed analysis from below code for time complexity
 *
 * Updated by brianzhang on 01/28/21
 */
public class WordLadderI {
    public static void main(String[] args) {
        System.out.println(ladderLength("hit", "cog",
                new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int wordLength = beginWord.length(); // Since all words are of same length.

        // Pre-processing: get all adjacent wildcard words by changing one letter to "*"
        // "Key": a wildcard pattern formed by replacing one letter with '*', e.g. "*ot"
        // "Value": all words that match this pattern, e.g. [hot, dot, lot]
        // Time Complexity: O(N*L), e.g. h*t -> hot, hat
        Map<String, List<String>> adjacentDict = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < wordLength; i++) {
                String wildcardWord = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
                adjacentDict.putIfAbsent(wildcardWord, new ArrayList<>());
                adjacentDict.get(wildcardWord).add(word);
            }
        }
        // Print out the wildcard graph
        for (Map.Entry<String, List<String>> entry : adjacentDict.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        Queue<String> queue = new LinkedList<>(); // Queue for BFS
        queue.add(beginWord);
        HashSet<String> visited = new HashSet<>(); // make sure don't repeat processing same word
        visited.add(beginWord);
        int level = 0; // steps
        // TC: O(N*L*M) = O(N*L), N is the number of word in wordList, L is the average
        // length of each word, M=(N*L) is constant since it's pre-calculated in above,
        // so can be ignored.
        // 下面就是一个standard bfs 找最短路径的套路解法, e.g.
        // CutOffTreesForGolfEventBFSGetMinSteps.java
        while (!queue.isEmpty()) { // TC: O(N)
            level++;
            int size = queue.size();
            for (int j = 0; j < size; j++) { // O(N), n is wordList.size
                String word = queue.poll();
                for (int i = 0; i < word.length(); i++) { // TC: O(L*M), M is constant, e.g. 26
                    String wildcardWord = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
                    // get all the words that map to the same wildcard word. // TC: N*L, constant complexity as already calculated in above
                    for (String adjacentWord : adjacentDict.getOrDefault(wildcardWord, new ArrayList<>())) {  // TC: O(M), M=N*L, pre-processing in above
                        // reached the end word
                        if (adjacentWord.equals(endWord)) {
                            return level + 1; // add "endWord" itself (+ 1)
                        }
                        // Otherwise, add it to the BFS Queue and mark it as visited
                        if (visited.add(adjacentWord)) {
                            queue.add(adjacentWord);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
