package com.mahjongscoring.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends Activity {
	
	private static final long SPLASH_SCREEN_DELAY = 1000;
//    public static final String EXTRA_ID_NUEVA_PARTIDA = "id_nueva_partida";
//    public static final String EXTRA_ES_REANUDACION = "es_reanudacion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Intent intent = new Intent();
        intent.setClass(ActivitySplash.this, ActivityConfigurarPartida.class);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);

//        DBManejador db = new DBManejador(this, DBManejador.DB_NOMBRE, DBManejador.DB_VERSION);
//        final Intent intent = new Intent();
//        int idPartida = db.obtenerIdUltimaPartida();
//        if(idPartida > 0) {
//            DBPartida partida = db.obtenerPartida(idPartida);
//            if (partida.getFechaFin().compareTo(new java.util.Date(0)) == 0) {
//                intent.setClass(ActivitySplash.this, ActivityMesa.class);
//                intent.putExtra(EXTRA_ES_REANUDACION, true);
//                intent.putExtra(EXTRA_ID_NUEVA_PARTIDA, idPartida);
//            }
//            else
//                intent.setClass(ActivitySplash.this, ActivityConfigurarPartida.class);
//        }
//        else
//            intent.setClass(ActivitySplash.this, ActivityConfigurarPartida.class);
//
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//        		startActivity(intent);
//                finish();
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
