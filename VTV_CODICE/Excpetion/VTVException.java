package Excpetion;

//Implementazione delle nuova eccezione VTVException
/**
 * Estenzione dell'eccezione Exception.
 *
 */
public class VTVException extends Exception {

	public VTVException(Throwable arg0) {
		
		super(arg0);
		
	}

	public VTVException(String message, Throwable arg0) {
		
		super(message, arg0);
		
	}

	public VTVException(String message) {
		
		super(message);
		
	}
}
