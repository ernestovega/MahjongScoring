package com.mahjongscoring.activities;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mahjongscoring.Receivers.BroadcastReceiverNotificaciones;
import com.mahjongscoring.Fragments.FragmentMesaAsientos;
import com.mahjongscoring.Fragments.FragmentMesaJugadas;
import com.mahjongscoring.Fragments.FragmentMesaListado;
import com.mahjongscoring.AppClasses.CJugador;
import com.mahjongscoring.AppClasses.CJugadores;
import com.mahjongscoring.DBClasses.DBManejador;
import com.mahjongscoring.DBClasses.DBPartida;
import com.mahjongscoring.DBClasses.DBRonda;
import com.mahjongscoring.AppClasses.Enums.Asientos;
import com.mahjongscoring.AppClasses.Enums.TiposJuego;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityMesa extends FragmentActivity {

    //CONSTANTES
    public static final int NUM_FRAGMENTS = 3;
    public static final int PUNTOS_MAXIMOS_MCR = 1059;
    public static final String ID_PARTIDA = "id_partida";
    public static final String IS_RON = "is_ron";
    public static final String IS_CHOMBO_MENOSDE8PUNTOS = "is_chombo_menosde8puntos";
    public static final String IS_SELECCION_DE_PERDEDOR_ON = "is_seleccion_de_perdedor_on";
    public static final String PUNTOS_GANADOR= "puntos_ganador";
    public static final String ASIENTO_PULSADO = "asiento_pulsado";
    public static final String ASIENTO_PERDEDOR = "asiento_perdedor";
    public static final String IS_TIEMPO_PARADO = "is_tiempo_parado";
    public static final String IS_FINAL_ON = "is_final_on";
    public static final String MILIS_CUANDO_ONPAUSE = "milis_cuando_onpause";

    //VARIABLES
    public ViewPager mPager;
    public PagerAdapter mPagerAdapter;
    public FragmentMesaJugadas fragmentJugadas;
    public FragmentMesaAsientos fragmentAsientos;
    public FragmentMesaListado fragmentListado;
    public DBManejador db;
    public Typeface typefaceKorean;
    public DBPartida partida;
    public List<DBRonda> rondas;
    public CJugadores jugadores;
    public OnCancelListener cancelDialogListener;
    public Asientos asientoPulsado;
    public Asientos asientoPerdedor;
    public int puntosGanador;
    public boolean isRon;
    public boolean isPenaltyMenosDe8Puntos;
    public boolean isSeleccionDePerdedorOn;
    public boolean isTiempoParado;
    public long milisCuandoOnPause;
    public boolean isFinalOn;
    public boolean isReanudacion;
    public AlarmManager alarmManager;
    public PendingIntent pendingIntent;
    public boolean isPausaIntencionada;

    //CICLO DE VIDA
	@Override    
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide_mesa);
        WindowManager.LayoutParams attrs = this.getWindow().getAttributes();
        attrs.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        this.getWindow().setAttributes(attrs);
        
	    mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager = (ViewPager) findViewById(R.id.pager_mesa);
	    mPager.setAdapter(mPagerAdapter);

	    inicializarObjetos();
        restaurarPartida(savedInstanceState);
    }
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            if (isFinalOn)
                mostrarAlertDialogRankingFinal();
            else if(isSeleccionDePerdedorOn)
                fragmentAsientos.clickCancelarCentroSeleccionDePerdedor();
            else
                mostrarAlertDialogTerminar();
        }
        else mPager.setCurrentItem(mPager.getCurrentItem() - 1);
    }
    @Override	
	protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    outState.putInt(ID_PARTIDA, partida.getId());
	    outState.putBoolean(IS_TIEMPO_PARADO, isTiempoParado);
	    outState.putBoolean(IS_RON, isRon);
	    outState.putBoolean(IS_CHOMBO_MENOSDE8PUNTOS, isPenaltyMenosDe8Puntos);
	    outState.putBoolean(IS_SELECCION_DE_PERDEDOR_ON, isSeleccionDePerdedorOn);
	    outState.putBoolean(IS_FINAL_ON, isFinalOn);
	    outState.putInt(PUNTOS_GANADOR, puntosGanador);
	    outState.putLong(MILIS_CUANDO_ONPAUSE, milisCuandoOnPause);
	    if(asientoPulsado != null) {
		    switch(asientoPulsado) {
				case ESTE:
				    outState.putInt(ASIENTO_PULSADO, 1);
					break;
				case SUR:
				    outState.putInt(ASIENTO_PULSADO, 2);
					break;
				case OESTE:
				    outState.putInt(ASIENTO_PULSADO, 3);
					break;
				case NORTE:
				    outState.putInt(ASIENTO_PULSADO, 4);
					break;
		    }
	    }
	    if(asientoPerdedor != null) {
			switch(asientoPerdedor) {
				case ESTE:
				    outState.putInt(ASIENTO_PERDEDOR, 1);
					break;
				case SUR:
				    outState.putInt(ASIENTO_PERDEDOR, 2);
					break;
				case OESTE:
				    outState.putInt(ASIENTO_PERDEDOR, 3);
					break;
				case NORTE:
				    outState.putInt(ASIENTO_PERDEDOR, 4);
					break;
		    }
	    }
	    /*if(partida.getTipoJuego() == TiposJuego.RICHII) {
		    *//*outState.putInt(CONTADORES_RICHII, partida.getContadoresRichii());
		    outState.putInt(BOTE_RICHII, partida.getBoteRichii());
		    outState.putInt(REPETICIONES_JEFE_RICHII, partida.getVecesJefeRichii());*//*
		    outState.putInt(MINIPUNTOS_GANADOR, minipuntosGanador);
	    }*/
	    db.close();
	}
    public void inicializarObjetos() {	
	    db = new DBManejador(this, DBManejador.DB_NOMBRE, DBManejador.DB_VERSION);
	    typefaceKorean = Typeface.createFromAsset(getAssets(), "fonts/dekiru.ttf");
	    cancelDialogListener = new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface arg0) {
				fragmentAsientos.destaparTodosAsientos();
				fragmentAsientos.taparChomberos();
				asientoPulsado = null;
				fragmentAsientos.asignarEventosPrincipales();
			}
		};
	    asientoPulsado = null;
	    asientoPerdedor = null;
	    puntosGanador = 0;
	    isRon = false;
	    isPenaltyMenosDe8Puntos = false;
	    isSeleccionDePerdedorOn = false;
        isReanudacion = false;
        isPausaIntencionada = false;
	    PagerTitleStrip pagerTitleStrip = (PagerTitleStrip)findViewById(R.id.pager_title_strip);
		for (int counter = 0 ; counter < pagerTitleStrip.getChildCount(); counter++) {
	        if (pagerTitleStrip.getChildAt(counter) instanceof TextView) {
	            ((TextView)pagerTitleStrip.getChildAt(counter)).setTypeface(typefaceKorean);
	            ((TextView)pagerTitleStrip.getChildAt(counter)).setTextSize(24);
	        }
	    }
    	milisCuandoOnPause = 0;
	}
	public void restaurarPartida(Bundle inState) {
		if(inState == null) {//PARTIDA QUE EMPIEZA
        	Bundle extras = getIntent().getExtras();
            isTiempoParado = true;
            if(extras.getBoolean(ActivityConfigurarPartida.EXTRA_ES_REANUDACION)) {
                partida = db.obtenerPartida(extras.getInt(ActivityConfigurarPartida.EXTRA_ID_NUEVA_PARTIDA));
                rondas = db.obtenerRondasPartida(partida.getId());
                isFinalOn = true;
                isReanudacion = true;
            }
            else {
            	partida = db.obtenerPartida(extras.getInt(ActivityConfigurarPartida.EXTRA_ID_NUEVA_PARTIDA));
        		rondas = db.obtenerRondasPartida(partida.getId());
            }
        }
        else
        {
    		partida = db.obtenerPartida(inState.getInt(ID_PARTIDA));
    		rondas = db.obtenerRondasPartida(partida.getId());
    		isTiempoParado = inState.getBoolean(IS_TIEMPO_PARADO);
    		isRon = inState.getBoolean(IS_RON);
    		isPenaltyMenosDe8Puntos = inState.getBoolean(IS_CHOMBO_MENOSDE8PUNTOS);
    		isSeleccionDePerdedorOn = inState.getBoolean(IS_SELECCION_DE_PERDEDOR_ON);
    		isFinalOn = inState.getBoolean(IS_FINAL_ON);
    		puntosGanador = inState.getInt(PUNTOS_GANADOR);
    		/*contadoresRichii = inState.getInt(CONTADORES_RICHII);
		    boteRichii = inState.getInt(BOTE_RICHII);
		    vecesJefeRichii = inState.getInt(REPETICIONES_JEFE_RICHII);
    		puntosGanador = inState.getInt(MINIPUNTOS_GANADOR);*/
    		milisCuandoOnPause = inState.getLong(MILIS_CUANDO_ONPAUSE);
    		if(inState.containsKey(ASIENTO_PULSADO))
    		{
	    	    switch(inState.getInt(ASIENTO_PULSADO)) {
	    			case 1:
	    			    asientoPulsado = Asientos.ESTE;
	    				break;
	    			case 2:
	    			    asientoPulsado = Asientos.SUR;
	    				break;
	    			case 3:
	    			    asientoPulsado = Asientos.OESTE;
	    				break;
	    			case 4:
	    			    asientoPulsado = Asientos.NORTE;
	    				break;
	    	    }
    		}
    		if(inState.containsKey(ASIENTO_PERDEDOR))
    		{
		    	switch(inState.getInt(ASIENTO_PERDEDOR)) {
	    			case 1:
	    			    asientoPerdedor = Asientos.ESTE;
	    				break;
	    			case 2:
	    				asientoPerdedor = Asientos.SUR;
	    				break;
	    			case 3:
	    				asientoPerdedor = Asientos.OESTE;
	    				break;
	    			case 4:
	    				asientoPerdedor = Asientos.NORTE;
	    				break;
	    	    }
    		}
        }
		if(rondas == null) {
			rondas = new ArrayList<DBRonda>(16);
			DBRonda ronda = new DBRonda(partida.getId(), 1);
			rondas.add(ronda);
			db.anadirRonda(ronda);
		}
		jugadores = new CJugadores(partida.getArrayNombres(), partida.getArrayPuntos(), rondas.get(rondas.size() - 1).getEstanChombo());
        if(isReanudacion) mostrarAlertDialogRankingFinal();

        jugadores.cambiarAsientosMcr(rondas.size());
	}

    //CLASES HIJAS
	public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
        	switch(position){
				case 0:
					fragmentAsientos = FragmentMesaAsientos.crearFragment();
					return fragmentAsientos;
				case 1:
					fragmentListado = FragmentMesaListado.crearFragment();
					return fragmentListado;
				case 2:
					fragmentJugadas = FragmentMesaJugadas.crearFragment();
					return fragmentJugadas;
				default:
					return null;
        	}
        }
        @Override
        public int getCount() {
            return NUM_FRAGMENTS;
        }
        @Override
        public CharSequence getPageTitle (int position) {
        	switch(position) {
        		case 0:
        			return getString(R.string.mesa);
        		case 1:
        			return getString(R.string.listado);
    			case 2:
    				return getString(R.string.jugadas);
    			default:
    				return null;
        	}
        }
    }

    //DIALOGOS
    public void mostrarAlertDialogAsientoPulsadoMcr() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item) {
			@Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView1 = (TextView)view.findViewById(android.R.id.text1);
                textView1.setTypeface(typefaceKorean);
                return view;
            }
        };
        arrayAdapter.add(getString(R.string.penalty));
        arrayAdapter.add(getString(R.string.washout));
        arrayAdapter.add(getString(R.string.mahjong_discard));
        arrayAdapter.add(getString(R.string.mahjong_selfdrawn));
        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	switch(which) {
            		case 0:
            			mostrarAlertDialogPenalty();
            			break;
            		case 1:
            			contarWashout();
            			break;
            		case 2:
            			isRon = true;
            			mostrarAlertDialogPedirPuntos();
            			break;
            		case 3:
            			isRon = false;
            			mostrarAlertDialogPedirPuntos();
            			break;
            	}
            }
        });
        builder.setOnCancelListener(cancelDialogListener);
		builder.create();
		builder.show();
    }
    public void mostrarAlertDialogCentroPulsado() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item) {
			@Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView1 = (TextView)view.findViewById(android.R.id.text1);
                textView1.setTypeface(typefaceKorean);
                return view;
            }
        };
	    arrayAdapter.add(getString(R.string.terminar));
        arrayAdapter.add(getString(R.string.pausar));
        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	switch(which) {
            		case 0:
            			mostrarAlertDialogTerminar();
            			break;
            		case 1:
                        if (alarmManager != null)
                            alarmManager.cancel(pendingIntent);
            			isTiempoParado = true;
                        isPausaIntencionada = true;
            			fragmentAsientos.stopTiempo();
            			break;
            	}
            }
        });
        builder.setOnCancelListener(cancelDialogListener);
		builder.create();
		builder.show();
    }
    public void mostrarAlertDialogPenalty() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item) {
			@Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView1 = (TextView)view.findViewById(android.R.id.text1);
                textView1.setTypeface(typefaceKorean);
                return view;
            }
        };
	    arrayAdapter.add(getString(R.string.penalty_combinacionerronea));
        arrayAdapter.add(getString(R.string.penalty_menosde8puntos));
        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	switch(which) {
            		case 0:
            			isPenaltyMenosDe8Puntos = false;
            			anotarPenalty();
            			break;
            		case 1:
            			isPenaltyMenosDe8Puntos = true;
            			anotarPenalty();
            			break;
            	}
            }
        });
        builder.setOnCancelListener(cancelDialogListener);
        builder.create();
        builder.show();
    }
    public void mostrarAlertDialogPedirPuntos() {
    	final Toast toastPuntosMal = Toast.makeText(this, getString(R.string.toast_puntos_incorrectos_0) + " "
    			+ partida.getPuntosMinimosMcr() + " " + getString(R.string.toast_puntos_incorrectos_1) + " "
    			+ PUNTOS_MAXIMOS_MCR, Toast.LENGTH_SHORT);
    	AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog);
	    LayoutInflater inflater = this.getLayoutInflater();
	    final View layoutDialog = inflater.inflate(R.layout.dialog_pedir_puntos, null);
	    String nombrePulsado = getResources().getString(R.string.dialog_title_default);
	    nombrePulsado = jugadores.getByAsiento(asientoPulsado).getNombre();
	    TextView customTitle =  new TextView(this);
        if (partida.getTipoJuego() == TiposJuego.MCR)
            customTitle.setText((isRon ? getString(R.string.mahjong_discard) : getString(R.string.mahjong_selfdrawn)) + "  " + nombrePulsado);
        else
            customTitle.setText((isRon ? getString(R.string.ron) : getString(R.string.tsumo)) + "  " + nombrePulsado);

	    customTitle.setGravity(Gravity.CENTER);
	    customTitle.setTypeface(typefaceKorean);
	    customTitle.setPadding(0, 20, 0, 20);
	    customTitle.setTextColor(getResources().getColor(R.color.azul));
	    customTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
	    builder.setView(layoutDialog)
	    	   .setCustomTitle(customTitle)
   			   .setOnCancelListener(cancelDialogListener)
   			   .setPositiveButton(android.R.string.ok, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText editxtPuntos = (EditText)layoutDialog.findViewById(R.id.edittext_dialog_pedirpuntos);
				if(editxtPuntos.getText() != null) {
					String strPuntos = editxtPuntos.getText().toString().trim();
					if (!strPuntos.equals("")
						&& Integer.valueOf(strPuntos) >= partida.getPuntosMinimosMcr()
						&& Integer.valueOf(strPuntos) <= PUNTOS_MAXIMOS_MCR) {
						puntosGanador = Integer.valueOf(strPuntos);
						if(partida.getTipoJuego() == TiposJuego.MCR)
							if (isRon)
								fragmentAsientos.asignarEventosSeleccionar1PerdedorMahjongDiscard();
							else {
								jugadores.contarMahjongSelfdrawn(asientoPulsado, puntosGanador);
								nuevaRondaMcr();
							}
						//else contarTsumo(strPuntos);
					}
					else {
						toastPuntosMal.show();
						mostrarAlertDialogPedirPuntos();
					}
				}
			}
		});
	    ((EditText)layoutDialog.findViewById(R.id.edittext_dialog_pedirpuntos)).setTypeface(typefaceKorean);
        AlertDialog dialog = builder.create();
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		dialog.show();
		Button okButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
		okButton.setTypeface(typefaceKorean);
        okButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
	}
    public void mostrarAlertDialogTerminar() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog);
	    builder.setMessage(R.string.message_confirmacion_terminar)
   			   .setOnCancelListener(cancelDialogListener)
   			   .setNegativeButton(android.R.string.cancel, null)
   			   .setPositiveButton(android.R.string.ok, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
                        if (partida.isTimerOn())
                            cancelarNotificaciones();
                        terminarPartidaMcr();
					}
   			   });
        AlertDialog dialog = builder.create();
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.show();
		TextView message = (TextView) dialog.findViewById(android.R.id.message);
		message.setTypeface(typefaceKorean);
		message.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
		message.setPadding(80, 45, 80, 0);
		Button okButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
		okButton.setTypeface(typefaceKorean);
        okButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		Button cancelButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
		cancelButton.setTypeface(typefaceKorean);
		cancelButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
	}
    public void mostrarAlertDialogRankingFinal() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_ranking, null);
        List<CJugador> js = jugadores.getListaOrdenadaPorPuntosDesc();
        TextView nombreTitulo = (TextView)view.findViewById(R.id.textview_ranking_nombre_titulo);
        TextView nombre1 = (TextView)view.findViewById(R.id.textview_ranking_nombre1);
        TextView nombre2 = (TextView)view.findViewById(R.id.textview_ranking_nombre2);
        TextView nombre3 = (TextView)view.findViewById(R.id.textview_ranking_nombre3);
        TextView nombre4 = (TextView)view.findViewById(R.id.textview_ranking_nombre4);
        TextView puntosPartidaTitulo = (TextView)view.findViewById(R.id.textview_ranking_puntos_partida_titulo);
        TextView puntosPartida1 = (TextView)view.findViewById(R.id.textview_ranking_puntos_partida1);
        TextView puntosPartida2 = (TextView)view.findViewById(R.id.textview_ranking_puntos_partida2);
        TextView puntosPartida3 = (TextView)view.findViewById(R.id.textview_ranking_puntos_partida3);
        TextView puntosPartida4 = (TextView)view.findViewById(R.id.textview_ranking_puntos_partida4);
        TextView puntosCampeonatoTitulo = (TextView)view.findViewById(R.id.textview_ranking_puntos_campeonato_titulo);
        TextView puntosCampeonato1 = (TextView)view.findViewById(R.id.textview_ranking_puntos_campeonato1);
        TextView puntosCampeonato2 = (TextView)view.findViewById(R.id.textview_ranking_puntos_campeonato2);
        TextView puntosCampeonato3 = (TextView)view.findViewById(R.id.textview_ranking_puntos_campeonato3);
        TextView puntosCampeonato4 = (TextView)view.findViewById(R.id.textview_ranking_puntos_campeonato4);
        TextView tituloMejorjugada = (TextView)view.findViewById(R.id.textview_ranking_titulo_mejorjugada);
        TextView mejorJugada = (TextView)view.findViewById(R.id.textview_ranking_mejorjugada);
        TextView tituloNumeroRondas = (TextView)view.findViewById(R.id.textview_ranking_titulo_numero_rondas);
        TextView numeroRondas = (TextView)view.findViewById(R.id.textview_ranking_numero_rondas);
        TextView tituloFechaInicio = (TextView)view.findViewById(R.id.textview_ranking_titulo_fecha_inicio);
        TextView fechaInicio = (TextView)view.findViewById(R.id.textview_ranking_fecha_inicio);
        TextView tituloFechaFin = (TextView)view.findViewById(R.id.textview_ranking_titulo_fecha_fin);
        TextView fechaFin = (TextView)view.findViewById(R.id.textview_ranking_fecha_fin);
        nombreTitulo.setTypeface(typefaceKorean);
        nombre1.setTypeface(typefaceKorean);
        nombre2.setTypeface(typefaceKorean);
        nombre3.setTypeface(typefaceKorean);
        nombre4.setTypeface(typefaceKorean);
        puntosPartidaTitulo.setTypeface(typefaceKorean);
        puntosPartida1.setTypeface(typefaceKorean);
        puntosPartida2.setTypeface(typefaceKorean);
        puntosPartida3.setTypeface(typefaceKorean);
        puntosPartida4.setTypeface(typefaceKorean);
        puntosCampeonatoTitulo.setTypeface(typefaceKorean);
        puntosCampeonato1.setTypeface(typefaceKorean);
        puntosCampeonato2.setTypeface(typefaceKorean);
        puntosCampeonato3.setTypeface(typefaceKorean);
        puntosCampeonato4.setTypeface(typefaceKorean);
        tituloMejorjugada.setTypeface(typefaceKorean);
        mejorJugada.setTypeface(typefaceKorean);
        tituloNumeroRondas.setTypeface(typefaceKorean);
        numeroRondas.setTypeface(typefaceKorean);
        tituloFechaInicio.setTypeface(typefaceKorean);
        fechaInicio.setTypeface(typefaceKorean);
        tituloFechaFin.setTypeface(typefaceKorean);
        fechaFin.setTypeface(typefaceKorean);
        nombre1.setText(js.get(0).getNombre());
        nombre2.setText(js.get(1).getNombre());
        nombre3.setText(js.get(2).getNombre());
        nombre4.setText(js.get(3).getNombre());
        puntosPartida1.setText(js.get(0).getPuntos() > 0 ? "+" + String.valueOf(js.get(0).getPuntos()) : String.valueOf(js.get(0).getPuntos()));
        puntosPartida2.setText(js.get(1).getPuntos() > 0 ? "+" + String.valueOf(js.get(1).getPuntos()) : String.valueOf(js.get(1).getPuntos()));
        puntosPartida3.setText(js.get(2).getPuntos() > 0 ? "+" + String.valueOf(js.get(2).getPuntos()) : String.valueOf(js.get(2).getPuntos()));
        puntosPartida4.setText(js.get(3).getPuntos() > 0 ? "+" + String.valueOf(js.get(3).getPuntos()) : String.valueOf(js.get(3).getPuntos()));
        String[] puntosCampeonato = jugadores.getListaOrdenadaPuntosCampeonato();
        puntosCampeonato1.setText(puntosCampeonato[0]);
        puntosCampeonato2.setText(puntosCampeonato[1]);
        puntosCampeonato3.setText(puntosCampeonato[2]);
        puntosCampeonato4.setText(puntosCampeonato[3]);
        int puntosMejorJugada = partida.getPuntosMejorJugada();
        mejorJugada.setText(puntosMejorJugada == 0 ? "-" : String.valueOf(puntosMejorJugada) + " (" + partida.getNombreJugadorMejorJugada() + ")");
        numeroRondas.setText(String.valueOf(partida.getNumeroRondas()));
        fechaInicio.setText(new SimpleDateFormat("dd/MM/yy   HH:mm").format(partida.getFechaInicio()));
        fechaFin.setText(new SimpleDateFormat("dd/MM/yy   HH:mm").format(partida.getFechaFin()));
        builder.setView(view)
        	   .setNegativeButton(R.string.salir_partida, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                       salir();
                   }
               });
            if (partida.getNumeroRondas() < 16) {
                builder.setPositiveButton(R.string.continuar_partida, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        isFinalOn = false;
                        if (partida.isTimerOn() && partida.getMilisTranscurridosORestantes() < 1000) {
                            partida.setIsTimerOn(false);
                            partida.setMilisTranscurridosORestantes(0);
                        }
                        db.actualizarPartida(partida);
                        jugadores.cambiarAsientosMcr(partida.getNumeroRondas() + 1);
                        fragmentAsientos.asignarEventosPrincipales();
                        fragmentAsientos.actualizarCentro();
                    }
                });
            }
        //}
        AlertDialog dialog = builder.create();
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.show();
		Button salirButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button continuarButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        salirButton.setTypeface(typefaceKorean);
        continuarButton.setTypeface(typefaceKorean);
        salirButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
        continuarButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
	}

	//OPERACIONES MCR
    public void anotarPenalty() {
        jugadores.getByAsiento(asientoPulsado).setEstaChonbo(true);
        if(isPenaltyMenosDe8Puntos)
            rondas.get(rondas.size() - 1).setChombo(jugadores.getPosicionByAsiento(asientoPulsado), 30);
        else
            rondas.get(rondas.size() - 1).setChombo(jugadores.getPosicionByAsiento(asientoPulsado), 60);
        db.actualizarRonda(rondas.get(rondas.size() - 1));
        if(jugadores.sonTodosPenalty())
            nuevaRondaMcr();
        else {
            asientoPulsado = null;
            //asientoPerdedor = null;
            fragmentAsientos.destaparTodosAsientos();
            fragmentAsientos.actualizarCentro();
            fragmentAsientos.asignarEventosPrincipales();
            fragmentAsientos.actualizarAsientos();
        }
    }
    public void desanotarPenalty() {
        jugadores.getByAsiento(asientoPulsado).setEstaChonbo(false);
        rondas.get(rondas.size() - 1).setChombo(jugadores.getPosicionByAsiento(asientoPulsado), 0);
        db.actualizarRonda(rondas.get(rondas.size() - 1));
        fragmentAsientos.destapar1Asiento(asientoPulsado);
        //fragmentAsientos.actualizarAsientos();
        asientoPulsado = null;
    }
	public void contarWashout() {
		nuevaRondaMcr();
	}

    //OPERACIONES UI Y BBDD
	public void nuevaRondaMcr() {
		DBRonda ronda = rondas.get(rondas.size() - 1);
		//CONTAR CHOMBOS
		for (int i = 0; i < 4; i++) {
			if(ronda.getEstanChombo()[i]) {
				jugadores.contarPenalty(jugadores.getByPosicion(i).getAsiento(), ronda.getChombos()[i]);
			}
		}
		//GUARDAR PUNTOS
		ronda.setPuntosRonda(
				jugadores.j1.getPuntos() - partida.getPuntosJ1(),
				jugadores.j2.getPuntos() - partida.getPuntosJ2(),
				jugadores.j3.getPuntos() - partida.getPuntosJ3(),
				jugadores.j4.getPuntos() - partida.getPuntosJ4());
		ronda.setPuntosTotales(
				jugadores.j1.getPuntos(),
				jugadores.j2.getPuntos(),
				jugadores.j3.getPuntos(),
				jugadores.j4.getPuntos());
		partida.setPuntos(
				jugadores.j1.getPuntos(),
				jugadores.j2.getPuntos(),
				jugadores.j3.getPuntos(),
				jugadores.j4.getPuntos());
		ronda.setPuntosJugada(puntosGanador);
		if(asientoPulsado != null && puntosGanador > 0)
			ronda.setNombreGanador(jugadores.getByAsiento(asientoPulsado).getNombre());
		if(asientoPerdedor != null)
			ronda.setNombrePerdedor(jugadores.getByAsiento(asientoPerdedor).getNombre());
		db.actualizarRonda(ronda);
		partida.setNumeroRondas(partida.getNumeroRondas() + 1);
		if(puntosGanador > partida.getPuntosMejorJugada()) {
			partida.setPuntosMejorJugada(puntosGanador);
			partida.setNombreJugadorMejorJugada(jugadores.getByAsiento(asientoPulsado).getNombre());
		}
		db.actualizarPartida(partida);
		iniciarRondaSiguienteMcr();
	}
    public void iniciarRondaSiguienteMcr() {
        asientoPulsado = null;
        asientoPerdedor = null;
        puntosGanador = 0;
        isRon = false;
        isSeleccionDePerdedorOn = false;
        isPenaltyMenosDe8Puntos = false;
        jugadores.despenalizarTodos();
        jugadores.destaparTodos();
        fragmentAsientos.destaparTodosAsientos();
        if(rondas.size() < 16) {
            if (partida.getTipoJuego() == TiposJuego.MCR)
                jugadores.cambiarAsientosMcr(partida.getNumeroRondas() + 1);
            fragmentAsientos.actualizarAsientos();
            fragmentAsientos.asignarEventosPrincipales();
            fragmentAsientos.actualizarCentro();
            DBRonda nuevaRonda = new DBRonda(partida.getId(), partida.getNumeroRondas() + 1);
            if(!isReanudacion || db.obtenerRonda(nuevaRonda.getIdPartida(), nuevaRonda.getNumeroRonda()) == null) {
                rondas.add(nuevaRonda);
                db.anadirRonda(rondas.get(rondas.size() - 1));
            }
            else {
                rondas.remove(rondas.size() - 1);
                rondas.add(nuevaRonda);
                db.actualizarRonda(nuevaRonda);
            }
            if(isReanudacion)
                isReanudacion = false;
            fragmentListado.actualizarLista();
        }
        else {
            fragmentListado.actualizarLista();
            db.actualizarRonda(rondas.get(rondas.size() - 1));
            terminarPartidaMcr();
        }
    }
    public void borrarUltimaRondaMcr() {
        DBRonda ultimaRonda = null;
        if (partida.getNumeroRondas() == 1) {
            db.eliminarRonda(rondas.get(1));
            rondas.remove(rondas.get(1));
            db.eliminarRonda(rondas.get(0));
            rondas.remove(rondas.get(0));
            partida.setNumeroRondas(0);
        }
        else if (partida.getNumeroRondas() < 16) {
            ultimaRonda = rondas.get(rondas.size() - 1);
            db.eliminarRonda(ultimaRonda);
            rondas.remove(ultimaRonda);
            partida.setNumeroRondas(partida.getNumeroRondas() - 1);
            ultimaRonda = rondas.get(rondas.size() - 1);
            db.eliminarRonda(ultimaRonda);
            rondas.remove(ultimaRonda);
        }
        else if (partida.getNumeroRondas() == 16){
            //isTiempoParado = false;
            fragmentAsientos.startTiempo();
            isFinalOn = false;
            db.eliminarRonda(rondas.get(15));
            rondas.remove(rondas.get(15));
            partida.setNumeroRondas(15);
            partida.setFechaFin(null);
        }

        if (rondas.size() > 0) {
            ultimaRonda = rondas.get(rondas.size() - 1);
            jugadores.j1.setPuntos(ultimaRonda.getPuntosTotalesJ1());
            jugadores.j2.setPuntos(ultimaRonda.getPuntosTotalesJ2());
            jugadores.j3.setPuntos(ultimaRonda.getPuntosTotalesJ3());
            jugadores.j4.setPuntos(ultimaRonda.getPuntosTotalesJ4());
        } else {
            jugadores.j1.setPuntos(0);
            jugadores.j2.setPuntos(0);
            jugadores.j3.setPuntos(0);
            jugadores.j4.setPuntos(0);
            fragmentListado.borrarLista();
        }
        partida.setPuntos(
                jugadores.j1.getPuntos(),
                jugadores.j2.getPuntos(),
                jugadores.j3.getPuntos(),
                jugadores.j4.getPuntos());
        db.actualizarPartida(partida);
        //if (partida.getTipoJuego() == TiposJuego.MCR)
        jugadores.cambiarAsientosMcr(partida.getNumeroRondas() + 1);
        iniciarRondaSiguienteMcr();
    }
    public void terminarPartidaMcr(){
        if(!isTiempoParado) {
			isTiempoParado = true;
			fragmentAsientos.stopTiempo();
		}
		isFinalOn = true;
		fragmentAsientos.quitarEventos();
		fragmentAsientos.destaparCentroFinal();
        if(partida.getNumeroRondas() < 16) {
            db.eliminarRonda(rondas.get(rondas.size() - 1));
            rondas.remove(rondas.size() - 1);
        }
        if(!isReanudacion)
		    partida.setFechaFin(new Date());
		db.actualizarPartida(partida);
		mostrarAlertDialogRankingFinal();
	}
	public void salir() {
		finish();
	}

    //NOTIFICACIONES
    public void crearNotificacionUltimos15() {
        long millisUntilNotification = partida.getMilisTranscurridosORestantes() - 1000*60*15 - 55000;
        if(partida.getMilisTranscurridosORestantes() > 1000*60*15 + 55000) {
            AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, BroadcastReceiverNotificaciones.class);
            intent.putExtra("uri", "android.resource://" + this.getPackageName() + "/" + R.raw.gong);
            intent.putExtra("title", "15 minutes remaining");
            intent.putExtra("content", "Hurry up!");
            //intent.putExtra("idPartida", partida.getId());
            PendingIntent pi = PendingIntent.getBroadcast(this, 15, intent, 0);
            am.cancel(pi);
            am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + millisUntilNotification, pi);
        }
    }
    public void crearNotificacionFinal() {
        long millisUntilNotification = partida.getMilisTranscurridosORestantes() - 1000*60*15 - 50000;
        if(partida.getMilisTranscurridosORestantes() > 1000*60*15 + 50000) {
            AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, BroadcastReceiverNotificaciones.class);
            intent.putExtra("uri", "android.resource://" + this.getPackageName() + "/" + R.raw.gong);
            intent.putExtra("title", "Time finished");
            intent.putExtra("content", "Thanks for playing");
            //intent.putExtra("idPartida", partida.getId());
            PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
            am.cancel(pi);
            am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + millisUntilNotification, pi);
        }
    }
    public void cancelarNotificaciones() {
        AlarmManager am=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, BroadcastReceiverNotificaciones.class);
        intent.putExtra("uri", "android.resource://" + this.getPackageName() + "/" + R.raw.gong);
        intent.putExtra("title", "15 minutes remaining");
        intent.putExtra("content", "Hurry up!");
        //intent.putExtra("idPartida", partida.getId());
        PendingIntent pi = PendingIntent.getBroadcast(this, 15, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.cancel(pi);
        Intent intent2 = new Intent(this, BroadcastReceiverNotificaciones.class);
        intent.putExtra("uri", "android.resource://" + this.getPackageName() + "/" + R.raw.gong);
        intent.putExtra("title", "Time finished");
        intent.putExtra("content", "Thanks for playing");
        //intent.putExtra("idPartida", partida.getId());
        PendingIntent pi2 = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.cancel(pi2);
    }
}



/*
puntosRiichiTsumoJefeMenosDe5 = new int[5][10];
puntosRiichiTsumoJefeMenosDe5[0] = new int[]{20, 25, 30, 40, 50, 60, 70, 80, 90, 100};
puntosRiichiTsumoJefeMenosDe5[1] = new int[]{0, 0, 500, 700, 800, 1000, 1200, 1300, 1500, 1600};
puntosRiichiTsumoJefeMenosDe5[2] = new int[]{700, 0, 1000, 1300, 1600, 2000, 2300, 2600, 2900, 3200};
puntosRiichiTsumoJefeMenosDe5[3] = new int[]{1300, 1600, 2000, 2600, 3200, 3900, 4000, 4000, 4000, 4000};
puntosRiichiTsumoJefeMenosDe5[4] = new int[]{2600, 3200, 3900, 4000, 4000, 4000, 4000, 4000, 4000, 4000};
puntosRiichiTsumoOtroJefeMenosDe5 = new int[5][10];
puntosRiichiTsumoOtroJefeMenosDe5[0] = new int[]{20, 25, 30, 40, 50, 60, 70, 80, 90, 100};
puntosRiichiTsumoOtroJefeMenosDe5[1] = new int[]{0,0,500,700,800,1000,1200,1300,1500,1600};
puntosRiichiTsumoOtroJefeMenosDe5[2] = new int[]{700,0,1000,1300,1600,2000,2300,2600,2900,3200};
puntosRiichiTsumoOtroJefeMenosDe5[3] = new int[]{1300,1600,2000,2600,3200,3900,4000,4000,4000,4000};
puntosRiichiTsumoOtroJefeMenosDe5[4] = new int[]{2600,3200,3900,4000,4000,4000,4000,4000,4000,4000};
puntosRiichiTsumoOtroOtroMenosDe5 = new int[5][10];
puntosRiichiTsumoOtroOtroMenosDe5[0] = new int[]{20, 25, 30, 40, 50, 60, 70, 80, 90, 100};
puntosRiichiTsumoOtroOtroMenosDe5[1] = new int[]{0,0,300,400,400,500,600,700,800,800};
puntosRiichiTsumoOtroOtroMenosDe5[2] = new int[]{400,0,500,700,800,1000,1200,1300,1500,1600};
puntosRiichiTsumoOtroOtroMenosDe5[3] = new int[]{700,800,1000,1300,1600,2000,2000,2000,2000,2000};
puntosRiichiTsumoOtroOtroMenosDe5[4] = new int[]{1300,1600,2000,2000,2000,2000,2000,2000,2000,2000};
puntosRiichiRonJefeMenosDe5 = new int[5][10];
puntosRiichiRonJefeMenosDe5[0] = new int[]{25, 30, 40, 50, 60, 70, 80, 90, 100};
puntosRiichiRonJefeMenosDe5[1] = new int[]{0,1500,2000,2400,2900,3400,3900,4400,4800};
puntosRiichiRonJefeMenosDe5[2] = new int[]{2400,2900,3900,4800,5800,6800,7700,8700,9600};
puntosRiichiRonJefeMenosDe5[3] = new int[]{4800,5800,7700,9600,11600,12000,12000,12000,12000};
puntosRiichiRonJefeMenosDe5[4] = new int[]{9600,11600,12000,12000,12000,12000,12000,12000,12000};
puntosRiichiRonOtroMenosDe5 = new int[5][10];
puntosRiichiRonOtroMenosDe5[0] = new int[]{25, 30, 40, 50, 60, 70, 80, 90, 100};
puntosRiichiRonOtroMenosDe5[1] = new int[]{0,1000,13000,16000,2000,2300,2600,2900,3200};
puntosRiichiRonOtroMenosDe5[2] = new int[]{1600,2000,2600,3200,3900,4500,5200,5800,6400};
puntosRiichiRonOtroMenosDe5[3] = new int[]{3200,3900,5200,6400,7700,8000,8000,8000,8000};
puntosRiichiRonOtroMenosDe5[4] = new int[]{6400,7700,8000,8000,8000,8000,8000,8000,8000};
puntosRiichiTsumoJefeMasDe5 = new int[2][9];
puntosRiichiTsumoJefeMasDe5[0] = new int[]{5,6,7,8,9,10,11,12,13};
puntosRiichiTsumoJefeMasDe5[1] = new int[]{4000,6000,6000,8000,8000,8000,12000,12000,16000};
puntosRiichiTsumoOtroJefeMasDe4 = new int[2][10];
puntosRiichiTsumoOtroJefeMasDe4[0] = new int[]{5,6,7,8,9,10,11,12,13};
puntosRiichiTsumoOtroJefeMasDe4[1] = new int[]{4000,6000,6000,8000,8000,8000,12000,12000,16000};
puntosRiichiTsumoOtroOtroMasDe4 = new int[2][9];
puntosRiichiTsumoOtroOtroMasDe4[0] = new int[]{5,6,7,8,9,10,11,12,13};
puntosRiichiTsumoOtroOtroMasDe4[1] = new int[]{2000,3000,3000,4000,4000,4000,6000,6000,8000};
puntosRiichiRonJefeMasDe4 = new int[5][10];
puntosRiichiRonJefeMasDe4[0] = new int[]{5,6,7,8,9,10,11,12,13};
puntosRiichiRonJefeMasDe4[1] = new int[]{12000,18000,18000,24000,24000,24000,36000,36000,48000};
puntosRiichiRonOtroMasDe4 = new int[2][9];
puntosRiichiRonOtroMasDe4[0] = new int[]{5,6,7,8,9,10,11,12,13};
puntosRiichiRonOtroMasDe4[1] = new int[]{8000,12000,12000,16000,16000,16000,24000,24000,32000};*/
