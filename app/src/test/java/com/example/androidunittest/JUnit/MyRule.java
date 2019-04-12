package com.example.androidunittest.JUnit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by 陈健宇 at 2019/3/23
 */
public class MyRule implements TestRule {
    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println(description.getMethodName() + "测试开始");
                base.evaluate();
                System.out.println(description.getMethodName() + "测试结束");

            }
        };
    }
}
