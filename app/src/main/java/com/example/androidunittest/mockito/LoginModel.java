package com.example.androidunittest.mockito;

/**
 * Model
 * Created by 陈健宇 at 2019/3/31
 */
public class LoginModel {

    public void login(String name, String id){
        if("rain".equals(name) && "123456".equals(id))
            System.out.println("登陆成功");
        else
            System.out.println("登陆失败");
    }

    public boolean verifyName(String name){
        return "rain".equals(name);
    }

}
