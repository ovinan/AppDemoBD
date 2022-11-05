package com.example.appdemobd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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

    public void insertarUsuario(String nombre, int edad, int numero) throws Exception
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

    private void cerrarBD() {
        if( bd != null )
            bd.close();
    }
}
