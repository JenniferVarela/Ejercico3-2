package com.example.ejercico3_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ActivityCreateEmple extends AppCompatActivity {

    EditText txtnombre, txtapellido, txtedad, txtdireccion, txtpuesto;
    Button btnGuardar,btnListar;

    DatabaseReference BaseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_emple);

        BaseDatos = FirebaseDatabase.getInstance().getReference();

        txtnombre = findViewById(R.id.txtNombre);
        txtapellido = findViewById(R.id.txtApellidos);
        txtedad = findViewById(R.id.txtEdad);
        txtdireccion = findViewById(R.id.txtDireccion);
        txtpuesto = findViewById(R.id.txtpuesto);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnListar = findViewById(R.id.btnListar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityListEmple.class);
                startActivity(intent);
            }
        });
    }

    private void validarCampos(){
        if (txtnombre.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Campo obligatorio ingrese un nombre"
                    ,Toast.LENGTH_LONG).show();
        }else if (txtapellido.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Campo obligatorio ingrese un apellido"
                    ,Toast.LENGTH_LONG).show();
        }else if (txtedad.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Campo obligatorio ingrese su edad"
                    ,Toast.LENGTH_LONG).show();
        }else if (txtdireccion.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Campo obligatorio ingrese su dirección"
                    ,Toast.LENGTH_LONG).show();
        }else if (txtpuesto.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Campo obligatorio ingrese su puesto de trabajo"
                    ,Toast.LENGTH_LONG).show();
        }else{
            agregarEmpleado();
        }
    }

    private void agregarEmpleado() {

        Map<String, String> map = new HashMap();


        map.put("nombres", txtnombre.getText().toString());
        map.put("apellidos", txtapellido.getText().toString());
        map.put("edad", txtedad.getText().toString());
        map.put("puesto", txtpuesto.getText().toString());
        map.put("direccion", txtdireccion.getText().toString());



        Toast.makeText(getApplicationContext(), "Registro ingreso con exito, Codigo ",Toast.LENGTH_LONG).show();


        limpiarPantalla();

    }
    private void limpiarPantalla(){
        txtnombre.setText("");
        txtapellido.setText("");
        txtedad.setText("");
        txtpuesto.setText("");
        txtdireccion.setText("");
    }
}