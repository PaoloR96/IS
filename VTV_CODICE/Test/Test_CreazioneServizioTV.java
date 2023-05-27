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

public class Test_CreazioneServizioTV {

	String titoloServizio = "Il dio del calcio";
	String nomeFilmato = "Filmato1";
	
	@Before
	public void init() throws VTVException {
		
		String clearFilmati = "DELETE FROM SERVIZI_FILMATI; DELETE FROM SERVIZIOTV; DELETE FROM FILMATO";
		Connection connection = DbManager.getConnection();
		
		try (PreparedStatement pstmt = connection.prepareStatement(clearFilmati)) {
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			throw new VTVException(e);
			
		}
		
		creaFilmato();
	}
	
	
	@org.junit.Test
	public void Test_CreazioneServizioTV() throws VTVException {
		
		VTVSOFTWARE VTV = new VTVSOFTWARE();
		List<Filmato> filmati = new ArrayList<>();
		
		filmati.add(new Filmato(nomeFilmato));
		
		String response = VTV.creaServizioTv(titoloServizio, "s", filmati);
		
		assertEquals("Servizio Tv Generato con successo", response);
		
		System.out.println("TEST Creazione ServizioTv: " + response);
	}
	
	
	

	private void creaFilmato() throws VTVException {
		
		BCameramen BCam = new BCameramen();
		
		String Data = "22/12/2020";
		Integer Durata = 90;
		Integer Dimensione = 4;
		String MSG;
		
		MSG = BCam.caricaFilmato(nomeFilmato, Data, Durata, Dimensione);
	}
}
