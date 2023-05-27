package Boundary;

import Controller.VTVSOFTWARE;
import Excpetion.VTVException;

public class BCameramen {

	/**
	 * Viene effettuato l'interfacciamento con il VTVSOFTWARE.
	 * @param Nome
	 * @param Data
	 * @param Durata
	 * @param Dimensione
	 * @return il messaggio MSG
	 */
	public String caricaFilmato(String Nome, String Data, Integer Durata, Integer Dimensione) {

		//Istanziamento del vtv e dichiarazione del messaggio MSG
		VTVSOFTWARE vtv = new VTVSOFTWARE();
		String MSG = null;
		
		//Utilizzo del try-chatch block per la gestione di un'eventuale eccezzione di tipo VTVException
		try {
			
			//Chiamata della funzione caricaFilmato
			MSG = vtv.caricaFilmato(Nome, Data, Durata, Dimensione);

		} catch (VTVException e) {
			
			e.printStackTrace();

		}

		//Ritorno del messaggio MSg
		return MSG;
	}

}