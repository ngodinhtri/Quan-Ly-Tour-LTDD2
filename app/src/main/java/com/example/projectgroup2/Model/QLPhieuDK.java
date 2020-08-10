package com.example.projectgroup2.Model;

import java.util.ArrayList;

public class QLPhieuDK {
    private int SoPhieu;
    private String NgayDK;
    private int MaKH;
    private int MaTour;
    private int SoNguoi;
    public QLPhieuDK() {
    }
    public ArrayList<String> getQLPDK()
    {
        ArrayList<String> arrQLKH = new ArrayList<>();
        arrQLKH.add(String.valueOf(SoPhieu));
        arrQLKH.add(NgayDK);
        arrQLKH.add(String.valueOf(MaKH));
        arrQLKH.add(String.valueOf(MaTour));
        arrQLKH.add(String.valueOf(SoNguoi));
        return  arrQLKH;
    }

    public QLPhieuDK(int soPhieu, String ngayDK, int maKH, int maTour, int soNguoi) {
        SoPhieu = soPhieu;
        NgayDK = ngayDK;
        MaKH = maKH;
        MaTour = maTour;
        SoNguoi = soNguoi;
    }

    public void setSoPhieu(int soPhieu) {
        SoPhieu = soPhieu;
    }

    public void setNgayDK(String ngayDK) {
        NgayDK = ngayDK;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }

    public void setMaTour(int maTour) {
        MaTour = maTour;
    }

    public void setSoNguoi(int soNguoi) {
        SoNguoi = soNguoi;
    }

    public int getSoPhieu() {
        return SoPhieu;
    }

    public String getNgayDK() {
        return NgayDK;
    }

    public int getMaKH() {
        return MaKH;
    }

    public int getMaTour() {
        return MaTour;
    }

    public int getSoNguoi() {
        return SoNguoi;
    }
}
