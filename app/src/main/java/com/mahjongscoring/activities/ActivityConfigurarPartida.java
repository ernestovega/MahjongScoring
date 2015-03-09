package com.mahjongscoring.activities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mahjongscoring.DBClasses.DBManejador;
import com.mahjongscoring.DBClasses.DBJugador;
import com.mahjongscoring.DBClasses.DBPartida;
import com.mahjongscoring.AppClasses.Enums;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;
//import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityConfigurarPartida extends ActionBarActivity {	
	//CONSTANTES
	/*private static final String IS_riichi_ON = "is_riichi_on";*/
	public static final String IS_TIMER_ON = "is_timer_on";
	/*private static final String IS_CONFIGS_MCR_OCULTAS = "is_configs_mcr_ocultas";
	private static final String IS_CONFIGS_riichi_OCULTAS = "is_configs_riichi_ocultas";*/
	private static final String IS_CONFIGS_TIMER_OCULTAS = "is_configs_timer_ocultas";
	private static final String FOCUS_OWNER_ID = "cursor_owner_id";
	private static final String CURSOR_POSITION_FOR_EDITTEXT_FOCUS_OWNER = "cursor_position_for_edittext_focus_owner";
	/*private static final String NUMBERPICKER_PUNTOS_MINIMOS_MCR_POSITION = "numberpickers_puntos_minimos_mcr_position";*/
	private static final String NUMBERPICKER_HOURS_POSITION = "numberpickers_hours_position";
	private static final String IS_SONIDO_FINAL_ON = "is_sonido_final_on";
	private static final String IS_SONIDO_ULTIMOS15_ON = "is_sonido_ultimos15_on";
	private static final String PUNTOS_INICIALES = "puntos_iniciales";
	/*private static final String BOTE_FINAL = "bote_final";*/
	/*private static final int PUNTOS_INICIALES_30K = 30000;
	private static final int PUNTOS_INICIALES_27K = 27000;
	private static final int PUNTOS_INICIALES_25K = 25000;
	private static final int BOTE_FINAL_30K = 30000;
	private static final int BOTE_FINAL_15K = 15000;
	private static final int BOTE_FINAL_9K = 9000;*/
	public static final String EXTRA_ID_NUEVA_PARTIDA = "id_nueva_partida";
    public static final String EXTRA_ES_REANUDACION = "es_reanudacion";
	//OBJETOS DE LA IU
	private ScrollView scrollviewConfigurarPartida;
	/*private LinearLayout linearlayoutConfigsMcr;
	private LinearLayout linearlayoutConfigsriichi;*/
	private LinearLayout linearlayoutConfigsTemporizador;
	private List<ImageView> imgsOksJugadores;
	/*private NumberPicker numberpickerPuntosMinimosMcr;*/
	private NumberPicker numberpickerHoras;
	private Button buttonCronometro;
	private Button buttonCuentaatras;
	/*private String[] arrayPuntosMinimosMcr;*/
	private String[] arrayHoras;
	private ImageButton imagebuttonSoundFinalOn;
	private ImageButton imagebuttonSoundFinalOff;
	private ImageButton imagebuttonSoundUltimos15On;
	private ImageButton imagebuttonSoundUltimos15Off;
	private View focusedElement;
	/*private Button buttonMcr;
	private Button buttonriichi;*/
	private List<EditText> txtsNombresJugadores;
	/*private TextView titlePuntosMinimosMcr;
	private TextView titlePuntosIniciales;
	private TextView titleBotesFinales;*/
	private TextView titleDuracionTimer;
	private TextView titleSonidoFinalTimer;
	private TextView titleSonidoUltimos15Timer;
	/*private RadioButton radiobuttonPuntosIniciales30k;
	private RadioButton radiobuttonPuntosIniciales27k;
	private RadioButton radiobuttonPuntosIniciales25k;
	private RadioButton radiobuttonBotesFinales30_10k;
	private RadioButton radiobuttonBotesFinales15_5k;
	private RadioButton radiobuttonBotesFinales9_3k;*/
	//CAMPOS
	private boolean[] areNombresOk;
	/*private boolean isriichiOn;*/
	private boolean isTimerOn;
	/*private boolean isConfigsMcrOcultas;
	private boolean isConfigsriichiOcultas;*/
	private boolean isConfigsTimerOcultas;	
	private boolean isTimerSoundFinalOn;
	private boolean isTimerSoundUltimos15On;
	private Typeface typefaceKorean;
	private int puntosIniciales;
	/*private int boteFinal;*/
	private DBManejador db;
    private TextView tituloPedirNombres;
    private TextView tituloPedirTiempo;
	
	//LIFECYCLE EVENTS
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setContentView(R.layout.activity_configurar_partida);
		
		typefaceKorean = Typeface.createFromAsset(getAssets(), "fonts/dekiru.ttf");
		/*SpannableString s = new SpannableString(getString(R.string.configuraciones_juego));
	    s.setSpan(new CustomTypefaceSpan("regular", typefaceKorean), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	    ActionBar actionBar = getActionBar();
	    actionBar.setTitle(s);*/
	    
		inicializarObjetos();
		asignarEventos();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_activity_configurar_partida, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.actionbar_item_action_accept:
				comprobarNombres();
				if (areNombresOk[0] && areNombresOk[1] && areNombresOk[2] && areNombresOk[3])
					empezar();
				else{
					for (int i = 0; i < areNombresOk.length; i++) {
						if(!areNombresOk[i]){
							/*if(!isConfigsriichiOcultas) ocultarConfigsriichi();*/
							if(!isConfigsTimerOcultas) ocultarConfigsTemporizador();
							txtsNombresJugadores.get(i).requestFocus();
							mostrarTeclado(txtsNombresJugadores.get(i));
							break;
						}
					}
				}
				break;
            case R.id.actionbar_item_action_old_games:
                db = new DBManejador(this, DBManejador.DB_NOMBRE, DBManejador.DB_VERSION);
                if(db.contarPartidas() > 0) {
                    Intent intent = new Intent();
                    intent.setClass(ActivityConfigurarPartida.this, ActivityExplorarPartidas.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, "No old games yet", Toast.LENGTH_SHORT).show();
                break;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
    }
	@Override
	protected void onPause() {
		super.onPause();
	}
	@Override	
	protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    if (focusedElement != null) {
	    	outState.putInt(FOCUS_OWNER_ID, focusedElement.getId());
	    	if (focusedElement instanceof EditText) {
	    		InputMethodManager inputManager = (InputMethodManager)focusedElement.getContext().getSystemService(INPUT_METHOD_SERVICE);
	    		inputManager.restartInput(focusedElement);
	    		outState.putInt(CURSOR_POSITION_FOR_EDITTEXT_FOCUS_OWNER, ((EditText) focusedElement).getSelectionStart());
	    	}
	    }
        /*outState.putBoolean(IS_riichi_ON, isriichiOn);*/
        outState.putBoolean(IS_TIMER_ON, isTimerOn);
        /*outState.putBoolean(IS_CONFIGS_MCR_OCULTAS, isConfigsMcrOcultas);
        outState.putBoolean(IS_CONFIGS_riichi_OCULTAS, isConfigsriichiOcultas);*/
        outState.putBoolean(IS_CONFIGS_TIMER_OCULTAS, isConfigsTimerOcultas);
        outState.putInt(PUNTOS_INICIALES, puntosIniciales);
        /*outState.putInt(BOTE_FINAL, boteFinal);
	    outState.putInt(NUMBERPICKER_PUNTOS_MINIMOS_MCR_POSITION, numberpickerPuntosMinimosMcr.getValue());*/
	    outState.putInt(NUMBERPICKER_HOURS_POSITION, numberpickerHoras.getValue());
        outState.putBoolean(IS_SONIDO_FINAL_ON, isTimerSoundFinalOn);
        outState.putBoolean(IS_SONIDO_ULTIMOS15_ON, isTimerSoundUltimos15On);
	}	
	@Override
	protected void onRestoreInstanceState(Bundle inState) {
		super.onRestoreInstanceState(inState);
	    focusedElement = findViewById(inState.getInt(FOCUS_OWNER_ID, View.NO_ID));
		if (focusedElement != null) {
		   focusedElement.requestFocus();
		   scrollviewConfigurarPartida.scrollTo(Math.round(focusedElement.getX()), Math.round(focusedElement.getY()));
		   if (focusedElement instanceof EditText) 
			   ((EditText) focusedElement).setSelection(inState.getInt(CURSOR_POSITION_FOR_EDITTEXT_FOCUS_OWNER, 0));
		}
		/*else {
			LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayout_tipos_juego);
			ll.requestFocus();
			scrollviewConfigurarPartida.scrollTo(Math.round(ll.getX()), Math.round(ll.getY()));
		}*/
		/*if (inState.getBoolean(IS_riichi_ON, false)) {
			activarriichi();
			if (inState.getBoolean(IS_CONFIGS_riichi_OCULTAS, false)) ocultarConfigsriichi();
			else mostrarConfigsriichi();
		}
		else {
			activarMcr();
			if (inState.getBoolean(IS_CONFIGS_MCR_OCULTAS, false)) ocultarConfigsMcr();
			else mostrarConfigsMcr();
		}*/
		if (inState.getBoolean(IS_TIMER_ON, false)) {
			activarTemporizador();
			if (inState.getBoolean(IS_CONFIGS_TIMER_OCULTAS, false)) ocultarConfigsTemporizador();
			else mostrarConfigsTemporizador();
		}
		else {
			activarCronometro();
			ocultarConfigsTemporizador();
		}
		/*puntosIniciales = inState.getInt(PUNTOS_INICIALES, PUNTOS_INICIALES_30K);
		boteFinal = inState.getInt(BOTE_FINAL, BOTE_FINAL_30K);
		numberpickerPuntosMinimosMcr.setValue(inState.getInt(NUMBERPICKER_PUNTOS_MINIMOS_MCR_POSITION, 7));*/
		numberpickerHoras.setValue(inState.getInt(NUMBERPICKER_HOURS_POSITION, 9));
		if (inState.getBoolean(IS_SONIDO_FINAL_ON, false)) activarSonidoFinalOn();
		else activarSonidoFinalOff();
		if (inState.getBoolean(IS_SONIDO_ULTIMOS15_ON, false)) activarSonidoUltimos15On();
		else activarSonidoUltimos15Off();
		comprobarNombres();
	}

	public void onButtonCronometroClicked(View view) {
		ocultarTeclado(view);
		view.requestFocusFromTouch();
		if(isTimerOn){
			activarCronometro(); 
			ocultarConfigsTemporizador();
		}
	}
	public void onbuttonCuentaatrasClicked(View view) {
		ocultarTeclado(view);
		view.requestFocusFromTouch();
		if(isTimerOn) {
			if(isConfigsTimerOcultas) mostrarConfigsTemporizador();
			else ocultarConfigsTemporizador();
		}
		else {
			activarTemporizador();
			mostrarConfigsTemporizador();
		}
	}
	public void onImagebuttonSonidoFinalOnClicked(View view) {
		if(!isTimerSoundFinalOn) activarSonidoFinalOn();
	}
	public void onImagebuttonSonidoFinalOffClicked(View view) {
		if(isTimerSoundFinalOn) activarSonidoFinalOff();
	}
	public void onImagebuttonSonidoUltimos15OnClicked(View view) {
		if(!isTimerSoundUltimos15On) activarSonidoUltimos15On();
	}
	public void onImagebuttonSonidoUltimos15OffClicked(View view) {
		if(isTimerSoundUltimos15On) activarSonidoUltimos15Off();
	}
	
	//FUNCIONES AUXILIARES
	public static class CustomTypefaceSpan extends TypefaceSpan {
        private final Typeface newType;
        public CustomTypefaceSpan(String family, Typeface type) {
            super(family);
            newType = type;
        }
        public void updateDrawState(TextPaint ds) {
            applyCustomTypeFace(ds, newType);
        }
        public void updateMeasureState(TextPaint paint) {
            applyCustomTypeFace(paint, newType);
        }
        private static void applyCustomTypeFace(Paint paint, Typeface tf) {
            int oldStyle;
            Typeface old = paint.getTypeface();
            if (old == null) {
                oldStyle = 0;
            } else {
                oldStyle = old.getStyle();
            }

            int fake = oldStyle & ~tf.getStyle();
            if ((fake & Typeface.BOLD) != 0) {
                paint.setFakeBoldText(true);
            }

            if ((fake & Typeface.ITALIC) != 0) {
                paint.setTextSkewX(-0.25f);
            }

            paint.setTypeface(tf);
        }
    }
	private void inicializarObjetos(){
		scrollviewConfigurarPartida = (ScrollView)findViewById(R.id.scroll_view_configurar_partida);
		imgsOksJugadores = new ArrayList<ImageView>(4);
		imgsOksJugadores.add((ImageView)findViewById(R.id.imageview_ok_jugador1));
		imgsOksJugadores.add((ImageView)findViewById(R.id.imageview_ok_jugador2));
		imgsOksJugadores.add((ImageView)findViewById(R.id.imageview_ok_jugador3));
		imgsOksJugadores.add((ImageView)findViewById(R.id.imageview_ok_jugador4));
		buttonCronometro = (Button)findViewById(R.id.button_cronometro);
		buttonCuentaatras = (Button)findViewById(R.id.button_temporizador);
		linearlayoutConfigsTemporizador = (LinearLayout)findViewById(R.id.linearlayout_configs_temporizador);
    	imagebuttonSoundFinalOn = (ImageButton) findViewById(R.id.button_timer_sonido_final_on);
    	imagebuttonSoundFinalOff = (ImageButton) findViewById(R.id.button_timer_sonido_final_off);
    	imagebuttonSoundUltimos15On = (ImageButton) findViewById(R.id.button_timer_sonido_ultimos15_on);
    	imagebuttonSoundUltimos15Off = (ImageButton) findViewById(R.id.button_timer_sonido_ultimos15_off);
		numberpickerHoras = (NumberPicker) findViewById(R.id.numberpicker_horas_temporizador);
		txtsNombresJugadores = new ArrayList<EditText>(4);
		txtsNombresJugadores.add((EditText)findViewById(R.id.edittext_pedir_nombre_jugador1));
		txtsNombresJugadores.add((EditText)findViewById(R.id.edittext_pedir_nombre_jugador2));
		txtsNombresJugadores.add((EditText)findViewById(R.id.edittext_pedir_nombre_jugador3));
		txtsNombresJugadores.add((EditText)findViewById(R.id.edittext_pedir_nombre_jugador4));
        tituloPedirNombres = (TextView)findViewById(R.id.titulo_pedir_nombres);
        tituloPedirTiempo = (TextView)findViewById(R.id.titulo_pedir_tiempo);
		titleDuracionTimer = (TextView) findViewById(R.id.title_pedir_duracion_temporizador);
		titleSonidoFinalTimer = (TextView) findViewById(R.id.title_pedir_sonido_final_temporizador);
		titleSonidoUltimos15Timer = (TextView) findViewById(R.id.title_pedir_sonido_ultimos15_temporizador);
		areNombresOk = new boolean[]{ false, false, false, false };
		isTimerOn = false;
		isConfigsTimerOcultas = true;
		isTimerSoundFinalOn = true;
		isTimerSoundUltimos15On = true;
		arrayHoras = new String[48];
		int count = 0;
    	for(int i = 0; i < 12; i++) {
    		for(int j = 0; j < 4; j++) {
    			if(i == 0 && j == 0) continue;
    			arrayHoras[count] = i + getString(R.string.txt_parcial_horas) + new DecimalFormat("00").format(j*15) + getString(R.string.txt_parcial_minutos);
    			count++;
    		}
    	}
    	arrayHoras[47] = 12 + getString(R.string.txt_parcial_horas) + new DecimalFormat("00").format(0) + getString(R.string.txt_parcial_minutos);
        /*TODO: Poner el 15 en el array.xml y quitar esta linea para restablecer los 15 minutos ------->*/ arrayHoras[0] = 00 + getString(R.string.txt_parcial_horas) + new DecimalFormat("00").format(1) + getString(R.string.txt_parcial_minutos);
        /*TODO: Poner el 30 en el array.xml y quitar esta linea para restablecer los 30 minutos ------->*/ arrayHoras[1] = 00 + getString(R.string.txt_parcial_horas) + new DecimalFormat("00").format(16) + getString(R.string.txt_parcial_minutos);
    	numberpickerHoras.setDisplayedValues(arrayHoras);
    	numberpickerHoras.setMaxValue(47);
    	numberpickerHoras.setMinValue(0);
    	numberpickerHoras.setValue(1);//9=2h30m
    	numberpickerHoras.setWrapSelectorWheel(true);
    	numberpickerHoras.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    	db = new DBManejador(this, DBManejador.DB_NOMBRE, DBManejador.DB_VERSION);
		
		for (EditText item : txtsNombresJugadores) item.setTypeface(typefaceKorean);
		/*titlePuntosMinimosMcr.setTypeface(typefaceKorean);
		titlePuntosIniciales.setTypeface(typefaceKorean);
		titleBotesFinales.setTypeface(typefaceKorean);*/
        tituloPedirNombres.setTypeface(typefaceKorean);
        tituloPedirTiempo.setTypeface(typefaceKorean);
		titleDuracionTimer.setTypeface(typefaceKorean);
		titleSonidoFinalTimer.setTypeface(typefaceKorean);
		titleSonidoUltimos15Timer.setTypeface(typefaceKorean);
		/*buttonMcr.setTypeface(typefaceKorean);
		buttonriichi.setTypeface(typefaceKorean);*/
		buttonCronometro.setTypeface(typefaceKorean);
		buttonCuentaatras.setTypeface(typefaceKorean);
		/*radiobuttonPuntosIniciales30k.setTypeface(typefaceKorean);
		radiobuttonPuntosIniciales27k.setTypeface(typefaceKorean);
		radiobuttonPuntosIniciales25k.setTypeface(typefaceKorean);
		radiobuttonBotesFinales30_10k.setTypeface(typefaceKorean);
		radiobuttonBotesFinales15_5k.setTypeface(typefaceKorean);
		radiobuttonBotesFinales9_3k.setTypeface(typefaceKorean);*/
		
		/*TODO: Quitar los nombres!*/String[] nombresProvisionales = new String[] { "Bea", "Chino", "Gurru", "Eto" };
		for(int i = 0; i < 4; i++) txtsNombresJugadores.get(i).setText(nombresProvisionales[i], TextView.BufferType.EDITABLE);
	}
	private void asignarEventos(){
		for (int i = 0; i < 4; i++) {
			final int j = i;
			txtsNombresJugadores.get(i).setOnFocusChangeListener(new OnFocusChangeListener() {
			    @Override
			    public void onFocusChange(View v, boolean hasFocus) {
			    	if(!hasFocus) comprobarNombre(j);
			    }
			});
		}
	}
	private void ocultarTeclado(View view) {
		view.getContext();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if(imm.getCurrentInputMethodSubtype() != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);		
	}	
  	private void mostrarTeclado(View view) {
		view.getContext();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if(imm.getCurrentInputMethodSubtype() != null) imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);	
	}
	/*private void activarMcr() {
		isriichiOn = false;
		buttonMcr.setBackgroundResource(R.drawable.shapebuttonselected);
		buttonriichi.setBackgroundResource(R.drawable.shapebuttonunselected);
	}
	private void activarriichi() {
		isriichiOn = true;	
		buttonMcr.setBackgroundResource(R.drawable.shapebuttonunselected);
		buttonriichi.setBackgroundResource(R.drawable.shapebuttonselected);
	}
	private void mostrarConfigsMcr() {
		if(!isConfigsriichiOcultas) ocultarConfigsriichi();
		isConfigsMcrOcultas = false;
		linearlayoutConfigsMcr.setVisibility(LinearLayout.VISIBLE);
	}
	private void ocultarConfigsMcr() {
		isConfigsMcrOcultas = true;
		linearlayoutConfigsMcr.setVisibility(LinearLayout.GONE);	
	}
	private void mostrarConfigsriichi() {
		if(!isConfigsMcrOcultas) ocultarConfigsMcr();
		isConfigsriichiOcultas = false;
		linearlayoutConfigsriichi.setVisibility(LinearLayout.VISIBLE);
	}
	private void ocultarConfigsriichi() {
		isConfigsriichiOcultas = true;
		linearlayoutConfigsriichi.setVisibility(LinearLayout.GONE);	
	}*/
	private void activarCronometro() {
		isTimerOn = false;
		buttonCronometro.setBackgroundResource(R.drawable.shapebuttonselected);
		buttonCuentaatras.setBackgroundResource(R.drawable.shapebuttonunselected);
	}
	private void activarTemporizador() {
		isTimerOn = true;
		buttonCronometro.setBackgroundResource(R.drawable.shapebuttonunselected);
		buttonCuentaatras.setBackgroundResource(R.drawable.shapebuttonselected);
	}
	private void mostrarConfigsTemporizador() {
		isConfigsTimerOcultas = false;
		linearlayoutConfigsTemporizador.setVisibility(LinearLayout.VISIBLE);
	}
	private void ocultarConfigsTemporizador() {
		isConfigsTimerOcultas = true;
		linearlayoutConfigsTemporizador.setVisibility(LinearLayout.GONE);
	}
	private void activarSonidoFinalOn() {
		isTimerSoundFinalOn = true;
		imagebuttonSoundFinalOn.setBackgroundResource(R.drawable.shapebuttonselected);
		imagebuttonSoundFinalOff.setBackgroundResource(R.drawable.shapebuttonunselected);
	}
	private void activarSonidoFinalOff() {
		isTimerSoundFinalOn = false;
		imagebuttonSoundFinalOn.setBackgroundResource(R.drawable.shapebuttonunselected);
		imagebuttonSoundFinalOff.setBackgroundResource(R.drawable.shapebuttonselected);
	}
	private void activarSonidoUltimos15On() {
		isTimerSoundUltimos15On = true;
		imagebuttonSoundUltimos15On.setBackgroundResource(R.drawable.shapebuttonselected);
		imagebuttonSoundUltimos15Off.setBackgroundResource(R.drawable.shapebuttonunselected);
	}
	private void activarSonidoUltimos15Off() {
		isTimerSoundUltimos15On = false;
		imagebuttonSoundUltimos15On.setBackgroundResource(R.drawable.shapebuttonunselected);
		imagebuttonSoundUltimos15Off.setBackgroundResource(R.drawable.shapebuttonselected);
	}
	private void comprobarNombres() { 
		for (int i = 0; i < txtsNombresJugadores.size(); i++) comprobarNombre(i); 
	}
	private void comprobarNombre(int i) {
		if (txtsNombresJugadores.get(i).getText().toString().trim().matches("")) {//SI ESTA VACIO O CON ESPACIOS: NO OK Y SE VACIA
			imgsOksJugadores.get(i).setImageResource(R.drawable.ic_no_ok);
			txtsNombresJugadores.get(i).getText().clear();
			areNombresOk[i] = false;
		}
		else if (estaRepetidoNombre(txtsNombresJugadores.get(i).getText())) {//SI ESTA LLENO PERO ESTA REPETIDO: NO OK Y SE QUITAN ESPACIOS
			imgsOksJugadores.get(i).setImageResource(R.drawable.ic_no_ok);
			txtsNombresJugadores.get(i).setText(txtsNombresJugadores.get(i).getText().toString().trim(), TextView.BufferType.EDITABLE);
			txtsNombresJugadores.get(i).setSelection(txtsNombresJugadores.get(i).getText().length());
			areNombresOk[i] = false;			
		}
		else {//SI ESTA LLENO Y NO ESTA REPETIDO: OK
			imgsOksJugadores.get(i).setImageResource(R.drawable.ic_ok);
			areNombresOk[i] = true;
		}		
	}
	private boolean estaRepetidoNombre(Editable nombre) {
		int count = 0;
		for (int i = 0; i < txtsNombresJugadores.size(); i++) {
			if (nombre.toString().trim().equalsIgnoreCase(txtsNombresJugadores.get(i).getText().toString().trim()))
				count++;
		}
		return count > 1;
	}
	private void empezar() {
		Intent intent = new Intent(this, ActivityMesa.class);
		intent.putExtra(EXTRA_ES_REANUDACION, false);
        intent.putExtra(EXTRA_ID_NUEVA_PARTIDA, CrearPartidaEnBBDD());
		startActivity(intent);
	}
	private int CrearPartidaEnBBDD() {
		String[] nombres = devuelveNombres();
		DBJugador jugador;
		for(int i = 0; i < 4; i++){
			jugador = db.obtenerJugador(nombres[i]);
			if(jugador == null) db.anadirJugador(new DBJugador(nombres[i], new Date()));
		}
		int duracionTimerMinutos = getResources().getIntArray(R.array.arrayMinutos)[numberpickerHoras.getValue()];
		long duracionTimerMilisegundos = duracionTimerMinutos * 60 * 1000;
		DBPartida partida = new DBPartida(/*isriichiOn ? Enums.TiposJuego.RICHII : */Enums.TiposJuego.MCR,
				/*puntosIniciales, Integer.valueOf(arrayPuntosMinimosMcr[numberpickerPuntosMinimosMcr.getValue()]),*/
				/*boteFinal,*/8, nombres, devuelvePuntosIniciales(), isTimerOn, isTimerSoundFinalOn, isTimerSoundUltimos15On,
				isTimerOn ? duracionTimerMilisegundos : 0, isTimerOn ? duracionTimerMilisegundos : 0, 0, /*0, 0, 0,*/"", 0, new Date(), null);
		return db.anadirPartida(partida);
	}
	private String[] devuelveNombres(){
		return new String[] { 
				txtsNombresJugadores.get(0).getText().toString(), 
				txtsNombresJugadores.get(1).getText().toString(),
				txtsNombresJugadores.get(2).getText().toString(), 
				txtsNombresJugadores.get(3).getText().toString()
		};
	}
	private int[] devuelvePuntosIniciales(){
		/*if (isriichiOn)	return new int[] { puntosIniciales, puntosIniciales, puntosIniciales, puntosIniciales };
		else*/ return new int[] { 0, 0, 0, 0 };
	}
}
