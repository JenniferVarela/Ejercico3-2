package com.example.ejercico3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ActivityModiEmple extends AppCompatActivity {


    EditText nombres, apellidos, edad, puesto, direccion, codigo;
    Button btnActualizar, btnEliminar, btnAtras;
    Context context = this;

    DatabaseReference BaseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modi_emple);

        BaseDatos = FirebaseDatabase.getInstance().getReference();

        codigo = (EditText) findViewById(R.id.mtxtid);
        nombres = (EditText) findViewById(R.id.mtxtNombre);
        apellidos = (EditText) findViewById(R.id.mtxtApellidos);
        edad = (EditText) findViewById(R.id.mtxtEdad);
        puesto = (EditText) findViewById(R.id.mtxtpuesto);
        direccion = (EditText) findViewById(R.id.mtxtDireccion);

        btnActualizar = (Button) findViewById(R.id.mbtnActualizar);
        btnEliminar = (Button) findViewById(R.id.mbtnEliminar);
        btnAtras = (Button) findViewById(R.id.mbtnAtras);

        codigo.setText(getIntent().getStringExtra("codigo"));
        nombres.setText(getIntent().getStringExtra("nombre"));
        apellidos.setText(getIntent().getStringExtra("apellidos"));
        edad.setText(getIntent().getStringExtra("edad"));
        puesto.setText(getIntent().getStringExtra("puesto"));
        direccion.setText(getIntent().getStringExtra("direccion"));

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(getApplicationContext(),ActivityListEmple.class);
                startActivity(inte);
                finish();
            }
        });


        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                actualizarEmpleado();


            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Eliminar");
                alertDialogBuilder
                        .setMessage("¿Desea eliminar a "+nombres.getText()+" "+apellidos.getText()+" ?")
                        .setCancelable(false)
                        .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                eliminarEmpleado();
                                startActivity(new Intent(getApplicationContext(), ActivityListEmple.class));
                                finish();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });


                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void actualizarEmpleado() {
        String obtenerCodigo = codigo.getText().toString();

        Map<String, String> map = new HashMap();

        map.put("nombres", nombres.getText().toString());
        map.put("apellidos", apellidos.getText().toString());
        map.put("edad", edad.getText().toString());
        map.put("puesto", puesto.getText().toString());
        map.put("direccion", direccion.getText().toString());


        Toast.makeText(getApplicationContext(), "Registro actualizado con exito"
                ,Toast.LENGTH_LONG).show();

        startActivity(new Intent(getApplicationContext(), ActivityListEmple.class));
        finish();


    }

    private void eliminarEmpleado() {

        String obtenerCodigo = codigo.getText().toString();

        Toast.makeText(getApplicationContext(), "Registro eliminado con exito, Codigo " + obtenerCodigo
                ,Toast.LENGTH_LONG).show();

    }
}