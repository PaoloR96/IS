package Entity;

import java.util.*;

class ClientePremium extends Cliente {

	/**
	 * @param Nome
	 * @param Cognome
	 * @param Email
	 */
	public ClientePremium(String Nome, String Cognome, String Email) {
		super(Nome, Cognome, Email);
		this.Nome = Nome;
		this.Cognome = Cognome;
		this.Email = Email;

	}

	private Date ScadenzaAbbonamento;

}