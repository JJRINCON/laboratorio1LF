package views;

import models.Node;
import models.Tree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class GeneralTreePanel extends JPanel {

    private DefaultTreeModel treeModel;
    private JTree jTree;

    public GeneralTreePanel(Tree tree){
        setLayout(new GridLayout(1,1));
        jTree = new JTree();
        paintTree(tree.getRoot());
        add(new JScrollPane(jTree));
    }

    public void paintTree(Node root) {
        DefaultMutableTreeNode visualRoot = new DefaultMutableTreeNode(root.getData());
        for (Node child : root.getChildren()) {
            addChild(visualRoot, child);
        }
        treeModel = new DefaultTreeModel(visualRoot);
        jTree.setModel(treeModel);
        //jTree.setCellRenderer(new RenderNode());
        jTree.repaint();
        expandAllNodes();
    }

    private void addChild(DefaultMutableTreeNode father, Node base) {
        DefaultMutableTreeNode visualNode = new DefaultMutableTreeNode(base.getData());
        father.add(visualNode);
        for (Node child : base.getChildren()) {
            addChild(visualNode, child);
        }
    }

    private void expandAllNodes() {
        for (int i = 0; i < jTree.getRowCount(); ++i) {
            jTree.expandRow(i);
        }
        if (jTree.getRowCount() != jTree.getRowCount()) {
            expandAllNodes();
        }
    }
}
