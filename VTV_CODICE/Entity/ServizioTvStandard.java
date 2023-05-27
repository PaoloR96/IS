package Entity;

public class ServizioTvStandard extends ServizioTv {

	/**
	 * @param titolo
	 */
	public ServizioTvStandard(String titolo) {
		super(titolo, null);

	}

	public String getTitoloServizio() {
		return this.titolo;
	}

}