package models;

import java.util.ArrayList;

public class Node {

    private CharacterString data;
    private ArrayList<Node> children;

    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(CharacterString data) {
        this.data = data;
        children = new ArrayList<Node>();
    }

    public Node addChild(Node node) {
        children.add(node);
        return node;
    }

    @Override
    public String toString() {
        return "Node{" + "data=" + data.getCharacters() + "}  " + "  {" +"produccion: "+ data.getActualProduction() + "}";
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public CharacterString getData() {
        return data;
    }
}
