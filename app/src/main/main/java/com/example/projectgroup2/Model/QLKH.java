package com.example.projectgroup2.Model;

import java.util.ArrayList;

public class QLKH {
    private int MaKH;
    private String TenKH;
    private String DiaChi;
    private byte [] HinhAnh;

    public QLKH(int maKH, String tenKH, String diaChi, byte[] hinhAnh) {
        MaKH = maKH;
        TenKH = tenKH;
        DiaChi = diaChi;
        HinhAnh = hinhAnh;
    }


    public QLKH() {

    }
    public ArrayList<String> getQLKH()
    {
        ArrayList<String> arrQLKH = new ArrayList<>();
        arrQLKH.add(String.valueOf(MaKH));
        arrQLKH.add(TenKH);
        arrQLKH.add(DiaChi);
        return  arrQLKH;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getMaKH() {
        return MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public byte[] getHinhAnh() {
        return HinhAnh;
    }
}
