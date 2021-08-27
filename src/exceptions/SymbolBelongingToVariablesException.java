package exceptions;

public class SymbolBelongingToVariablesException extends Exception{

    public SymbolBelongingToVariablesException(){
        super("El simbolo que intenta ingresar pertenece al conjunto de variables");
    }
}
