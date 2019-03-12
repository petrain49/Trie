public class Trie {
    static class TrieNode {
        TrieNode[] children = new TrieNode[128]; //массив на 128 элементов
        boolean fin; //является ли узел конечным
    }

    public static void insertString(TrieNode root, String s) {
        TrieNode v = root;
        for (char ch : s.toCharArray()) {
            TrieNode next = v.children[ch];
            if (next == null)
                v.children[ch] = next = new TrieNode();
            v = next;
        }
        v.fin = true;
    }

    public static void main(String[] args) {
        TrieNode root = new TrieNode();
        insertString(root, "hello");
        insertString(root, "world");
        insertString(root, "heck");
    }
}