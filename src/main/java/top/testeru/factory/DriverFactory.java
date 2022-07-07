package top.testeru.factory;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @program: auto
 * @author: testeru.top
 * @description: driver工厂的创建
 * @Version 1.0
 * @create: 2022/7/6 19:04
 */
public class DriverFactory {

    //获取Class对象
    static Object obj = null;


    //反射生成构造函数的对象
    public static WebDriver getDriver(String ClassPath, Object[] Params) throws Exception {
        Class demo = Class.forName(ClassPath);

        Class[] argsClass = new Class[Params.length];
        for (int i = 0, j = Params.length; i < j; i++) {
            argsClass[i] = Params[i].getClass();
        }
        Constructor cons = demo.getConstructor(argsClass);
        obj =  cons.newInstance(Params);
        Method m = demo.getMethod("getdriver");
        return (WebDriver)m.invoke(obj);
    }

}
