package com.mahjongscoring.DBClasses;

import java.util.Date;

public class DBJugador {

	//ATRIBUTOS
	private String Nombre;
	private Date FechaAlta;

	//GETTERS Y SETTERS DE LOS ATRIBUTOS
	public String getNombre() {
		return Nombre;
	}
	public Date getFechaAlta() {
		return FechaAlta;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public void setFechaAlta(Date fechaAlta) {
		FechaAlta = fechaAlta;
	}
	
	//CONSTRUCTORES
	public DBJugador(){}
	public DBJugador(String nombre, Date fechaAlta){
		Nombre = nombre;
		FechaAlta = fechaAlta;
	}
}