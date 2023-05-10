package com.ksi.mystudyapp;

public class Ex28FriendsItem {
    String name;
    String hp;
    String sex;
    String addr;
    int age;
    Ex28FriendsItem(String name, String hp, String sex, String addr, int age)
    {
        this.name = name;
        this.hp = hp;
        this.sex = sex;
        this.addr = addr;
        this.age = age;
    }
    void setName (String name) {  this.name = name;  }
    String getName(){   return name;  }
    void setHp (String hp) {  this.hp = hp;  }
    String getHp(){   return hp;  }
    void setSex (String sex) {  this.sex = sex;  }
    String getSex(){   return sex;  }
    void setAddr (String addr) {  this.addr = addr;  }
    String getAddr(){   return addr;  }
    void setAge (int age) {  this.age = age;  }
    int getAge(){   return age;  }
}
