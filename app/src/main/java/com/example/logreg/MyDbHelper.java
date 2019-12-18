package com.example.logreg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(@Nullable Context context) {
        super(context, "db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS felhasznalok" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "email TEXT NOT NULL UNIQUE," +
                "felhasznalonev TEXT NOT NULL UNIQUE," +
                "jelszo TEXT  NOT NULL," +
                "teljesnev TEXT NOT NULL)");
    }

    public void insert(Editable email, Editable felh, Editable jelszo, Editable teljesNev, Context context, SQLiteDatabase db){
        try{
            String sql = "INSERT INTO felhasznalok (email, felhasznalonev, jelszo, teljesnev) VALUES ('"+email+"', '"+felh+"', '"+jelszo+"', '"+teljesNev+"')";
            db.execSQL(sql);
            Toast.makeText(context, "Sikeres regisztráció!", Toast.LENGTH_SHORT).show();
        }
        catch (SQLiteConstraintException e){
            Toast.makeText(context, "Ilyen felhasználó már létezik az adatbázisban!", Toast.LENGTH_SHORT);
        }
        catch (Exception e){
            Toast.makeText(context, e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public Cursor select(String felh, SQLiteDatabase db, Context context){
        Cursor c = null;
        try{
            String sql = "SELECT * FROM felhasznalok WHERE felhasznalonev= " + felh;
            c = db.rawQuery("SELECT * FROM felhasznalok WHERE felhasznalonev='"+felh+"' OR email='"+felh+"'",null);
            c.moveToFirst();
            return c;
        }
        catch (Exception e){
            Toast.makeText(context,e.toString(), Toast.LENGTH_LONG);
        }
        return c;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
