package Excpetion;

import java.sql.SQLException;

//Implementazione della nuova eccezione DAOException
/**
 * Estenzione dell'eccezione VTVException.
 *
 */
public class DAOException extends VTVException {

	public DAOException(String message, Throwable arg0) {
		
		super(message, arg0);
		
	}


	public DAOException(Exception e) {
		
		super(e);
		
	}

}
