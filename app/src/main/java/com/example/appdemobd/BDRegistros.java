package com.example.appdemobd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BDRegistros extends SQLiteOpenHelper {
    private Context contexto;

    public static final int VERSION_BD = 1;
    public static final String NOMBRE_BD = "DBRegistros.db";

    // Sentencia SQL para crear la tabla de Registros
    private final String SQLCREATE = "CREATE TABLE Registros (nombre TEXT, edad INTEGER, numero INTEGER)";
    // Sentencia SQL para eliminar la tabla de Registros
    private final String SQLDROP = "DROP TABLE IF EXISTS Registros";
    // Base de datos
    private SQLiteDatabase bd = null;

    public BDRegistros(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLCREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQLDROP);
        sqLiteDatabase.execSQL(SQLCREATE);
    }

    private void cerrarBD() {
        if( bd != null )
            bd.close();
    }

    public void insertarRegistroBD(String nombre, int edad, int numero) throws Exception
    {
        // Obtengo los datos en modos de escritura
        // NOTA: bd es un objeto de tipo SQLiteDatabase
        bd = getWritableDatabase();
        // Si hemos abierto correctamente la base de datos
        if(bd != null)
        {
            long newRowId;
            try
            {
                // Creo un ContentValues con los valores a insertar
                ContentValues values = new ContentValues();
                values.put("nombre", nombre);
                values.put("edad", edad);
                values.put("numero", numero);
                // Inserta la nueva fila, devolviendo el valor de la clave
                // primaria de la nueva fila
                newRowId = bd.insert("Registros", "", values);

                cerrarBD();
            }
            catch (Exception e)
            {
                throw new Exception(e.toString());
            }
        }
    }

    public ArrayList<Registro> consultarRegistros() throws Exception
    {
        ArrayList<Registro> registros = new ArrayList<>();
        // Obtengo los datos en modo de lectura
        bd = getReadableDatabase();
        // Si hemos abierto correctamente la base de datos
        try
        {
            // Indico como quiero que se ordenen los resultados
            String sortOrder = "numero ASC";
            // Creo el cursor de la consulta
            Cursor c = bd.query
                    (
                            "Registros",   // Tabla para consultar
                            null,       // Columnas a devolver (con NULL las devuelve todas)
                            null,      // Columnas de la clausula WHERE
                            null,   // Valores de la columna de la clausula WHERE
                            null,       // Valores de la clausula GROUP BY
                            null,        // Valores de la clausula HAVING
                            sortOrder           // Orden de la clausula ORDER BY
                    );
            // Muestro los datos
            c.moveToFirst();
            if( c.getCount() > 0 )
            {
                do
                {

                    String nombre = c.getString(0);
                    int edad = c.getInt(1);
                    int numero = c.getInt(2);
                    registros.add(new Registro(nombre, edad, numero));
                } while (c.moveToNext());
            }

            cerrarBD();

            return registros;
        }
        catch (Exception e)
        {
            throw new Exception(e.toString());
        }
    }
}
