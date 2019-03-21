import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TrieTest {
    @Test
    void search() {
        Trie.Node root = new Trie.Node();

        Trie.add("app", root);
        Trie.add("apple", root);
        Trie.add("applause", root);
        Trie.add("accord", root);
        Trie.add("b", root);

        assertTrue(Trie.search("app", root));
        assertTrue(Trie.search("apple", root));
        assertTrue(Trie.search("applause", root));
        assertTrue(Trie.search("accord", root));
        assertTrue(Trie.search("b", root));

        Trie.delete("app", root);
        Trie.delete("apple", root);
        Trie.delete("b", root);

        assertFalse(Trie.search("app", root));
        assertFalse(Trie.search("apple", root));
        assertTrue(Trie.search("applause", root));
        assertTrue(Trie.search("accord", root));
        assertFalse(Trie.search("b", root));

        Trie.delete("applause", root);
        Trie.delete("accord", root);

        assertFalse(Trie.search("app", root));
        assertFalse(Trie.search("apple", root));
        assertFalse(Trie.search("applause", root));
        assertFalse(Trie.search("accord", root));
        assertFalse(Trie.search("b", root));
    }
}