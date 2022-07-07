package top.testeru.browser;

import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @program: auto
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/7/6 20:12
 */
public class FireFox  extends Browser{
    public FireFox(String propath, String driverpath) {
        // 设置 Firefox驱动的路径
        System.setProperty("webdriver.gecko.driver", driverpath);
        // 设置Firefox的安装目录,如果不需要设置，那么参数给一个空字符串
        if (propath != null && propath.length() > 0) {
            System.setProperty("webdriver.firefox.bin", propath);
        }


        // 创建一个 Firefox的浏览器实例，赋值给成员变量
        try {
            driver = new FirefoxDriver();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("log--error：创建driver失败！！");
        }
    }

}
