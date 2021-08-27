package exceptions;

public class SymbolBelongingToSymbolsException extends Exception{

    public SymbolBelongingToSymbolsException(){
        super("La variable que intenta ingresar pertenece al conjunto de simbolos terminales");
    }
}
