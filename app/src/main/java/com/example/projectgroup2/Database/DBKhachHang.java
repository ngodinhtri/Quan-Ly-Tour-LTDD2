package com.example.projectgroup2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.projectgroup2.Model.QLKH;

import java.util.ArrayList;

public class  DBKhachHang{
    SQLiteDatabase database;
    DBHelper dbHelper;

    public DBKhachHang(Context context) {
        dbHelper = new DBHelper(context, "DMKHACHHANG");
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            database = dbHelper.getReadableDatabase();
        }

    }

    public DBKhachHang() {
    }

    public void close() {
        dbHelper.close();

    }

    public Cursor layTatCaDuLieu() {
        String[] cot = new String[dbHelper.getTableKH().size()];
        for (int i = 0; i < dbHelper.getTableKH().size(); i++){
            cot[i] = dbHelper.getTableKH().get(i);
        }

        Cursor cursor = null;
        cursor = database.query("DMKHACHHANG", cot, null, null, null, null,
                dbHelper.getTableKH().get(1) + " DESC");
        return cursor;
    }

    public ArrayList<QLKH> getAllSV (){
        ArrayList<QLKH> QLKHs = new ArrayList<>();
        Cursor cursor = layTatCaDuLieu();
        while (cursor.moveToNext()){
            QLKH QLKH = new QLKH();
            QLKH.setMaKH( Integer.parseInt(cursor.getString(0)));
            QLKH.setTenKH(cursor.getString(1));
            QLKH.setDiaChi(cursor.getString(2));
            QLKH.setHinhAnh(cursor.getBlob(3));
            QLKHs.add(QLKH);
        }
        return QLKHs;
    }

    public long them(QLKH QLKH) {
        ContentValues values = new ContentValues();
        for (int i = 1; i < dbHelper.getTableKH().size() - 1; i++){
            values.put(dbHelper.getTableKH().get(i), QLKH.getQLKH().get(i));
        }
        values.put("HinhAnh", QLKH.getHinhAnh());
        return database.insert("DMKHACHHANG", null, values);
    }
    public Cursor timkiem(int MaKH)
    {
        String[] cot = new String[dbHelper.getTableKH().size()];
        for (int i = 0; i < dbHelper.getTableKH().size(); i++){
            cot[i] = dbHelper.getTableKH().get(i);
        }
        String selection = "kc_MaKH = ?";
        String[] selectionArgs = new String[]{
                String.valueOf(MaKH)
        };
        Cursor cursor = null;
        cursor = database.query("DMKHACHHANG", cot, selection, selectionArgs, null, null,
                null);
        return cursor;
    }
    public ArrayList<QLKH> getAllTimKiem (int MaKH){
        ArrayList<QLKH> QLKHs = new ArrayList<>();
        Cursor cursor = timkiem(MaKH);
        while (cursor.moveToNext()){
            QLKH QLKH = new QLKH();
            QLKH.setMaKH( Integer.parseInt(cursor.getString(0)));
            QLKH.setTenKH(cursor.getString(1));
            QLKH.setDiaChi(cursor.getString(2));
            QLKH.setHinhAnh(cursor.getBlob(3));
            QLKHs.add(QLKH);
        }
        return QLKHs;
    }
    public long xoa(QLKH QLKH) {
        return database.delete("DMKHACHHANG", dbHelper.getTableKH().get(0) + " = " + "'" +
                QLKH.getMaKH() + "'", null);
    }

    public long sua(QLKH QLKH) {
        ContentValues values = new ContentValues();
        for (int i = 1; i < dbHelper.getTableKH().size()-1; i++){
            values.put(dbHelper.getTableKH().get(i),
                    QLKH.getQLKH().get(i));
            Log.e("a", QLKH.getQLKH().get(i).toString());
        }
        values.put("HinhAnh", QLKH.getHinhAnh());
        return database.update("DMKHACHHANG", values,
                dbHelper.getTableKH().get(0) + " = "
                        + QLKH.getMaKH(), null);
    }

    public Cursor timkiemPhieuDK(String searchValue)
    {
        String[] cot = new String[dbHelper.getTableKH().size()];
        for (int i = 0; i < dbHelper.getTableKH().size(); i++){
            cot[i] = dbHelper.getTableKH().get(i);
        }
        String selection = "TenKH LIKE ? OR DiaChi LIKE ?  ";
        String[] selectionArgs = new String[]{
                "%" + String.valueOf(searchValue)+ "%", "%" + String.valueOf(searchValue)+ "%"
        };
        Cursor cursor = null;
        cursor = database.query("DMKHACHHANG", cot, selection, selectionArgs, null, null,
                null);
        return cursor;
    }
    public ArrayList<QLKH> getAllKhachHangSearch(String searchValue){
        ArrayList<QLKH> QLKHs = new ArrayList<>();
        Cursor cursor = timkiemPhieuDK(searchValue);
        while (cursor.moveToNext()){
            QLKH qlKH = new QLKH();
            qlKH.setMaKH( Integer.parseInt(cursor.getString(0)));
            qlKH.setTenKH(cursor.getString(1));
            qlKH.setDiaChi(cursor.getString(2));
            qlKH.setHinhAnh(cursor.getBlob(3));
            QLKHs.add(qlKH);
        }
        return QLKHs;
    }
}
