package com.mahjongscoring.Fragments;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

import com.mahjongscoring.activities.ActivityConfigurarPartida;
import com.mahjongscoring.activities.R;
import com.mahjongscoring.activities.ActivityMesa;
import com.mahjongscoring.AppClasses.CJugador;
import com.mahjongscoring.AppClasses.Enums.Asientos;
import com.mahjongscoring.AppClasses.Enums.TiposJuego;

import java.util.concurrent.TimeUnit;

public class FragmentMesaAsientos extends Fragment {

	//VARIABLES
	private ActivityMesa padre;
	private ViewGroup rootView;
	private int contadorCancelar;
	private CountDownTimer timer;
    	
	//CONSTRUCTORES
	public static FragmentMesaAsientos crearFragment() {
		FragmentMesaAsientos fragment = new FragmentMesaAsientos();
        return fragment;
    }
	
	//CICLO DE VIDA
	@Override    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
    		Bundle savedInstanceState) {
		rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mesa_asientos, container, false);
		padre = (ActivityMesa)getActivity();
		actualizarAsientos();
		actualizarCentro();
		
		return rootView;
    }
	@Override
	public void onPause() {
		super.onPause();
		if (!padre.isTiempoParado) {
			padre.milisCuandoOnPause = SystemClock.elapsedRealtime();
            stopTiempo();
		}
	}
	@Override
	public void onResume() {
		super.onResume();
		if (padre.isTiempoParado) {
			RelativeLayout centro = (RelativeLayout)rootView.findViewById(R.id.relativelayout_centro);
			centro.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
                    if(padre.isFinalOn)
                        padre.mostrarAlertDialogRankingFinal();
                    else
					    startTiempo();
				}
			});
		}
		else {
            if (!padre.getIntent().getExtras().getBoolean(ActivityConfigurarPartida.EXTRA_ES_REANUDACION))
                padre.partida.setMilisTranscurridosORestantes(padre.partida.getMilisTranscurridosORestantes() - (SystemClock.elapsedRealtime() - padre.milisCuandoOnPause));

            startTiempo();
		}
	}

	//ACTUALIZAR UI
	public void actualizarAsientos() {
		/*RelativeLayout relativeLayoutInfoRichii = (RelativeLayout)rootView.findViewById(R.id.relativelayout_info_richi);
		TextView textviewTitleCountersRichii = (TextView)rootView.findViewById(R.id.textview_title_counters_richii);
		TextView textviewCountersRichii = (TextView)rootView.findViewById(R.id.textview_counters_richii);
		TextView textviewTitlePotRichii = (TextView)rootView.findViewById(R.id.textview_title_pot_richii);
		TextView textviewPotRichii = (TextView)rootView.findViewById(R.id.textview_pot_richii);*/
		TextView textviewNombreEste = (TextView)rootView.findViewById(R.id.textview_nombre_jugador_este);
		TextView textviewPuntosEste = (TextView)rootView.findViewById(R.id.textview_puntos_jugador_este);
		/*TextView textviewvecesJefeRichii = (TextView)rootView.findViewById(R.id.textview_repeticiones_jefe_richii);*/
		TextView textviewNombreSur = (TextView)rootView.findViewById(R.id.textview_nombre_jugador_sur);
    	TextView textviewPuntosSur = (TextView)rootView.findViewById(R.id.textview_puntos_jugador_sur);
		TextView textviewNombreOeste = (TextView)rootView.findViewById(R.id.textview_nombre_jugador_oeste);
		TextView textviewPuntosOeste = (TextView)rootView.findViewById(R.id.textview_puntos_jugador_oeste);
		TextView textviewNombreNorte = (TextView)rootView.findViewById(R.id.textview_nombre_jugador_norte);
		TextView textviewPuntosNorte = (TextView)rootView.findViewById(R.id.textview_puntos_jugador_norte);		
		textviewNombreEste.setTypeface(padre.typefaceKorean);
		textviewNombreSur.setTypeface(padre.typefaceKorean);
		textviewNombreOeste.setTypeface(padre.typefaceKorean);
		textviewNombreNorte.setTypeface(padre.typefaceKorean);
		textviewPuntosEste.setTypeface(padre.typefaceKorean);
		textviewPuntosSur.setTypeface(padre.typefaceKorean);
		textviewPuntosOeste.setTypeface(padre.typefaceKorean);
		textviewPuntosNorte.setTypeface(padre.typefaceKorean);
		textviewNombreEste.setText(padre.jugadores.getByAsiento(Asientos.ESTE).getNombre(), BufferType.EDITABLE);
		textviewNombreSur.setText(padre.jugadores.getByAsiento(Asientos.SUR).getNombre(), BufferType.EDITABLE);
		textviewNombreOeste.setText(padre.jugadores.getByAsiento(Asientos.OESTE).getNombre(), BufferType.EDITABLE);
		textviewNombreNorte.setText(padre.jugadores.getByAsiento(Asientos.NORTE).getNombre(), BufferType.EDITABLE);
		int ptsJ1 = padre.jugadores.getByAsiento(Asientos.ESTE).getPuntos();
		int ptsJ2 = padre.jugadores.getByAsiento(Asientos.SUR).getPuntos();
		int ptsJ3 = padre.jugadores.getByAsiento(Asientos.OESTE).getPuntos();
		int ptsJ4 = padre.jugadores.getByAsiento(Asientos.NORTE).getPuntos();
		textviewPuntosEste.setText(ptsJ1 > 0 ? "+" + String.valueOf(ptsJ1) : String.valueOf(ptsJ1), BufferType.EDITABLE);
		textviewPuntosSur.setText(ptsJ2 > 0 ? "+" + String.valueOf(ptsJ2) : String.valueOf(ptsJ2), BufferType.EDITABLE);
		textviewPuntosOeste.setText(ptsJ3 > 0 ? "+" + String.valueOf(ptsJ3) : String.valueOf(ptsJ3), BufferType.EDITABLE);
		textviewPuntosNorte.setText(ptsJ4 > 0 ? "+" + String.valueOf(ptsJ4) : String.valueOf(ptsJ4), BufferType.EDITABLE);
		if(padre.isTiempoParado) {
            taparTodosAsientos(R.color.gristransparente);
        }
		else {
			destaparTodosAsientos();
			taparChomberos();
		}
		ImageView imgAsientoEste = (ImageView)rootView.findViewById(R.id.imageview_viento_asiento_este);
		ImageView imgAsientoSur = (ImageView)rootView.findViewById(R.id.imageview_viento_asiento_sur);
		ImageView imgAsientoOeste = (ImageView)rootView.findViewById(R.id.imageview_viento_asiento_oeste);
		ImageView imgAsientoNorte = (ImageView)rootView.findViewById(R.id.imageview_viento_asiento_norte);
		if(padre.partida.getTipoJuego() == TiposJuego.MCR) {
			switch(padre.partida.getNumeroRondas() + 1) {
				case 1:case 5:case 9:case 13:
					imgAsientoEste.setImageResource(R.drawable.imgvientoeste);
					imgAsientoSur.setImageResource(R.drawable.imgvientosur);
					imgAsientoOeste.setImageResource(R.drawable.imgvientooeste);
					imgAsientoNorte.setImageResource(R.drawable.imgvientonorte);
					imgAsientoEste.setContentDescription(getString(R.string.img_viento_este));
					imgAsientoSur.setContentDescription(getString(R.string.img_viento_sur));
					imgAsientoOeste.setContentDescription(getString(R.string.img_viento_oeste));
					imgAsientoNorte.setContentDescription(getString(R.string.img_viento_norte));
					break;
				case 2:case 6:case 10:case 14:
					imgAsientoEste.setImageResource(R.drawable.imgvientonorte);
					imgAsientoSur.setImageResource(R.drawable.imgvientoeste);
					imgAsientoOeste.setImageResource(R.drawable.imgvientosur);
					imgAsientoNorte.setImageResource(R.drawable.imgvientooeste);
					imgAsientoEste.setContentDescription(getString(R.string.img_viento_norte));
					imgAsientoSur.setContentDescription(getString(R.string.img_viento_este));
					imgAsientoOeste.setContentDescription(getString(R.string.img_viento_sur));
					imgAsientoNorte.setContentDescription(getString(R.string.img_viento_oeste));
					break;
				case 3:case 7:case 11:case 15:
					imgAsientoEste.setImageResource(R.drawable.imgvientooeste);
					imgAsientoSur.setImageResource(R.drawable.imgvientonorte);
					imgAsientoOeste.setImageResource(R.drawable.imgvientoeste);
					imgAsientoNorte.setImageResource(R.drawable.imgvientosur);
					imgAsientoEste.setContentDescription(getString(R.string.img_viento_oeste));
					imgAsientoSur.setContentDescription(getString(R.string.img_viento_norte));
					imgAsientoOeste.setContentDescription(getString(R.string.img_viento_este));
					imgAsientoNorte.setContentDescription(getString(R.string.img_viento_sur));
					break;
				case 4:case 8:case 12:case 16:
					imgAsientoEste.setImageResource(R.drawable.imgvientosur);
					imgAsientoSur.setImageResource(R.drawable.imgvientooeste);
					imgAsientoOeste.setImageResource(R.drawable.imgvientonorte);
					imgAsientoNorte.setImageResource(R.drawable.imgvientoeste);
					imgAsientoEste.setContentDescription(getString(R.string.img_viento_sur));
					imgAsientoSur.setContentDescription(getString(R.string.img_viento_oeste));
					imgAsientoOeste.setContentDescription(getString(R.string.img_viento_norte));
					imgAsientoNorte.setContentDescription(getString(R.string.img_viento_este));
					break;
			}
		}
		/*else {
			relativeLayoutInfoRichii.setVisibility(View.VISIBLE);
			textviewTitleCountersRichii.setVisibility(View.VISIBLE);
			textviewvecesJefeRichii.setVisibility(View.VISIBLE);
			textviewTitlePotRichii.setVisibility(View.VISIBLE);
			textviewCountersRichii.setTypeface(padre.typefaceKorean);
			textviewTitleCountersRichii.setTypeface(padre.typefaceKorean);
			textviewPotRichii.setTypeface(padre.typefaceKorean);
			textviewTitlePotRichii.setTypeface(padre.typefaceKorean);
			textviewvecesJefeRichii.setTypeface(padre.typefaceKorean);
			*//*textviewCountersRichii.setText(String.valueOf(padre.contadoresRichii));
			textviewPotRichii.setText(String.valueOf(padre.boteRichii));
			textviewvecesJefeRichii.setText(getString(R.string.x) + String.valueOf(padre.vecesJefeRichii));*//*
		}*/
	}		
	public void actualizarCentro() {
//        if (padre.partida.getTipoJuego() == TiposJuego.RICHII) {
//            RelativeLayout relativeLayoutCentro = (RelativeLayout) rootView.findViewById(R.id.relativelayout_centro);
//            int dpsInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, padre.getResources().getDisplayMetrics());
//            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) relativeLayoutCentro.getLayoutParams();
//            params.setMargins(0, dpsInPixels, 0, 0);
//            relativeLayoutCentro.setLayoutParams(params);
//        }
        int imgVientoRonda = 0;
        String contentDescription = "";
        ImageView imageviewTaparCentro = (ImageView) rootView.findViewById(R.id.imageview_tapar_centro);
        ImageView imageviewVientoRonda = (ImageView) rootView.findViewById(R.id.imageview_viento_ronda);
        TextView textviewNumRonda = (TextView) rootView.findViewById(R.id.textview_numero_ronda);
        Chronometer chronometer = (Chronometer) rootView.findViewById(R.id.chronometer_mesa);
        textviewNumRonda.setTypeface(padre.typefaceKorean);
        chronometer.setTypeface(padre.typefaceKorean);
        //NUMERO RONDA
        //if (padre.partida.getTipoJuego() == TiposJuego.MCR) {
            if (padre.partida.getNumeroRondas() < 16) {
                textviewNumRonda.setVisibility(View.VISIBLE);
                textviewNumRonda.setText(String.valueOf(padre.partida.getNumeroRondas() + 1));
            } else
                textviewNumRonda.setVisibility(View.INVISIBLE);
        //}
        //TIEMPO
        if (padre.isFinalOn) {
            quitarEventos();
            destaparCentroFinal();
        }
        else {
            if (padre.isTiempoParado) {
                imageviewTaparCentro.setVisibility(View.VISIBLE);
                imageviewTaparCentro.setImageResource(R.drawable.ic_action_play);
                if (padre.partida.getNumeroRondas() > 0)
                    imageviewTaparCentro.setContentDescription(getString(R.string.reanudar));
                else
                    imageviewTaparCentro.setContentDescription(getString(R.string.empezar));
            }
            else {
                imageviewTaparCentro.setVisibility(View.INVISIBLE);
            }
        }
		//VIENTO RONDA
		switch(padre.partida.getNumeroRondas() + 1) {
			case 1:case 2:case 3:case 4:
				imgVientoRonda = R.drawable.imgvientoeste_sinmargen;
				contentDescription = getString(R.string.img_viento_este);
				break;
			case 5:case 6:case 7:case 8:
				imgVientoRonda = R.drawable.imgvientosur_sinmargen;
				contentDescription = getString(R.string.img_viento_sur);
				break;
			case 9:case 10:case 11:case 12:
				imgVientoRonda = R.drawable.imgvientooeste_sinmargen;
				contentDescription = getString(R.string.img_viento_oeste);
				break;
			case 13:case 14:case 15:case 16:
				imgVientoRonda = R.drawable.imgvientonorte_sinmargen;
				contentDescription = getString(R.string.img_viento_norte);
				break;
		}		
		imageviewVientoRonda.setImageResource(imgVientoRonda);
		imageviewVientoRonda.setContentDescription(contentDescription);
	}

	//EVENTOS
	public void asignarEventosPrincipales() {
		rootView.findViewById(R.id.relativelayout_asiento_este).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    CJugador jugador = padre.jugadores.getByAsiento(Asientos.ESTE);
                    if (jugador.estaChonbo()) {
                        jugador.setEstaChonbo(false);
                        padre.rondas.get(padre.rondas.size() - 1).setChombo(padre.jugadores.getPosicionByAsiento(Asientos.ESTE), 0);
                        padre.db.actualizarRonda(padre.rondas.get(padre.rondas.size() - 1));
                        padre.asientoPulsado = null;
                        padre.asientoPerdedor = null;
                        destaparTodosAsientos();
                        actualizarCentro();
                        asignarEventosPrincipales();
                        actualizarAsientos();
                } else if (!padre.isTiempoParado) {
                    padre.asientoPulsado = Asientos.ESTE;
                    tapar1Asiento(Asientos.ESTE, R.color.verdetransparente);
                    quitarEventos();
                    if (padre.partida.getTipoJuego() == TiposJuego.MCR) {
                        if(padre.jugadores.getByAsiento(padre.asientoPulsado).estaChonbo())
                            padre.desanotarPenalty();
                        else
                            padre.mostrarAlertDialogAsientoPulsadoMcr();
                    }
                    /*else
                        padre.mostrarAlertDialogAsientoPulsadoRichii();*/
                }
            }
        });
		rootView.findViewById(R.id.relativelayout_asiento_sur).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CJugador jugador = padre.jugadores.getByAsiento(Asientos.SUR);
                if(jugador.estaChonbo()) {
                    jugador.setEstaChonbo(false);
                    padre.rondas.get(padre.rondas.size() - 1).setChombo(padre.jugadores.getPosicionByAsiento(Asientos.SUR), 0);
                    padre.db.actualizarRonda(padre.rondas.get(padre.rondas.size() - 1));
                    padre.asientoPulsado = null;
                    padre.asientoPerdedor = null;
                    destaparTodosAsientos();
                    actualizarCentro();
                    asignarEventosPrincipales();
                    actualizarAsientos();
                }
                else if (!padre.isTiempoParado) {
                    padre.asientoPulsado = Asientos.SUR;
                    tapar1Asiento(Asientos.SUR, R.color.verdetransparente);
                    quitarEventos();
                    if (padre.partida.getTipoJuego() == TiposJuego.MCR) {
                        if(padre.jugadores.getByAsiento(padre.asientoPulsado).estaChonbo())
                            padre.desanotarPenalty();
                        else
                            padre.mostrarAlertDialogAsientoPulsadoMcr();
                    }
                    /*else
                        padre.mostrarAlertDialogAsientoPulsadoRichii();*/
                }
            }
        });
		rootView.findViewById(R.id.relativelayout_asiento_oeste).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CJugador jugador = padre.jugadores.getByAsiento(Asientos.OESTE);
                if (jugador.estaChonbo()) {
                    jugador.setEstaChonbo(false);
                    padre.rondas.get(padre.rondas.size() - 1).setChombo(padre.jugadores.getPosicionByAsiento(Asientos.OESTE), 0);
                    padre.db.actualizarRonda(padre.rondas.get(padre.rondas.size() - 1));
                    padre.asientoPulsado = null;
                    padre.asientoPerdedor = null;
                    destaparTodosAsientos();
                    actualizarCentro();
                    asignarEventosPrincipales();
                    actualizarAsientos();
                }
                else if (!padre.isTiempoParado) {
	            	padre.asientoPulsado = Asientos.OESTE;
	            	tapar1Asiento(Asientos.OESTE, R.color.verdetransparente);
	            	quitarEventos();
                    if (padre.partida.getTipoJuego() == TiposJuego.MCR) {
                        if(padre.jugadores.getByAsiento(padre.asientoPulsado).estaChonbo())
                            padre.desanotarPenalty();
                        else
                            padre.mostrarAlertDialogAsientoPulsadoMcr();
                    }
                    /*else
                        padre.mostrarAlertDialogAsientoPulsadoRichii();*/
	            }
            }
        });
		rootView.findViewById(R.id.relativelayout_asiento_norte).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CJugador jugador = padre.jugadores.getByAsiento(Asientos.NORTE);
                if (jugador.estaChonbo()) {
                    jugador.setEstaChonbo(false);
                    padre.rondas.get(padre.rondas.size() - 1).setChombo(padre.jugadores.getPosicionByAsiento(Asientos.NORTE), 0);
                    padre.db.actualizarRonda(padre.rondas.get(padre.rondas.size() - 1));
                    padre.asientoPulsado = null;
                    padre.asientoPerdedor = null;
                    destaparTodosAsientos();
                    actualizarCentro();
                    asignarEventosPrincipales();
                    actualizarAsientos();
                }
                else if (!padre.isTiempoParado) {
	            	padre.asientoPulsado = Asientos.NORTE;
	            	tapar1Asiento(Asientos.NORTE, R.color.verdetransparente);
	            	quitarEventos();
                    if (padre.partida.getTipoJuego() == TiposJuego.MCR) {
                        if(padre.jugadores.getByAsiento(padre.asientoPulsado).estaChonbo())
                            padre.desanotarPenalty();
                        else
                            padre.mostrarAlertDialogAsientoPulsadoMcr();
                    }
                    /*else
                        padre.mostrarAlertDialogAsientoPulsadoRichii();*/
	            }
            }
        });
		rootView.findViewById(R.id.relativelayout_centro).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(padre.isTiempoParado)
                    startTiempo();
                else
                    padre.mostrarAlertDialogCentroPulsado();
            }
        });
	}
	public void asignarEventosSeleccionar1PerdedorMahjongDiscard() {
		padre.isSeleccionDePerdedorOn = true;
		destaparTodosAsientosExcepto1(padre.asientoPulsado);
		contadorCancelar = 0;
		((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.ic_no_ok);
		rootView.findViewById(R.id.textview_numero_ronda).setVisibility(View.INVISIBLE);
		rootView.findViewById(R.id.chronometer_mesa).setVisibility(View.INVISIBLE);
		rootView.findViewById(R.id.imageview_viento_ronda).setVisibility(View.INVISIBLE);
		rootView.findViewById(R.id.imageview_tapar_centro).setVisibility(View.VISIBLE);
		Toast.makeText(padre, getResources().getString(R.string.toast_seleccione_perdedor), Toast.LENGTH_SHORT).show();
		
		rootView.findViewById(R.id.relativelayout_asiento_este).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
        		contadorCancelar = 0;
            	if(padre.asientoPulsado != Asientos.ESTE) {
            		CJugador jugador = padre.jugadores.getByAsiento(Asientos.ESTE);
					if(jugador.estaTapado()) {
						destapar1Asiento(Asientos.ESTE);
						jugador.setEstaTapado(false);
            			padre.asientoPerdedor = null;
	        			((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.ic_no_ok);    							
					}
					else {
						destaparTodosAsientosExcepto1(padre.asientoPulsado);
						tapar1Asiento(Asientos.ESTE, R.color.rojotransparente);
						jugador.setEstaTapado(true);
						padre.asientoPerdedor = Asientos.ESTE;
	        			((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.ic_ok);
					}
            	}
            	else if (padre.asientoPerdedor == null)
            		((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.img_center_no_ok);
            }
        });
		rootView.findViewById(R.id.relativelayout_asiento_sur).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
        		contadorCancelar = 0;
            	if(padre.asientoPulsado != Asientos.SUR) {
            		CJugador jugador = padre.jugadores.getByAsiento(Asientos.SUR);
					if(jugador.estaTapado()) {
						destapar1Asiento(Asientos.SUR);
						jugador.setEstaTapado(false);
            			padre.asientoPerdedor = null;
	        			((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.ic_no_ok);    							
					}
					else {
						destaparTodosAsientosExcepto1(padre.asientoPulsado);
						tapar1Asiento(Asientos.SUR, R.color.rojotransparente);
						jugador.setEstaTapado(true);
						padre.asientoPerdedor = Asientos.SUR;
	        			((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.ic_ok);
					}
            	}
            	else if (padre.asientoPerdedor == null)
            		((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.img_center_no_ok);
            }
        });
		rootView.findViewById(R.id.relativelayout_asiento_oeste).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
        		contadorCancelar = 0;
            	if(padre.asientoPulsado != Asientos.OESTE) {
            		CJugador jugador = padre.jugadores.getByAsiento(Asientos.OESTE);
					if(jugador.estaTapado()) {
						destapar1Asiento(Asientos.OESTE);
						jugador.setEstaTapado(false);
            			padre.asientoPerdedor = null;
	        			((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.ic_no_ok);    							
					}
					else {
						destaparTodosAsientosExcepto1(padre.asientoPulsado);
						tapar1Asiento(Asientos.OESTE, R.color.rojotransparente);
						jugador.setEstaTapado(true);
						padre.asientoPerdedor = Asientos.OESTE;
	        			((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.ic_ok);
					}
            	}
            	else if (padre.asientoPerdedor == null)
            		((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.img_center_no_ok);
            }
        });
		rootView.findViewById(R.id.relativelayout_asiento_norte).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
        		contadorCancelar = 0;
            	if(padre.asientoPulsado != Asientos.NORTE) {
            		CJugador jugador = padre.jugadores.getByAsiento(Asientos.NORTE);
					if(jugador.estaTapado()) {
						destapar1Asiento(Asientos.NORTE);
						jugador.setEstaTapado(false);
            			padre.asientoPerdedor = null;
	        			((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.ic_no_ok);    							
					}
					else {
						destaparTodosAsientosExcepto1(padre.asientoPulsado);
						tapar1Asiento(Asientos.NORTE, R.color.rojotransparente);
						jugador.setEstaTapado(true);
						padre.asientoPerdedor = Asientos.NORTE;
	        			((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.ic_ok);
					}
            	}
            	else if (padre.asientoPerdedor == null)
            		((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.img_center_no_ok);
            }
        });
		rootView.findViewById(R.id.relativelayout_centro).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
        		if(padre.asientoPerdedor != null)
        			clickAceptarCentroSeleccionDePerdedor();
            	else
            		clickCancelarCentroSeleccionDePerdedor();
            }
        });
	}
	public void quitarEventos() {
		rootView.findViewById(R.id.relativelayout_asiento_este).setOnClickListener(null);
		rootView.findViewById(R.id.relativelayout_asiento_sur).setOnClickListener(null);
		rootView.findViewById(R.id.relativelayout_asiento_oeste).setOnClickListener(null);
		rootView.findViewById(R.id.relativelayout_asiento_norte).setOnClickListener(null);
		rootView.findViewById(R.id.relativelayout_centro).setOnClickListener(null);
	}
	public void clickAceptarCentroSeleccionDePerdedor() {
		rootView.findViewById(R.id.imageview_tapar_centro).setVisibility(View.INVISIBLE);
		rootView.findViewById(R.id.textview_numero_ronda).setVisibility(View.VISIBLE);
		rootView.findViewById(R.id.chronometer_mesa).setVisibility(View.VISIBLE);
		rootView.findViewById(R.id.imageview_viento_ronda).setVisibility(View.VISIBLE);
		padre.jugadores.contarMahjongDiscard(padre.asientoPulsado, padre.asientoPerdedor, padre.puntosGanador);
		padre.nuevaRondaMcr();
	}
	public void clickCancelarCentroSeleccionDePerdedor() {
		if(padre.asientoPerdedor != null) {
			contadorCancelar = 0;
			padre.jugadores.getByAsiento(padre.asientoPulsado).setEstaTapado(false);
        	destapar1Asiento(padre.asientoPerdedor);
			padre.asientoPerdedor = null;
			((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.ic_no_ok);
		}
		if(padre.asientoPerdedor == null) {
			contadorCancelar++;
			if(contadorCancelar > 1) {
	    		rootView.findViewById(R.id.imageview_tapar_centro).setVisibility(View.INVISIBLE);
	    		rootView.findViewById(R.id.textview_numero_ronda).setVisibility(View.VISIBLE);
	    		rootView.findViewById(R.id.chronometer_mesa).setVisibility(View.VISIBLE);
	    		rootView.findViewById(R.id.imageview_viento_ronda).setVisibility(View.VISIBLE);
	    		destaparTodosAsientos();
	    		padre.jugadores.destaparTodos();
	    		padre.asientoPulsado = null;
	    		asignarEventosPrincipales();
	    		actualizarAsientos();
			}
			else {
	    		Toast.makeText(padre, getResources().getString(R.string.toast_volver_a_pulsar_para_cancelar), Toast.LENGTH_SHORT).show();
	    		((ImageView)rootView.findViewById(R.id.imageview_tapar_centro)).setImageResource(R.drawable.img_center_no_ok_big);
			}
		}
	}
	
	//USO DE TAPAS
	public void tapar1Asiento(Asientos asiento, int color) {
		switch(asiento) {
			case ESTE:
				rootView.findViewById(R.id.relativelayout_tapa_este).setBackgroundColor(getResources().getColor(color));
				break;
			case SUR:
				rootView.findViewById(R.id.relativelayout_tapa_sur).setBackgroundColor(getResources().getColor(color));
				break;
			case OESTE:
				rootView.findViewById(R.id.relativelayout_tapa_oeste).setBackgroundColor(getResources().getColor(color));
				break;
			case NORTE:
				rootView.findViewById(R.id.relativelayout_tapa_norte).setBackgroundColor(getResources().getColor(color));
				break;
		}
	}
	public void taparChomberos() {
		if(padre.jugadores.getByAsiento(Asientos.ESTE).estaChonbo())
			tapar1Asiento(Asientos.ESTE, R.color.moradotransparente);
		if(padre.jugadores.getByAsiento(Asientos.SUR).estaChonbo())
			tapar1Asiento(Asientos.SUR, R.color.moradotransparente);
		if(padre.jugadores.getByAsiento(Asientos.OESTE).estaChonbo())
			tapar1Asiento(Asientos.OESTE, R.color.moradotransparente);
		if(padre.jugadores.getByAsiento(Asientos.NORTE).estaChonbo())
			tapar1Asiento(Asientos.NORTE, R.color.moradotransparente);
	}
	public void taparTodosAsientos(int color) {
		rootView.findViewById(R.id.relativelayout_tapa_este).setBackgroundColor(color);
		rootView.findViewById(R.id.relativelayout_tapa_sur).setBackgroundColor(color);
		rootView.findViewById(R.id.relativelayout_tapa_oeste).setBackgroundColor(color);
		rootView.findViewById(R.id.relativelayout_tapa_norte).setBackgroundColor(color);
	}
	public void destapar1Asiento(Asientos asiento) {
		switch(asiento) {
			case ESTE:
				rootView.findViewById(R.id.relativelayout_tapa_este).setBackgroundColor(Color.TRANSPARENT);
				break;
			case SUR:
				rootView.findViewById(R.id.relativelayout_tapa_sur).setBackgroundColor(Color.TRANSPARENT);
				break;
			case OESTE:
				rootView.findViewById(R.id.relativelayout_tapa_oeste).setBackgroundColor(Color.TRANSPARENT);
				break;
			case NORTE:
				rootView.findViewById(R.id.relativelayout_tapa_norte).setBackgroundColor(Color.TRANSPARENT);
				break;
		}
	}
	public void destaparTodosAsientos() {
		rootView.findViewById(R.id.relativelayout_tapa_este).setBackgroundColor(Color.TRANSPARENT);
		rootView.findViewById(R.id.relativelayout_tapa_sur).setBackgroundColor(Color.TRANSPARENT);
		rootView.findViewById(R.id.relativelayout_tapa_oeste).setBackgroundColor(Color.TRANSPARENT);
		rootView.findViewById(R.id.relativelayout_tapa_norte).setBackgroundColor(Color.TRANSPARENT);
	}
	public void destaparTodosAsientosExcepto1(Asientos asiento) {
		if (Asientos.ESTE != asiento) {
			rootView.findViewById(R.id.relativelayout_tapa_este).setBackgroundColor(Color.TRANSPARENT);
			padre.jugadores.getByAsiento(Asientos.ESTE).setEstaTapado(false);
		}
		if (Asientos.SUR != asiento) {
			rootView.findViewById(R.id.relativelayout_tapa_sur).setBackgroundColor(Color.TRANSPARENT);
			padre.jugadores.getByAsiento(Asientos.SUR).setEstaTapado(false);
		}
		if (Asientos.OESTE != asiento) {
			rootView.findViewById(R.id.relativelayout_tapa_oeste).setBackgroundColor(Color.TRANSPARENT);
			padre.jugadores.getByAsiento(Asientos.OESTE).setEstaTapado(false);
		}
		if (Asientos.NORTE != asiento) {
			rootView.findViewById(R.id.relativelayout_tapa_norte).setBackgroundColor(Color.TRANSPARENT);
			padre.jugadores.getByAsiento(Asientos.NORTE).setEstaTapado(false);
		}
	}
	public void destaparCentroFinal() {
		ImageView imageviewTaparCentro = (ImageView)rootView.findViewById(R.id.imageview_tapar_centro);
		imageviewTaparCentro.setVisibility(View.INVISIBLE);
		rootView.findViewById(R.id.relativelayout_centro).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	padre.mostrarAlertDialogRankingFinal();
            }
        });
        if (padre.partida.isTimerOn()) {
            long millisUntilFinished = padre.partida.getMilisTranscurridosORestantes();
            Chronometer crono = (Chronometer)rootView.findViewById(R.id.chronometer_mesa);
            if(millisUntilFinished >= 1000*60*60) {
                crono.setText(String.format("%02d:%02d:%02d",
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
                );
            }
            else {
                crono.setText(String.format("%02d:%02d",
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
                );
            }
        }
        else {
            Chronometer crono = (Chronometer) rootView.findViewById(R.id.chronometer_mesa);
            crono.setOnChronometerTickListener(new OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer arg0) {
                    Chronometer crono = (Chronometer) rootView.findViewById(R.id.chronometer_mesa);
                    padre.partida.setMilisTranscurridosORestantes(crono.getBase() - SystemClock.elapsedRealtime());
                    if (crono.getText().toString().split(":").length > 2) {
                        crono.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    }
                }
            });
            crono.setBase(SystemClock.elapsedRealtime() + padre.partida.getMilisTranscurridosORestantes());
        }
	}

	//USO DEL TIEMPO
	public void startTiempo() {
        if(padre.isReanudacion)
            padre.iniciarRondaSiguienteMcr();

        if(padre.partida.getDuracionTimer() == padre.partida.getMilisTranscurridosORestantes()) {
            MediaPlayer mp = MediaPlayer.create(padre, R.raw.gong);
            mp.start();
        }
		if (padre.partida.isTimerOn()) {
			timer = new CountDownTimer(padre.partida.getMilisTranscurridosORestantes(), 500) {					
				@Override
				public void onTick(long millisUntilFinished) {
					padre.partida.setMilisTranscurridosORestantes(millisUntilFinished);
	                Chronometer crono = (Chronometer)rootView.findViewById(R.id.chronometer_mesa);
	                if(millisUntilFinished >= 1000*60*60) {
		         		crono.setText(String.format("%02d:%02d:%02d", 
								TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
								TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
								TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
		         		);
					}
		         	else {
		         		crono.setText(String.format("%02d:%02d", 
								TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
								TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))
                        );
		         	}
				}
				@Override
				public void onFinish() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
	                padre.terminarPartidaMcr();
	     		}
			};

            if(padre.partida.isSonidoUltimos15On())
                padre.crearNotificacionUltimos15();
            if(padre.partida.isSonidoFinalOn())
                padre.crearNotificacionFinal();

            timer.start();
		}
		else {
			Chronometer crono = (Chronometer)rootView.findViewById(R.id.chronometer_mesa);
			crono.setOnChronometerTickListener(new OnChronometerTickListener() {
				@Override
				public void onChronometerTick(Chronometer arg0) {
					Chronometer crono = (Chronometer)rootView.findViewById(R.id.chronometer_mesa);
					padre.partida.setMilisTranscurridosORestantes(crono.getBase() - SystemClock.elapsedRealtime());
					if(arg0.getText().toString().split(":").length > 2) {
						arg0.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
					}
				}
			});
			crono.setBase(SystemClock.elapsedRealtime() + padre.partida.getMilisTranscurridosORestantes());
			crono.start();
		}
		padre.isTiempoParado = false;
		asignarEventosPrincipales();
		actualizarAsientos();
		actualizarCentro();
	}
	public void stopTiempo() {
		if (padre.partida.isTimerOn()) {
            if (padre.isPausaIntencionada)
                padre.cancelarNotificaciones();
            padre.isPausaIntencionada = false;
			timer.cancel();
        }
		else {
			Chronometer crono = (Chronometer)rootView.findViewById(R.id.chronometer_mesa);
			crono.stop();
		}
		RelativeLayout centro = (RelativeLayout)rootView.findViewById(R.id.relativelayout_centro);
		centro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                if(padre.isFinalOn)
                    padre.mostrarAlertDialogRankingFinal();
                else
                    startTiempo();
			}
		});
		actualizarCentro();
		actualizarAsientos();
	}
}