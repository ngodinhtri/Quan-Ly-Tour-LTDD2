package com.example.projectgroup2.Model;

import android.util.Log;

import java.util.ArrayList;

public class QLTour {
    int MaTour;
    String LoTrinh;
    String HanhTrinh;
    int GiaTour;

    public QLTour(int maTour, String loTrinh, String hanhTrinh, int giaTour) {
        MaTour = maTour;
        LoTrinh = loTrinh;
        HanhTrinh = hanhTrinh;
        GiaTour = giaTour;
    }

    public QLTour() {
    }
    public ArrayList<String> getQLTour()
    {
        ArrayList<String> arrQLTour = new ArrayList<>();
        arrQLTour.add(String.valueOf(MaTour));
        arrQLTour.add(LoTrinh);
        arrQLTour.add(HanhTrinh);
        arrQLTour.add(String.valueOf(GiaTour));
        Log.d("ggg", arrQLTour.get(3));
        return  arrQLTour;
    }
    public void setMaTour(int maTour) {
        MaTour = maTour;
    }

    public void setLoTrinh(String loTrinh) {
        LoTrinh = loTrinh;
    }

    public void setHanhTrinh(String hanhTrinh) {
        HanhTrinh = hanhTrinh;
    }

    public void setGiaTour(int giaTour) {
        GiaTour = giaTour;
    }

    public int getMaTour() {
        return MaTour;
    }

    public String getLoTrinh() {
        return LoTrinh;
    }

    public String getHanhTrinh() {
        return HanhTrinh;
    }

    public int getGiaTour() {
        return GiaTour;
    }
}
