package com.mahjongscoring.DBClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mahjongscoring.AppClasses.CPartidaAntigua;
import com.mahjongscoring.AppClasses.Enums;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBManejador extends SQLiteOpenHelper {

	//region CONSTANTES

    //region Database

	public static final int DB_VERSION = 15;
    public static final String DB_NOMBRE = "MahjongScoringDB";

    //endregion

	//region Jugadores

    private static final String TABLA_JUGADORES = "jugadores";

	private static final String COLUMNA_JUGADORES_NOMBRE = "nombre";
	private static final String COLUMNA_JUGADORES_FECHAALTA = "fecha_alta";

	private static final String CREAR_TABLA_JUGADORES = "CREATE TABLE " + TABLA_JUGADORES + "("
			+ COLUMNA_JUGADORES_NOMBRE + " TEXT NOT NULL PRIMARY KEY,"
			+ COLUMNA_JUGADORES_FECHAALTA + " INTEGER NOT NULL)";

	//endregion

	//region Partidas

	private static final String TABLA_PARTIDAS = "partidas";

    private static final String COLUMNA_PARTIDAS_ID = "id";
    private static final String COLUMNA_PARTIDAS_TIPOJUEGO = "tipo_juego";
    private static final String COLUMNA_PARTIDAS_PUNTOSMINIMOSMCR = "puntos_minimos_mcr";
    private static final String COLUMNA_PARTIDAS_NOMBREJ1 = "nombre_j1";
    private static final String COLUMNA_PARTIDAS_NOMBREJ2 = "nombre_j2";
    private static final String COLUMNA_PARTIDAS_NOMBREJ3 = "nombre_j3";
    private static final String COLUMNA_PARTIDAS_NOMBREJ4 = "nombre_j4";
    private static final String COLUMNA_PARTIDAS_PUNTOSJ1 = "puntos_j1";
    private static final String COLUMNA_PARTIDAS_PUNTOSJ2 = "puntos_j2";
    private static final String COLUMNA_PARTIDAS_PUNTOSJ3 = "puntos_j3";
    private static final String COLUMNA_PARTIDAS_PUNTOSJ4 = "puntos_j4";
    private static final String COLUMNA_PARTIDAS_ISTIMERON = "is_timer_on";
    private static final String COLUMNA_PARTIDAS_ISSONIDOFINALON = "is_sonido_final_on";
    private static final String COLUMNA_PARTIDAS_ISSONIDOULTIMOS15ON = "is_sonido_ultimos15_on";
    private static final String COLUMNA_PARTIDAS_DURACIONTIMER = "duracion_timer";
    private static final String COLUMNA_PARTIDAS_MILISTRASCURRIDOSORESTANTES = "milis_transcurridos_o_restantes";
    private static final String COLUMNA_PARTIDAS_NUMERORONDAS = "numero_rondas";
    private static final String COLUMNA_PARTIDAS_NOMBREJUGADORMEJORJUGADA = "nombre_jugador_mejor_jugada";
    private static final String COLUMNA_PARTIDAS_PUNTOSMEJORJUGADA = "puntos_mejor_jugada";
    private static final String COLUMNA_PARTIDAS_FECHAINICIO = "fecha_inicio";
    private static final String COLUMNA_PARTIDAS_FECHAFIN = "fecha_fin";

	private static final String CREAR_TABLA_PARTIDAS = "CREATE TABLE " + TABLA_PARTIDAS + "("
			+ COLUMNA_PARTIDAS_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ COLUMNA_PARTIDAS_TIPOJUEGO + " TEXT NOT NULL,"
			+ COLUMNA_PARTIDAS_PUNTOSMINIMOSMCR + " INTEGER NOT NULL,"
			+ COLUMNA_PARTIDAS_NOMBREJ1 + " TEXT NOT NULL,"
			+ COLUMNA_PARTIDAS_NOMBREJ2 + " TEXT NOT NULL,"
			+ COLUMNA_PARTIDAS_NOMBREJ3 + " TEXT NOT NULL,"
			+ COLUMNA_PARTIDAS_NOMBREJ4 + " TEXT NOT NULL,"
			+ COLUMNA_PARTIDAS_PUNTOSJ1 + " INTEGER NOT NULL,"
			+ COLUMNA_PARTIDAS_PUNTOSJ2 + " INTEGER NOT NULL,"
			+ COLUMNA_PARTIDAS_PUNTOSJ3 + " INTEGER NOT NULL,"
			+ COLUMNA_PARTIDAS_PUNTOSJ4 + " INTEGER NOT NULL,"
			+ COLUMNA_PARTIDAS_ISTIMERON + " BOOLEAN NOT NULL,"
			+ COLUMNA_PARTIDAS_ISSONIDOFINALON + " BOOLEAN NOT NULL,"
			+ COLUMNA_PARTIDAS_ISSONIDOULTIMOS15ON + " BOOLEAN NOT NULL,"
			+ COLUMNA_PARTIDAS_DURACIONTIMER + " INTEGER NOT NULL,"
			+ COLUMNA_PARTIDAS_MILISTRASCURRIDOSORESTANTES + " INTEGER NOT NULL,"
			+ COLUMNA_PARTIDAS_NUMERORONDAS + " INTEGER NOT NULL,"
			+ COLUMNA_PARTIDAS_NOMBREJUGADORMEJORJUGADA + " TEXT NOT NULL,"
			+ COLUMNA_PARTIDAS_PUNTOSMEJORJUGADA + " INTEGER NOT NULL,"
			+ COLUMNA_PARTIDAS_FECHAINICIO + " INTEGER NOT NULL,"
			+ COLUMNA_PARTIDAS_FECHAFIN + " INTEGER)";

	//endregion

	//region Rondas

	private static final String TABLA_RONDAS = "rondas";

    private static final String COLUMNA_RONDAS_IDPARTIDA = "id_partida";
    private static final String COLUMNA_RONDAS_NUMERORONDA = "numero_ronda";
    private static final String COLUMNA_RONDAS_PUNTOSRONDAJ1 = "puntos_ronda_j1";
    private static final String COLUMNA_RONDAS_PUNTOSRONDAJ2 = "puntos_ronda_j2";
    private static final String COLUMNA_RONDAS_PUNTOSRONDAJ3 = "puntos_ronda_j3";
    private static final String COLUMNA_RONDAS_PUNTOSRONDAJ4 = "puntos_ronda_j4";
    private static final String COLUMNA_RONDAS_PUNTOSTOTALESJ1 = "puntos_totales_j1";
    private static final String COLUMNA_RONDAS_PUNTOSTOTALESJ2 = "puntos_totales_j2";
    private static final String COLUMNA_RONDAS_PUNTOSTOTALESJ3 = "puntos_totales_j3";
    private static final String COLUMNA_RONDAS_PUNTOSTOTALESJ4 = "puntos_totales_j4";
    private static final String COLUMNA_RONDAS_CHOMBOJ1 = "chombo_j1";
    private static final String COLUMNA_RONDAS_CHOMBOJ2 = "chombo_j2";
    private static final String COLUMNA_RONDAS_CHOMBOJ3 = "chombo_j3";
    private static final String COLUMNA_RONDAS_CHOMBOJ4 = "chombo_j4";
    private static final String COLUMNA_RONDAS_PUNTOSJUGADA = "puntos_jugada";
    private static final String COLUMNA_RONDAS_NOMBREGANADOR = "nombre_ganador";
    private static final String COLUMNA_RONDAS_NOMBREPERDEDOR = "nombre_perdedor";

	private static final String CREAR_TABLA_RONDAS = "CREATE TABLE " + TABLA_RONDAS + "("
			+ COLUMNA_RONDAS_IDPARTIDA + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_NUMERORONDA + " INTEGER NOT NULL," 
            + COLUMNA_RONDAS_PUNTOSRONDAJ1 + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_PUNTOSRONDAJ2 + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_PUNTOSRONDAJ3 + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_PUNTOSRONDAJ4 + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_PUNTOSTOTALESJ1 + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_PUNTOSTOTALESJ2 + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_PUNTOSTOTALESJ3 + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_PUNTOSTOTALESJ4 + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_CHOMBOJ1 + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_CHOMBOJ2 + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_CHOMBOJ3 + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_CHOMBOJ4 + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_PUNTOSJUGADA + " INTEGER NOT NULL,"
            + COLUMNA_RONDAS_NOMBREGANADOR + " TEXT NOT NULL,"
            + COLUMNA_RONDAS_NOMBREPERDEDOR + " TEXT NOT NULL,"
            + "PRIMARY KEY (" + COLUMNA_RONDAS_IDPARTIDA + "," + COLUMNA_RONDAS_NUMERORONDA + "))";

	//endregion

	//endregion

	//region CONTRUCTORES

    public DBManejador(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

	//endregion

	//region CICLO DE VIDA

    @Override
    public void onCreate(SQLiteDatabase db) {    
    	db.execSQL(CREAR_TABLA_JUGADORES);
        db.execSQL(CREAR_TABLA_PARTIDAS);
        db.execSQL(CREAR_TABLA_RONDAS);
//        db.execSQL(CREAR_TABLA_PUNTOS_RICHII);
    }

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_JUGADORES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PARTIDAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_RONDAS);
        onCreate(db);
    }

	//endregion

	//region MÉTODOS PÚBLICOS

	//region Jugadores

	public void anadirJugador(DBJugador dbJugador) {
	    SQLiteDatabase db = this.getWritableDatabase();	 
	    ContentValues values = new ContentValues();	    
	    values.put(COLUMNA_JUGADORES_NOMBRE, dbJugador.getNombre());
	    values.put(COLUMNA_JUGADORES_FECHAALTA, dbJugador.getFechaAlta().getTime());
	    db.insert(TABLA_JUGADORES, null, values);
	    db.close();
	}
	public DBJugador obtenerJugador(String nombre) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT  * FROM " + TABLA_JUGADORES + " WHERE "
	            + COLUMNA_JUGADORES_NOMBRE + " = '" + nombre + "'";
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	    	DBJugador dbJugador = new DBJugador();
	    	dbJugador.setNombre(cursor.getString(cursor.getColumnIndex(COLUMNA_JUGADORES_NOMBRE)));
	    	dbJugador.setFechaAlta(new Date(cursor.getLong(cursor.getColumnIndex(COLUMNA_JUGADORES_FECHAALTA))));
	    	return dbJugador;
	    }
	    return null;	    
	}
	public List<DBJugador> obtenerTodosDBJugadores() {
	    SQLiteDatabase db = this.getReadableDatabase();
		List<DBJugador> dbJugadores = new ArrayList<DBJugador>(4);
	    String selectQuery = "SELECT * FROM " + TABLA_JUGADORES;
	    Cursor cursor = db.rawQuery(selectQuery, null);	 
	    if (cursor.moveToFirst()) {
	        do {
	        	DBJugador dbJugador = new DBJugador();
	        	dbJugador.setNombre(cursor.getString(cursor.getColumnIndex(COLUMNA_JUGADORES_NOMBRE)));
	        	dbJugador.setFechaAlta(new Date(cursor.getLong(cursor.getColumnIndex(COLUMNA_JUGADORES_FECHAALTA))));
	        	dbJugadores.add(dbJugador);
	        } while (cursor.moveToNext());
		    return dbJugadores;
	    }
	    return null;
	}
	public int contarJugadores() {
		String countQuery = "SELECT * FROM " + TABLA_JUGADORES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();	
	}
	public int actualizarJugador(DBJugador dbJugador) {
		SQLiteDatabase db = this.getWritableDatabase();		 
	    ContentValues values = new ContentValues();
	    values.put(COLUMNA_JUGADORES_NOMBRE, dbJugador.getNombre());
	    values.put(COLUMNA_JUGADORES_FECHAALTA, dbJugador.getFechaAlta().getTime());
	    return db.update(TABLA_JUGADORES, values, COLUMNA_JUGADORES_NOMBRE + " = ?", new String[] { dbJugador.getNombre() });
	}
	public void eliminarJugador(DBJugador dbJugador) {
		SQLiteDatabase db = this.getWritableDatabase();	
		db.delete(TABLA_JUGADORES, COLUMNA_JUGADORES_NOMBRE + " = ?", new String[] { dbJugador.getNombre() });
		db.close();
	}

	//endregion

	//region Partidas

	public int anadirPartida(DBPartida partida) {
	    SQLiteDatabase db = this.getWritableDatabase();	 
	    ContentValues values = new ContentValues();
	    values.put(COLUMNA_PARTIDAS_TIPOJUEGO, partida.getTipoJuego() == Enums.TiposJuego.MCR ? "MCR" : "RICHII" );
	    /*values.put(COLUMNA_PARTIDAS_PUNTOSINICIALES, partida.getPuntosIniciales());*/
	    values.put(COLUMNA_PARTIDAS_PUNTOSMINIMOSMCR, partida.getPuntosMinimosMcr());
	    /*values.put(COLUMNA_PARTIDAS_BOTEFINALRICHII, partida.getBoteFinalRichii());*/
	    values.put(COLUMNA_PARTIDAS_NOMBREJ1, partida.getNombreJ1());
	    values.put(COLUMNA_PARTIDAS_NOMBREJ2, partida.getNombreJ2());
	    values.put(COLUMNA_PARTIDAS_NOMBREJ3, partida.getNombreJ3());
	    values.put(COLUMNA_PARTIDAS_NOMBREJ4, partida.getNombreJ4());
	    values.put(COLUMNA_PARTIDAS_PUNTOSJ1, partida.getPuntosJ1());
	    values.put(COLUMNA_PARTIDAS_PUNTOSJ2, partida.getPuntosJ2());    
	    values.put(COLUMNA_PARTIDAS_PUNTOSJ3, partida.getPuntosJ3());    
	    values.put(COLUMNA_PARTIDAS_PUNTOSJ4, partida.getPuntosJ4());    
	    values.put(COLUMNA_PARTIDAS_ISTIMERON, partida.isTimerOn() ? 1 : 0);   
	    values.put(COLUMNA_PARTIDAS_ISSONIDOFINALON, partida.isSonidoFinalOn() ? 1 : 0);   
	    values.put(COLUMNA_PARTIDAS_ISSONIDOULTIMOS15ON, partida.isSonidoUltimos15On() ? 1 : 0);
	    values.put(COLUMNA_PARTIDAS_DURACIONTIMER, partida.getDuracionTimer());
        values.put(COLUMNA_PARTIDAS_MILISTRASCURRIDOSORESTANTES, partida.getMilisTranscurridosORestantes());
	    values.put(COLUMNA_PARTIDAS_NUMERORONDAS, partida.getNumeroRondas());
	    /*values.put(COLUMNA_PARTIDAS_CONTADORESRICHII, partida.getContadoresRichii());
	    values.put(COLUMNA_PARTIDAS_BOTERICHII, partida.getBoteRichii());
	    values.put(COLUMNA_PARTIDAS_VECESJEFERICHII, partida.getVecesJefeRichii());*/
	    values.put(COLUMNA_PARTIDAS_NOMBREJUGADORMEJORJUGADA, partida.getNombreJugadorMejorJugada());
	    values.put(COLUMNA_PARTIDAS_PUNTOSMEJORJUGADA, partida.getPuntosMejorJugada());
	    values.put(COLUMNA_PARTIDAS_FECHAINICIO, partida.getFechaInicio().getTime());    
	    values.put(COLUMNA_PARTIDAS_FECHAFIN, partida.getFechaFin() != null ? partida.getFechaFin().getTime() : null);
	    db.insert(TABLA_PARTIDAS, null, values);	    
	    db.close();
	    return obtenerIdUltimaPartida();
	}
	public DBPartida obtenerPartida(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT  * FROM " + TABLA_PARTIDAS + " WHERE "
	            + COLUMNA_PARTIDAS_ID + " = " + id;
		Cursor cursor = db.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	    	DBPartida partida = new DBPartida();
	    	partida.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_ID)));
	    	partida.setTipoJuego(cursor.getString(cursor.getColumnIndex(COLUMNA_PARTIDAS_TIPOJUEGO)).equals("MCR") ? Enums.TiposJuego.MCR : Enums.TiposJuego.RICHII);
	    	/*partida.setPuntosIniciales(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_PUNTOSINICIALES)));*/
	    	partida.setPuntosMinimosMcr(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_PUNTOSMINIMOSMCR)));
	    	/*partida.setBoteFinalRichii(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_BOTEFINALRICHII)));*/
	    	partida.setNombreJ1(cursor.getString(cursor.getColumnIndex(COLUMNA_PARTIDAS_NOMBREJ1)));
	    	partida.setNombreJ2(cursor.getString(cursor.getColumnIndex(COLUMNA_PARTIDAS_NOMBREJ2)));
	    	partida.setNombreJ3(cursor.getString(cursor.getColumnIndex(COLUMNA_PARTIDAS_NOMBREJ3)));
	    	partida.setNombreJ4(cursor.getString(cursor.getColumnIndex(COLUMNA_PARTIDAS_NOMBREJ4)));
	    	partida.setPuntosJ1(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_PUNTOSJ1)));
	    	partida.setPuntosJ2(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_PUNTOSJ2)));
	    	partida.setPuntosJ3(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_PUNTOSJ3)));
	    	partida.setPuntosJ4(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_PUNTOSJ4)));
	    	partida.setPuntosJ4(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_PUNTOSJ4)));
	    	partida.setIsTimerOn(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_ISTIMERON)) == 1);
	    	partida.setIsSonidoFinalOn(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_ISSONIDOFINALON)) == 1);
	    	partida.setIsSonidoUltimos15On(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_ISSONIDOULTIMOS15ON)) == 1);
	    	partida.setDuracionTimer(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_DURACIONTIMER)));
            partida.setMilisTranscurridosORestantes(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_MILISTRASCURRIDOSORESTANTES)));
	    	partida.setNumeroRondas(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_NUMERORONDAS)));
	    	/*partida.setContadoresRichii(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_CONTADORESRICHII)));
	    	partida.setBoteRichii(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_BOTERICHII)));
	    	partida.setVecesJefeRichii(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_VECESJEFERICHII)));*/
	    	partida.setNombreJugadorMejorJugada(cursor.getString(cursor.getColumnIndex(COLUMNA_PARTIDAS_NOMBREJUGADORMEJORJUGADA)));
	    	partida.setPuntosMejorJugada(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_PUNTOSMEJORJUGADA)));
	    	partida.setFechaInicio(new Date(cursor.getLong(cursor.getColumnIndex(COLUMNA_PARTIDAS_FECHAINICIO))));
	    	partida.setFechaFin(new Date(cursor.getLong(cursor.getColumnIndex(COLUMNA_PARTIDAS_FECHAFIN))));
	    	return partida;
	    }
	    return null;	    
	}
    public ArrayList<CPartidaAntigua> obtenerTodasPartidasAntiguas() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + COLUMNA_PARTIDAS_ID + ","
                + COLUMNA_PARTIDAS_NOMBREJ1 + "," + COLUMNA_PARTIDAS_NOMBREJ2 + ","
                + COLUMNA_PARTIDAS_NOMBREJ3 + "," + COLUMNA_PARTIDAS_NOMBREJ4 + ","
                + COLUMNA_PARTIDAS_PUNTOSJ1 + "," + COLUMNA_PARTIDAS_PUNTOSJ2 + ","
                + COLUMNA_PARTIDAS_PUNTOSJ3 + "," + COLUMNA_PARTIDAS_PUNTOSJ4 + ","
                + COLUMNA_PARTIDAS_NUMERORONDAS + "," + COLUMNA_PARTIDAS_FECHAINICIO + ","
                + COLUMNA_PARTIDAS_NOMBREJUGADORMEJORJUGADA + "," + COLUMNA_PARTIDAS_PUNTOSMEJORJUGADA
                + " FROM " + TABLA_PARTIDAS + " ORDER BY " + COLUMNA_PARTIDAS_ID + " DESC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<CPartidaAntigua> partidas = new ArrayList<CPartidaAntigua>(cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                CPartidaAntigua partida = new CPartidaAntigua();
                partida.setId(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_ID)));
                partida.setNombreJ1(cursor.getString(cursor.getColumnIndex(COLUMNA_PARTIDAS_NOMBREJ1)));
                partida.setNombreJ2(cursor.getString(cursor.getColumnIndex(COLUMNA_PARTIDAS_NOMBREJ2)));
                partida.setNombreJ3(cursor.getString(cursor.getColumnIndex(COLUMNA_PARTIDAS_NOMBREJ3)));
                partida.setNombreJ4(cursor.getString(cursor.getColumnIndex(COLUMNA_PARTIDAS_NOMBREJ4)));
                partida.setPuntosJ1(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_PUNTOSJ1)));
                partida.setPuntosJ2(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_PUNTOSJ2)));
                partida.setPuntosJ3(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_PUNTOSJ3)));
                partida.setPuntosJ4(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_PUNTOSJ4)));
                partida.setNumeroRondas(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_NUMERORONDAS)));
                partida.setFechaInicio(new Date(cursor.getLong(cursor.getColumnIndex(COLUMNA_PARTIDAS_FECHAINICIO))));
                partida.setNombreMejorMano(cursor.getString(cursor.getColumnIndex(COLUMNA_PARTIDAS_NOMBREJUGADORMEJORJUGADA)));
                partida.setPuntosMejorMano(cursor.getInt(cursor.getColumnIndex(COLUMNA_PARTIDAS_PUNTOSMEJORJUGADA)));
                partidas.add(partida);
            } while (cursor.moveToNext());
            return partidas;
        }
        return null;
    }
    public int contarPartidas() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT COUNT(*) FROM " + TABLA_PARTIDAS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }
	public int obtenerIdUltimaPartida() {
	    SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT MAX(" + COLUMNA_PARTIDAS_ID + ") FROM " + TABLA_PARTIDAS;
		Cursor cursor = db.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) return cursor.getInt(0);
	    return 0;
	}
	public int actualizarPartida(DBPartida partida) {
		SQLiteDatabase db = this.getWritableDatabase();		 
	    ContentValues values = new ContentValues();
	    values.put(COLUMNA_PARTIDAS_TIPOJUEGO, partida.getTipoJuego() == Enums.TiposJuego.MCR ? "MCR" : "RICHII" );
	    /*values.put(COLUMNA_PARTIDAS_PUNTOSINICIALES, partida.getPuntosIniciales());*/
	    values.put(COLUMNA_PARTIDAS_PUNTOSMINIMOSMCR, partida.getPuntosMinimosMcr());
	    /*values.put(COLUMNA_PARTIDAS_BOTEFINALRICHII, partida.getBoteFinalRichii());*/
	    values.put(COLUMNA_PARTIDAS_NOMBREJ1, partida.getNombreJ1());
	    values.put(COLUMNA_PARTIDAS_NOMBREJ2, partida.getNombreJ2());
	    values.put(COLUMNA_PARTIDAS_NOMBREJ3, partida.getNombreJ3());
	    values.put(COLUMNA_PARTIDAS_NOMBREJ4, partida.getNombreJ4());
	    values.put(COLUMNA_PARTIDAS_PUNTOSJ1, partida.getPuntosJ1());
	    values.put(COLUMNA_PARTIDAS_PUNTOSJ2, partida.getPuntosJ2());    
	    values.put(COLUMNA_PARTIDAS_PUNTOSJ3, partida.getPuntosJ3());    
	    values.put(COLUMNA_PARTIDAS_PUNTOSJ4, partida.getPuntosJ4()); 
	    values.put(COLUMNA_PARTIDAS_ISTIMERON, partida.isTimerOn() ? 1 : 0);   
	    values.put(COLUMNA_PARTIDAS_ISSONIDOFINALON, partida.isSonidoFinalOn() ? 1 : 0);   
	    values.put(COLUMNA_PARTIDAS_ISSONIDOULTIMOS15ON, partida.isSonidoUltimos15On() ? 1 : 0);
	    values.put(COLUMNA_PARTIDAS_DURACIONTIMER, partida.getDuracionTimer());
        values.put(COLUMNA_PARTIDAS_MILISTRASCURRIDOSORESTANTES, partida.getMilisTranscurridosORestantes());
	    values.put(COLUMNA_PARTIDAS_NUMERORONDAS, partida.getNumeroRondas());
	    /*values.put(COLUMNA_PARTIDAS_CONTADORESRICHII, partida.getContadoresRichii());
	    values.put(COLUMNA_PARTIDAS_BOTERICHII, partida.getBoteRichii());
	    values.put(COLUMNA_PARTIDAS_VECESJEFERICHII, partida.getVecesJefeRichii());*/
	    values.put(COLUMNA_PARTIDAS_NOMBREJUGADORMEJORJUGADA, partida.getNombreJugadorMejorJugada());
	    values.put(COLUMNA_PARTIDAS_PUNTOSMEJORJUGADA, partida.getPuntosMejorJugada());
	    values.put(COLUMNA_PARTIDAS_FECHAINICIO, partida.getFechaInicio().getTime());    
	    values.put(COLUMNA_PARTIDAS_FECHAFIN, partida.getFechaFin() != null ? partida.getFechaFin().getTime() : null);
	    return db.update(TABLA_PARTIDAS, values, COLUMNA_PARTIDAS_ID + " = ?", new String[] { String.valueOf(partida.getId()) });
	}
	public void eliminarPartida(int idPartida) {
		SQLiteDatabase db = this.getWritableDatabase();
        eliminarRondas(idPartida, db);
		db.delete(TABLA_PARTIDAS, COLUMNA_PARTIDAS_ID + " = ?", new String[] { String.valueOf(idPartida) });
		db.close();
	}

	//endregion

	//region Rondas

	public void anadirRonda(DBRonda ronda) {
	    SQLiteDatabase db = this.getWritableDatabase();	 
	    ContentValues values = new ContentValues();
	    values.put(COLUMNA_RONDAS_IDPARTIDA, ronda.getIdPartida());
	    values.put(COLUMNA_RONDAS_NUMERORONDA, ronda.getNumeroRonda());
	    values.put(COLUMNA_RONDAS_PUNTOSRONDAJ1, ronda.getPuntosRondaJ1());
	    values.put(COLUMNA_RONDAS_PUNTOSRONDAJ2, ronda.getPuntosRondaJ2());
	    values.put(COLUMNA_RONDAS_PUNTOSRONDAJ3, ronda.getPuntosRondaJ3());
	    values.put(COLUMNA_RONDAS_PUNTOSRONDAJ4, ronda.getPuntosRondaJ4());
	    values.put(COLUMNA_RONDAS_PUNTOSTOTALESJ1, ronda.getPuntosTotalesJ1());
	    values.put(COLUMNA_RONDAS_PUNTOSTOTALESJ2, ronda.getPuntosTotalesJ2());
	    values.put(COLUMNA_RONDAS_PUNTOSTOTALESJ3, ronda.getPuntosTotalesJ3());
	    values.put(COLUMNA_RONDAS_PUNTOSTOTALESJ4, ronda.getPuntosTotalesJ4());
	    values.put(COLUMNA_RONDAS_CHOMBOJ1, ronda.getChomboJ1());
	    values.put(COLUMNA_RONDAS_CHOMBOJ2, ronda.getChomboJ2());
	    values.put(COLUMNA_RONDAS_CHOMBOJ3, ronda.getChomboJ3());
	    values.put(COLUMNA_RONDAS_CHOMBOJ4, ronda.getChomboJ4());
	    values.put(COLUMNA_RONDAS_PUNTOSJUGADA, ronda.getPuntosJugada());
	    values.put(COLUMNA_RONDAS_NOMBREGANADOR, ronda.getNombreGanador());
	    values.put(COLUMNA_RONDAS_NOMBREPERDEDOR, ronda.getNombrePerdedor());
	    db.insert(TABLA_RONDAS, null, values);
	    db.close();
	}
	public DBRonda obtenerRonda(int idPartida, int numeroRonda) {
		SQLiteDatabase db = this.getReadableDatabase();		 
		String selectQuery = "SELECT  * FROM " + TABLA_RONDAS + " WHERE "
	            + COLUMNA_RONDAS_IDPARTIDA + " = " + idPartida + " AND "
	            + COLUMNA_RONDAS_NUMERORONDA + " = " + numeroRonda;
		Cursor cursor = db.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	    	DBRonda ronda = new DBRonda();
	    	ronda.setIdPartida(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_IDPARTIDA)));
	    	ronda.setNumeroRonda(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_NUMERORONDA)));
	    	ronda.setPuntosRondaJ1(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSRONDAJ1)));
	    	ronda.setPuntosRondaJ2(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSRONDAJ2)));
	    	ronda.setPuntosRondaJ3(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSRONDAJ3)));
	    	ronda.setPuntosRondaJ4(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSRONDAJ4)));
	    	ronda.setPuntosTotalesJ1(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSTOTALESJ1)));
	    	ronda.setPuntosTotalesJ2(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSTOTALESJ2)));
	    	ronda.setPuntosTotalesJ3(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSTOTALESJ3)));
	    	ronda.setPuntosTotalesJ4(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSTOTALESJ4)));
	    	ronda.setChomboJ1(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_CHOMBOJ1)));
	    	ronda.setChomboJ2(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_CHOMBOJ2)));
	    	ronda.setChomboJ3(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_CHOMBOJ3)));
	    	ronda.setChomboJ4(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_CHOMBOJ4)));
	    	ronda.setPuntosJugada(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSJUGADA)));
	    	return ronda;
	    }
	    return null;
	}	
	public List<DBRonda> obtenerRondasPartida(int idPartida) {
		SQLiteDatabase db = this.getReadableDatabase();

        List<DBRonda> rondas = new ArrayList<DBRonda>(16);
		String selectQuery = "SELECT * FROM " + TABLA_RONDAS + " WHERE "
	            + COLUMNA_RONDAS_IDPARTIDA + " = " + idPartida;
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
	        do {
		    	DBRonda ronda = new DBRonda();
		    	ronda.setIdPartida(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_IDPARTIDA)));
		    	ronda.setNumeroRonda(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_NUMERORONDA)));
		    	ronda.setPuntosRondaJ1(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSRONDAJ1)));
		    	ronda.setPuntosRondaJ2(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSRONDAJ2)));
		    	ronda.setPuntosRondaJ3(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSRONDAJ3)));
		    	ronda.setPuntosRondaJ4(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSRONDAJ4)));
		    	ronda.setPuntosTotalesJ1(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSTOTALESJ1)));
		    	ronda.setPuntosTotalesJ2(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSTOTALESJ2)));
		    	ronda.setPuntosTotalesJ3(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSTOTALESJ3)));
		    	ronda.setPuntosTotalesJ4(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSTOTALESJ4)));
		    	ronda.setChomboJ1(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_CHOMBOJ1)));
		    	ronda.setChomboJ2(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_CHOMBOJ2)));
		    	ronda.setChomboJ3(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_CHOMBOJ3)));
		    	ronda.setChomboJ4(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_CHOMBOJ4)));
		    	ronda.setPuntosJugada(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSJUGADA)));
		    	ronda.setNombreGanador(cursor.getString(cursor.getColumnIndex(COLUMNA_RONDAS_NOMBREGANADOR)));
		    	ronda.setNombrePerdedor(cursor.getString(cursor.getColumnIndex(COLUMNA_RONDAS_NOMBREPERDEDOR)));
	            rondas.add(ronda);
	        } while (cursor.moveToNext());
		    return rondas;
		}
		return null;
	}
	public List<DBRonda> obtenerTodasRondas() {
	    SQLiteDatabase db = this.getReadableDatabase();
		List<DBRonda> rondas = new ArrayList<DBRonda>(16);
	    String selectQuery = "SELECT * FROM " + TABLA_RONDAS;
	    Cursor cursor = db.rawQuery(selectQuery, null);	 
	    if (cursor.moveToFirst()) {
	        do {
		    	DBRonda ronda = new DBRonda();
		    	ronda.setIdPartida(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_IDPARTIDA)));
		    	ronda.setNumeroRonda(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_NUMERORONDA)));
		    	ronda.setPuntosRondaJ1(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSRONDAJ1)));
		    	ronda.setPuntosRondaJ2(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSRONDAJ2)));
		    	ronda.setPuntosRondaJ3(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSRONDAJ3)));
		    	ronda.setPuntosRondaJ4(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSRONDAJ4)));
		    	ronda.setPuntosTotalesJ1(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSTOTALESJ1)));
		    	ronda.setPuntosTotalesJ2(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSTOTALESJ2)));
		    	ronda.setPuntosTotalesJ3(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSTOTALESJ3)));
		    	ronda.setPuntosTotalesJ4(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSTOTALESJ4)));
		    	ronda.setChomboJ1(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_CHOMBOJ1)));
		    	ronda.setChomboJ2(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_CHOMBOJ2)));
		    	ronda.setChomboJ3(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_CHOMBOJ3)));
		    	ronda.setChomboJ4(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_CHOMBOJ4)));
		    	ronda.setPuntosJugada(cursor.getInt(cursor.getColumnIndex(COLUMNA_RONDAS_PUNTOSJUGADA)));
		    	ronda.setNombreGanador(cursor.getString(cursor.getColumnIndex(COLUMNA_RONDAS_NOMBREGANADOR)));
		    	ronda.setNombrePerdedor(cursor.getString(cursor.getColumnIndex(COLUMNA_RONDAS_NOMBREPERDEDOR)));
		    	rondas.add(ronda);
	        } while (cursor.moveToNext());
		    return rondas;
	    }
	    return null;
	}
	public int contarRondas() {
		String countQuery = "SELECT * FROM " + TABLA_RONDAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        int num = cursor.getCount();
        return num;
	}
	public int actualizarRonda(DBRonda ronda) {
		SQLiteDatabase db = this.getWritableDatabase();		 
	    ContentValues values = new ContentValues();
	    values.put(COLUMNA_RONDAS_IDPARTIDA, ronda.getIdPartida());
	    values.put(COLUMNA_RONDAS_NUMERORONDA, ronda.getNumeroRonda());
	    values.put(COLUMNA_RONDAS_PUNTOSRONDAJ1, ronda.getPuntosRondaJ1());
	    values.put(COLUMNA_RONDAS_PUNTOSRONDAJ2, ronda.getPuntosRondaJ2());
	    values.put(COLUMNA_RONDAS_PUNTOSRONDAJ3, ronda.getPuntosRondaJ3());
	    values.put(COLUMNA_RONDAS_PUNTOSRONDAJ4, ronda.getPuntosRondaJ4());
	    values.put(COLUMNA_RONDAS_PUNTOSTOTALESJ1, ronda.getPuntosTotalesJ1());
	    values.put(COLUMNA_RONDAS_PUNTOSTOTALESJ2, ronda.getPuntosTotalesJ2());
	    values.put(COLUMNA_RONDAS_PUNTOSTOTALESJ3, ronda.getPuntosTotalesJ3());
	    values.put(COLUMNA_RONDAS_PUNTOSTOTALESJ4, ronda.getPuntosTotalesJ4());
	    values.put(COLUMNA_RONDAS_CHOMBOJ1, ronda.getChomboJ1());
	    values.put(COLUMNA_RONDAS_CHOMBOJ2, ronda.getChomboJ2());
	    values.put(COLUMNA_RONDAS_CHOMBOJ3, ronda.getChomboJ3());
	    values.put(COLUMNA_RONDAS_CHOMBOJ4, ronda.getChomboJ4());
	    values.put(COLUMNA_RONDAS_PUNTOSJUGADA, ronda.getPuntosJugada());
	    values.put(COLUMNA_RONDAS_NOMBREGANADOR, ronda.getNombreGanador());
	    values.put(COLUMNA_RONDAS_NOMBREPERDEDOR, ronda.getNombrePerdedor());
	    return db.update(TABLA_RONDAS, values, COLUMNA_RONDAS_IDPARTIDA + " = ? AND " + COLUMNA_RONDAS_NUMERORONDA + " = ?", 
	    		new String[] { String.valueOf(ronda.getIdPartida()), String.valueOf(ronda.getNumeroRonda()) });
	}
	public void eliminarRonda(DBRonda ronda) {
		SQLiteDatabase db = this.getWritableDatabase();	
		db.delete(TABLA_RONDAS, COLUMNA_RONDAS_IDPARTIDA + " = ? AND " + COLUMNA_RONDAS_NUMERORONDA + " = ?",
	    		new String[] { String.valueOf(ronda.getIdPartida()), String.valueOf(ronda.getNumeroRonda()) });
		db.close();
	}
    public void eliminarRondas(int idPartida, SQLiteDatabase db) {
        db.delete(TABLA_RONDAS, COLUMNA_RONDAS_IDPARTIDA + " = ?", new String[] { String.valueOf(idPartida) });
    }

	//endregion

	//endregion
}
