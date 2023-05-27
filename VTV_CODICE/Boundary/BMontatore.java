package Boundary;

import java.util.List;

import Controller.VTVSOFTWARE;
import Entity.Filmato;
import Excpetion.VTVException;

/**
 * 
 */
public class BMontatore {

	/**
	 * Viene effettuato l'interfacciamento con il VTVSOFTWARE.
	 * @param titolo
	 * @param tipologia
	 * @param listaFilmato
	 * @return il messaggio MSG
	 */
	public String creaServizioTv(String titolo, String tipologia, List<Filmato> listaFilmato) {

		//Istanziamento del vtv e dichiarazione del messaggio MSG
		VTVSOFTWARE vtv = new VTVSOFTWARE();
		String MSG = null;

		try {

			//Chiamata della funzione creaServizioTv
			MSG = vtv.creaServizioTv(titolo, tipologia, listaFilmato);

		} catch (VTVException e) {

			e.printStackTrace();

		}

		//Ritorno del messaggio MSG
		return MSG;

	}

}