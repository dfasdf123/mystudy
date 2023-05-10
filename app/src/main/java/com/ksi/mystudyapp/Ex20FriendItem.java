package com.ksi.mystudyapp;

public class Ex20FriendItem {
    /* 아이템의 정보를 담기 위한 클래스 */
    String name;
    String hp;

    String sex;

    String addr;

    String age;


    public Ex20FriendItem(String name, String hp, String sex, String addr, String age) {
        this.name = name;
        this.hp = hp;
        this.sex = sex;
        this.addr = addr;
        this.age = age;
    }




    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getHp() {
        return hp;
    }
    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
}
