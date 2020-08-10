package com.example.projectgroup2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.projectgroup2.Model.QLKH;
import com.example.projectgroup2.Model.QLPhieuDK;

import java.util.ArrayList;

public class DB_PhieuDK {
    SQLiteDatabase database;
    DBHelper dbHelper;
    public DB_PhieuDK(Context context) {
        dbHelper = new DBHelper(context, "PHIEUDANGKY");
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            database = dbHelper.getReadableDatabase();
        }
    }

    public DB_PhieuDK() {
    }
    public void close() {
        dbHelper.close();
    }
    public Cursor layTatCaDuLieu() {
        String[] cot = new String[dbHelper.getTablePhieuDK().size()];
        for (int i = 0; i < dbHelper.getTablePhieuDK().size(); i++){
            cot[i] = dbHelper.getTablePhieuDK().get(i);
        }

        Cursor cursor = null;
        cursor = database.query("PHIEUDANGKY", cot, null, null, null, null,
                dbHelper.getTablePhieuDK().get(1) + " DESC");
        return cursor;
    }

    public Cursor layTatCaMaTour(int ma) {
        String[] cot = new String[dbHelper.getTablePhieuDK().size()];
        for (int i = 0; i < dbHelper.getTablePhieuDK().size(); i++){
            cot[i] = dbHelper.getTablePhieuDK().get(i);
        }
        Cursor cursor = null;
        String sql = "SELECT * FROM PHIEUDANGKY WHERE kp_MaKH = " + ma;
        cursor = database.rawQuery(sql,null);
        return cursor;
    }
    public ArrayList<QLPhieuDK> getAllMaTour(int ma){
        ArrayList<QLPhieuDK> QLPhieuDKs = new ArrayList<>();
        Cursor cursor = layTatCaMaTour(ma);
        while (cursor.moveToNext()){
            QLPhieuDK QLPDK = new QLPhieuDK();
            QLPDK.setSoPhieu(Integer.parseInt(cursor.getString(0)));
            QLPDK.setNgayDK(cursor.getString(1));
            QLPDK.setMaKH(Integer.parseInt(cursor.getString(2)));
            QLPDK.setMaTour(Integer.parseInt(cursor.getString(3)));
            QLPDK.setSoNguoi(Integer.parseInt(cursor.getString(4)));
            QLPhieuDKs.add(QLPDK);
        }
        return QLPhieuDKs;
    }
    public ArrayList<QLPhieuDK> getAllPhieuDK (){
        ArrayList<QLPhieuDK> QLPhieuDKs = new ArrayList<>();
        Cursor cursor = layTatCaDuLieu();
        while (cursor.moveToNext()){
            QLPhieuDK QLPDK = new QLPhieuDK();
            QLPDK.setSoPhieu(Integer.parseInt(cursor.getString(0)));
            QLPDK.setNgayDK(cursor.getString(1));
            QLPDK.setMaKH(Integer.parseInt(cursor.getString(2)));
            QLPDK.setMaTour(Integer.parseInt(cursor.getString(3)));
            QLPDK.setSoNguoi(Integer.parseInt(cursor.getString(4)));
            QLPhieuDKs.add(QLPDK);
        }
        return QLPhieuDKs;
    }
    public long them(QLPhieuDK QLPDK) {
        ContentValues values = new ContentValues();
        for (int i = 1; i < dbHelper.getTablePhieuDK().size(); i++){
            values.put(dbHelper.getTablePhieuDK().get(i), QLPDK.getQLPDK().get(i));
        }
        return database.insert("PHIEUDANGKY", null, values);
    }
    public long xoaPhieuDKTheoMaKH(QLKH QLKH) {
        return database.delete("PHIEUDANGKY", dbHelper.getTablePhieuDK().get(2) + " = " + "'" +
                QLKH.getMaKH() + "'", null);
    }
    public long xoa(QLPhieuDK qlPhieuDK) {
        return database.delete("PHIEUDANGKY", dbHelper.getTablePhieuDK().get(0) + " = " + "'" +
                qlPhieuDK.getSoPhieu() + "'", null);
    }
    public long sua(QLPhieuDK QLPDK) {
        ContentValues values = new ContentValues();
        for (int i = 1; i < dbHelper.getTablePhieuDK().size(); i++){
            values.put(dbHelper.getTablePhieuDK().get(i),
                    QLPDK.getQLPDK().get(i));
        }
        return database.update("PHIEUDANGKY", values,
                dbHelper.getTablePhieuDK().get(0) + " = "
                        + QLPDK.getSoPhieu(), null);
    }
//
    public Cursor timkiemPhieuDK(String searchValue)
    {
        String[] cot = new String[dbHelper.getTablePhieuDK().size()];
        for (int i = 0; i < dbHelper.getTablePhieuDK().size(); i++){
            cot[i] = dbHelper.getTablePhieuDK().get(i);
        }
        String selection = "kc_SoPhieu LIKE ? OR NgayDK LIKE ?  ";
        String[] selectionArgs = new String[]{
                "%" + String.valueOf(searchValue)+ "%", "%" + String.valueOf(searchValue)+ "%"
        };
        Cursor cursor = null;
        cursor = database.query("PHIEUDANGKY", cot, selection, selectionArgs, null, null,
                null);
        return cursor;
    }
    public ArrayList<QLPhieuDK> getAllPhieuDKSearch (String searchValue){
        Log.e( "getAllPhieuDKSearch: ", searchValue + "");
        ArrayList<QLPhieuDK> QLPDKS = new ArrayList<>();
        Cursor cursor = timkiemPhieuDK(searchValue);
        while (cursor.moveToNext()){
            QLPhieuDK qlPhieuDK = new QLPhieuDK();
            qlPhieuDK.setSoPhieu( Integer.parseInt(cursor.getString(0)));
            qlPhieuDK.setNgayDK(cursor.getString(1));
            qlPhieuDK.setMaKH(cursor.getInt(2));
            qlPhieuDK.setMaTour(cursor.getInt(3));
            qlPhieuDK.setSoNguoi(cursor.getInt(3));
            QLPDKS.add(qlPhieuDK);
        }
        return QLPDKS;
    }
    public Cursor timkiemTheoMaKH(String searchValue)
    {
        String[] cot = new String[dbHelper.getTablePhieuDK().size()];
        for (int i = 0; i < dbHelper.getTablePhieuDK().size(); i++){
            cot[i] = dbHelper.getTablePhieuDK().get(i);
        }
        String selection = "kp_MaKH = ?";
        String[] selectionArgs = new String[]{
                String.valueOf(searchValue)
        };
        Cursor cursor = null;
        cursor = database.query("PHIEUDANGKY", cot, selection, selectionArgs, null, null,
                null);
        return cursor;
    }
    public ArrayList<QLPhieuDK> getAllTheoMaKH (String searchValue){
        Log.e("getAllTheoMaKH: ", searchValue);
        Log.e( "getAllPhieuDKSearch: ", searchValue + "");
        ArrayList<QLPhieuDK> QLPDKS = new ArrayList<>();
        Cursor cursor = timkiemTheoMaKH(searchValue);
        while (cursor.moveToNext()){
            QLPhieuDK qlPhieuDK = new QLPhieuDK();
            qlPhieuDK.setSoPhieu( Integer.parseInt(cursor.getString(0)));
            qlPhieuDK.setNgayDK(cursor.getString(1));
            qlPhieuDK.setMaKH(cursor.getInt(2));
            qlPhieuDK.setMaTour(cursor.getInt(3));
            qlPhieuDK.setSoNguoi(cursor.getInt(3));
            QLPDKS.add(qlPhieuDK);
        }
        return QLPDKS;
    }
}
