package com.mahjongscoring.DBClasses;

public class DBRonda {

	//region CAMPOS

	private int IdPartida;
	private int NumeroRonda;
	private int PuntosRondaJ1;
	private int PuntosRondaJ2;
	private int PuntosRondaJ3;
	private int PuntosRondaJ4;
	private int PuntosTotalesJ1;
	private int PuntosTotalesJ2;
	private int PuntosTotalesJ3;
	private int PuntosTotalesJ4;
	private int ChomboJ1;
	private int ChomboJ2;
	private int ChomboJ3;
	private int ChomboJ4;
	private int PuntosJugada;
	private String NombreGanador;
	private String NombrePerdedor;

	//endregion

	//region GETTERS

	public int getIdPartida() { return IdPartida; }
	public int getNumeroRonda() { return NumeroRonda; }
	public int getPuntosRondaJ1() {	return PuntosRondaJ1; }
	public int getPuntosRondaJ2() {	return PuntosRondaJ2; }
	public int getPuntosRondaJ3() {	return PuntosRondaJ3; }
	public int getPuntosRondaJ4() {	return PuntosRondaJ4; }
	public int getPuntosTotalesJ1() { return PuntosTotalesJ1; }
	public int getPuntosTotalesJ2() { return PuntosTotalesJ2; }
	public int getPuntosTotalesJ3() { return PuntosTotalesJ3; }
	public int getPuntosTotalesJ4() { return PuntosTotalesJ4; }
	public int getChomboJ1() { return ChomboJ1;	}
	public int getChomboJ2() { return ChomboJ2;	}
	public int getChomboJ3() { return ChomboJ3;	}
	public int getChomboJ4() { return ChomboJ4;	}
	public int getPuntosJugada() { return PuntosJugada;	}
	public String getNombreGanador() { return NombreGanador; }
	public String getNombrePerdedor() {	return NombrePerdedor; }

	//endregion

	//region SETTERS

	public void setIdPartida(int idPartida) { IdPartida = idPartida; }
	public void setNumeroRonda(int numeroRonda) { NumeroRonda = numeroRonda; }
	public void setPuntosRondaJ1(int puntosRondaJ1) { PuntosRondaJ1 = puntosRondaJ1; }
	public void setPuntosRondaJ2(int puntosRondaJ2) { PuntosRondaJ2 = puntosRondaJ2; }
	public void setPuntosRondaJ3(int puntosRondaJ3) { PuntosRondaJ3 = puntosRondaJ3; }
	public void setPuntosRondaJ4(int puntosRondaJ4) { PuntosRondaJ4 = puntosRondaJ4; }
	public void setPuntosTotalesJ1(int puntosTotalesJ1) { PuntosTotalesJ1 = puntosTotalesJ1; }
	public void setPuntosTotalesJ2(int puntosTotalesJ2) { PuntosTotalesJ2 = puntosTotalesJ2; }
	public void setPuntosTotalesJ3(int puntosTotalesJ3) { PuntosTotalesJ3 = puntosTotalesJ3; }
	public void setPuntosTotalesJ4(int puntosTotalesJ4) { PuntosTotalesJ4 = puntosTotalesJ4; }
	public void setChomboJ1(int chomboJ1) { ChomboJ1 = chomboJ1; }
	public void setChomboJ2(int chomboJ2) {	ChomboJ2 = chomboJ2; }
	public void setChomboJ3(int chomboJ3) {	ChomboJ3 = chomboJ3; }
	public void setChomboJ4(int chomboJ4) {	ChomboJ4 = chomboJ4; }
	public void setPuntosJugada(int puntosJugada) {	PuntosJugada = puntosJugada; }
	public void setNombreGanador(String nombreGanador) { NombreGanador = nombreGanador; }
	public void setNombrePerdedor(String nombrePerdedor) { NombrePerdedor = nombrePerdedor;	}

	//endregion
}
