package com.example.ejercico3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ejercico3_2.Models.Empleados;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActivityListEmple extends AppCompatActivity {

    ListView lista;
    ArrayList<Empleados> listaEmpleado;
    ArrayList <String> ArregloPersona;
    DatabaseReference BaseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_emple);

        BaseDatos = FirebaseDatabase.getInstance().getReference();

        lista = (ListView) findViewById(R.id.listaEmpleados);


        obtenerlistaEmpleado();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_checked,ArregloPersona);
        lista.setAdapter(adp);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ObtenerEmpleado(i);

            }
        });
    }

    private void ObtenerEmpleado(int id) {
        Empleados empleado = listaEmpleado.get(id);

        Intent intent = new Intent(getApplicationContext(),ActivityModiEmple.class);

        intent.putExtra("codigo", empleado.getId()+"");
        intent.putExtra("nombre", empleado.getNombre());
        intent.putExtra("apellidos", empleado.getApellidos());
        intent.putExtra("edad", empleado.getEdad());
        intent.putExtra("puesto", empleado.getPuesto());
        intent.putExtra("direccion", empleado.getDireccion());

        startActivity(intent);
        finish();
    }


    private void obtenerlistaEmpleado() {


        Empleados lista_empleado = null;

        listaEmpleado = new ArrayList<Empleados>();

        llenarlista();
    }
    private void llenarlista()
    {
        ArregloPersona = new ArrayList<String>();

        for (int i = 0; i< listaEmpleado.size(); i++)
        {
            ArregloPersona.add(listaEmpleado.get(i).getId()+" "+
                    listaEmpleado.get(i).getNombre()+" "+
                    listaEmpleado.get(i).getApellidos()+" | "+
                    listaEmpleado.get(i).getEdad()+" | "+
                    listaEmpleado.get(i).getDireccion()+" | "+
                    listaEmpleado.get(i).getPuesto());

        }
    }
}