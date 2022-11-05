package com.example.appdemobd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            bd.insertarUsuario(nombre, edad, numero);
            Toast.makeText(getApplicationContext(), "Usuario insertado correctamete", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
            System.out.println("Error: " + e.toString());
        }
    }

}