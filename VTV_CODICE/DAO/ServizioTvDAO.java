package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Filmato;
import Entity.ServizioTvStandard;
import Excpetion.VTVException;

/**
 * 
 */
public class ServizioTvDAO {

	//Implementazione della metodo createServizio
	/**
	 * Effettua la creazione del servizoTv 
	 * 
	 * @param titolo
	 * @param tipologia
	 * @param listaIdFilmati
	 * @return ritorna un valore di tipo Boolean
	 * @throws VTVException
	 */
	public Boolean createServizio(String titolo, String tipologia, List<Filmato> listaIdFilmati) throws VTVException {

		Boolean isCaricato = false;

		//Connessione al dataBase
		Connection conn = DbManager.getConnection();
		
		//Dichiarazione delle query da effettuare
		String query = "INSERT INTO SERVIZIOTV VALUES (?,?); ";
		String queryAssociazioneFilmato = "INSERT INTO SERVIZI_FILMATI VALUES (nextval('SERVIZI_FILMATI_SEQUENCE'), ?,?); ";

		//Utilizzo del try-catch block per la gestione di eventuali eccezioni
		try (PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, titolo);
			stmt.setString(2, tipologia);
			stmt.executeUpdate();

			//Prendo l'id del servizio tv appena inserito
			for (Filmato filmato : listaIdFilmati) {

				try (PreparedStatement associazioneStmt = conn.prepareStatement(queryAssociazioneFilmato)) {

					associazioneStmt.setString(1, titolo);
					associazioneStmt.setInt(2, filmato.getId());
					associazioneStmt.executeUpdate();

				}
			}

			isCaricato = true;

		} catch (Exception e) {

			throw new VTVException(e);

		}

		//Ritonro di isCaricato
		return isCaricato;
	}

	
	//Implementazione del metodo readServiziotv
	/**
	 * Effettua una ricerca all'ntero della Tabella SERVIZIOTV per verificare la presenza del servizioTv
	 * all'interno del DataBase
	 * @param titolo
	 * @return ritorna un valore di tipo Boolean
	 * @throws VTVException
	 */
	public Boolean readServizioTv(String titolo) throws VTVException {

		Boolean IsPresenteS = false;

		//Connessione del DataBase
		Connection conn = DbManager.getConnection();
		
		//Dichiarazione della query da effettuare
		String query = "SELECT * FROM SERVIZIOTV WHERE nome = ?;";

		//Utilizzo del try-catch block per la gestione di eventuali eccezioni
		try (PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, titolo);

			try (ResultSet result = stmt.executeQuery()) {

				//Controllo della presenza del servizioTv
				if (result.next()) {

					IsPresenteS = true;

				}
			}

		} catch (SQLException e) {

			throw new VTVException(e);

		}

		//Ritorno di IsPresenteS
		return IsPresenteS;
	}

}