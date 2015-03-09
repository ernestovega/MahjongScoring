package com.mahjongscoring.AppClasses;

import java.util.Date;

/**
 * Created by Eto on 20/02/2015.
 */
public class CPartidaAntigua {

    private int Id;
    private int NumeroRondas;
    private String NombreJ1;
    private String NombreJ2;
    private String NombreJ3;
    private String NombreJ4;
    private int PuntosJ1;
    private int PuntosJ2;
    private int PuntosJ3;
    private int PuntosJ4;
    private Date FechaInicio;
    private String NombreMejorMano;
    private int PuntosMejorMano;

    public int getNumeroRondas() {
        return NumeroRondas;
    }
    public void setNumeroRondas(int numeroRondas) {
        NumeroRondas = numeroRondas;
    }
    public String getNombreJ1() {
        return NombreJ1;
    }
    public void setNombreJ1(String nombreJ1) {
        NombreJ1 = nombreJ1;
    }
    public String getNombreJ2() {
        return NombreJ2;
    }
    public void setNombreJ2(String nombreJ2) {
        NombreJ2 = nombreJ2;
    }
    public String getNombreJ3() {
        return NombreJ3;
    }
    public void setNombreJ3(String nombreJ3) {
        NombreJ3 = nombreJ3;
    }
    public String getNombreJ4() {
        return NombreJ4;
    }
    public void setNombreJ4(String nombreJ4) {
        NombreJ4 = nombreJ4;
    }
    public int getPuntosJ1() {
        return PuntosJ1;
    }
    public void setPuntosJ1(int puntosJ1) {
        PuntosJ1 = puntosJ1;
    }
    public int getPuntosJ2() {
        return PuntosJ2;
    }
    public void setPuntosJ2(int puntosJ2) {
        PuntosJ2 = puntosJ2;
    }
    public int getPuntosJ3() {
        return PuntosJ3;
    }
    public void setPuntosJ3(int puntosJ3) {
        PuntosJ3 = puntosJ3;
    }
    public int getPuntosJ4() {
        return PuntosJ4;
    }
    public void setPuntosJ4(int puntosJ4) {
        PuntosJ4 = puntosJ4;
    }
    public Date getFechaInicio() {
        return FechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        FechaInicio = fechaInicio;
    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getNombreMejorMano() {
        return NombreMejorMano;
    }
    public void setNombreMejorMano(String nombreMejorMano) {
        NombreMejorMano = nombreMejorMano;
    }
    public void setPuntosMejorMano(int puntosMejorMano) {
        PuntosMejorMano = puntosMejorMano;
    }
    public int getPuntosMejorMano() {
        return PuntosMejorMano;
    }
}
