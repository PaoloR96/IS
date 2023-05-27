package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.ServizioTvStandard;
import Excpetion.VTVException;

/**
 * 
 */
public class ServizioTvStandardDAO extends ServizioTvDAO {

	//Implementazione del metodo readAll
	/**
	 * Effettua la lettura dei Servizitv all'interno del DataBase
	 * @return ritorna la lsita dei Servizitv presenti nel DataBase
	 * @throws VTVException
	 */
	public static List<ServizioTvStandard> readAll() throws VTVException {

		//Implementazione della lista
		ArrayList<ServizioTvStandard> lista = new ArrayList<>();

		//Connessione al DataBase
		Connection conn = DbManager.getConnection();

		//Dichiarazione della query da effettuare
		String query = "select * from SERVIZIOTV WHERE TIPOLOGIA='s';";

		//Utilizzo del try-catch block per la gestione di eventuali eccezioni
		try(PreparedStatement stmt = conn.prepareStatement(query)) {

			try(ResultSet result = stmt.executeQuery()) {

				//Ciclo di lettura
				while(result.next()) {
					
					String titolo = result.getString(1);
					ServizioTvStandard servizioTv = new ServizioTvStandard(titolo);
					
					//Aggiunta del servizioTv alla lista
					lista.add(servizioTv);
					
				}
			}

		}catch(SQLException e) {
			
			throw new VTVException("Errore nella readAll!",e);
			
		}

		//Ritorno di lista
		return lista;

	}

}