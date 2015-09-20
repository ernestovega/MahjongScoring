package com.mahjongscoring.AppClasses;

import com.mahjongscoring.DBClasses.DBJugador;

public class CJugador extends DBJugador implements Comparable<CJugador>{

	//region PROPIEDADES

    //region Campos

	private int Puntos;
	private Enums.Asientos Asiento;
	private boolean EstaTapado;
	private boolean EstaChonbo;

	//endregion

	//region Getters

	public int getPuntos() {
		return Puntos;
	}
	public Enums.Asientos getAsiento() {
		return Asiento;
	}
	public boolean estaChonbo() {
		return EstaChonbo;
	}
	public boolean estaTapado() {
		return EstaTapado;
	}

	//endregion

	//region Setters

	public void setPuntos(int puntos) {
		Puntos = puntos;
	}
	public void setAsiento(Enums.Asientos asiento) {
		Asiento = asiento;
	}
	public void setEstaTapado(boolean estaTapado) {
		this.EstaTapado = estaTapado;
	}
	public void setEstaChonbo(boolean estaChonbo) {
		this.EstaChonbo = estaChonbo;
	}

	//endregion

	//endregion

	//region CONSTRUCTORES

	public CJugador(String nombre, int puntos, Enums.Asientos asiento) {
		setNombre(nombre);
		Puntos = puntos;
		Asiento = asiento;
		EstaTapado = false;
		EstaChonbo = false;
	}

	public CJugador(String nombre, int puntos, Enums.Asientos asiento, boolean estaTapado, boolean estaChonbo) {
		setNombre(nombre);
		Puntos = puntos;
		Asiento = asiento;
		EstaTapado = estaTapado;
		EstaChonbo = estaChonbo;
	}

	//endregion

	//region MÉTODOS PÚBLICOS

	public void sumarPuntos(int puntos) {
		Puntos += puntos;
	}

	public void restarPuntos(int puntos) {
		Puntos -= puntos;
	}

	//region Comparable<CJugador>

	//Orden descendente
	public int compareTo(CJugador jugador) {
		return jugador.Puntos - Puntos;
	}

	//Orden ascendente
//	public int compareTo(CJugador jugador) {
//		return Puntos - jugador.Puntos;
//	}

	//endregion

	//endregion
}
