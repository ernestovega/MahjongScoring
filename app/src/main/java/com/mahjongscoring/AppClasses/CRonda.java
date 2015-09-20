package com.mahjongscoring.AppClasses;

import com.mahjongscoring.DBClasses.DBRonda;

public class CRonda extends DBRonda {

    //region CONSTRUCTORES

    public CRonda (){}

    public CRonda(int idPartida, int numeroRonda) {
        setIdPartida(idPartida);
        setNumeroRonda(numeroRonda);
        setPuntosRondaJ1(0);
        setPuntosRondaJ2(0);
        setPuntosRondaJ3(0);
        setPuntosRondaJ4(0);
        setPuntosTotalesJ1(0);
        setPuntosTotalesJ2(0);
        setPuntosTotalesJ3(0);
        setPuntosTotalesJ4(0);
        setChomboJ1(0);
        setChomboJ2(0);
        setChomboJ3(0);
        setChomboJ4(0);
        setPuntosJugada(0);
        setNombreGanador("");
        setNombrePerdedor("");
    }

    //endregion

    //region MÉTODOS PÚBLICOS

    public boolean[] getEstanChombo() {
        boolean[] chombos = { getChomboJ1() != 0, getChomboJ2() != 0, getChomboJ3() != 0, getChomboJ4() != 0 };
        return chombos;
    }

    public int[] getChombos() {
        int[] chombos = { getChomboJ1(), getChomboJ2(), getChomboJ3(), getChomboJ4() };
        return chombos;
    }

    public void setChombo(int posicion, int tipoChombo) {
        if(posicion == 1)
            setChomboJ1(tipoChombo);
        else if(posicion == 2)
            setChomboJ2(tipoChombo);
        else if(posicion == 3)
            setChomboJ3(tipoChombo);
        else
            setChomboJ4(tipoChombo);
    }

    public void setPuntosRonda(int puntosRondaJ1, int puntosRondaJ2, int puntosRondaJ3, int puntosRondaJ4) {
        setPuntosRondaJ1(puntosRondaJ1;
        setPuntosRondaJ2(puntosRondaJ2;
        setPuntosRondaJ3(puntosRondaJ3;
        setPuntosRondaJ4(puntosRondaJ4;
    }

    public void setPuntosTotales(int puntosTotalesJ1, int puntosTotalesJ2, int puntosTotalesJ3, int puntosTotalesJ4) {
        PuntosTotalesJ1 = puntosTotalesJ1;
        PuntosTotalesJ2 = puntosTotalesJ2;
        PuntosTotalesJ3 = puntosTotalesJ3;
        PuntosTotalesJ4 = puntosTotalesJ4;
    }

    //endregion
}
