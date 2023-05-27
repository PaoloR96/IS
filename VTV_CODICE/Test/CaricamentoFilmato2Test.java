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

public class CaricamentoFilmato2Test {
	String Nome = "Ricordo di Maradona";

	@Before
	public void init() throws VTVException {
		
		String clearFilmati = "DELETE FROM SERVIZI_FILMATI; DELETE FROM FILMATO;" + 
					"INSERT INTO  FILMATO VALUES(nextval('FILMATI_SEQUENCE'),'" + Nome + "','23/12/2019',90,20);";
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
		
		System.out.println("TEST 2:NOME FILMATO ESISTENTE\n");
		MSG = BCam.caricaFilmato(Nome, Data, Durata, Dimensione);

		assertEquals(MSG, "Erroe il fimato è già presente!!");
		
		System.out.println(MSG);
	}
}