package Entity;

import java.util.*;

public class ClienteStandard extends Cliente {
	
	/**
	 * @param Nome
	 * @param Cognome
	 * @param Email
	 */
	public ClienteStandard(String Nome, String Cognome, String Email) {
		super(Nome, Cognome, Email);
		this.Nome = Nome;
		this.Cognome = Cognome;
		this.Email = Email;
	}
}