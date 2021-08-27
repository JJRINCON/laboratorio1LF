package exceptions;

public class NumberOfSymbolsException extends Exception{

    public NumberOfSymbolsException(){
        super("Numero de simbolos insuficiente (Minimo 2)");
    }
}
