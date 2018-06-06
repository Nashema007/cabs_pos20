package com.example.dell.cabs_pos20.utilities;

public class Home {

    private String posName;
    private int img;

    public Home(String posName, int img) {
        this.posName = posName;
        this.img = img;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
