package com.example.androidunittest.powermockito;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.rule.PowerMockRule;

import static org.junit.Assert.*;

/**
 * PowerMockito的简单使用：
 * PowerMockito是一个扩展了Mockito的具有更强大功能的单元测试框架，它支持mock匿名类、final类、static方法、private方法
 * Created by 陈健宇 at 2019/4/3
 */

//使用PowerMock必须要加@RunWith(PowerMockRunner.class) 或 @Rule注解修饰的PowerMockRule变量类型
//@RunWith(PowerMockRunner.class)
@PrepareForTest(Banana.class)
public class BananaTest {

    //使用@Rule代替@RunWith(PowerMockRunner.class)
    @Rule
    public PowerMockRule mPowerMockRule = new PowerMockRule();

    //测试mock静态方法
    @Test
    public void staticMethodTest(){

        //1、添加@PrepareForTest注解，里面写静态方法所在的类
        //2、调用 PowerMockito.mockStatic去mock静态方法所在的类
        PowerMockito.mockStatic(Banana.class);
        // PowerMockito.spy(Banana.class);

        //3、使用Mockito去mock对象
        Mockito.when(Banana.getColor()).thenReturn("绿色");

        //验证static方法是否调用了一次,必须在调用static方法前使用
        PowerMockito.verifyStatic(Banana.class);
        Assert.assertEquals("绿色", Banana.getColor());

    }

    //测试mock私有方法和成员变量
    @Test
    public void privateMethodAndFeildTest() throws Exception {

        //1、调用 PowerMockito.mock去mock私有方法所在的类
        Banana banana = PowerMockito.mock(Banana.class);

        //当调用banana.getBananaInfo()时调用它的真实行为
        PowerMockito.when(banana.getBananaInfo()).thenCallRealMethod();
        //2、使用PowerMockito去mock私有方法
        PowerMockito.when(banana, "flavor").thenReturn("苦苦的");
        //使用PowerMockito去modify私有变量
        MemberModifier.field(Banana.class, "name").set(banana, "苹果");

        Assert.assertEquals("黄色的苹果苦苦的", banana.getBananaInfo());
        //验证flavor方法是否调用了一次
        PowerMockito.verifyPrivate(banana).invoke("flavor");
    }

    //测试跳过mock私有方法
    @Test
    public void privateMethodTest(){

        Banana banana = new Banana();
        //跳过flavor方法
        PowerMockito.suppress(PowerMockito.method(Banana.class, "flavor"));
        Assert.assertEquals("黄色的香蕉null", banana.getBananaInfo());

    }

    //测试mock final方法
    @Test
    public void finalMethodTest(){

        Banana banana = PowerMockito.mock(Banana.class);
        //mock isLike方法使它返回false
        PowerMockito.when(banana.isLike()).thenReturn(false);
        Assert.assertFalse(false);

    }

    //测试mock构造方法
    @Test
    public void constructorTest() throws Exception {

        Banana banana = PowerMockito.mock(Banana.class);
       PowerMockito.when(banana.getBananaInfo()).thenReturn("苹果");
        //如果new新对象，则返回这个上面mock的这个对象,如果构造方法有参数，可以在withArguments方法中传入
        PowerMockito.whenNew(Banana.class).withNoArguments().thenReturn(banana);
        //new新的对象, 其实是上面mock的对象
        Banana newBanana = new Banana();
        Assert.assertEquals("苹果", newBanana.getBananaInfo());
    }

}