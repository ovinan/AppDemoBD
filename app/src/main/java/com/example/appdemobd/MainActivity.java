package com.example.appdemobd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BDRegistros bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = new BDRegistros(this);
    }

    public void insertaRegistro(View vista) {
        EditText et1 = (EditText) findViewById(R.id.edtNombre);
        EditText et2 = (EditText) findViewById(R.id.edtEdad);
        EditText et3 = (EditText) findViewById(R.id.edtNumero);
        String nombre = et1.getText().toString();
        int edad = Integer.valueOf(et2.getText().toString());
        int numero = Integer.valueOf(et3.getText().toString());
        try
        {
            bd.insertarRegistroBD(nombre, edad, numero);
            Toast.makeText(getApplicationContext(), "Registro insertado correctamente", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
            System.out.println("Error: " + e.toString());
        }
    }

    public void consultarRegistros(View vista) {
        RecyclerView listaRegistros = (RecyclerView) findViewById(R.id.listaRegistros);
        // Con esto el tamaño del recyclerwiew no cambiará
        listaRegistros.setHasFixedSize(true);
        // Creo un layoutmanager para el recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listaRegistros.setLayoutManager(llm);
        try
        {
            ArrayList<Registro> registrostotales = bd.consultarRegistros();

            AdaptadorRegistros adaptador = new AdaptadorRegistros(this, registrostotales);
            listaRegistros.setAdapter(adaptador);
            adaptador.refrescar();

            Toast.makeText(getApplicationContext(), "Consulta de registros realizada correctamente", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
            System.out.println("Error obteniendo los registros: " + e.toString());
        }
    }

}