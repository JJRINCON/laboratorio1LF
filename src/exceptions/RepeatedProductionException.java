package exceptions;

public class RepeatedProductionException extends Exception{

    public RepeatedProductionException(){
        super("Produccion repetida");
    }
}
