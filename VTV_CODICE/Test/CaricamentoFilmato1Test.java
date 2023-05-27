package Test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import Boundary.BCameramen;
import DAO.DbManager;
import Excpetion.VTVException;

public class CaricamentoFilmato1Test {
	
	String Nome = "Ricordo di Maradona";
	Boolean isPresente;

	@Before
	public void init() throws VTVException {
		
		String clearFilmati = "DELETE FROM SERVIZI_FILMATI; DELETE FROM FILMATO;";
		Connection connection = DbManager.getConnection();
		
		try (PreparedStatement pstmt = connection.prepareStatement(clearFilmati)) {
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			throw new VTVException(e);
			
		}

	}

	@Test
	public void testCaricamentoFilmato1() throws VTVException {
		
		BCameramen BCam = new BCameramen();
		
		String Data = "22/12/2020";
		Integer Durata = 90;
		Integer Dimensione = 4;
		String MSG;
		
		System.out.println("TEST 1:NOME FILMATO  NON ESISTENTE \n");
		MSG = BCam.caricaFilmato(Nome, Data, Durata, Dimensione);
		
		assertEquals(MSG, "Il filmato è stato caricato con successo");
		
		System.out.println(MSG);
	}
	
}