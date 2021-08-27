package views;

import controllers.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InitialPanel extends MyGridPanel{

    public InitialPanel(ActionListener listener){
        setBackground(Color.decode("#f8f8f8"));
        JLabel titlelLb = new JLabel("Gramaticas");
        titlelLb.setForeground(Color.decode("#041e47"));
        titlelLb.setFont(new Font("Arial", Font.ITALIC, 80));
        titlelLb.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titlelLb, 3,0,5,0.1);
        addComponent(new JLabel(""), 0,1,10,0.02);
        addComponent(createBtn(listener, "Nueva gramatica", Events.ADD_GRAMMAR.toString(), "#46AFF6"), 4,2, 3,0.05);
        addComponent(new JLabel(""), 0,3,10,0.05);
        addComponent(createBtn(listener, "Salir", Events.EXIT.toString(), "#F64646"), 4,4, 3,0.05);
        addComponent(new JLabel(""), 0,5,10,0.05);
    }

    private JButton createBtn(ActionListener listener, String txt, String name, String color){
        JButton btn = new JButton(txt);
        btn.setName(name);
        btn.setFont(new Font("Arial", Font.PLAIN, 18));
        btn.setActionCommand(Events.INITIAL_PANEL_ACTIONS.toString());
        btn.setBackground(Color.decode(color));
        btn.setForeground(Color.WHITE);
        btn.addActionListener(listener);
        return btn;
    }
}
