package com.example.androidunittest.JUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Junit4的简单使用;
 * Junit4是用来测试java代码的正确性（断言），不依赖Android的SDK
 * Created by 陈健宇 at 2019/3/23
 */
public class CalculatorTest {

    @Rule
    public final MyRule mMyRule = new MyRule();

    @Test
    public void sum() {
        Calculator calculator = new Calculator();
        assertEquals("求和失败",3, calculator.sum("1+1+1"));
        assertThat("失败", calculator.sum("1+1"), notNullValue());
    }
}