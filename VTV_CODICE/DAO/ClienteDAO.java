package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Cliente;
import Excpetion.VTVException;

public class ClienteDAO {

	//Implementazione del metodo readListaClientiAbilitati
	/**
	 * Effettua la ricerca dei clienti abilitati all'interno del DataBase e li inserisce in una lista.
	 * @return la lista dei clienti abilitati
	 * @throws VTVException
	 */
	public static ArrayList<Cliente> readListaClientiAbilitati() throws VTVException {

		//Istanziamento delle lista
		ArrayList<Cliente> lista = new ArrayList<Cliente>();

		//Connessione al DataBase
		Connection conn = DbManager.getConnection();

		//Dichiarazione della query da effettuare
		String query = "SELECT NOME, COGNOME, EMAIL FROM CLIENTE WHERE ABILITAZIONE='si';";

		//Utlizzo del try-catch block per la gestione di eventuali eccezioni
		try (PreparedStatement stmt = conn.prepareStatement(query)) {

			try (ResultSet result = stmt.executeQuery()) {

				//Ciclo di letture all'interno della tabella CLIENTE
				while (result.next()) {

					String email = result.getString("EMAIL");
					String nome = result.getString("NOME");
					String cognome = result.getString("COGNOME");

					Cliente c = new Cliente(nome, cognome, email);

					//Aggiunta del cliente alla lista
					lista.add(c);

				}
			}

		} catch (SQLException e) {

			throw new VTVException("Errore nella readListaClientiAbilitati!", e);

		}

		//Ritorno della lista
		return lista;

	}

}