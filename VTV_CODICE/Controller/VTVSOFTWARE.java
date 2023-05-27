package Controller;

import java.util.ArrayList;
import java.util.List;

import DAO.ClienteDAO;
import DAO.FilmatoDAO;
import DAO.ServizioTvDAO;
import DAO.ServizioTvStandardDAO;
import Entity.Cliente;
import Entity.Filmato;
import Entity.ServizioTvStandard;
import Excpetion.VTVException;

public class VTVSOFTWARE {
	
	/**
	 * Ricerca il titolo passato come parametro d'ingresso all'intero dellal lista dei ServiziTv Standard.
	 * Nel caso in cui venga trovato un servizioTv con quel titolo, esso verrà messo all'interno della lista
	 * di serviziTv passata come parametro di ritorno.
	 * @param titoloServizio
	 * @return la lista dei SeerviziTv ricercati
	 * 
	 */
	public List<ServizioTvStandard> ricercaContenuto(String titoloServizio) {

		//Istaziamento della listaServiziStandard
		List<ServizioTvStandard> listaServiziStandard = null;
		
		//Utilizzo il try-chatch block per la gestione e la cattura dell'eventuali eccezioni
		try {

			//Chiamata della funzione readAll
			listaServiziStandard = ServizioTvStandardDAO.readAll();

		} catch (VTVException e) {

			e.printStackTrace();

		}

		//Istaziamento della listaRisultati
		ArrayList<ServizioTvStandard> listaRisultati = new ArrayList<>();

		//Ciclo per riempire la listaRisultati
		for (ServizioTvStandard s : listaServiziStandard) {

			if (s.getTitoloServizio().contains(titoloServizio)) {

				//Aggiunta del nuovo oggetto ServizioTv all'interno della listaRisultati
				listaRisultati.add(s);

			}

		}

		//Ritorno la lista Risultati
		return listaRisultati;

	}

	
	/**
	 * Verifica la presenza all'interno del DataBAse del Filmato in ingresso. Nel caso non fosse presente, 
	 * verrà caricato il nuovo filmato e verrà invocato il metodo InviaNotifiche. 
	 * @param data
	 * @param durata
	 * @param dimensione
	 * @return un messaggio di conferma o di errore
	 * @throws VTVException
	 */
	public String caricaFilmato(String nome, String data, Integer durata, Integer dimensione) throws VTVException {

		//Istanziamento di filmatoDAO e di clienteDAO
		FilmatoDAO filmatoDAO = new FilmatoDAO();
		ClienteDAO clienteDAO = new ClienteDAO();

		
		//Chiamata della funzione readFilmatoPresente
		Boolean isPresente = filmatoDAO.readFilmatoPresente(nome);

		//Controllo presenza effettiva del filmato
		if (!isPresente) {
			
			//Chiamata della funzione createFilmato
			Boolean isCreato = filmatoDAO.createFilmato(nome, data, durata, dimensione);

			//Controllo creazione del Filmato
			if (Boolean.TRUE.equals(isCreato)) {
				
				//Invia notifica relativa all'upload del nuovo filmato ai clienti clienti abilitati
				//tramite la readListaClientiAbilitati e la funzione InviaNotifiche
				ArrayList<Cliente> listaClientiAbilitati = clienteDAO.readListaClientiAbilitati();
				InviaNotifiche("Nuovo Filmato Caricato", listaClientiAbilitati);

				//Ritorno del messaggio "Il filmato è stato caricato con successo"
				return "Il filmato è stato caricato con successo";

			}
		}

		//Ritorno del filmato "Erroe il fimato è già presente!!"
		return "Erroe il fimato è già presente!!";
	}



	/**
	 * Verifica la presenza all'interno del DataBAse del ServizioTv in ingresso e dei filmati 
	 * inseriti nella listaFilmato. Nel caso non fosse presente il servizio tv e fossero presenti i filmati, 
	 * verrà caricato il nuovo ServizioTv e verrà invocato il metodo InviaNotifiche.
	 * @param titolo
	 * @param tipologia
	 * @param listaFilmato
	 * @return un messaggio di conferma o di errore
	 * @throws VTVException
	 */
	public String creaServizioTv(String titolo, String tipologia, List<Filmato> listaFilmato) throws VTVException {

		//Istanziamento di filmatoDAO e di clienteDAO
		FilmatoDAO filmatoDAO = new FilmatoDAO();
		ClienteDAO clienteDAO = new ClienteDAO();

		// Controllo della tipologia per evitare accessi inutili al database in
		// caso fosse errata
		if (!tipologia.equals("s") && !tipologia.equals("p")) {

			throw new VTVException("Tipologia può essere 's' oppure 'p'");

		}

		//Chiamata della funzione readFilmati
		List<Filmato> listaFilmatiPresenti = filmatoDAO.readFilmati(listaFilmato);
		
		//Lstanziamento del servizioTvDAO
		ServizioTvDAO servizioTvDAO = new ServizioTvDAO();

		//Chiamata della funzione readServizioTv
		Boolean isPresentS = servizioTvDAO.readServizioTv(titolo);
		
		//Controllo del match tra la dimensione della listaFilmatoPresente e della listaFilmato
		Boolean isPresentF = (listaFilmatiPresenti.size() == listaFilmato.size());

		//Creazione del servizio tv nel caso in cui la condizione venga verificata
		if (!isPresentS && isPresentF) {

			//Chiamata della funzione createServizio
			Boolean isCaricato = servizioTvDAO.createServizio(titolo, tipologia, listaFilmatiPresenti);

			//Controllo della creazione del nuovo servizioTv
			if (isCaricato) {

				//Invia notifica relativa all'upload del nuovo filmato ai clienti clienti abilitati
				//tramite la readListaClientiAbilitati e la funzione InviaNotifiche
				ArrayList<Cliente> listaClientiAbilitati = clienteDAO.readListaClientiAbilitati();
				InviaNotifiche("Nuovo Servizio Tv Caricato", listaClientiAbilitati);

				//Ritorno del messaggio "Servizio Tv Generato con successo
				return "Servizio Tv Generato con successo";

			} else {

				//Ritorno del messaggio "Errore!! Impossibile generare il Servizio Tv"
				return "Errore!! Impossibile generare il Servizio Tv";

			}

		}

		//Ritonro del messaggio "Errore!! Impossibile generare il Servizio Tv"
		return "Errore!! Impossibile generare il Servizio Tv";
	}

	/*
	 * 
	 */
	private void InviaNotifiche(String string, ArrayList<Cliente> lista_ClieteAbilitati2) {
		// TODO Auto-generated method stub
	}
}
