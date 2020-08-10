package com.example.projectgroup2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.projectgroup2.Model.QLTour;

import java.util.ArrayList;

public class DBTour {
    SQLiteDatabase database;
    DBHelper dbHelper;
    public DBTour(Context context) {
        dbHelper = new DBHelper(context, "DMTOUR");
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            database = dbHelper.getReadableDatabase();
        }
    }
    public DBTour() {
    }

    public void close() {
        dbHelper.close();

    }
    public Cursor layTatCaDuLieu() {
        String[] cot = new String[dbHelper.getTableTour().size()];
        for (int i = 0; i < dbHelper.getTableTour().size(); i++){
            cot[i] = dbHelper.getTableTour().get(i);
        }

        Cursor cursor = null;
        cursor = database.query("DMTOUR", cot, null, null, null, null,
                dbHelper.getTableTour().get(1) + " DESC");
        return cursor;
    }
//
        public Cursor layTatCaDuLieuByTour(int ma) {
            String[] cot = new String[dbHelper.getTableTour().size()];
            for (int i = 0; i < dbHelper.getTableTour().size(); i++){
                cot[i] = dbHelper.getTableTour().get(i);
            }

            Cursor cursor = null;
            String sql = "SELECT * FROM DMTOUR WHERE kc_MaTour = " + ma;
            cursor = database.rawQuery(sql,null);
            return cursor;
        }
        public ArrayList<QLTour> getAllTourByTour(int ma){
            ArrayList<QLTour> QLTours = new ArrayList<>();
            Log.e("getAllSV: ", String.valueOf(QLTours.size()));
            Cursor cursor = layTatCaDuLieuByTour(ma);
            while (cursor.moveToNext()){
                QLTour qlTour = new QLTour();
                qlTour.setMaTour( Integer.parseInt(cursor.getString(0)));
                qlTour.setLoTrinh(cursor.getString(1));
                qlTour.setHanhTrinh(cursor.getString(2));
                qlTour.setGiaTour(Integer.parseInt(cursor.getString(3)));
                QLTours.add(qlTour);

            }
            return QLTours;
        }
//
    public ArrayList<QLTour> getAllTour(){
        ArrayList<QLTour> QLTours = new ArrayList<>();
        Log.e("getAllSV: ", String.valueOf(QLTours.size()));
        Cursor cursor = layTatCaDuLieu();
        while (cursor.moveToNext()){
            QLTour qlTour = new QLTour();
            qlTour.setMaTour( Integer.parseInt(cursor.getString(0)));
            qlTour.setLoTrinh(cursor.getString(1));
            qlTour.setHanhTrinh(cursor.getString(2));
            qlTour.setGiaTour(Integer.parseInt(cursor.getString(3)));
            QLTours.add(qlTour);

        }
        return QLTours;
    }
    public long them(QLTour qlTour) {
        ContentValues values = new ContentValues();
        for (int i = 1; i < dbHelper.getTableTour().size(); i++){
            values.put(dbHelper.getTableTour().get(i), qlTour.getQLTour().get(i));
        }
        return database.insert("DMTOUR", null, values);
    }
    public long xoa(QLTour qlTour) {
        return database.delete("DMTOUR", dbHelper.getTableTour().get(0) + " = " + "'" +
                qlTour.getMaTour() + "'", null);
    }
    public long sua(QLTour qlTour) {
        ContentValues values = new ContentValues();
        for (int i = 1; i < dbHelper.getTableTour().size()-1; i++){
            values.put(dbHelper.getTableTour().get(i),
                    qlTour.getQLTour().get(i));
            Log.e("a", qlTour.getQLTour().get(i).toString());
        }
        return database.update("DMTOUR", values,
                dbHelper.getTableTour().get(0) + " = "
                        + qlTour.getMaTour(), null);
    }
    public Cursor timkiem(int MaTour)
    {
        String[] cot = new String[dbHelper.getTableTour().size()];
        for (int i = 0; i < dbHelper.getTableTour().size(); i++){
            cot[i] = dbHelper.getTableTour().get(i);
        }
        String selection = "kc_MaTour = ?";
        String[] selectionArgs = new String[]{
                String.valueOf(MaTour)
        };
        Cursor cursor = null;
        cursor = database.query("DMTOUR", cot, selection, selectionArgs, null, null,
                null);
        return cursor;
    }
    public ArrayList<QLTour> getAllTimKiem (int MaTour){
        ArrayList<QLTour> QLTours = new ArrayList<>();
        Cursor cursor = timkiem(MaTour);
        while (cursor.moveToNext()){
            QLTour qlTour = new QLTour();
            qlTour.setMaTour( Integer.parseInt(cursor.getString(0)));
            qlTour.setLoTrinh(cursor.getString(1));
            qlTour.setHanhTrinh(cursor.getString(2));
            qlTour.setGiaTour(cursor.getInt(3));
            QLTours.add(qlTour);
        }
        return QLTours;
    }
//

//
    public Cursor timkiemPhieuDK(String searchValue)
    {
        String[] cot = new String[dbHelper.getTableTour().size()];
        for (int i = 0; i < dbHelper.getTableTour().size(); i++){
            cot[i] = dbHelper.getTableTour().get(i);
        }
        String selection = "LoTrinh LIKE ? OR HanhTrinh LIKE ? OR GiaTour LIKE ? OR kc_MaTour LIKE ?";
        String[] selectionArgs = new String[]{
                "%" + String.valueOf(searchValue)+ "%", "%" + String.valueOf(searchValue)+ "%","%" + String.valueOf(searchValue)+ "%","%" + String.valueOf(searchValue)+ "%"
        };
        Cursor cursor = null;
        cursor = database.query("DMTOUR", cot, selection, selectionArgs, null, null,
                null);
        return cursor;
    }
    public ArrayList<QLTour> getAllTourSearch (String searchValue){
        ArrayList<QLTour> QLTS = new ArrayList<>();
        Cursor cursor = timkiemPhieuDK(searchValue);
        while (cursor.moveToNext()){
            QLTour qlTour = new QLTour();
            qlTour.setMaTour( Integer.parseInt(cursor.getString(0)));
            qlTour.setLoTrinh(cursor.getString(1));
            qlTour.setHanhTrinh(cursor.getString(2));
            qlTour.setGiaTour(cursor.getInt(3));
            QLTS.add(qlTour);
        }
        return QLTS;
    }
}
