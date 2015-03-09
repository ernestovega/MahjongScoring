package com.mahjongscoring.AppClasses;

public class CJugador implements Comparable<CJugador>{
	
	//CAMPOS
	private String Nombre;
	private int Puntos;
	private Enums.Asientos Asiento;
	private boolean EstaTapado;
	private boolean EstaChonbo;
	
	//GETTERS
	public String getNombre() {
		return Nombre;
	}
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
	
	//SETTERS
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
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
	
	//CONSTRUCTORES
	public CJugador() {}
	public CJugador(String nombre, int puntos, Enums.Asientos asiento) {
		Nombre = nombre;
		Puntos = puntos;
		Asiento = asiento;
		EstaTapado = false;
		EstaChonbo = false;
	}
	public CJugador(String nombre, int puntos, Enums.Asientos asiento, boolean estaTapado, boolean estaChonbo) {
		Nombre = nombre;
		Puntos = puntos;
		Asiento = asiento;
		EstaTapado = estaTapado;
		EstaChonbo = estaChonbo;
	}
	
	//METODOS COMPARABLE
	//Orden descendente
	public int compareTo(CJugador jugador) {
		return jugador.Puntos - Puntos;
	}
	
	//Orden ascendente
	/*public int compareTo(CJugador jugador) {
		return Puntos - jugador.Puntos;
	}*/
	
	//METODOS
	public void sumarPuntos(int puntos) {
		Puntos += puntos;
	}
	public void restarPuntos(int puntos) {
		Puntos -= puntos;
	}
}
