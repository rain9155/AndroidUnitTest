package com.example.androidunittest.powermockito;

/**
 * 香蕉,里面有静态方法，final方法，私有成员变量和方法，静态成员变量
 * Created by 陈健宇 at 2019/4/3
 */
public class Banana {

    private String name = "香蕉";

    private static String sColor = "黄色的";

    public Banana() {
    }

    public Banana(String name) {
        this.name = name;
    }

    public static String getColor(){
        return sColor;
    }

    public final boolean isLike() {
        return true;
    }

    private String flavor() {
        return "甜甜的";
    }

    public String getBananaInfo() {
        return getColor() + name + flavor();
    }


}
