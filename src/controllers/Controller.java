package controllers;

import exceptions.*;
import models.Manager;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private Manager manager;
    private MainFrame mainFrame;

    public Controller() {
        manager = new Manager();
        mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Events.valueOf(e.getActionCommand())) {
            case INITIAL_PANEL_ACTIONS:
                manageInitialPanelActions(e);
                break;
            case ADD_GRAMMAR_ACTIONS:
                manageAddGrammarActions(e);
                break;
            case CREATE_GRAMMAR:
                manageCreateGrammarAction();
                break;
            case LANGUAGE_PANEL_ACTIONS:
                manageLanguagePanelActions(e);
                break;
            case NEW_GRAMMAR:
                manageNewGrammarAction();
                break;
        }
    }

    private void manageInitialPanelActions(ActionEvent e) {
        String action = ((JButton) e.getSource()).getName();
        if (action.equals(Events.ADD_GRAMMAR.toString())) {
            mainFrame.initAddGrammarPanel(this);
            update();
        } else {
            System.exit(0);
        }
    }

    private void manageAddGrammarActions(ActionEvent e) {
        String action = ((JButton) e.getSource()).getName();
        if (action.equals(Events.ADD_SYMBOL.toString())) {
            manageAddSymbolAction();
        } else if (action.equals(Events.ADD_VARIABLE.toString())) {
            manageAddVariableAction();
        } else if(action.equals(Events.ADD_PRODUCTION.toString())){
            manageAddProductionAction();
        }
    }

    private void manageCreateGrammarAction() {
        manager.addInitialSymbol(mainFrame.getInitialSymbol());
        try {
            manager.verifyGrammar();
            manager.generateGeneralTree();
            mainFrame.initLanguagePanel(this, manager.getGeneralTree());
        } catch (NumberOfSymbolsException | NumberOfVariablesException | NumberOfProductionsException e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manageLanguagePanelActions(ActionEvent e) {
        String action = ((JButton) e.getSource()).getName();
        if(action.equals(Events.CHECK_WORD.toString())){
            String word = null;
            try {
                word = mainFrame.getWord();
                if(manager.verifyWord(word)){
                    JOptionPane.showMessageDialog(mainFrame, "Palabra perteneciente al lenguaje");
                    mainFrame.addParticularDerivationTree(manager.getPathOf(word), word);
                }else{
                    JOptionPane.showMessageDialog(mainFrame, "La palabra no pertenece al lenguaje");
                }
            } catch (EmptyTextFieldException ex) {
                JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void manageAddSymbolAction() {
        try {
            manager.addSymbol(mainFrame.getSymbol());
            mainFrame.updateSymbols(manager.getSymbols());
            mainFrame.clearSymbolTxt();
        } catch (NumberOfSymbolsException | RepeatedSymbolException | SymbolBelongingToVariablesException |
                    EmptyTextFieldException e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manageAddVariableAction() {
        try {
            manager.addVariable(mainFrame.getVariable());
            mainFrame.updateVariables(manager.getVariables());
            mainFrame.updateInitialSymbolCombo(manager.getVariables());
            mainFrame.clearVariableTxt();
        } catch (NumberOfSymbolsException | RepeatedSymbolException | SymbolBelongingToSymbolsException |
                EmptyTextFieldException e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manageAddProductionAction() {
        try {
            manager.addProduction(mainFrame.getLeftSide(), mainFrame.getRightSide());
            mainFrame.updateProductions(manager.getProductions());
            mainFrame.clearProductionPanelTextFields();
        } catch (EmptyTextFieldException | WrongProductionException | RepeatedProductionException e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manageNewGrammarAction() {
        mainFrame.initAddGrammarPanel(this);
        manager.restartGrammar();
    }

    public void update(){
        mainFrame.updateInitialSymbolCombo(manager.getVariables());
        mainFrame.updateProductions(manager.getProductions());
        mainFrame.updateVariables(manager.getVariables());
        mainFrame.updateSymbols(manager.getSymbols());
    }
}
