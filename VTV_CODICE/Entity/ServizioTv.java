package Entity;

import java.util.*;

/*
 * 
 */
public class ServizioTv {

	protected String titolo;
	protected List<Filmato> listaFilmatiScelti;

	/**
	 * @param titolo
	 * @param listaFilmatiScelti
	 */
	public ServizioTv(String titolo, List<Filmato> listaFilmatiScelti) {
		this.titolo = titolo;
		this.listaFilmatiScelti = listaFilmatiScelti;
	}

	//Implementazione delle get e delle set per l'accesso e l'utilizzo dei dati
	
	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public List<Filmato> getListaFilmatiScelti() {
		return listaFilmatiScelti;
	}

	/**
	 * @param listaFilmatiScelti
	 */
	public void setListaFilmatiScelti(List<Filmato> listaFilmatiScelti) {
		this.listaFilmatiScelti = listaFilmatiScelti;
	}


}