package com.ksi.mystudyapp;

public class Ex19NumberItem {
    /* 아이템의 정보를 담기 위한 클래스 */
    String name;
    String hp;
    int resId;

    public Ex19NumberItem(String name, String hp, int resId) {
        this.name = name;
        this.hp = hp;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }
    public void setNum(String num) {
        this.name = num;
    }

    public String getHp() {
        return hp;
    }
    public void setHp(String hp) {
        this.hp = hp;
    }

    public int getResId() {
        return resId;
    }
    public void setResId(int resId) {
        this.resId = resId;
    }
}
