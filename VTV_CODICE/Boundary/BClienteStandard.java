package Boundary;

import java.util.ArrayList;
import java.util.List;

import Controller.VTVSOFTWARE;
import Entity.ServizioTvStandard;
import Excpetion.DAOException;
import Excpetion.VTVException;

public class BClienteStandard {

	/**
	 * Viene effettuato l'interfacciamento con il VTVSOFTWARE.
	 * @param Titolo 
	 * @return la lista dei servizi standard
	 */
	public List<ServizioTvStandard> RicercaContenuto(String Titolo) {
		
		//Istanziamento del vtv
		VTVSOFTWARE vtv = new VTVSOFTWARE();

		//Chiamata della funzione ricercaContenuto
		List<ServizioTvStandard> listaServiziStandard = vtv.ricercaContenuto(Titolo);

		//Ritorno delle listaServizioStandard
		return listaServiziStandard;

	}

}
