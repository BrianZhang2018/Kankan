package category.BFS.wordLadderI.preCalculatedAdjacentWildcardNodes;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/, Return the length of the shortest transformation sequence
 *
 * Problem: BFS traverse undirected and unweighted graph
 *
 * We will essentially be working with an undirected and unweighted graph with words as nodes and edges between words which differ by just one letter.
 * The problem boils down to finding the shortest path from a start node to a destination node, if there exists one. Hence it can be solved using Breadth First Search approach.
 *
 * Time Complexity: O(N*L), where L is the length of each word and N is the total number of words in the input word list.
 * check the detailed analysis from below code for time complexity
 *
 * Updated by brianzhang on 01/28/21
 */
public class WordLadderI {

    public static void main(String[] args) {
        WordLadderI test = new WordLadderI();
        System.out.println(test.ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int wordLength = beginWord.length(); // Since all words are of same length.

        // Pre-processing: get all adjacent wildcard words by changing one letter to "*"
        // Time Complexity: O(N*L), e.g. h*t -> hot, hat
        Map<String, List<String>> adjacentDict = new HashMap<>();
        for(String word : wordList){
            for (int i = 0; i < wordLength; i++) {
                // "Key" is the generic word (wildcard word), "Value" is a list of words which have the same intermediate generic word.
                String wildcardWord = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
                adjacentDict.putIfAbsent(wildcardWord, new ArrayList<>());
                adjacentDict.get(wildcardWord).add(word);
            }
        }
        // Queue for BFS
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        // visited to make sure we don't repeat processing same word
        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 0; // steps
        // TC: O(N*L*M) = O(N*L), N is the number of word in wordList, L is the average length of each word, M=(N*L) is constant since it's pre-calculated in above, so can be ignored.
        // 下面就是一个standard bfs 找最短路径的套路解法, e.g. CutOffTreesForGolfEventBFSGetMinSteps.java
        while (!queue.isEmpty()) {  // TC: O(N)
            level++;
            int size = queue.size();
            for(int j=0; j<size; j++){ // O(N), n is wordList.size
                String word = queue.poll();
                for (int i=0; i<word.length(); i++) {   // TC: O(L*M), M is constant, e.g. 26

                    String wildCardWord = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
                    // get all the words which map to the same wildcard word. // TC: N*L, constant complexity as already calculated in above
                    for (String adjacentWord : adjacentDict.getOrDefault(wildCardWord, new ArrayList<>())) {  // TC: O(M), M=N*L, pre-processing in above
                        // reached the end word
                        if (adjacentWord.equals(endWord)) {
                            return level + 1; // add "endWord" itself (+ 1)
                        }
                        // Otherwise, add it to the BFS Queue and mark it as visited
                        if (!visited.contains(adjacentWord)) {
                            visited.add(adjacentWord);
                            queue.add(adjacentWord);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
