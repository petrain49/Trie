import java.util.*;

public class Trie {
    //добавление строки в дерево
    static void add(String text, Node curNode) {
        if (curNode.getC() == (text.length() - 1)) curNode.fin = true;
        else {
            //новые узлы
            if (!curNode.map.containsKey(text.charAt(curNode.getC() + 1))) {
                Node child = new Node(text.charAt(curNode.getC() + 1), (curNode.getC() + 1));
                curNode.map.put(text.charAt(curNode.getC() + 1), child);
                Trie.add(text, child);
            }
            //проход по существующим узлам
            else if (curNode.map.containsKey(text.charAt(curNode.getC() + 1))) {
                Trie.add(text, curNode.map.get(text.charAt(curNode.getC() + 1)));
            }
        }
    }
    public static void main(String[] args) {
        Node root = new Node();
        Trie.add("abcdefg", root);
        Trie.add("aoplk", root);
        Trie.add("booya", root);
        System.out.println(root.map);
    }
}

class Node {
    Character key;
    boolean fin;
    int c;
    Map<Character, Node> map = new HashMap<Character, Node>();

    Node() {
        this.key = null;
        this.fin = false;
        this.c = -1;
    }

    Node(Character key, int c) {
        this.key = key;
        this.fin = false;
        this.c = c;
    }

    int getC() {return this.c;}
    int getKey() {return this.key;}
    boolean isLeaf() {return this.fin;}
}