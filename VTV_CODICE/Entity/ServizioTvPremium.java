package Entity;

import java.util.*;

/**
 * 
 */
class ServizioTvPremium extends ServizioTv {

	public ServizioTvPremium() {
		super(null, null);
	}

	/**
	 * @param titolo
	 * @param listaFilmatiScelti
	 */
	public ServizioTvPremium(String titolo, List<Filmato> listaFilmatiScelti) {
		super(titolo, listaFilmatiScelti);
	}

	/**
	 * 
	 */
	public Set<ClientePremium> clientePremium;

	/**
	 * 
	 */
	// public Acquisto acquisto;

}