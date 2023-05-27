package Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import Controller.VTVSOFTWARE;
import DAO.DbManager;
import DAO.ServizioTvDAO;
import Entity.Filmato;
import Entity.ServizioTvStandard;
import Excpetion.VTVException;

public class RicercaContenutoTest1 {
	
	String Titolo = "VITO";

	@Before
	public void init() throws VTVException {
		
		String clearFilmati = "DELETE  FROM SERVIZI_FILMATI; DELETE  FROM SERVIZIOTV; DELETE FROM FILMATO;";
		Connection connection = DbManager.getConnection();
		
		try (PreparedStatement pstmt = connection.prepareStatement(clearFilmati)) {
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			throw new VTVException(e);
			
		}
		
		ServizioTvDAO servizioTvDAO = new ServizioTvDAO();
		//Il servizio viene reinserito
		//servizioTvDAO.createServizio(Titolo, "s", new ArrayList<Filmato>());
	}

	@org.junit.Test
	public void testRicercaContenuto() throws VTVException {
		
		// if(!isPresente) {
		System.out.println("Test Servizio NON Presente\n");
		
		VTVSOFTWARE VTV = new VTVSOFTWARE();
		List<ServizioTvStandard> lista_Ricerca;

		// String MSG;

		System.out.println("Lista Servizi Tv:\n");
		
		lista_Ricerca = VTV.ricercaContenuto(Titolo);
		
		for (ServizioTvStandard s : lista_Ricerca) {
			
			System.out.println(s.getTitoloServizio() + "\n");
			
		}

	}
	
}