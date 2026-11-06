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

    private TrieNode find(String text) {
        TrieNode node = root;
        for (char c : text.toCharArray()) {
            node = node.children[c - 'a'];
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    boolean contains(String word) {
        TrieNode node = find(word);
        return node != null && node.isWordEnd;
    }

    // How many children a node has.
    private static int countChildren(TrieNode node) {
        int count = 0;
        for (TrieNode child : node.children) {
            if (child != null) {
                count++;
            }
        }
        return count;
    }

    // Removes a word. Only nodes that belong to this word alone are removed. The walk remembers the last node that
    // branches, since everything below that node is used by this word and no other part of the trie.
    boolean delete(String word) {
        TrieNode node = root;
        TrieNode lastBranch = null;
        int lastBranchIndex = -1;

        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            if (countChildren(node) > 1) {
                lastBranch = node;
                lastBranchIndex = index;
            }
            node = node.children[index];
        }

        // A prefix that isn't a word must not be deleted.
        if (!node.isWordEnd) {
            return false;
        }

        // Other words go on past this node, so only the word end is cleared.
        if (countChildren(node) > 0) {
            node.isWordEnd = false;
            return true;
        }

        // Nothing goes on past this node, so the chain from the last branch is removed.
        if (lastBranch != null) {
            lastBranch.children[lastBranchIndex] = null;
            return true;
        }

        // The word is the only one that starts with its first letter, so that link is removed from the root.
        root.children[word.charAt(0) - 'a'] = null;
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("any");
        trie.insert("anyone");

        System.out.println("delete(\"an\"): " + trie.delete("an"));
        System.out.println("delete(\"any\"): " + trie.delete("any"));
        System.out.println("contains(\"anyone\"): " + trie.contains("anyone"));
        System.out.println("contains(\"any\"): " + trie.contains("any"));
    }
}
