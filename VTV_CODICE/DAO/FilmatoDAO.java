package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Filmato;
import Excpetion.DAOException;
import Excpetion.VTVException;

public class FilmatoDAO {

	//Implementazione del metodo createFilmato
	/**
	 * Effettua il caricamento del Filmato all'interno del DataBase.
	 * @param Nome
	 * @param Data
	 * @param Durata
	 * @param Dimensione
	 * @return ritorna un valore di tipo Boolean
	 * @throws VTVException
	 */
	public Boolean createFilmato(String Nome, String Data, Integer Durata, Integer Dimensione) throws VTVException {

		Boolean IsCreato = false;

		//Connessione al DataBase
		Connection conn = DbManager.getConnection();
		
		//Dichiarazione della query da effettuare
		//Si utulizza "nextval('FILMATI_SEQUENCE')" per la gestione degl'indici da assegnare ai filmati
		String query = "INSERT INTO FILMATO VALUES(nextval('FILMATI_SEQUENCE'),?,?,?,?);";

		//Utilizzo del try-catch block per la gestione di eventuali eccezioni
		try (PreparedStatement stmt = conn.prepareStatement(query)) {

			//Inserimento del nuovo filmato all'interno della tabella FILMATO
			stmt.setString(1, Nome);
			stmt.setString(2, Data);
			stmt.setInt(3, Durata);
			stmt.setInt(4, Dimensione);
			stmt.executeUpdate();
			
			IsCreato = true;

		} catch (SQLException e) {

			throw new DAOException("Impossibile caricare il filmato nel database", e);

		}

		//Ritorno di IsCreato
		return IsCreato;
	}

	/*
	 * 
	 * 
	 * 
	 */
	//Implementazione del metodo readFilmatoPresente
	/**
	 * Lettura all'interno del DataBase per la ricerca del filmato richiesto e
	 * verifica se quest'ultimo fosse presente all'interno del DataBAse.
	 * @param nome
	 * @return ritorna un valore di tipo Boolean
	 * @throws VTVException
	 */
	public Boolean readFilmatoPresente(String nome) throws VTVException {

		Boolean isPresente = false;
		
		//Connessione al DataBase
		Connection conn = DbManager.getConnection();
		
		//Dichiarazione della query da effettuare
		String query = "SELECT * FROM FILMATO WHERE nome = ?;";

		//Utilizzo del try-catch block per la gestione di eventuali eccezioni
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			
			stmt.setString(1, nome);

			try (ResultSet result = stmt.executeQuery()) {

				if (result.next()) {
					isPresente = true;
				}
			}

		} catch (SQLException e) {

			throw new DAOException("Impossibile leggere i filmati", e);

		}

		//ritorno isPresente
		return isPresente;
	}


	//Implemetazione del metodo readID
	/**
	 * Effetua una ricerca dei titoli dei filmati presenti nella listaFilmatiInput
	 * all'interno del DataBase. Per ogni titolo, se viene trovato un filmato con lo stesso titolo
	 * veine aggiunto alla listaFilmati.
	 * @param listaFilmatiInput
	 * @return ritorna la lista dei filmati
	 * @throws VTVException
	 */
	public List<Filmato> readFilmati(List<Filmato> listaFilmatiInput) throws VTVException {

		//Istanzazione delle lista
		ArrayList<Filmato> lista = new ArrayList<>();

		//Connessione al DataBase
		Connection conn = DbManager.getConnection();

		//Dichiarazione della query da effettuare
		String query = "select * from FILMATO;";

		////Utilizzo del try-catch block per la gestione di eventuali eccezioni
		try (PreparedStatement stmt = conn.prepareStatement(query)) {

			try (ResultSet result = stmt.executeQuery()) {

				//Ciclo di lettura
				while (result.next()) {
					
					Integer idFilmato = result.getInt("ID");
					String titolo = result.getString("NOME");
					String data = result.getString("DATA");
					Integer dimensione = result.getInt("DIMENSIONE");
					Integer durata = result.getInt("DURATA");
					
					for (Filmato s : listaFilmatiInput) {
						
						if (s.getNome().contains(titolo)) {
							
							Filmato f = new Filmato(idFilmato, titolo, data, dimensione, durata);
							
							//Aggiunta del filmato alla lista
							lista.add(f);
							
						}
					}
				}
			}
			
			//Ritorno di lista
			return lista;
			
		} catch (SQLException e) {
			
			throw new VTVException(e);
			
		}

	}

}