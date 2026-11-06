class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWordEnd = false;
}

class Trie {
    private final TrieNode root = new TrieNode();

    void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isWordEnd = true;
    }

    boolean contains(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children[c - 'a'];
            if (node == null) {
                return false;
            }
        }
        return node.isWordEnd;
    }
}

public class Main {
    public static void main(String[] args) {
        String[] dictionary = {"stop", "pots", "dog", "god", "cat"};
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }

        for (String word : dictionary) {
            String reversed = new StringBuilder(word).reverse().toString();
            System.out.println(word + ": its reverse is a word: " + trie.contains(reversed));
        }
    }
}
