package category.tree.trie.LinkedList;

/**
 * a trie, also called digital tree, radix tree or prefix tree is a kind of search tree
 * <p>
 * Created by brianzhang on 11/4/18.
 */
public class TrieImpl {
    public static void main(String args[]) {
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        String output[] = {"Not present in trie", "Present in trie"};
        root = new TrieNode();

        // Construct trie
        for (int i = 0; i < keys.length; i++)
            insert(keys[i]);

        // Search for different keys
        if (search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if (search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if (search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if (search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);
    }

    // Alphabet size (# of symbols)
    private static final int ALPHABET_SIZE = 26;
    private static TrieNode root;

    // TrieNode class
    public static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        String word = "";
        // isEndOfWord is true if the node represents end of a word
        boolean isEndOfWord = false;

        TrieNode() {
        }
    }

    // If not present, inserts word into trie
    // If the word is prefix of trie node, just marks leaf node
    public static void insert(String word) {
        TrieNode curr = root;
        for (int level = 0; level < word.length(); level++) {
            int index = word.charAt(level) - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        // mark last node as leaf
        curr.isEndOfWord = true;
        curr.word = word;
    }

    // Returns true if word presents in trie, else false
    public static boolean search(String word) {
        TrieNode curr = root;

        for (int level = 0; level < word.length(); level++) {
            int index = word.charAt(level) - 'a';
            if (curr.children[index] == null)
                return false;
            curr = curr.children[index];
        }
        return (curr != null && curr.isEndOfWord);
    }

}

