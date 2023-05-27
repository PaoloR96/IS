package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Excpetion.VTVException;

/*
 * Gestisce la logica di connessione al DbManager.
 * 
 * Implementa il pattern sigleton.
 * 
 */
public class DbManager {

	private static Connection conn = null;

	
	/**
	 * @return lo stato della connessione
	 * @throws VTVException
	 */
	//Implementazione del metodo getConnection
	public static Connection getConnection() throws VTVException {

		//Utilizzo del try-catch block per la gestione di eventuali eccezioni
		try {

			//Controllo della connessione (se è già aperta)
			if(conn == null) {

				//Connessione al Database
				conn = DriverManager.getConnection("jdbc:h2:~/VTV_DATABASE", "sa", "");

			}

		}catch(SQLException e) {

			throw new VTVException("Errore connesione al database",e);

		}

		//Ritonro della conn
		return conn;
	}


	/**
	 * @throws VTVException
	 */
	//Implementazione del metodo closeConnection
	public static void closeConnection() throws VTVException {

		//Utilizzo del try-catch block per la gestione di eventuali eccezioni
		try {

			//Controllo della connessione (se è già chiusa)
			if(conn != null) {

				//Chiusura della connesione
				conn.close();
			}

		}catch(SQLException e) {

			throw new VTVException("Errore chiusura Database",e);

		}

	}

}


