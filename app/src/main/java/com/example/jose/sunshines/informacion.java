package com.example.jose.sunshines;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 12/26/2016.
 */

public class informacion extends AppCompatActivity{



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_layout);
        TextView dia = (TextView) findViewById(R.id.dia);


        Bundle b = this.getIntent().getExtras();
        ListObject objeto = (ListObject) b.getSerializable("bundle");
        dia.setText( objeto.getDay() );






    }
}