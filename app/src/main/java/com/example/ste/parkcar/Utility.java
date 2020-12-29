package com.example.ste.parkcar;

public class Utility {

// *** VARIABILI DI CLASSE *** 

private double km_da_percorrere;
private double litri_spesi;
private double soldi_spesi;
private String andata_o_ritorno;
	
// *** METODI ***
	
	// *** metodo 1 ***
	
	protected double km_da_percorrere (double numero_giorni, double numero_km,double numViaggi, String andata_o_ritorno ) {
		
		double risultato = 0;
		if (andata_o_ritorno.equals("andata")) {
			risultato = numero_km * numero_giorni;
		} else if (andata_o_ritorno.equals("andata e ritorno")) {
			risultato = (numero_km * numero_giorni) * 2;
		}
		km_da_percorrere = risultato;	
		return km_da_percorrere * numViaggi;
	}
	
	// *** metodo 2 ***
	
	protected double litri_spesi (double numViaggi,double km_da_percorrere, double consumo_auto, String andata_o_ritorno) {
		
		double risultato = 0;
		if (andata_o_ritorno.equals("andata")) {
			risultato = km_da_percorrere / consumo_auto;
		} else if (andata_o_ritorno.equals("andata e ritorno")) {
			risultato = (km_da_percorrere / consumo_auto) *2 ;
		} else {
			throw new IllegalArgumentException ()  ;
		}
		litri_spesi=risultato;
		litri_spesi=litri_spesi * numViaggi;
		return risultato;
	}
	
	// *** metodo 3 ***
	
	protected double prezzo_selfservice_benzina (double litri_spesi, double prezzo_al_litro_benzina ) {
		
		double risultato = litri_spesi * prezzo_al_litro_benzina;
		soldi_spesi = risultato;
		return risultato;
	}
	

}