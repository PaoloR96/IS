package Test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import Boundary.BCameramen;
import Controller.VTVSOFTWARE;
import DAO.DbManager;
import Entity.Filmato;
import Excpetion.VTVException;

public class Test_FilmatoEsistenteServizioEsistente {

	String titoloServizio = "Servizio_tv";
	String nomeFilmato = "Filmato1";
	
	@Before
	public void init() throws VTVException {
		
		String clearFilmati = "DELETE FROM SERVIZI_FILMATI; DELETE FROM SERVIZIOTV; DELETE FROM FILMATO;"
				+ " INSERT INTO  SERVIZIOTV VALUES ('" + titoloServizio + "', 's');";
		Connection connection = DbManager.getConnection();
		
		try (PreparedStatement pstmt = connection.prepareStatement(clearFilmati)) {
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			throw new VTVException(e);
			
		}
		
		creaFilmato(nomeFilmato);
	}
	
	
	@org.junit.Test
	public void titoloEsistenteFilmatoEsistente() throws VTVException {
		
		VTVSOFTWARE VTV = new VTVSOFTWARE();
		
		List<Filmato> filmati = new ArrayList<>();
		filmati.add(new Filmato(nomeFilmato));
		
		//String response = VTV.creaServizioTv(titoloServizio, "s", filmati);
		//assertEquals("Servizio Tv Generato con successo", response);
		String response = VTV.creaServizioTv(titoloServizio, "s", filmati);
		//System.out.println(response);
		assertEquals("Errore!! Impossibile generare il Servizio Tv", response);
		System.out.println("TEST FilmatoEsistente Servizio Esistente: " + response);
		
	}
	
	
	

	private void creaFilmato(String nomeFilmato) throws VTVException {
		
		BCameramen BCam = new BCameramen();
		
		String Data = "22/12/2020";
		Integer Durata = 90;
		Integer Dimensione = 4;
		
		String MSG;
		
		MSG = BCam.caricaFilmato(nomeFilmato, Data, Durata, Dimensione);
		//System.out.println(MSG);
		
	}
}
