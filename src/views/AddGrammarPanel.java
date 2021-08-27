package views;

import controllers.Events;
import exceptions.EmptyTextFieldException;
import exceptions.NumberOfSymbolsException;
import models.Production;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddGrammarPanel extends JPanel {

    public static final String VARIABLES_PANEL_TITLE = "Variables";
    public static final String SYMBOLS_PANEL_TITLE = "Simbolos terminales";
    private AddSymbolsPanel variablesPanel;
    private AddSymbolsPanel symbolsPanel;
    private AddProductionsPanel productionsPanel;
    private JComboBox<Character> initialSymbolCombo;
    private JPanel initialSymbolPanel;

    public AddGrammarPanel(ActionListener listener){
        setBackground(Color.WHITE);
        setLayout(new GridLayout(2,2,10,10));
        variablesPanel = new AddSymbolsPanel(listener, VARIABLES_PANEL_TITLE, Events.ADD_VARIABLE.toString());
        add(variablesPanel);
        symbolsPanel = new AddSymbolsPanel(listener, SYMBOLS_PANEL_TITLE, Events.ADD_SYMBOL.toString());
        add(symbolsPanel);
        productionsPanel = new AddProductionsPanel(listener);
        add(productionsPanel);
        initInitialSymbolPanel(listener);
    }

    private void initInitialSymbolPanel(ActionListener listener){
        initialSymbolPanel = new JPanel(new BorderLayout());
        initialSymbolPanel.setBackground(Color.WHITE);
        initialSymbolCombo = new JComboBox<>();
        initialSymbolCombo.setBackground(Color.WHITE);
        initialSymbolCombo.setBorder(BorderFactory.createTitledBorder("Seleccione el simbolo inicial"));
        initialSymbolPanel.add(initialSymbolCombo, BorderLayout.NORTH);
        JButton acceptBtn = new JButton("Crear gramatica");
        acceptBtn.setActionCommand(Events.CREATE_GRAMMAR.toString());
        acceptBtn.addActionListener(listener);
        acceptBtn.setBackground(Color.decode("#46AFF6"));
        acceptBtn.setForeground(Color.WHITE);
        initialSymbolPanel.add(acceptBtn, BorderLayout.SOUTH);
        add(initialSymbolPanel);
    }

    public void updateInitialSymbolCombo(ArrayList<Character> variables){
        DefaultComboBoxModel<Character> model = new DefaultComboBoxModel<>();
        for(char variable : variables){
            model.addElement(variable);
        }
        initialSymbolCombo.setModel(model);
    }

    public char getSymbol() throws NumberOfSymbolsException, EmptyTextFieldException {
        return symbolsPanel.getSymbol();
    }

    public char getVariable() throws NumberOfSymbolsException, EmptyTextFieldException {
        return variablesPanel.getSymbol();
    }

    public String getLeftSide() throws EmptyTextFieldException {
        return productionsPanel.getTxtLeft();
    }

    public String getRightSide() throws EmptyTextFieldException {
        return productionsPanel.getTxtRight();
    }

    public char getInitialSymbol(){
        return (char) initialSymbolCombo.getSelectedItem();
    }

    public void updateSymbols(ArrayList<Character> symbols){
        symbolsPanel.updateList(symbols);
    }

    public void updateVariables(ArrayList<Character> variables){
        variablesPanel.updateList(variables);
    }

    public void updateProductions(ArrayList<Production> productions){
        productionsPanel.updateProductions(productions);
    }

    public void clearProductionPanelTextFields(){
        productionsPanel.clearTextFields();
    }

    public void clearVariableTxt(){
        variablesPanel.clearSymbolTxt();
    }

    public void clearSymbolTxt(){
        symbolsPanel.clearSymbolTxt();
    }
}
