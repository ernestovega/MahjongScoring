package com.mahjongscoring.DBClasses;

import com.mahjongscoring.AppClasses.Enums;

import java.util.Date;

public class DBPartida {

	//region CAMPOS

	private int Id;
	private Enums.TiposJuego TipoJuego;
	private int PuntosMinimosMcr;
	private String NombreJ1;
	private String NombreJ2;
	private String NombreJ3;
	private String NombreJ4;
	private int PuntosJ1;
	private int PuntosJ2;
	private int PuntosJ3;
	private int PuntosJ4;
	private boolean IsTimerOn;
	private boolean IsSonidoFinalOn;
	private boolean IsSonidoUltimos15On;
	private long DuracionTimer;
    private long MilisTranscurridosORestantes;
	private int NumeroRondas;
	private int PuntosMejorJugada;
	private String NombreJugadorMejorJugada;
	private Date FechaInicio;
	private Date FechaFin;

    //endregion

    //region GETTERS

	protected int getId() { return Id;	}
	protected Enums.TiposJuego getTipoJuego() { return TipoJuego; }
	protected int getPuntosMinimosMcr() { return PuntosMinimosMcr; }
	protected String getNombreJ1() { return NombreJ1; }
	protected String getNombreJ2() { return NombreJ2; }
	protected String getNombreJ3() { return NombreJ3; }
	protected String getNombreJ4() { return NombreJ4; }
	protected int getPuntosJ1() { return PuntosJ1; }
	protected int getPuntosJ2() { return PuntosJ2; }
	protected int getPuntosJ3() { return PuntosJ3; }
	protected int getPuntosJ4() { return PuntosJ4; }
	protected boolean isTimerOn() { return IsTimerOn; }
	protected boolean isSonidoFinalOn() { return IsSonidoFinalOn; }
	protected boolean isSonidoUltimos15On() { return IsSonidoUltimos15On; }
	protected long getDuracionTimer() { return DuracionTimer; }
    protected long getMilisTranscurridosORestantes() { return MilisTranscurridosORestantes; }
	protected int getNumeroRondas() { return NumeroRondas; }
	protected String getNombreJugadorMejorJugada() { return NombreJugadorMejorJugada; }
	protected int getPuntosMejorJugada() { return PuntosMejorJugada; }
	protected Date getFechaInicio() { return FechaInicio; }
	protected Date getFechaFin() { return FechaFin; }

	//endregion

	//region SETTERS

	protected void setId(int id) {	Id = id; }
	protected void setTipoJuego(Enums.TiposJuego tipoJuego) { TipoJuego = tipoJuego; }
	protected void setPuntosMinimosMcr(int puntosMinimosMcr) { PuntosMinimosMcr = puntosMinimosMcr; }
	protected void setNombreJ1(String nombreJ1) { NombreJ1 = nombreJ1;	}
	protected void setNombreJ2(String nombreJ2) { NombreJ2 = nombreJ2;	}
	protected void setNombreJ3(String nombreJ3) { NombreJ3 = nombreJ3;	}
	protected void setNombreJ4(String nombreJ4) { NombreJ4 = nombreJ4;	}
	protected void setPuntosJ1(int puntosJ1) { PuntosJ1 = puntosJ1; }
	protected void setPuntosJ2(int puntosJ2) { PuntosJ2 = puntosJ2; }
	protected void setPuntosJ3(int puntosJ3) { PuntosJ3 = puntosJ3; }
	protected void setPuntosJ4(int puntosJ4) { PuntosJ4 = puntosJ4; }
	protected void setIsTimerOn(boolean isTimerOn) { IsTimerOn = isTimerOn; }
	protected void setIsSonidoFinalOn(boolean isSonidoFinalOn) { IsSonidoFinalOn = isSonidoFinalOn; }
	protected void setIsSonidoUltimos15On(boolean isSonidoUltimos15On) { IsSonidoUltimos15On = isSonidoUltimos15On; }
	protected void setDuracionTimer(long duracionTimer) { DuracionTimer = duracionTimer; }
    protected void setMilisTranscurridosORestantes(long milisTranscurridosORestantes) { this.MilisTranscurridosORestantes = milisTranscurridosORestantes; }
	protected void setNumeroRondas(int numeroRondas) { NumeroRondas = numeroRondas; }
	protected void setNombreJugadorMejorJugada(String nombreJugadorMejorJugada) { NombreJugadorMejorJugada = nombreJugadorMejorJugada; }
	protected void setPuntosMejorJugada(int puntosMejorJugada) { PuntosMejorJugada = puntosMejorJugada; }
	protected void setFechaInicio(Date fechaInicio) { FechaInicio = fechaInicio; }
	protected void setFechaFin(Date fechaFin) { FechaFin = fechaFin; }

	//endregion
}
