package com.mahjongscoring.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mahjongscoring.Adapters.AdapterGridViewPartidasAntiguas;
import com.mahjongscoring.Adapters.AdapterListViewPartidasAntiguas;
import com.mahjongscoring.AppClasses.CPartidaAntigua;
import com.mahjongscoring.DBClasses.DBManejador;

import java.util.ArrayList;

//import android.app.ActionBar;

public class ActivityExplorarPartidas extends Activity {

    private Typeface typefaceKorean;
    private DBManejador db;
    private ArrayList<CPartidaAntigua> partidas;
    private ListView listview_partidas_antiguas;
    private AdapterListViewPartidasAntiguas adapterListViewPartidasAntiguas;
    GridView gridview;

	//LIFECYCLE EVENTS
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_explorar_partidas);
        typefaceKorean = Typeface.createFromAsset(this.getAssets(), "fonts/dekiru.ttf");
        gridview = (GridView) findViewById(R.id.gridview_partidas_antiguas);
	}
    @Override
    protected void onResume(){
        super.onResume();
        CargarLista();
    }
	@Override
	protected void onRestoreInstanceState(Bundle inState) {
		super.onRestoreInstanceState(inState);
        CargarLista();
	}

    private void CargarLista(){
    	db = new DBManejador(this, DBManejador.DB_NOMBRE, DBManejador.DB_VERSION);
        partidas = db.obtenerTodasPartidasAntiguas();
        db.close();
        if(partidas == null)
            onBackPressed();
        else {
            /*listview_partidas_antiguas = (ListView)findViewById(android.R.id.list);
            adapterListViewPartidasAntiguas = new AdapterListViewPartidasAntiguas(ActivityExplorarPartidas.this, partidas);
            listview_partidas_antiguas.setAdapter(adapterListViewPartidasAntiguas);*/
            gridview.setAdapter(new AdapterGridViewPartidasAntiguas(this, partidas));
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Intent intent = new Intent();
                    intent.putExtra("es_reanudacion", true);
                    String idPartida = ((TextView) v.findViewById(R.id.textview_id_partida_antigua)).getText().toString();
                    intent.putExtra("id_nueva_partida", Integer.parseInt(idPartida));
                    intent.setClass(ActivityExplorarPartidas.this, ActivityMesa.class);
                    startActivity(intent);
                }
            });
            gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    String idPartida = ((TextView) view.findViewById(R.id.textview_id_partida_antigua)).getText().toString();
                    mostrarAlertDialogTerminar(Integer.parseInt(idPartida), position);
                    return true;
                }
            });
        }
    }

    public void mostrarAlertDialogTerminar(final int id, final int listPos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog);
        builder.setMessage(R.string.message_confirmacion_borrar_partida_antigua)
                .setOnCancelListener(null)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.eliminarPartida(id);
                        CargarLista();
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
}
