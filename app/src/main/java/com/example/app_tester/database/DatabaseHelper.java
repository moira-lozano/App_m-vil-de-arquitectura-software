package com.example.app_tester.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_emprendedor.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "email TEXT," +
                "password TEXT," +
                "celular TEXT)");

        db.execSQL("CREATE TABLE inventario (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "descripcion TEXT NOT NULL," +
                "unidad NCHAR NOT NULL," +
                "cantidad INTEGER NOT NULL," +
                "fecha DATETIME ," +
                "id_user INTEGER," +
                "FOREIGN KEY (id_user) REFERENCES usuario(id))");

        db.execSQL("CREATE TABLE categoria (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "estado TEXT)");

        db.execSQL("CREATE TABLE subcategoria (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "estado TEXT," +
                "id_categoria INTEGER," +
                "FOREIGN KEY (id_categoria) REFERENCES categoria(id))");

        db.execSQL("CREATE TABLE catalogo (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "descripcion TEXT," +
                "fecha_creada DATETIME)");

        db.execSQL("CREATE TABLE productos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "descripcion TEXT," +
                "precio REAL," +
                "unidad TEXT," +
                "cantidad INTEGER," +
                "estado TEXT," +
                "foto TEXT," +
                "id_inventario INTEGER," +
                "id_subcategoria INTEGER," +
                "id_catalogo INTEGER," +
                "FOREIGN KEY (id_inventario) REFERENCES inventario(id)," +
                "FOREIGN KEY (id_subcategoria) REFERENCES subcategoria(id)," +
                "FOREIGN KEY (id_catalogo) REFERENCES catalogo(id))");

        db.execSQL("CREATE TABLE notaVenta (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "cantidad INTEGER," +
                "fecha DATETIME," +
                "total REAL," +
                "pago TEXT," +
                "estado TEXT," +
                "id_user INTEGER," +
                "FOREIGN KEY (id_user) REFERENCES usuario(id))");

        db.execSQL("CREATE TABLE cliente (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "email TEXT," +
                "ubicacion DATETIME)");

        db.execSQL("CREATE TABLE detalleCatalogo (" +
                "id_producto INTEGER," +
                "id_catalogo INTEGER," +
                "subtotal REAL," +
                "descuento REAL," +
                "PRIMARY KEY (id_producto, id_catalogo)," +
                "FOREIGN KEY (id_producto) REFERENCES productos(id)," +
                "FOREIGN KEY (id_catalogo) REFERENCES catalogo(id))");

        db.execSQL("CREATE TABLE pedido (" +
                "id_producto INTEGER," +
                "id_notaVenta INTEGER," +
                "id_cliente INTEGER," +
                "fecha DATETIME," +
                "estado TEXT," +
                "PRIMARY KEY (id_producto, id_notaVenta)," +
                "FOREIGN KEY (id_producto) REFERENCES productos(id)," +
                "FOREIGN KEY (id_notaVenta) REFERENCES notaVenta(id)," +
                "FOREIGN KEY (id_cliente) REFERENCES cliente(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS inventario");
        db.execSQL("DROP TABLE IF EXISTS categoria");
        db.execSQL("DROP TABLE IF EXISTS subcategoria");
        db.execSQL("DROP TABLE IF EXISTS productos");
        db.execSQL("DROP TABLE IF EXISTS catalogo");
        db.execSQL("DROP TABLE IF EXISTS notaVenta");
        db.execSQL("DROP TABLE IF EXISTS cliente");
        db.execSQL("DROP TABLE IF EXISTS pedido");
        db.execSQL("DROP TABLE IF EXISTS detalleCatalogo");
        /*if (oldVersion < 2) { // Solo agregar la columna si la versión es anterior a 2
            db.execSQL("ALTER TABLE inventario ADD COLUMN fecha DATETIME");
            db.execSQL("ALTER TABLE pedido ADD COLUMN estado TEXT");
        }*/
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;"); // Activa el soporte para llaves foráneas
    }
}
