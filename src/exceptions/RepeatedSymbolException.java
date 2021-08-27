package exceptions;

public class RepeatedSymbolException extends Exception{

    public RepeatedSymbolException(String message){
        super( message +" repetido");
    }
}
