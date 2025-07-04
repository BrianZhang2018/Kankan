package category.DFSBacktracing.wordLadder;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder-ii/
 *
 * BFS
 *
 * Created by brianzhang on 10/12/18.
 */
public class WordLadderIIBfs {
    public static void main(String[] args) {
        Set<String> dict = new HashSet<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        System.out.println(findLadders("hit", "cog", dict));
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, Set<String> dict) {
        List<List<String>> result = new ArrayList<>();
        LinkedList<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1, null));
        dict.add(endWord);

        HashSet<String> visited = new HashSet<>(), unvisited = new HashSet<>();
        unvisited.addAll(dict);

        int minStep = 0, preNumSteps = 0;
        while (!queue.isEmpty()) {
            WordNode top = queue.remove();
            String word = top.word;
            int currNumSteps = top.numSteps;
            if (word.equals(endWord)) {
                if (minStep == 0) {
                    minStep = top.numSteps;
                }
                if (top.numSteps == minStep && minStep != 0) {
                    //nothing
                    ArrayList<String> t = new ArrayList<>();
                    t.add(top.word);
                    while (top.pre != null) {
                        t.add(0, top.pre.word);
                        top = top.pre;
                    }
                    result.add(t);
                    continue;
                }
            }
            if (preNumSteps < currNumSteps) {
                unvisited.removeAll(visited);
            }
            preNumSteps = currNumSteps;
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char temp = arr[i];
                    if (arr[i] != c) {
                        arr[i] = c;
                    }
                    String newWord = new String(arr);
                    if (unvisited.contains(newWord)) {
                        queue.add(new WordNode(newWord, top.numSteps + 1, top));
                        visited.add(newWord);
                    }
                    arr[i] = temp;
                }
            }
        }
        return result;
    }
}
class WordNode {
    String word;
    int numSteps;
    WordNode pre;

    public WordNode(String word, int numSteps, WordNode pre) {
        this.word = word;
        this.numSteps = numSteps;
        this.pre = pre;
    }
}