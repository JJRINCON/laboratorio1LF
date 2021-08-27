package exceptions;

public class EmptyTextFieldException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmptyTextFieldException(){
        super("Campo de texto vacio");
    }
}
