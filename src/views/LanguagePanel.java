package views;

import controllers.Events;
import exceptions.EmptyTextFieldException;
import models.Node;
import models.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LanguagePanel extends JPanel {

    private JTextField wordTxt;
    private JTabbedPane tabbedPane;

    public LanguagePanel(ActionListener listener, Tree tree){
        setLayout(new BorderLayout());
        initWordPanel(listener);
        initOptionsPanel(listener, tree);
    }

    private void initWordPanel(ActionListener listener){
        JPanel wordPanel = new JPanel(new BorderLayout());
        wordTxt = new JTextField();
        JButton checkBtn = new JButton("Comprobar");
        checkBtn.setActionCommand(Events.LANGUAGE_PANEL_ACTIONS.toString());
        checkBtn.setName(Events.CHECK_WORD.toString());
        checkBtn.addActionListener(listener);
        wordPanel.add(wordTxt, BorderLayout.CENTER);
        wordPanel.add(checkBtn, BorderLayout.EAST);
        add(wordPanel, BorderLayout.NORTH);
    }

    private void initOptionsPanel(ActionListener listener, Tree tree){
        tabbedPane = new JTabbedPane();
        tabbedPane.add("Arbol general",new GeneralTreePanel(tree));
        add(tabbedPane, BorderLayout.CENTER);
    }

    public void addParticularDerivationTree(ArrayList<Node> pathOfWord, String word){
        tabbedPane.add(new ParticularDerivationTree(pathOfWord, word), word);
    }

    public String getWord() throws EmptyTextFieldException {
        if(wordTxt.getText().length() > 0){
            return wordTxt.getText();
        }else{
            throw new EmptyTextFieldException();
        }

    }
}
