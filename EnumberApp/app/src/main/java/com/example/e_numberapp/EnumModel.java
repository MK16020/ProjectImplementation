package com.example.e_numberapp;


import java.io.Serializable;

public class EnumModel implements Serializable {
    //database information with Serializable to turn it into bytes to manage it.
    int Color_Id,E_Id;
    String Detail, E_no, Name, Side_effect, Status;

    public EnumModel() {
    }

    public EnumModel(String name) {
        Name = name;
    }

    public EnumModel(int color_Id, int e_Id, String detail, String e_no, String name, String side_effect, String status) {
        Color_Id = color_Id;
        E_Id = e_Id;
        Detail = detail;
        E_no = e_no;
        Name = name;
        Side_effect = side_effect;
        Status = status;
    }

    public int getColor_Id() {
        return Color_Id;
    }

    public int getE_Id() {
        return E_Id;
    }

    public String getDetail() {
        return Detail;
    }

    public String getE_no() {
        return E_no;
    }

    public String getName() {
        return Name;
    }

    public String getSide_effect() {
        return Side_effect;
    }

    public String getStatus() {
        return Status;
    }
}
