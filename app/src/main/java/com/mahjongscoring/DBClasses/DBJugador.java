package com.mahjongscoring.DBClasses;

import java.util.Date;

public class DBJugador {

    //region CAMPOS

	private String Nombre;
	private Date FechaAlta;

	//endregion

	//region GETTERS

	protected String getNombre() { return Nombre; }
	protected Date getFechaAlta() { return FechaAlta; }

	//endregion

	//region SETTERS

	protected void setNombre(String nombre) { Nombre = nombre;	}
	protected void setFechaAlta(Date fechaAlta) {	FechaAlta = fechaAlta;	}

	//endregion
}