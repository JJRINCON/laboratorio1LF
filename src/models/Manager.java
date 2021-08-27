package models;

import exceptions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class Manager {

    private static Grammar grammar;
    private Tree generalTree;
    private ConcurrentHashMap<Integer, Node> nodes;
    private int nodeCount;
    private int count;

    public Manager(){
        nodes = new ConcurrentHashMap<>();
        grammar = new Grammar();
    }

    public void addSymbol(char symbol) throws RepeatedSymbolException, SymbolBelongingToVariablesException {
        grammar.addSymbol(symbol);
    }

    public void addVariable(char variable) throws RepeatedSymbolException, SymbolBelongingToSymbolsException {
        grammar.addVariable(variable);
    }

    public void addProduction(String leftSide, String rightSide) throws WrongProductionException, RepeatedProductionException {
        grammar.addProduction(leftSide, rightSide);
    }

    public void addInitialSymbol(char initialSymbol){
        grammar.setInitialSymbol(initialSymbol);
    }

    public void verifyGrammar() throws NumberOfSymbolsException, NumberOfVariablesException, NumberOfProductionsException {
        if(!grammar.verifyNumberOfSymbols()){
            throw new NumberOfSymbolsException();
        }else if(!grammar.verifyNumberOfVariables()){
            throw new NumberOfVariablesException();
        }else if(!grammar.verifyNumberOfProductions()){
            throw new NumberOfProductionsException();
        }
    }

    public void generateGeneralTree(){
        generalTree = new Tree(new CharacterString(null,String.valueOf(grammar.getInitialSymbol())));
        addSymbols(generalTree.getRoot());
    }

    private void addSymbols(Node father){
        if(count <= 3){
            addProductionsToNode(father);
            for(Node node: father.getChildren()){
               addProductionsToNode(node);
            }
            count++;
        }
        addProductionsToAllNodes();
    }

    private void addProductionsToNodes(){
        for (Integer nodeKey : nodes.keySet()){
            if(nodes.get(nodeKey).getChildren().isEmpty() && nodes.get(nodeKey).getData().getCharacters().length() < 6){
                addProductionsToNode(nodes.get(nodeKey));
            }
            nodes.remove(nodeKey);
        }
    }

    private void addProductionsToNode(Node father){
        for(Production production : grammar.getProductions()){
               if(father.getData().getCharacters().contains(production.getLeftSide())){
                   if(grammar.verifyTerminalSymbol(father.getData().getCharacters())){
                       addCharactersFromRight(father, production);
                   }else{
                       addCharactersFromLeft(father, production);
                   }
            }
        }
    }

    private void addCharactersFromRight(Node father, Production production){
        String actualCharacters = father.getData().getCharacters();
        CharacterString word = new CharacterString(production, actualCharacters.substring(0, actualCharacters.length() - 1)
                                + production.getRightSide());
        nodeCount++;
        nodes.put(nodeCount, generalTree.add(father, word));
    }

    private void addCharactersFromLeft(Node father, Production production){
        String actualCharacters = father.getData().getCharacters();
        CharacterString word = new CharacterString(production, production.getRightSide() + actualCharacters.substring(1));
        nodeCount++;
        nodes.put(nodeCount, generalTree.add(father, word));
    }

    private void addProductionsToAllNodes(){
        int aux = 0;
        while(aux < 2){
            addProductionsToNodes();
            aux++;
        }
    }

    public boolean verifyWord(String word){
        return generalTree.search(word) != null && !checkIfItsWord(word);
    }

    public boolean checkIfItsWord(String word){
        CharacterString data = generalTree.search(word).getData();
        return Grammar.verifyProduction(data.getActualProduction());
    }

    public ArrayList<Node> getPathOf(String word){
        ArrayList<Node> path = generalTree.getPathOf(word);
        Collections.reverse(path);
        return path;
    }

    public void restartGrammar(){
        grammar = new Grammar();
        nodes = new ConcurrentHashMap<>();
        generalTree = new Tree();
        count = 0;
        nodeCount = 0;
    }

    public static boolean verifyVariableInWord(String word){
        for(char variable : grammar.getVariables()){
            if(word.contains(String.valueOf(variable))){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Character> getSymbols(){
        return grammar.getSymbols();
    }

    public ArrayList<Character> getVariables(){
        return grammar.getVariables();
    }

    public ArrayList<Production> getProductions(){
        return grammar.getProductions();
    }

    public Tree getGeneralTree(){
        return generalTree;
    }
}
