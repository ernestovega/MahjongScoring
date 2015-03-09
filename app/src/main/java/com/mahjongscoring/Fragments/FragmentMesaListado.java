package com.mahjongscoring.Fragments;

import com.mahjongscoring.activities.R;
import com.mahjongscoring.activities.ActivityMesa;
import com.mahjongscoring.DBClasses.DBRonda;
import com.mahjongscoring.AppClasses.Enums.TiposJuego;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;

public class FragmentMesaListado extends Fragment {

    private ActivityMesa padre;
	private ViewGroup rootView;
	private int itemCount;
    private boolean isBorrarPulsadoUnaVez;
    private boolean yaHayMejorJugadaMarcada;

	//CONSTRUCTORES
	public static FragmentMesaListado crearFragment() {
        return new FragmentMesaListado();
    }
	public FragmentMesaListado() {}
	
	//CICLO DE VIDA
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mesa_listado, container, false);
        padre = (ActivityMesa)getActivity();
        itemCount = 0;
        yaHayMejorJugadaMarcada = false;

		agregarListItemTitulos();
        actualizarLista();
        
		return rootView;
    }

	public void borrarLista() {
        ((ViewGroup)rootView.findViewById(R.id.linearlayout_lista_listado)).removeAllViews();
    }

	public void actualizarLista() {
        ViewGroup linearLayoutLista = (ViewGroup)rootView.findViewById(R.id.linearlayout_lista_listado);
        if(padre.rondas.size() > 1 || (padre.isReanudacion && padre.rondas.size() == 1 && padre.partida.getNumeroRondas() == 1) ) {
            borrarLista();
            isBorrarPulsadoUnaVez = false;
			itemCount = 0;
            if (padre.isReanudacion && padre.rondas.size() == 1 && padre.partida.getNumeroRondas() == 1) {
                DBRonda ronda = padre.rondas.get(0);
                agregarListItemPuntosRonda(ronda);
                agregarListItemPuntosTotales(ronda);
                rootView.findViewById(R.id.linearlayout_borrar_ronda).setVisibility(View.VISIBLE);
                rootView.findViewById(R.id.imagebutton_cancelar_borrar_ronda).setVisibility(View.GONE);
                agregarEventoBorrarUltimaRonda();
            }
            else {
                int numVueltas = padre.rondas.size() - 1;
                if (padre.isReanudacion) {
                    if (padre.rondas.size() == padre.partida.getNumeroRondas())
                        numVueltas--;
                }
                for (int i = 0; i < numVueltas; i++) {
                    DBRonda ronda = padre.rondas.get(i);
                    agregarListItemPuntosRonda(ronda);
                    agregarListItemPuntosTotales(ronda);
                    rootView.findViewById(R.id.linearlayout_borrar_ronda).setVisibility(View.VISIBLE);
                    rootView.findViewById(R.id.imagebutton_cancelar_borrar_ronda).setVisibility(View.GONE);
                    agregarEventoBorrarUltimaRonda();
                }
            }
            if(padre.rondas.size() == 16 && padre.partida.getNumeroRondas() == 16) {
                DBRonda ronda = padre.rondas.get(15);
                agregarListItemPuntosRonda(ronda);
                agregarListItemPuntosTotales(ronda);
                rootView.findViewById(R.id.linearlayout_borrar_ronda).setVisibility(View.VISIBLE);
                rootView.findViewById(R.id.imagebutton_cancelar_borrar_ronda).setVisibility(View.GONE);
                agregarEventoBorrarUltimaRonda();
            }
		}
        else
            rootView.findViewById(R.id.linearlayout_borrar_ronda).setVisibility(View.GONE);
	}

    //CREACION DE ITEMS
	private void agregarListItemTitulos() {
        ViewGroup linearLayoutTitulo = (ViewGroup) rootView.findViewById(R.id.linearlayout_titulo_listado);
        final ViewGroup listItem = (ViewGroup)LayoutInflater.from(padre).inflate(
                R.layout.listitem_mesa_listado, linearLayoutTitulo, false);
        LinearLayout linearlayoutListitem = (LinearLayout)listItem.findViewById(R.id.linearlayout_listitem_listado);
        TextView textviewNumeroRonda = (TextView)listItem.findViewById(R.id.textview_listitem_numronda);
        TextView textviewJugador1 = (TextView)listItem.findViewById(R.id.textview_listitem_jugador1);
        TextView textviewJugador2 = (TextView)listItem.findViewById(R.id.textview_listitem_jugador2);
        TextView textviewJugador3 = (TextView)listItem.findViewById(R.id.textview_listitem_jugador3);
        TextView textviewJugador4 = (TextView)listItem.findViewById(R.id.textview_listitem_jugador4);
        TextView textviewPuntosJugada = (TextView)listItem.findViewById(R.id.textview_listitem_puntosJugada);

        linearlayoutListitem.setBackgroundColor(getResources().getColor(R.color.verde));
        textviewNumeroRonda.setTypeface(padre.typefaceKorean);
        textviewJugador1.setTypeface(padre.typefaceKorean);
        textviewJugador2.setTypeface(padre.typefaceKorean);
        textviewJugador3.setTypeface(padre.typefaceKorean);
        textviewJugador4.setTypeface(padre.typefaceKorean);
        textviewPuntosJugada.setTypeface(padre.typefaceKorean);
        textviewNumeroRonda.setTextColor(getResources().getColor(android.R.color.primary_text_dark));
        textviewJugador1.setTextColor(getResources().getColor(android.R.color.primary_text_dark));
        textviewJugador2.setTextColor(getResources().getColor(android.R.color.primary_text_dark));
        textviewJugador3.setTextColor(getResources().getColor(android.R.color.primary_text_dark));
        textviewJugador4.setTextColor(getResources().getColor(android.R.color.primary_text_dark));
        textviewPuntosJugada.setTextColor(getResources().getColor(android.R.color.primary_text_dark));        
        textviewNumeroRonda.setGravity(Gravity.CENTER);
        textviewJugador1.setGravity(Gravity.CENTER);
        textviewJugador2.setGravity(Gravity.CENTER);
        textviewJugador3.setGravity(Gravity.CENTER);
        textviewJugador4.setGravity(Gravity.CENTER);
        textviewPuntosJugada.setGravity(Gravity.CENTER);
        //textviewNumeroRonda.setIncludeFontPadding(false);
        textviewNumeroRonda.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        textviewPuntosJugada.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textviewNumeroRonda.setText(getString(R.string.lista_titulo_numronda));
        textviewJugador1.setText(padre.partida.getNombreJ1());
        textviewJugador2.setText(padre.partida.getNombreJ2());
        textviewJugador3.setText(padre.partida.getNombreJ3());
        textviewJugador4.setText(padre.partida.getNombreJ4());
        textviewPuntosJugada.setText(getString(R.string.lista_titulo_puntosjugada));        
        linearLayoutTitulo.addView(listItem);
    }
	private void agregarListItemPuntosRonda(DBRonda ronda) {
        ViewGroup linearLayoutLista = (ViewGroup)rootView.findViewById(R.id.linearlayout_lista_listado);
        final ViewGroup listItem = (ViewGroup)LayoutInflater.from(padre).inflate(
                R.layout.listitem_mesa_listado, linearLayoutLista, false);        
        LinearLayout linearlayoutListitem = (LinearLayout)listItem.findViewById(R.id.linearlayout_listitem_listado);
        TextView textviewNumeroRonda = (TextView)listItem.findViewById(R.id.textview_listitem_numronda);
        TextView textviewJugador1 = (TextView)listItem.findViewById(R.id.textview_listitem_jugador1);
        TextView textviewJugador2 = (TextView)listItem.findViewById(R.id.textview_listitem_jugador2);
        TextView textviewJugador3 = (TextView)listItem.findViewById(R.id.textview_listitem_jugador3);
        TextView textviewJugador4 = (TextView)listItem.findViewById(R.id.textview_listitem_jugador4);
        TextView textviewPuntosJugada = (TextView)listItem.findViewById(R.id.textview_listitem_puntosJugada);
        
        linearlayoutListitem.setBackgroundColor(getResources().getColor(R.color.grisclaro));
        textviewNumeroRonda.setTypeface(padre.typefaceKorean);
        textviewJugador1.setTypeface(padre.typefaceKorean);
        textviewJugador2.setTypeface(padre.typefaceKorean);
        textviewJugador3.setTypeface(padre.typefaceKorean);
        textviewJugador4.setTypeface(padre.typefaceKorean);
        textviewPuntosJugada.setTypeface(padre.typefaceKorean);
        textviewNumeroRonda.setGravity(Gravity.CENTER);
        textviewJugador1.setGravity(Gravity.LEFT);
        textviewJugador2.setGravity(Gravity.LEFT);
        textviewJugador3.setGravity(Gravity.LEFT);
        textviewJugador4.setGravity(Gravity.LEFT);
        textviewPuntosJugada.setGravity(Gravity.CENTER);
        textviewNumeroRonda.setText(String.valueOf(ronda.getNumeroRonda()));
        textviewJugador1.setText(ronda.getPuntosRondaJ1() > 0 ? "+" + String.valueOf(ronda.getPuntosRondaJ1()) : String.valueOf(ronda.getPuntosRondaJ1()));
        textviewJugador2.setText(ronda.getPuntosRondaJ2() > 0 ? "+" + String.valueOf(ronda.getPuntosRondaJ2()) : String.valueOf(ronda.getPuntosRondaJ2()));
        textviewJugador3.setText(ronda.getPuntosRondaJ3() > 0 ? "+" + String.valueOf(ronda.getPuntosRondaJ3()) : String.valueOf(ronda.getPuntosRondaJ3()));
        textviewJugador4.setText(ronda.getPuntosRondaJ4() > 0 ? "+" + String.valueOf(ronda.getPuntosRondaJ4()) : String.valueOf(ronda.getPuntosRondaJ4()));
        //chomberos
        if (ronda.getChomboJ1() > 0)
        	textviewJugador1.setTextColor(getResources().getColor(R.color.morado));
        if (ronda.getChomboJ2() > 0)
        	textviewJugador2.setTextColor(getResources().getColor(R.color.morado));
        if (ronda.getChomboJ3() > 0)
        	textviewJugador3.setTextColor(getResources().getColor(R.color.morado));
        if (ronda.getChomboJ4() > 0)
        	textviewJugador4.setTextColor(getResources().getColor(R.color.morado));
        //ganador y/o perdedor
        if (padre.partida.getNombreJ1().equals(ronda.getNombreGanador()))
        	textviewJugador1.setTextColor(getResources().getColor(R.color.verde));
        else if (padre.partida.getNombreJ1().equals(ronda.getNombrePerdedor())) {
        	if(ronda.getChomboJ1() > 0)
        		textviewJugador1.setTextColor(getResources().getColor(R.color.marron));
        	else
            	textviewJugador1.setTextColor(getResources().getColor(R.color.rojo));
        }
        if (padre.partida.getNombreJ2().equals(ronda.getNombreGanador()))
        	textviewJugador2.setTextColor(getResources().getColor(R.color.verde));
        else if (padre.partida.getNombreJ2().equals(ronda.getNombrePerdedor())) {
        	if(ronda.getChomboJ2() > 0)
        		textviewJugador2.setTextColor(getResources().getColor(R.color.marron));
        	else
            	textviewJugador2.setTextColor(getResources().getColor(R.color.rojo));
        }
        if (padre.partida.getNombreJ3().equals(ronda.getNombreGanador()))
        	textviewJugador3.setTextColor(getResources().getColor(R.color.verde));
        else if (padre.partida.getNombreJ3().equals(ronda.getNombrePerdedor())) {
        	if(ronda.getChomboJ3() > 0)
        		textviewJugador3.setTextColor(getResources().getColor(R.color.marron));
        	else
            	textviewJugador3.setTextColor(getResources().getColor(R.color.rojo));
        }
        if (padre.partida.getNombreJ4().equals(ronda.getNombreGanador()))
        	textviewJugador4.setTextColor(getResources().getColor(R.color.verde));
        else if (padre.partida.getNombreJ4().equals(ronda.getNombrePerdedor())) {
        	if(ronda.getChomboJ4() > 0)
        		textviewJugador4.setTextColor(getResources().getColor(R.color.marron));
        	else
            	textviewJugador4.setTextColor(getResources().getColor(R.color.rojo));
        }
        textviewPuntosJugada.setText(String.valueOf(ronda.getPuntosJugada()));
        	textviewNumeroRonda.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        if (padre.partida.getPuntosMejorJugada() > 0 &&
                padre.partida.getPuntosMejorJugada() == ronda.getPuntosJugada() &&
                padre.partida.getNombreJugadorMejorJugada().equals(ronda.getNombreGanador())) {
            yaHayMejorJugadaMarcada = true;
            textviewPuntosJugada.setTextColor(getResources().getColor(R.color.naranja));
        }
        if(padre.partida.getTipoJuego() == TiposJuego.RICHII) {
        	textviewJugador1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
	        textviewJugador2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
	        textviewJugador3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
	        textviewJugador4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
	        textviewPuntosJugada.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }
        linearLayoutLista.addView(listItem, itemCount);
        itemCount++;
    }
	private void agregarListItemPuntosTotales(DBRonda ronda) {
        ViewGroup linearLayoutLista = (ViewGroup)rootView.findViewById(R.id.linearlayout_lista_listado);
        final ViewGroup listItem = (ViewGroup)LayoutInflater.from(padre).inflate(
                R.layout.listitem_mesa_listado, linearLayoutLista, false);        
        LinearLayout linearlayoutListitem = (LinearLayout)listItem.findViewById(R.id.linearlayout_listitem_listado);
        TextView textviewJugador1 = (TextView)listItem.findViewById(R.id.textview_listitem_jugador1);
        TextView textviewJugador2 = (TextView)listItem.findViewById(R.id.textview_listitem_jugador2);
        TextView textviewJugador3 = (TextView)listItem.findViewById(R.id.textview_listitem_jugador3);
        TextView textviewJugador4 = (TextView)listItem.findViewById(R.id.textview_listitem_jugador4);
        linearlayoutListitem.setBackgroundColor(getResources().getColor(R.color.grismedio));
        textviewJugador1.setTypeface(padre.typefaceKorean);
        textviewJugador2.setTypeface(padre.typefaceKorean);
        textviewJugador3.setTypeface(padre.typefaceKorean);
        textviewJugador4.setTypeface(padre.typefaceKorean);
        textviewJugador1.setGravity(Gravity.RIGHT);
        textviewJugador2.setGravity(Gravity.RIGHT);
        textviewJugador3.setGravity(Gravity.RIGHT);
        textviewJugador4.setGravity(Gravity.RIGHT);
        textviewJugador1.setText(ronda.getPuntosTotalesJ1() > 0 ? "+" + String.valueOf(ronda.getPuntosTotalesJ1()) : String.valueOf(ronda.getPuntosTotalesJ1()));
        textviewJugador2.setText(ronda.getPuntosTotalesJ2() > 0 ? "+" + String.valueOf(ronda.getPuntosTotalesJ2()) : String.valueOf(ronda.getPuntosTotalesJ2()));
        textviewJugador3.setText(ronda.getPuntosTotalesJ3() > 0 ? "+" + String.valueOf(ronda.getPuntosTotalesJ3()) : String.valueOf(ronda.getPuntosTotalesJ3()));
        textviewJugador4.setText(ronda.getPuntosTotalesJ4() > 0 ? "+" + String.valueOf(ronda.getPuntosTotalesJ4()) : String.valueOf(ronda.getPuntosTotalesJ4()));
        if(padre.partida.getTipoJuego() == TiposJuego.RICHII) {
        	textviewJugador1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
	        textviewJugador2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
	        textviewJugador3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
	        textviewJugador4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }
        linearLayoutLista.addView(listItem, itemCount);
        itemCount++;
    }
    private void agregarEventoBorrarUltimaRonda() {
        ImageButton imagebuttonBorrarRonda = (ImageButton) rootView.findViewById(R.id.imagebutton_borrar_ronda);
        if(padre.isFinalOn)
            imagebuttonBorrarRonda.setVisibility(View.GONE);
        else {
            imagebuttonBorrarRonda.setVisibility(View.VISIBLE);
            imagebuttonBorrarRonda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewGroup linearLayoutLista = (ViewGroup) rootView.findViewById(R.id.linearlayout_lista_listado);
                    final ViewGroup listItemRonda = (ViewGroup) linearLayoutLista.getChildAt(linearLayoutLista.getChildCount() - 2);
                    final ViewGroup listItemTotales = (ViewGroup) linearLayoutLista.getChildAt(linearLayoutLista.getChildCount() - 1);
                    final ImageButton imagebuttonCancelarBorrarRonda = (ImageButton) rootView.findViewById(R.id.imagebutton_cancelar_borrar_ronda);
                    if (!isBorrarPulsadoUnaVez) {
                        isBorrarPulsadoUnaVez = true;
                        listItemRonda.findViewById(R.id.linearlayout_listitem_listado).setBackgroundColor(getResources().getColor(R.color.rojo));
                        listItemTotales.findViewById(R.id.linearlayout_listitem_listado).setBackgroundColor(getResources().getColor(R.color.rojo));
                        imagebuttonCancelarBorrarRonda.setVisibility(View.VISIBLE);
                        imagebuttonCancelarBorrarRonda.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                isBorrarPulsadoUnaVez = false;
                                listItemRonda.findViewById(R.id.linearlayout_listitem_listado).setBackgroundColor(getResources().getColor(R.color.grisclaro));
                                listItemTotales.findViewById(R.id.linearlayout_listitem_listado).setBackgroundColor(getResources().getColor(R.color.grismedio));
                                imagebuttonCancelarBorrarRonda.setVisibility(View.GONE);
                            }
                        });
                        Toast.makeText(padre, padre.getString(R.string.toast_borrar_ultima_ronda), Toast.LENGTH_SHORT).show();
                    } else
                        padre.borrarUltimaRondaMcr();
                }
            });
        }
    }

}
