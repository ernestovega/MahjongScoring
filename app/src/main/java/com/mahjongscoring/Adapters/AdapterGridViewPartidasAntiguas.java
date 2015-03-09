package com.mahjongscoring.Adapters;

import android.app.ActionBar;
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

public class AdapterGridViewPartidasAntiguas extends BaseAdapter {
    private Context contexto;
    private LayoutInflater inflater = null;
    private ArrayList<CPartidaAntigua> partidas;
    Typeface typefaceKorean;

    public static class ViewHolder {
        TextView id;
        TextView fechaInicio;
        TextView numeroRondas;
        TextView nombreJ1;
        TextView nombreJ2;
        TextView nombreJ3;
        TextView nombreJ4;
        TextView puntosJ1;
        TextView puntosJ2;
        TextView puntosJ3;
        TextView puntosJ4;
    }

    public AdapterGridViewPartidasAntiguas(Context context, ArrayList<CPartidaAntigua> partidasAntiguas){
        contexto = context;
        this.partidas = partidasAntiguas;
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
            convertView = inflater.inflate(R.layout.griditem_partidas_antiguas, null);
            holder = new ViewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.textview_id_partida_antigua);
            holder.fechaInicio = (TextView) convertView.findViewById(R.id.textview_fecha_partida_antigua);
            holder.numeroRondas = (TextView) convertView.findViewById(R.id.textview_numero_rondas_partida_antigua);
            holder.nombreJ1 = (TextView) convertView.findViewById(R.id.textview_nombre_jugador_este_partida_antigua);
            holder.nombreJ2 = (TextView) convertView.findViewById(R.id.textview_nombre_jugador_sur_partida_antigua);
            holder.nombreJ3 = (TextView) convertView.findViewById(R.id.textview_nombre_jugador_oeste_partida_antigua);
            holder.nombreJ4 = (TextView) convertView.findViewById(R.id.textview_nombre_jugador_norte_partida_antigua);
            holder.puntosJ1 = (TextView) convertView.findViewById(R.id.textview_puntos_jugador_este_partida_antigua);
            holder.puntosJ2 = (TextView) convertView.findViewById(R.id.textview_puntos_jugador_sur_partida_antigua);
            holder.puntosJ3 = (TextView) convertView.findViewById(R.id.textview_puntos_jugador_oeste_partida_antigua);
            holder.puntosJ4 = (TextView) convertView.findViewById(R.id.textview_puntos_jugador_norte_partida_antigua);
            holder.fechaInicio.setTypeface(typefaceKorean);
            holder.numeroRondas.setTypeface(typefaceKorean);
            holder.nombreJ1.setTypeface(typefaceKorean);
            holder.nombreJ2.setTypeface(typefaceKorean);
            holder.nombreJ3.setTypeface(typefaceKorean);
            holder.nombreJ4.setTypeface(typefaceKorean);
            holder.puntosJ1.setTypeface(typefaceKorean);
            holder.puntosJ2.setTypeface(typefaceKorean);
            holder.puntosJ3.setTypeface(typefaceKorean);
            holder.puntosJ4.setTypeface(typefaceKorean);

            convertView.setTag(holder);
        } else
            holder = (AdapterGridViewPartidasAntiguas.ViewHolder) convertView.getTag();

        CPartidaAntigua partida = (CPartidaAntigua) getItem(position);
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy   HH:mm", new Locale("es_ES"));
        holder.id.setText(String.valueOf(partida.getId()));
        holder.fechaInicio.setText(formateador.format(partida.getFechaInicio()));
        holder.numeroRondas.setText(String.valueOf(partida.getNumeroRondas()));
        holder.nombreJ1.setText(partida.getNombreJ1());
        holder.nombreJ2.setText(partida.getNombreJ2());
        holder.nombreJ3.setText(partida.getNombreJ3());
        holder.nombreJ4.setText(partida.getNombreJ4());
        holder.puntosJ1.setText(String.valueOf(partida.getPuntosJ1()));
        holder.puntosJ2.setText(String.valueOf(partida.getPuntosJ2()));
        holder.puntosJ3.setText(String.valueOf(partida.getPuntosJ3()));
        holder.puntosJ4.setText(String.valueOf(partida.getPuntosJ4()));
        return convertView;
    }
}
