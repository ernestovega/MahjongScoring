package com.mahjongscoring.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mahjongscoring.activities.ActivityMesa;
import com.mahjongscoring.activities.R;

public class FragmentMesaJugadas extends Fragment {

	//VARIABLES
    private ActivityMesa padre;
	private ViewGroup rootView;
	
	//CONSTRUCTORES
	public static FragmentMesaJugadas crearFragment() {
		FragmentMesaJugadas fragment = new FragmentMesaJugadas();
        return fragment;
    }
	public FragmentMesaJugadas() {}
	
	//CICLO DE VIDA
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mesa_jugadas, container, false);
        padre = (ActivityMesa)getActivity();

        LinearLayout linearLayoutJugadas = (LinearLayout) rootView.findViewById(R.id.linearlayout_lista_jugadas);

        for (int i = 0; i < linearLayoutJugadas.getChildCount(); i++) {
            LinearLayout linearLayoutJugada = (LinearLayout)linearLayoutJugadas.getChildAt(i);
            ((TextView) linearLayoutJugada.getChildAt(0)).setTypeface(padre.typefaceKorean);
            View view = linearLayoutJugada.getChildAt(1);
            if(view != null && view instanceof TextView)
                ((TextView) view).setTypeface(padre.typefaceKorean);
        }

        //((TextView)rootView.findViewById(R.id.textview_jugada_separador_1_punto)).setTypeface(padre.typefaceKorean);

        return rootView;
    }
}
