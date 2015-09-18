package com.mahjongscoring.DBClasses;

import java.util.Date;

public class DBJugador {

	//region PROPIEDADES

    //region Campos

	private String Nombre;
	private Date FechaAlta;

	//endregion

	//region Getters

	public String getNombre() {
		return Nombre;
	}
	public Date getFechaAlta() {
		return FechaAlta;
	}

	//endregion

	//region Setters

	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public void setFechaAlta(Date fechaAlta) {
		FechaAlta = fechaAlta;
	}

	//endregion

	//endregion

	//region CONSTRUCTORES

	public DBJugador(){}

	public DBJugador(String nombre, Date fechaAlta){
		Nombre = nombre;
		FechaAlta = fechaAlta;
	}

	//endregion

	//region MÉTODOS PÚBLICOS



	//endregion

    //region MÉTODOS PRIVADOS



    //endregion
}