package Entity;

import java.util.*;

public class Cliente {
	protected String Nome;
	protected String Cognome;
	protected Date DataDiNascita;
	protected String Email;
	protected String Indirizzo;
	protected Set<ServizioTvStandard> servizioStandard;

	/**
	 * @param nome
	 * @param cognome
	 * @param email
	 */
	public Cliente(String nome, String cognome, String email) {

	}
}