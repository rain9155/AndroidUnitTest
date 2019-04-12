package com.example.androidunittest.mockito;

/**
 * 模拟用户登陆场景
 * Created by 陈健宇 at 2019/3/31
 */
public class Main {

    public static void main(String[] args){
        LoginPresenter loginPresenter = new LoginPresenter();
        User user = new User("rain", "123456");
        loginPresenter.login(user.name, user.id);
    }

}
