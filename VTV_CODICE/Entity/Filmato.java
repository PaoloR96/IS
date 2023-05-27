package Entity;

import java.util.*;


public class Filmato {
	
	protected Integer id;
	protected String nome;
	protected String data;
	protected Integer durata;
	protected Integer dimensione;
	public Set<ServizioTv> servizioTv;
	

	/**
	 * @param nomeFilmato
	 */
	public Filmato(String nomeFilmato) {
		this.nome = nomeFilmato;
	}

	/**
	 * @param id
	 * @param nome
	 * @param data
	 * @param durata
	 * @param dimensione
	 */
	public Filmato(Integer id, String nome, String data, Integer durata, Integer dimensione) {
		super();
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.durata = durata;
		this.dimensione = dimensione;
	}

	
	//Implementazione delle get e delle set per l'accesso e l'utilizzo dei dati

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getDurata() {
		return durata;
	}

	public void setDurata(Integer durata) {
		this.durata = durata;
	}

	public Integer getDimensione() {
		return dimensione;
	}

	public void setDimensione(Integer dimensione) {
		this.dimensione = dimensione;
	}

	public Set<ServizioTv> getServizioTv() {
		return servizioTv;
	}

	public void setServizioTv(Set<ServizioTv> servizioTv) {
		this.servizioTv = servizioTv;
	}

}