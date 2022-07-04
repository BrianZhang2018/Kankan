package category.graph.topologicalsort;

import java.util.*;

/**
 * https://www.youtube.com/watch?v=RIrTuf4DfPE
 * https://happygirlzt.com/code/269.html
 * https://zhuhan0.blogspot.com/2017/06/leetcode-269-alien-dictionary.html
 *
 * 构建一个directed graph通过比较两个word的character, 然后用拓扑排序得出字符的order
 *
 * Created by brianzhang on 3/29/20.
 */
public class AlienDictionary {

    public static void main(String[] args) {
        AlienDictionary ad = new AlienDictionary();
        System.out.println(ad.alienOrder(new String[]{"wrt","wrf","eee","err","rftt"}));
    }

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        int[] inDegree = new int[26];
        buildGraph(words, graph, inDegree);

        String orderedStr = topologicalBFSSort(graph, inDegree);
        return orderedStr.length() == graph.size() ? orderedStr : ""; // not equal mean it's Acyclic graph, (存在环, 那就是无序了)
        // graph.size() = count of all unique Characters, or you can consider it as all vertexes in a graph
    }

    // template method
    private void buildGraph(String[] words, Map<Character, Set<Character>> graph, int[] inDegree) {
        // initiate all the vertex in your graph
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.put(c, new HashSet<>());
            }
        }

        // build graph and inDegree count
        for (int i = 1; i < words.length; i++) {
            String first = words[i-1];
            String second = words[i];
            int len = Math.min(first.length(), second.length());

            for (int j = 0; j < len; j++) {
                char out = first.charAt(j);
                char in = second.charAt(j);
                if (out != in) {
                    if (!graph.get(out).contains(in)) {
                        graph.get(out).add(in);
                        inDegree[in - 'a']++;
                    }
                    break;
                }
                // handle ["abc", "ab"] case, result is ""
                if (j + 1 == len && first.length() > second.length()) {
                    graph.clear();
                    return;
                }
            }
        }
    }
    // template method: bfs
    private String topologicalBFSSort(Map<Character, Set<Character>> graph, int[] inDegree) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : graph.keySet()) {
            if (inDegree[c - 'a'] == 0) {
                queue.offer(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for (char neighbor : graph.get(c)) {
                inDegree[neighbor - 'a']--;
                if (inDegree[neighbor - 'a'] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return sb.toString();
    }
}
