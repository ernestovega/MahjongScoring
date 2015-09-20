package com.mahjongscoring.AppClasses;

import com.mahjongscoring.DBClasses.DBPartida;

import java.util.Date;

public class CPartida extends DBPartida {

    //region CONSTRUCTORES

    public CPartida() {}

    public CPartida(Enums.TiposJuego tipoJuego,
                    int puntosMinimosMcr,
                    String[] nombresJs,
                    int[] puntosJs,
                    boolean isTimerOn,
                    boolean isSonidoFinalOn,
                    boolean isSonidoUltimos15On,
                    long duracionTimer,
                    long milisTranscurridosORestantes,
                    int numeroRondas,
                    String nombreJugadorMejorJugada,
                    int puntosMejorJugada,
                    Date fechaInicio,
                    Date fechaFin) {
        setTipoJuego(tipoJuego);
        setPuntosMinimosMcr(puntosMinimosMcr);
        setNombreJ1(nombresJs[0]);
        setNombreJ2(nombresJs[1]);
        setNombreJ3(nombresJs[2]);
        setNombreJ4(nombresJs[3]);
        setPuntosJ1(puntosJs[0]);
        setPuntosJ2(puntosJs[1]);
        setPuntosJ3(puntosJs[2]);
        setPuntosJ4(puntosJs[3]);
        setIsTimerOn(isTimerOn);
        setIsSonidoFinalOn(isSonidoFinalOn);
        setIsSonidoUltimos15On(isSonidoUltimos15On);
        setDuracionTimer(duracionTimer);
        setMilisTranscurridosORestantes(milisTranscurridosORestantes);
        setNumeroRondas(numeroRondas);
        setNombreJugadorMejorJugada(nombreJugadorMejorJugada);
        setPuntosMejorJugada(puntosMejorJugada);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
    }

    //endregion

    //region MÉTODOS PÚBLICOS

    public String[] getArrayNombres() {
        String[] nombres = { getNombreJ1(), getNombreJ2(), getNombreJ3(), getNombreJ4() };
        return nombres;
    }

    public int[] getArrayPuntos() {
        int[] puntos = { getPuntosJ1(), getPuntosJ2(), getPuntosJ3(), getPuntosJ4() };
        return puntos;
    }

    public void setPuntos(int puntosJ1, int puntosJ2, int puntosJ3, int puntosJ4) {
        setPuntosJ1(puntosJ1);
        setPuntosJ2(puntosJ2);
        setPuntosJ3(puntosJ3);
        setPuntosJ4(puntosJ4);
    }

    //endregion
}
