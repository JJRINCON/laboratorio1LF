package models;

import java.util.ArrayList;

public class Tree {

    protected Node root;

    public Tree() {
        root = new Node();
    }

    public Tree(CharacterString rootData) {
        root = new Node(rootData);
    }

    public Node add(Node father, CharacterString data) {
        return father.addChild(new Node(data));
    }

    public Node add(Node father, Node data) {
        return father.addChild(data);
    }

    public Node getRoot() {
        return root;
    }

    public Node search(String data) {
        return search(root, data);
    }

    private Node search(Node actual, String data) {
        if (actual.getData().getCharacters().equals(data)) {
            return actual;
        }
        for (Node child : actual.getChildren()) {
            Node result = search(child, data);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public Node getFatherOf(String data) {
        return getFatherOf(root, data);
    }

    private Node getFatherOf(Node base, String data) {
        for (Node child : base.getChildren()) {
            if (child.getData().getCharacters().equals(data)) {
                return base;
            } else {
                Node father = getFatherOf(child, data);
                if (father != null) {
                    return father;
                }
            }
        }
        return null;
    }

    public ArrayList<Node> getPathOf(String data) {
        ArrayList<Node> list = new ArrayList<>();
        Node actual = search(data);
        while(!(actual.getData().getCharacters().equals(root.getData().getCharacters()))) {
            list.add(actual);
            actual = getFatherOf(actual.getData().getCharacters());
        }
        return list;
    }
}
