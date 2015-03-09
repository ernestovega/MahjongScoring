package com.mahjongscoring.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mahjongscoring.AppClasses.CPartidaAntigua;
import com.mahjongscoring.activities.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterListViewPartidasAntiguas extends BaseAdapter {
    private ArrayList<CPartidaAntigua> partidas;
    private LayoutInflater inflater = null;
    private Context context;
    Typeface typefaceKorean;

    public static class ViewHolder {
        TextView id;
        TextView fechaInicio;
        TextView numeroRondas;
        TextView mejorMano;
        TextView jugador1;
        TextView jugador2;
        TextView jugador3;
        TextView jugador4;
    }

    public AdapterListViewPartidasAntiguas(Context contexto, ArrayList<CPartidaAntigua> array) {
        this.partidas = array;
        this.context = contexto;
        typefaceKorean = Typeface.createFromAsset(context.getAssets(), "fonts/dekiru.ttf");
        inflater = LayoutInflater.from(contexto);
    }

    @Override
    public int getCount() {
        return partidas.size();
    }

    @Override
    public Object getItem(int position) {
        return partidas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listitem_partida_antigua, null);
            holder = new ViewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.textview_id_partida_antigua);
            holder.fechaInicio = (TextView) convertView.findViewById(R.id.textview_fecha_inicio_partida_antigua);
            holder.numeroRondas = (TextView) convertView.findViewById(R.id.textview_numero_rondas_partida_antigua);
            holder.mejorMano = (TextView) convertView.findViewById(R.id.textview_mejor_mano_partida_antigua);
            holder.jugador1 = (TextView) convertView.findViewById(R.id.textview_jugador1_partida_antigua);
            holder.jugador2 = (TextView) convertView.findViewById(R.id.textview_jugador2_partida_antigua);
            holder.jugador3 = (TextView) convertView.findViewById(R.id.textview_jugador3_partida_antigua);
            holder.jugador4 = (TextView) convertView.findViewById(R.id.textview_jugador4_partida_antigua);
            holder.fechaInicio.setTypeface(typefaceKorean);
            holder.numeroRondas.setTypeface(typefaceKorean);
            holder.mejorMano.setTypeface(typefaceKorean);
            holder.jugador1.setTypeface(typefaceKorean);
            holder.jugador2.setTypeface(typefaceKorean);
            holder.jugador3.setTypeface(typefaceKorean);
            holder.jugador4.setTypeface(typefaceKorean);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        CPartidaAntigua partida = (CPartidaAntigua) getItem(position);
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy   HH:mm", new Locale("es_ES"));
        holder.id.setText(String.valueOf(partida.getId()));
        holder.fechaInicio.setText(formateador.format(partida.getFechaInicio()));
        holder.numeroRondas.setText("Rounds" + "\n" + String.valueOf(partida.getNumeroRondas()));
        holder.mejorMano.setText("Best" + "\n" + partida.getNombreMejorMano() + " " + String.valueOf(partida.getPuntosMejorMano()));
        holder.jugador1.setText(partida.getNombreJ1() + " " + partida.getPuntosJ1());
        holder.jugador2.setText(partida.getNombreJ2() + " " + partida.getPuntosJ2());
        holder.jugador3.setText(partida.getNombreJ3() + " " + partida.getPuntosJ3());
        holder.jugador4.setText(partida.getNombreJ4() + " " + partida.getPuntosJ4());

        return convertView;
    }

}
