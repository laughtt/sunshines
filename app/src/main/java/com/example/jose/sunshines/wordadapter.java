package com.example.jose.sunshines;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jose on 12/25/2016.
 */

public class wordadapter extends ArrayAdapter<ListObject> {


    public wordadapter(Activity context, ArrayList<ListObject> contenido) {

        super(context, 0, contenido);


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.formato_listview, parent, false);
        }


        final ListObject currentword = getItem(position);

        ImageView imagen = (ImageView) listItemView.findViewById(R.id.imagen) ;
        TextView txtday = (TextView) listItemView.findViewById(R.id.Txtday) ;
        TextView txttime = (TextView) listItemView.findViewById(R.id.TxtTime) ;
        TextView Grados = (TextView) listItemView.findViewById(R.id.GradosCentigrado) ;
        TextView Numero = (TextView) listItemView.findViewById(R.id.version_number) ;
        Numero.setText(currentword.getGradosmax());
        imagen.setImageResource(currentword.getImagen());
        txtday.setText(currentword.getDay());
        txttime.setText(currentword.getTime());
        Grados.setText(currentword.getGradosmax());


        return listItemView;
    }



}

