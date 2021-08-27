package exceptions;

public class NumberOfVariablesException extends Exception{

    public NumberOfVariablesException(){
        super("Numero insuficiente de variables (Minimo 3)");
    }
}
