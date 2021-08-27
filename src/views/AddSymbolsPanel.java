package views;

import controllers.Events;
import exceptions.EmptyTextFieldException;
import exceptions.NumberOfSymbolsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddSymbolsPanel extends JPanel {

    public static final String TXT_ADD_BTN = "Agregar";
    private JList<Character> list;
    private JTextField symbolTxt;

    public AddSymbolsPanel(ActionListener listener, String title, String name){
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(title));
        list = new JList<>();
        add(list, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new GridLayout(1,2));
        symbolTxt = new JTextField();
        actionPanel.add(symbolTxt);
        JButton addBtn = new JButton(TXT_ADD_BTN);
        addBtn.setActionCommand(Events.ADD_GRAMMAR_ACTIONS.toString());
        addBtn.addActionListener(listener);
        addBtn.setBackground(Color.decode("#17A589"));
        addBtn.setForeground(Color.WHITE);
        addBtn.setName(name);
        actionPanel.add(addBtn);
        add(actionPanel, BorderLayout.SOUTH);
    }

    public void updateList(ArrayList<Character> symbols){
        DefaultListModel<Character> model = new DefaultListModel<>();
        for(char symbol : symbols){
            if(symbol != 'λ'){
                model.addElement(symbol);
            }
        }
        list.setModel(model);
    }

    public char getSymbol() throws NumberOfSymbolsException, EmptyTextFieldException {
        if(symbolTxt.getText().length() > 1){
            throw new NumberOfSymbolsException();
        }else if(symbolTxt.getText().isEmpty()){
            throw new EmptyTextFieldException();
        }else{
            return symbolTxt.getText().charAt(0);
        }
    }

    public void clearSymbolTxt(){
        symbolTxt.setText("");
    }
}
