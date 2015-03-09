package com.mahjongscoring.DBClasses;

public class DBRonda {

	//CAMPOS
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
	private int ChomboJ1; //30 o 60 para saber de que tipo era en MCR; 12000 o 8000 para saber si es jefe o no en RIICHI.
	private int ChomboJ2;
	private int ChomboJ3;
	private int ChomboJ4;
	private int PuntosJugada;
	private String NombreGanador;
	private String NombrePerdedor;

	//GETTERS
	public int getIdPartida() {
		return IdPartida;
	}
	public int getNumeroRonda() {
		return NumeroRonda;
	}
	public int getPuntosRondaJ1() {
		return PuntosRondaJ1;
	}
	public int getPuntosRondaJ2() {
		return PuntosRondaJ2;
	}
	public int getPuntosRondaJ3() {
		return PuntosRondaJ3;
	}
	public int getPuntosRondaJ4() {
		return PuntosRondaJ4;
	}
	public int getPuntosTotalesJ1() {
		return PuntosTotalesJ1;
	}
	public int getPuntosTotalesJ2() {
		return PuntosTotalesJ2;
	}
	public int getPuntosTotalesJ3() {
		return PuntosTotalesJ3;
	}
	public int getPuntosTotalesJ4() {
		return PuntosTotalesJ4;
	}
	public int getChomboJ1() {
		return ChomboJ1;
	}
	public int getChomboJ2() {
		return ChomboJ2;
	}
	public int getChomboJ3() {
		return ChomboJ3;
	}
	public int getChomboJ4() {
		return ChomboJ4;
	}
	public int getPuntosJugada() {
		return PuntosJugada;
	}
	public String getNombreGanador() {
		return NombreGanador;
	}
	public String getNombrePerdedor() {
		return NombrePerdedor;
	}

	//SETTERS
	public void setIdPartida(int idPartida) {
		IdPartida = idPartida;
	}
	public void setNumeroRonda(int numeroRonda) {
		NumeroRonda = numeroRonda;
	}
	public void setPuntosRondaJ1(int puntosRondaJ1) {
		PuntosRondaJ1 = puntosRondaJ1;
	}
	public void setPuntosRondaJ2(int puntosRondaJ2) {
		PuntosRondaJ2 = puntosRondaJ2;
	}
	public void setPuntosRondaJ3(int puntosRondaJ3) {
		PuntosRondaJ3 = puntosRondaJ3;
	}
	public void setPuntosRondaJ4(int puntosRondaJ4) {
		PuntosRondaJ4 = puntosRondaJ4;
	}
	public void setPuntosTotalesJ1(int puntosTotalesJ1) {
		PuntosTotalesJ1 = puntosTotalesJ1;
	}
	public void setPuntosTotalesJ2(int puntosTotalesJ2) {
		PuntosTotalesJ2 = puntosTotalesJ2;
	}
	public void setPuntosTotalesJ3(int puntosTotalesJ3) {
		PuntosTotalesJ3 = puntosTotalesJ3;
	}
	public void setPuntosTotalesJ4(int puntosTotalesJ4) {
		PuntosTotalesJ4 = puntosTotalesJ4;
	}
	public void setChomboJ1(int chomboJ1) {
		ChomboJ1 = chomboJ1;
	}
	public void setChomboJ2(int chomboJ2) {
		ChomboJ2 = chomboJ2;
	}
	public void setChomboJ3(int chomboJ3) {
		ChomboJ3 = chomboJ3;
	}
	public void setChomboJ4(int chomboJ4) {
		ChomboJ4 = chomboJ4;
	}
	public void setPuntosJugada(int puntosJugada) {
		PuntosJugada = puntosJugada;
	}
	public void setNombreGanador(String nombreGanador) {
		NombreGanador = nombreGanador;
	}
	public void setNombrePerdedor(String nombrePerdedor) {
		NombrePerdedor = nombrePerdedor;
	}
	
	//CONSTRUCTORES
	public DBRonda (){}
	public DBRonda(int idPartida, int numeroRonda) {
		IdPartida = idPartida;
		NumeroRonda = numeroRonda;
		PuntosRondaJ1 = 0;
		PuntosRondaJ2 = 0;
		PuntosRondaJ3 = 0;
		PuntosRondaJ4 = 0;
		PuntosTotalesJ1 = 0;
		PuntosTotalesJ2 = 0;
		PuntosTotalesJ3 = 0;
		PuntosTotalesJ4 = 0;
		ChomboJ1 = 0;
		ChomboJ2 = 0;
		ChomboJ3 = 0;
		ChomboJ4 = 0;
		PuntosJugada = 0;
		NombreGanador = "";
		NombrePerdedor = "";
	}
	public DBRonda(int idPartida, int numeroRonda, int[] puntosRondaJs, int[] puntosTotalesJs, int[] chombosJs, String nombreGanador, String nombrePerdedor, int puntosJugada){
		IdPartida = idPartida;
		NumeroRonda = numeroRonda;
		PuntosRondaJ1 = puntosRondaJs[0];
		PuntosRondaJ2 = puntosRondaJs[1];
		PuntosRondaJ3 = puntosRondaJs[2];
		PuntosRondaJ4 = puntosRondaJs[3];
		PuntosTotalesJ1 = puntosTotalesJs[0];
		PuntosTotalesJ2 = puntosTotalesJs[1];
		PuntosTotalesJ3 = puntosTotalesJs[2];
		PuntosTotalesJ4 = puntosTotalesJs[3];
		ChomboJ1 = chombosJs[0];
		ChomboJ2 = chombosJs[1];
		ChomboJ3 = chombosJs[2];
		ChomboJ4 = chombosJs[3];
		PuntosJugada = puntosJugada;
		NombreGanador = nombreGanador;
		NombrePerdedor = nombrePerdedor;
	}

	//METODOS
	public boolean[] getEstanChombo() {
		boolean[] chombos = { ChomboJ1 != 0, ChomboJ2 != 0, ChomboJ3 != 0, ChomboJ4 != 0 };
		return chombos;
	}
	public int[] getChombos() {
		int[] chombos = { ChomboJ1, ChomboJ2, ChomboJ3, ChomboJ4 };
		return chombos;
	}
	public void setChombo(int posicion, int tipoChombo) {
		if(posicion == 1)
			ChomboJ1 = tipoChombo;
		else if(posicion == 2)
			ChomboJ2 = tipoChombo;
		else if(posicion == 3)
			ChomboJ3 = tipoChombo;
		else
			ChomboJ4 = tipoChombo;
	}
	public void setPuntosRonda(int puntosRondaJ1, int puntosRondaJ2, int puntosRondaJ3, int puntosRondaJ4) {
		PuntosRondaJ1 = puntosRondaJ1;
		PuntosRondaJ2 = puntosRondaJ2;
		PuntosRondaJ3 = puntosRondaJ3;
		PuntosRondaJ4 = puntosRondaJ4;
	}
	public void setPuntosTotales(int puntosTotalesJ1, int puntosTotalesJ2, int puntosTotalesJ3, int puntosTotalesJ4) {
		PuntosTotalesJ1 = puntosTotalesJ1;
		PuntosTotalesJ2 = puntosTotalesJ2;
		PuntosTotalesJ3 = puntosTotalesJ3;
		PuntosTotalesJ4 = puntosTotalesJ4;
	}
}
