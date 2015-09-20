package com.mahjongscoring.AppClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CJugadores {
	
	//region CAMPOS

	public CJugador j1;
	public CJugador j2;
	public CJugador j3;
	public CJugador j4;

	//endregion

	//region CONSTRUCTORES

	public CJugadores() {}
	public CJugadores (String[] nombres, int[] puntos, boolean[] chombos) {
		j1 = new CJugador(nombres[0], puntos[0], Enums.Asientos.ESTE);
		j2 = new CJugador(nombres[1], puntos[1], Enums.Asientos.SUR);
		j3 = new CJugador(nombres[2], puntos[2], Enums.Asientos.OESTE);
		j4 = new CJugador(nombres[3], puntos[3], Enums.Asientos.NORTE);
	}
	public CJugadores (String[] nombres, int[] puntos, Enums.Asientos[] asientos, boolean[] estanTapados, boolean[] estanChombo) {
		j1 = new CJugador(nombres[0], puntos[0], asientos[0], estanTapados[0], estanChombo[0]);
		j2 = new CJugador(nombres[1], puntos[1], asientos[1], estanTapados[1], estanChombo[1]);
		j3 = new CJugador(nombres[2], puntos[2], asientos[2], estanTapados[2], estanChombo[2]);
		j4 = new CJugador(nombres[3], puntos[3], asientos[3], estanTapados[3], estanChombo[3]);
	}

	//endregion

	//region  MÉTODOS PÚBLICOS

	public int getPosicionByAsiento(Enums.Asientos asiento) {
		if (j1.getAsiento() == asiento) return 1;
		else if (j2.getAsiento() == asiento) return 2;
		else if (j3.getAsiento() == asiento) return 3;
		else return 4;
	}
	public CJugador getByAsiento(Enums.Asientos asiento) {
		if (j1.getAsiento() == asiento) return j1;
		if (j2.getAsiento() == asiento) return j2;
		if (j3.getAsiento() == asiento) return j3;
		if (j4.getAsiento() == asiento) return j4;
		else return null;
	}
	public CJugador getByNombre(String nombre) {
		if (j1.getNombre() == nombre) return j1;
		if (j2.getNombre() == nombre) return j2;
		if (j3.getNombre() == nombre) return j3;
		if (j4.getNombre() == nombre) return j4;
		else return null;
	}
	public CJugador getByPosicion(int posicion) {
		if (posicion == 0) return j1;
		else if (posicion == 1) return j2;
		else if (posicion == 2) return j3;
		else return j4;
	}
	public List<CJugador> getListaOrdenadaPorPuntosDesc() {
		List<CJugador> jugadores = new ArrayList<CJugador>();
		jugadores.add(j1);
		jugadores.add(j2);
		jugadores.add(j3);
		jugadores.add(j4);
		Collections.sort(jugadores);
		return jugadores;
	}
	public String[] getListaOrdenadaPuntosCampeonato() {
		String[] puntos = { "4", "2", "1", "0" };
		List<CJugador> js = getListaOrdenadaPorPuntosDesc();
		
		if(js.get(0).getPuntos() == js.get(1).getPuntos() 
		&& js.get(1).getPuntos() == js.get(2).getPuntos()
		&& js.get(2).getPuntos() == js.get(3).getPuntos()) {
			puntos[0] = "1.75";
			puntos[1] = "1.75";
			puntos[2] = "1.75";
			puntos[3] = "1.75";
			return puntos;
		}
		
		if(js.get(0).getPuntos() == js.get(1).getPuntos() && js.get(1).getPuntos() == js.get(2).getPuntos()) {
			puntos[0] = "2.33";
			puntos[1] = "2.33";
			puntos[2] = "2.33";
			return puntos;
		}
		else if(js.get(1).getPuntos() == js.get(2).getPuntos() && js.get(2).getPuntos() == js.get(3).getPuntos()) {
			puntos[1] = "1";
			puntos[2] = "1";
			puntos[3] = "1";
			return puntos;
		}
		else {		
			if(js.get(0).getPuntos() == js.get(1).getPuntos()) {
				puntos[0] = "3";
				puntos[1] = "3";
			}
			if(js.get(1).getPuntos() == js.get(2).getPuntos()) {
				puntos[1] = "1.5";
				puntos[2] = "1.5";
			}
			if(js.get(2).getPuntos() == js.get(3).getPuntos()) {
				puntos[1] = "0.5";
				puntos[2] = "0.5";
			}
			return puntos;
		}
	}
	public void destaparTodos () {
		j1.setEstaTapado(false);
		j2.setEstaTapado(false);
		j3.setEstaTapado(false);
		j4.setEstaTapado(false);
	}
	public void despenalizarTodos () {
		j1.setEstaChonbo(false);
		j2.setEstaChonbo(false);
		j3.setEstaChonbo(false);
		j4.setEstaChonbo(false);
	}
	public void cambiarAsientosMcr(int ronda) {
		switch(ronda) {
			case 1:case 2:case 3:case 4:
	        	j1.setAsiento(Enums.Asientos.ESTE);
				j2.setAsiento(Enums.Asientos.SUR);
				j3.setAsiento(Enums.Asientos.OESTE);
				j4.setAsiento(Enums.Asientos.NORTE);
	        	break;
			case 5:case 6:case 7:case 8:
	        	j1.setAsiento(Enums.Asientos.SUR);
				j2.setAsiento(Enums.Asientos.ESTE);
				j3.setAsiento(Enums.Asientos.NORTE);
				j4.setAsiento(Enums.Asientos.OESTE);
				break;
			case 9:case 10:case 11:case 12:
	        	j1.setAsiento(Enums.Asientos.NORTE);
				j2.setAsiento(Enums.Asientos.OESTE);
				j3.setAsiento(Enums.Asientos.ESTE);
				j4.setAsiento(Enums.Asientos.SUR);
				break;
			case 13:case 14:case 15:case 16:
	        	j1.setAsiento(Enums.Asientos.OESTE);
				j2.setAsiento(Enums.Asientos.NORTE);
				j3.setAsiento(Enums.Asientos.SUR);
				j4.setAsiento(Enums.Asientos.ESTE);
	        	break;
		}
	}
	public void contarPenalty(Enums.Asientos asientoChombero, int puntos) {
		
		if (j1.getAsiento() == asientoChombero) 
			j1.restarPuntos(puntos);
		else 
			j1.sumarPuntos(puntos / 3);
		
		if (j2.getAsiento() == asientoChombero) 
			j2.restarPuntos(puntos);
		else 
			j2.sumarPuntos(puntos / 3);
		
		if (j3.getAsiento() == asientoChombero) 
			j3.restarPuntos(puntos);
		else 
			j3.sumarPuntos(puntos / 3);
		
		if (j4.getAsiento() == asientoChombero) 
			j4.restarPuntos(puntos);
		else 
			j4.sumarPuntos(puntos / 3);
	}
	public void contarMahjongDiscard(Enums.Asientos asientoGanador, Enums.Asientos asientoPerdedor, int puntosGanador) {
		
		if (j1.getAsiento() == asientoGanador) 
			j1.sumarPuntos(puntosGanador + (8 * 3));
		else if (j1.getAsiento() == asientoPerdedor) 
			j1.restarPuntos(puntosGanador + 8);
		else 
			j1.restarPuntos(8);
		
		if (j2.getAsiento() == asientoGanador) 
			j2.sumarPuntos(puntosGanador + (8 * 3));
		else if (j2.getAsiento() == asientoPerdedor) 
			j2.restarPuntos(puntosGanador + 8);
		else 
			j2.restarPuntos(8);
		
		if (j3.getAsiento() == asientoGanador) 
			j3.sumarPuntos(puntosGanador + (8 * 3));
		else if (j3.getAsiento() == asientoPerdedor) 
			j3.restarPuntos(puntosGanador + 8);
		else 
			j3.restarPuntos(8);
		
		if (j4.getAsiento() == asientoGanador) 
			j4.sumarPuntos(puntosGanador + (8 * 3));
		else if (j4.getAsiento() == asientoPerdedor) 
			j4.restarPuntos(puntosGanador + 8);
		else 
			j4.restarPuntos(8);
	}
	public void contarMahjongSelfdrawn(Enums.Asientos asientoGanador, int puntosGanador) {
		
		if (j1.getAsiento() == asientoGanador) 
			j1.sumarPuntos((puntosGanador + 8) * 3);
		else 
			j1.restarPuntos(puntosGanador + 8);
		
		if (j2.getAsiento() == asientoGanador) 
			j2.sumarPuntos((puntosGanador + 8) * 3);
		else 
			j2.restarPuntos(puntosGanador + 8);
		
		if (j3.getAsiento() == asientoGanador) 
			j3.sumarPuntos((puntosGanador + 8) * 3);
		else 
			j3.restarPuntos(puntosGanador + 8);
		
		if (j4.getAsiento() == asientoGanador) 
			j4.sumarPuntos((puntosGanador + 8) * 3);
		else 
			j4.restarPuntos(puntosGanador + 8);
	}
	public boolean sonTodosPenalty() {
		int count = 0;
		if(j1.estaChonbo()) count++;
		if(j2.estaChonbo()) count++;
		if(j3.estaChonbo()) count++;
		if(j4.estaChonbo()) count++;
		return count == 4;		
	}

	//endregion
}
