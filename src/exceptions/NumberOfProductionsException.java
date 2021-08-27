package exceptions;

public class NumberOfProductionsException extends Exception{

    public NumberOfProductionsException(){
        super("Numero insuficiente de producciones (Minimo 3)");
    }
}
