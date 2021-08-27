package models;

import exceptions.*;

import java.util.ArrayList;

public class Grammar {

    private ArrayList<Character> symbols;
    private static ArrayList<Character> variables;
    private ArrayList<Production> productions;
    private char initialSymbol;

    public Grammar(){
        symbols = new ArrayList<>();
        symbols.add('λ');
        variables = new ArrayList<>();
        productions = new ArrayList<>();
    }

    public void addSymbol(char symbol) throws SymbolBelongingToVariablesException, RepeatedSymbolException {
        if (variables.contains(symbol)) {
            throw new SymbolBelongingToVariablesException();
        }else if(symbols.contains(symbol)){
            throw new RepeatedSymbolException("Simbolo termial");
        }else{
            symbols.add(Character.toLowerCase(symbol));
        }
    }

    public void addVariable(char variable) throws SymbolBelongingToSymbolsException, RepeatedSymbolException {
        if(symbols.contains(variable)){
            throw new SymbolBelongingToSymbolsException();
        }else if(variables.contains(variable)){
            throw new RepeatedSymbolException("Variable");
        }else{
            variables.add(Character.toUpperCase(variable));
        }
    }

    public void addProduction(String leftSide, String rightSide) throws WrongProductionException, RepeatedProductionException {
        verifyNewProduction(leftSide, rightSide);
        if(verifyLeftSide(leftSide) && verifyRightSide(rightSide)){
            productions.add(new Production(leftSide, rightSide));
        }else{
            throw new WrongProductionException();
        }
    }

    private boolean verifyRightSide(String rightSide){
        for (int i = 0; i < rightSide.length(); i++) {
            if(!symbols.contains(rightSide.charAt(i)) && !variables.contains(rightSide.charAt(i))){
                return false;
            }
        }
        return true;
    }

    private boolean verifyLeftSide(String leftSide){
      return !symbols.contains(leftSide) || variables.contains(leftSide);
    }

    public void verifyNewProduction(String leftSide, String rightSide) throws RepeatedProductionException {
        for(Production production : productions){
            if(production.getLeftSide().equals(leftSide) && production.getRightSide().equals(rightSide)){
               throw new RepeatedProductionException();
            }
        }
    }

    public boolean verifyTerminalSymbol(String txt){
        return variables.contains(txt.charAt(txt.length() - 1));
    }

    public static boolean verifyProduction(Production actualProduction){
        String rightSide = actualProduction.getRightSide();
        return variables.contains(rightSide.charAt(0)) || variables.contains(rightSide.charAt(rightSide.length() - 1));
    }

    public static boolean verifyCharacterStringByRight(CharacterString data){
        return variables.contains(data.getCharacters().charAt(data.getCharacters().length() - 1));
    }

    public boolean verifyNumberOfVariables(){
        return variables.size() >= 3;
    }

    public boolean verifyNumberOfSymbols(){
        return symbols.size() >= 2;
    }

    public boolean verifyNumberOfProductions(){
        return variables.size() >= 3;
    }


    public void setInitialSymbol(char initialSymbol) {
        this.initialSymbol = initialSymbol;
    }

    public char getInitialSymbol() {
        return initialSymbol;
    }

    public ArrayList<Character> getSymbols() {
        return symbols;
    }

    public ArrayList<Character> getVariables() {
        return variables;
    }

    public ArrayList<Production> getProductions() {
        return productions;
    }
}
