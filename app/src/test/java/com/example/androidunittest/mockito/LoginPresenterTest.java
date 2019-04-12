package com.example.androidunittest.mockito;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Mockito的简单使用：
 *  Mockito是在测试环境中，创建一个虚假的对象，替换掉真实的对象，验证与模拟真实对象的行为或动作，同样是测试java代码的
 * Mockito框架不支持mock匿名类、final类、static方法、private方法
 * Created by 陈健宇 at 2019/3/31
 */
//@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Rule
    public MockitoRule mMockitoRule = MockitoJUnit.rule();

    //1、使用@Mock注解模拟一个对象,要先用@Rule或使用@RunWith(MockitoJUnitRunner.class)
    @Mock
    LoginModel loginModel;


    //验证LoginPresenter的login方法中的LoginModel的login方法得到了一次调用
    @Test
    public void login() {

        LoginPresenter loginPresenter = new LoginPresenter();
        User user = new User("rain", "123456");

        //2、使用mock方法模拟一个对象
        //LoginModel loginModel = mock(LoginModel.class);

        //使用spy方法可以监控真实对象的行为，调用真实对象的方法
        //LoginModel loginModel = spy(LoginModel.class);

        //把模拟对象替换掉真实对象（注入）
        loginPresenter.setModel(loginModel);

        //模拟行为
        when(loginModel.verifyName(user.name)).thenReturn(false);
        doNothing().when(loginModel).login(user.name, user.id);

        loginPresenter.login(user.name, user.id);

        //验证方法调用次数
        verify(loginModel, times(1)).login(anyString(), anyString());


    }
}