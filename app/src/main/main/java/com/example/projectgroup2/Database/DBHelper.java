package com.example.projectgroup2.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    ArrayList<String> tableKH = new ArrayList<>();
    ArrayList<String> tablePhieuDK = new ArrayList<>();
    ArrayList<String> tableTour = new ArrayList<>();
    // Tên cơ sở dữ liệu
    public static final String TEN_DATABASE = "qlTour.db";

    public DBHelper(Context context, String tenBang) {
        super(context,TEN_DATABASE,null,1);
        //Khach Hang
        tableKH.add("kc_MaKH");
        tableKH.add("TenKH");
        tableKH.add("DiaChi");
        tableKH.add("HinhAnh");
        //Phieu DK
        tablePhieuDK.add("kc_SoPhieu");
        tablePhieuDK.add("NgayDK");
        tablePhieuDK.add("kp_MaKH");
        tablePhieuDK.add("kp_MaTour");
        tablePhieuDK.add("SoNguoi");
        //Tour
        tableTour.add("kc_MaTour");
        tableTour.add("LoTrinh");
        tableTour.add("HanhTrinh");
        tableTour.add("GiaTour");
    }
    public ArrayList<String> getTableKH() {
        return tableKH;
    }

    public ArrayList<String> getTablePhieuDK() {
        return tablePhieuDK;
    }

    public ArrayList<String> getTableTour() {
        return tableTour;
    }

    private String sqlTaoBang(ArrayList<String> cot, String name){
        String swap = "" + "create table if not exists " + name + " ( ";
        boolean dau = true;
        for (String s: cot
        ) {
            if(dau){
                swap += s + " integer primary key autoincrement ";
                dau = false;
            }else{
                swap += ", " + s + " text not null ";
            }
        }
        swap += ");";

        return swap;
    }

    private String sqlTaoBang(ArrayList<String> cot, String name, ArrayList<String> bang){
        String swap = "" + "create table if not exists " + name + " ( ";
        int i = 0;
        boolean kiemKhoa = false;
        boolean landau = true;
        for (String s: cot
        ) {
            if(s.contains("kc_")){
                swap += s + " integer primary key autoincrement ";
                kiemKhoa = true;
            }else{
                if(!kiemKhoa){
                    if(landau){
                        swap +=  s + " text not null ";
                        landau = false;
                    }else {
                        swap += " , " + s + " text not null ";
                    }
                }else {
                    swap += ", " + s + " text not null ";
                }

            }
        }
        for (String s: cot){
//            if(kiemKhoa){
                if(s.contains("kp_")){
                    swap += ", foreign key("+ s +") references " + bang.get(i)+"("+ s.replaceFirst("kp_", "kc_") +") ";
                    i++;
                }
//            }else {
//                if(s.contains("kp_")){
//                    swap += " foreign key("+ s +") references " + bang.get(i)+"("+ s.replaceFirst("kp_", "kc_") +") ";
//                    i++;
//                    kiemKhoa = true;
//                }
//            }
        }
        swap += ");";

        return swap;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ArrayList<String> PhieuCTDK = new ArrayList<>();
        PhieuCTDK.add("DMKHACHHANG");
        PhieuCTDK.add("DMTOUR");

        db.execSQL(sqlTaoBang(tableKH, "DMKHACHHANG", new ArrayList<String>()));
        db.execSQL(sqlTaoBang(tablePhieuDK, "PHIEUDANGKY", PhieuCTDK));
        db.execSQL(sqlTaoBang(tableTour,"DMTOUR", new ArrayList<String>()));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}