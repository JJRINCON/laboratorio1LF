package views;

import controllers.Events;
import exceptions.EmptyTextFieldException;
import models.Production;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddProductionsPanel extends JPanel {

    private JList<String> productionsList;
    private JTextField txtLeft;
    private JTextField txtRight;

    public AddProductionsPanel(ActionListener listener){
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        productionsList = new JList<>();
        add(productionsList, BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder("Producciones"));
        JPanel txtPanel = new JPanel(new GridLayout(2,3));
        txtPanel.setBackground(Color.WHITE);
        txtLeft = new JTextField();
        txtPanel.add(txtLeft);
        JLabel arrowLb = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/resources/arrow.png"))
                                .getImage().getScaledInstance(50,20, Image.SCALE_SMOOTH)));
        arrowLb.setHorizontalAlignment(SwingConstants.CENTER);
        arrowLb.setOpaque(true);
        arrowLb.setBackground(Color.WHITE);
        txtPanel.add(arrowLb);
        txtRight = new JTextField();
        txtPanel.add(txtRight);

        txtPanel.add(new JLabel("  "));
        JButton addBtn = new JButton("Agregar");
        addBtn.setActionCommand(Events.ADD_GRAMMAR_ACTIONS.toString());
        addBtn.addActionListener(listener);
        addBtn.setName(Events.ADD_PRODUCTION.toString());
        addBtn.setBackground(Color.decode("#17A589"));
        addBtn.setForeground(Color.WHITE);
        txtPanel.add(addBtn);
        add(txtPanel, BorderLayout.SOUTH);
    }

    public void updateProductions(ArrayList<Production> productions){
        DefaultListModel<String> model = new DefaultListModel<>();
        for(Production production : productions){
            model.addElement(production.toString());
        }
        productionsList.setModel(model);
    }

    public String getTxtLeft() throws EmptyTextFieldException {
        if(txtLeft.getText().isEmpty()){
            throw new EmptyTextFieldException();
        }else{
            return txtLeft.getText();
        }
    }

    public String getTxtRight() throws EmptyTextFieldException {
        if(txtRight.getText().isEmpty()){
            throw new EmptyTextFieldException();
        }else{
            return txtRight.getText();
        }
    }

    public void clearTextFields(){
        txtLeft.setText("");
        txtRight.setText("");
    }
}
