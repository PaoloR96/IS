import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Boundary.BCameramen;
import Boundary.BClienteStandard;
import Boundary.BMontatore;
import Controller.VTVSOFTWARE;
import Entity.Filmato;
import Entity.ServizioTvStandard;
import Excpetion.VTVException;


/**
 * classe main
 *
 */
public class main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//Inserimento della scelta per il menù
		Integer scelta = 2;

		switch (scelta) {

		case 1:

			//Ricerca contenuto: l'utente effettuta la ricrca di un ServizioTV

			//Istanziamento cliente standard
			BClienteStandard clienteS = new BClienteStandard();

			//Definizione del titolo da passare
			String Titolo = "CAPRI";

			System.out.println("Risultati della ricerca:\n");

			//Istanziamento della lista s_listaRicerca e viene effettuata la chiamata della funzione RicercaContenuto
			List<ServizioTvStandard> s_listaRicerca = clienteS.RicercaContenuto(Titolo);

			//Stampa degli eventuali risultati della ricerca
			if(s_listaRicerca.size()!=0) {

				for (ServizioTvStandard s : s_listaRicerca) {

					System.out.println(s.getTitoloServizio() + "\n");

				}
			}else {

				System.out.println("Nessun ServizioTv con titolo " + Titolo + " è stato trovato");

			}

			break;

		case 2:

			//Istanziamento cameramen
			BCameramen cameraman = new BCameramen();

			//Definizione dei parametri da passare
			String nome = "nuovo7";
			String data = "22/12/2020";
			Integer durata = 90;
			Integer dimensione = 20;
			String msg;

			//Viene effettuata la chiamata della funzione caricaFilmato
			msg = cameraman.caricaFilmato(nome, data, durata, dimensione);

			//Stampa degli eventuali risultati
			System.out.println(msg);

			break;

		case 3:

			//Istanziamento della listaFillmati e viene effettuata la chiamata della funzione creaListaFilmati
			List<Filmato> listaFilmati = creaListaFilmati();

			//Istanziamento montatore
			BMontatore montatore = new BMontatore();

			//Definizione della tipologia e del titolo del ServizioTV
			String tipologia = "s"; 
			String titoloServizio = "CAPRI";

			//Chiamata della funzione creaServizioTv
			String Msg=montatore.creaServizioTv(titoloServizio, tipologia, listaFilmati);

			//Stampa degli eventuali risultati
			System.out.println(Msg);
			break;

		}

	}

	
	/**
	 * Implementazine della funzione creaListaFilmati. 
	 * Viengono aggiunti gli oggetti filmato alla listaFilmati
	 * @return la lista dei filmati scelti
	 */
	public static List<Filmato> creaListaFilmati() {

		//lettura da linea di comando dei filmati
		Scanner sc = new Scanner(System.in);
		List<Filmato> listaFilmati = new ArrayList<>();
		String risposta;

		//Chiamata della funzione aggiuntaFilmato
		Filmato filmato = aggiuntaFilmato();

		//Riempimento della listaFilmati con l'oggetto filmato inserito
		listaFilmati.add(filmato);

		//Menù per l'aggiunta di ulteriori filmati
		do {

			System.out.println("Aggiungere un altro filmato?  si/no");
			//Inserimento della risposta
			risposta = sc.nextLine();

			if (risposta.equalsIgnoreCase("si")) {

				//Aggiunta del nuovo filmato tramite la chiamata della funzione aggiuntaFilmato
				filmato = aggiuntaFilmato();
				listaFilmati.add(filmato);

			}

		} while (risposta.equalsIgnoreCase("si"));

		//Ritorno della listaFilmati
		return listaFilmati;
	}


	/**
	 * Viene chiesto l'inserimento del titolo di un filmato tramite linea di comando.
	 * Si effettua la creazione dell'oggetto filmato con il titolo passato dall'utente
	 * @return l'oggetto filmato
	 */
	public static Filmato aggiuntaFilmato() { 
		
		//Lettura da linea di comando nel nome del nuovo filmato
		Scanner sc = new Scanner(System.in);

		System.out.println("quale filmato vuoi inserire?");
		String nomeFilmato = sc.nextLine();
		System.out.println("filmato inserito");

		//Creazione dell'oggetto filmato con titolo inserito dall'utente
		Filmato	filmato = new Filmato(nomeFilmato);

		//Ritorno dell'oggetto filmato
		return filmato;
	}

}