package views;

import controllers.Events;
import exceptions.EmptyTextFieldException;
import exceptions.NumberOfSymbolsException;
import models.Node;
import models.Production;
import models.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private static final String TITLE = "Gramaticas";
    private static final String ICON_PATH = "/resources/app_icon.png";
    public static final String LANGUAGE_PANEL_TITLE = "Lenguaje";
    private AddGrammarPanel addGrammarPanel;
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private LanguagePanel languagePanel;

    public MainFrame(ActionListener listener){
        setTitle(TITLE);
       // setIconImage(new ImageIcon(getClass().getResource(ICON_PATH)).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        initComponents(listener);
    }

    private void initComponents(ActionListener listener){
        tabbedPane = new JTabbedPane();
        mainPanel = new JPanel(new GridLayout(1,1));
        initInitialPanel(listener);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void initMenu(ActionListener listener){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        JMenuItem newGrammarOption = new JMenuItem("Nueva Gramatica");
        newGrammarOption.setActionCommand(Events.NEW_GRAMMAR.toString());
        newGrammarOption.addActionListener(listener);
        menu.add(newGrammarOption);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    public void initInitialPanel(ActionListener listener){
        InitialPanel initialPanel = new InitialPanel(listener);
        mainPanel.add(initialPanel);
    }

    public void initAddGrammarPanel(ActionListener listener) {
        initMenu(listener);
        mainPanel.removeAll();
        addGrammarPanel = new AddGrammarPanel(listener);
        mainPanel.add(addGrammarPanel, BorderLayout.CENTER);
        mainPanel.repaint();
        mainPanel.updateUI();
        repaint();
    }

    public void initLanguagePanel(ActionListener listener, Tree tree){
        mainPanel.removeAll();
        tabbedPane = new JTabbedPane();
        languagePanel = new LanguagePanel(listener, tree);
        tabbedPane.add(LANGUAGE_PANEL_TITLE, languagePanel);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.updateUI();
        mainPanel.repaint();
    }

    public void updateInitialSymbolCombo(ArrayList<Character> variables){
        addGrammarPanel.updateInitialSymbolCombo(variables);
    }

    public char getSymbol() throws NumberOfSymbolsException, EmptyTextFieldException {
        return addGrammarPanel.getSymbol();
    }

    public char getVariable() throws NumberOfSymbolsException, EmptyTextFieldException {
        return addGrammarPanel.getVariable();
    }

    public String getLeftSide() throws EmptyTextFieldException {
        return addGrammarPanel.getLeftSide();
    }

    public String getRightSide() throws EmptyTextFieldException {
        return addGrammarPanel.getRightSide();
    }

    public String getWord() throws EmptyTextFieldException {
        return languagePanel.getWord();
    }

    public void addParticularDerivationTree(ArrayList<Node> pathOfWord, String word){
        languagePanel.addParticularDerivationTree(pathOfWord, word);
    }

    public char getInitialSymbol(){
        return addGrammarPanel.getInitialSymbol();
    }

    public void updateSymbols(ArrayList<Character> symbols){
        addGrammarPanel.updateSymbols(symbols);
    }

    public void updateVariables(ArrayList<Character> variables){
        addGrammarPanel.updateVariables(variables);
    }

    public void updateProductions(ArrayList<Production> productions){
        addGrammarPanel.updateProductions(productions);
    }

    public void clearProductionPanelTextFields(){
        addGrammarPanel.clearProductionPanelTextFields();
    }

    public void clearVariableTxt(){
        addGrammarPanel.clearVariableTxt();
    }

    public void clearSymbolTxt(){
        addGrammarPanel.clearSymbolTxt();
    }
}
