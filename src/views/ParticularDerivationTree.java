package views;

import models.CharacterString;
import models.Grammar;
import models.Manager;
import models.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ParticularDerivationTree extends JPanel {

    private int x, y, x2, y2;
    private int size;
    private ArrayList<Node> pathOfWord;
    private String word;

    public ParticularDerivationTree(ArrayList<Node> pathOfWord, String word){
        setBackground(Color.WHITE);
        this.pathOfWord = pathOfWord;
        this.word = word;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString( "Arbol de derivacion particular de la palabra " + "\"" + word + "\"",
                (int)(getWidth() * 0.1), (int)(getHeight() * 0.05));
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        paintTree(g2);
    }

    public void paintTree(Graphics2D g2){
        size = (int )(getWidth() * 0.05);
        x = (int)(getWidth() * 0.3);
        y = (int)(getHeight() * 0.2);
        x2 = (int)(getWidth() * 0.07);
        y2 = (int)(getHeight() * 0.24);
        int[] lastCoordinates  = {x, y};
        paintNode(g2, pathOfWord.get(0));
        for(Node data : pathOfWord){
            if(!data.getData().getCharacters().equals(pathOfWord.get(0).getData().getCharacters())){
                changeCoordinates();
                g2.drawLine(lastCoordinates[0] + (int)(size/1.17), lastCoordinates[1] + (int)(size/1.17),
                                x + (size/2), y + (size/2));
                paintNode(g2, data);
                lastCoordinates[0] = x;
                lastCoordinates[1] = y;
            }
        }
    }

    private void changeCoordinates(){
        x += (getWidth()  * 0.1);
        y += (getHeight() * 0.1);
        x2 += (getWidth()  * 0.1);
        y2 += (getHeight() * 0.1);
    }

    private void paintNode(Graphics2D g2, Node data){
        g2.setColor(Color.BLACK);
        if (!(data.getData().getActualProduction().getRightSide().length() == 1 && Manager.verifyVariableInWord(data.getData().getCharacters()))){
            paintVariableOval(g2, data);
        }
        paintSymbolOval(g2, data);
    }

    private void paintVariableOval(Graphics2D g2, Node data){
        g2.drawLine(x + (size/2) ,y + (size/2), x2 + (size/2),y2 + (size/2));
        g2.setColor(Color.decode("#0efff9"));
        g2.fillOval(x2,y2, size, size);
        g2.setColor(Color.BLACK);
        g2.drawOval(x2,y2, size, size);
        g2.drawString(verifyNodeText(data.getData()), x2 + (size/3),y2 + (int)(size/1.5));
        g2.setColor(Color.BLACK);
    }

    private void paintSymbolOval(Graphics2D g2, Node data){
        g2.setColor(Color.decode("#10304d"));
        g2.fillOval(x,y,size, size);
        g2.setColor(Color.BLACK);
        g2.drawOval(x,y,size, size);
        g2.setColor(Color.WHITE);
        g2.drawString(data.getData().getActualProduction().getLeftSide(),x + (size/3) ,y + (int)(size/1.5));
        g2.setColor(Color.BLACK);
    }

    private String verifyNodeText(CharacterString data){
        String txtProduction = data.getActualProduction().getRightSide();
        if(Grammar.verifyProduction(data.getActualProduction())) {
            if (Grammar.verifyCharacterStringByRight(data)) {
                return txtProduction.substring(0, txtProduction.length() - 1);
            } else if (data.getActualProduction().getRightSide().length() == 1) {
                return txtProduction;
            }
        }else{
            return txtProduction;
        }
        return " ";
    }
}
