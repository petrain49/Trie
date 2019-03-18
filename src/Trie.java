import java.util.*;

public class Trie {
    //добавление строки в дерево
    public static void add(String text, Node curNode) {
        if (curNode.getLvl() == text.length() - 1) curNode.fin = true;
        else {
            //создает новый объект, в хэшмэп текущего вставляется пара (следующий символ -> новый объект)
            if (!curNode.map.containsKey(text.charAt(curNode.getLvl() + 1))) {
                Node child = new Node(text.charAt(curNode.getLvl() + 1), (curNode.getLvl() + 1));
                curNode.map.put(text.charAt(curNode.getLvl() + 1), child);
                Trie.add(text, child);
            }
            //проход по существующим узлам
            else {
                Trie.add(text, curNode.map.get(text.charAt(curNode.getLvl() + 1)));
            }
        }
    }

    //поиск строки
    public static boolean search(String text, Node curNode) {
        //если узел последнего символа строки отмечен как конечный -> true
        if (curNode.getLvl() == text.length() - 1) return curNode.isFin();

        else if (curNode.map.containsKey(text.charAt(curNode.getLvl() + 1))) {
            return Trie.search(text, curNode.map.get(text.charAt(curNode.getLvl() + 1)));
        }
        return false;
    }

    public static void searchByPrefix(String prefix, Node curNode) {
        if (curNode.getLvl() < prefix.length() - 1) {
            searchByPrefix(prefix, curNode.map.get((prefix.charAt(curNode.getLvl() + 1))));
        }//TODO!!!
    }

    //удаление строки
    public static void delete(String text, Node curNode) {
        try {
            if (curNode.getLvl() < text.length() - 1) {
                Trie.delete(text, curNode.map.get(text.charAt(curNode.getLvl() + 1)));
            } else curNode.fin = false;
        }
        catch (Exception e) {
            System.out.println("Несуществующая строка");
        }
    }

    public static void main(String[] args) {
        Node root = new Node();
        Trie.add("abcdefg", root);
        Trie.add("aoplk", root);
        Trie.add("booya", root);
        System.out.println(Trie.search("aopl", root));
        System.out.println(Trie.search("aok", root));
        Trie.delete("booya", root);
        System.out.println(Trie.search("booya", root));
        System.out.println(root.map);
    }
}

class Node {
    Character key;
    boolean fin;
    int lvl;
    Map<Character, Node> map = new HashMap<Character, Node>();

    Node() {
        this.key = null;
        this.fin = false;
        this.lvl = -1;
    }

    Node(Character key, int c) {
        this.key = key;
        this.fin = false;
        this.lvl = c;
    }

    int getLvl() {return this.lvl;}
    int getKey() {return this.key;}
    boolean isFin() {return this.fin;}
}