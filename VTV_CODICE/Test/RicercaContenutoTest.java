package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

public class RicercaContenutoTest {

	String Titolo = "CAPRI";

	@Before
	public void init() throws VTVException {

		String clearFilmati = "DELETE FROM SERVIZI_FILMATI; DELETE FROM SERVIZIOTV; DELETE FROM FILMATO";
		Connection connection = DbManager.getConnection();

		try (PreparedStatement pstmt = connection.prepareStatement(clearFilmati)) {

			pstmt.executeUpdate();

		} catch (SQLException e) {

			throw new VTVException(e);

		}

		ServizioTvDAO servizioTvDAO = new ServizioTvDAO();

		//Il servizio viene reinserito
		servizioTvDAO.createServizio(Titolo, "s", new ArrayList<Filmato>());
	}

	/**
	 * 
	 * @throws VTVException
	 */
	@org.junit.Test
	public void testRicercaContenuto() throws  VTVException {

		System.out.println("Test Servizio Presente\n");

		VTVSOFTWARE VTV = new VTVSOFTWARE();

		List<ServizioTvStandard> lista_Ricerca;
		lista_Ricerca = VTV.ricercaContenuto(Titolo);

		assertNotNull(lista_Ricerca);
		assertEquals(1, lista_Ricerca.size());

		if(lista_Ricerca.size()!=0) {

			for (ServizioTvStandard s : lista_Ricerca) {

				System.out.println(s.getTitoloServizio() + "\n");

			}
			
		}else {
			
			System.out.println("Nessun ServizioTv con titolo " + Titolo + " è stato trovato");
			
		}
	}
}
