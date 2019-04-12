package com.example.androidunittest.JUnit;

/**
 * 计算器求和
 * Created by 陈健宇 at 2019/3/23
 */
public class Calculator {

    public int sum(String expression){
        int sum = 0;
        for(String num : expression.split("\\+")){
            sum += Integer.valueOf(num);
        }
        return sum;
    }

}
