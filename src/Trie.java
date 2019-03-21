import java.util.*;

public class Trie {
    //добавление строки в дерево
    public static void add(String text, Node curNode) {
        if (curNode.getLvl() == text.length() - 1) curNode.setFin(true);
        else {
            int nextLvl = curNode.getLvl() + 1;
            char nextSymbol = text.charAt(nextLvl);

            // создание новых узлов
            if (!curNode.getChildren().containsKey(nextSymbol)) {
                Node child = new Node(nextSymbol, nextLvl);

                child.setParent(curNode);
                curNode.getChildren().put(nextSymbol, child);
                Trie.add(text, child);
            }

            //проход по существующим узлам
            else {
                Trie.add(text, curNode.getChildren().get(nextSymbol));
            }
        }
    }

    public static boolean search(String text, Node curNode) {
        //если узел последнего символа строки отмечен как конечный -> true
        if (curNode.getLvl() == text.length() - 1) return curNode.isFin();

        // проход по существующим узлам
        char nextSymbol = text.charAt(curNode.getLvl() + 1);
        if (curNode.getChildren().containsKey(nextSymbol)) {
            return Trie.search(text, curNode.getChildren().get(nextSymbol));
        }
        // если нужного узла не оказалось
        return false;
    }

    public static void searchByPrefix(String prefix, Node curNode) {
        if (curNode.getLvl() < prefix.length() - 1) {
            char nextSymbol = prefix.charAt(curNode.getLvl() + 1);
            searchByPrefix(prefix, curNode.getChildren().get((nextSymbol)));
        }
        else {

        }
    }

    public static void delete(String text, Node curNode) {
        if (curNode.getLvl() == text.length() - 1) {
            curNode.setFin(false);
            deleteNodes(text, curNode);
        }
        else {
            try {
                char nextSymbol = text.charAt(curNode.getLvl() + 1);
                delete(text, curNode.getChildren().get(nextSymbol));
            } catch (Exception e) { throw new IllegalArgumentException(); }
        }
    }

    private static void deleteNodes(String text, Node curNode) {
        // если вдруг на ветви есть поддеревья или она содержит еще наборы символов
        if (curNode.getChildren().size() > 1 && curNode.getLvl() > -1
                || curNode.getChildren().size() > 0 && curNode.getLvl() == text.length() - 1
                || curNode.isFin()) return;

        // если у текущего узла есть дети
        if (!curNode.getChildren().isEmpty() && curNode.getLvl() < text.length() - 1) {
            char nextSymbol = text.charAt(curNode.getLvl() + 1);
            curNode.getChildren().remove(nextSymbol);
        }

        if (curNode.getLvl() > -1) {
            deleteNodes(text, curNode.getParent());
        }
    }

    public static void main(String[] args) {}

    // узлы
    public static class Node {
        private char key;
        private boolean fin;
        private int lvl;
        private Node parent = null;
        private HashMap<Character, Node> children = new HashMap<Character, Node>();

        Node() {
            this.fin = false;
            this.lvl = -1;
        }

        Node(Character key, int c) {
            //key = null
            this.key = key;
            this.fin = false;
            this.lvl = c;
        }
        //getters
        public int getLvl() {
            return this.lvl;
        }
        public int getKey() {
            return this.key;
        }
        public boolean isFin() {
            return this.fin;
        }
        public Node getParent() {
            return this.parent;
        }
        public HashMap<Character, Node> getChildren() {
            return this.children;
        }

        //setters
        public void setKey(Character key) {
            this.key = key;
        }
        public void setLvl(int lvl) {
            this.lvl = lvl;
        }
        public void setFin(boolean fin) {
            this.fin = fin;
        }
        public void setParent(Node parent) { this.parent = parent; }
    }
}