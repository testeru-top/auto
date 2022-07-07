package top.testeru.util;

import java.io.Serializable;

/**
 * @program: auto
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/7/6 18:02
 */
public final class Driver implements Cloneable, Serializable {

    public static final String CHROME = "top.testeru.browser.Chrome";

    public static final String FIREFOX = "top.testeru.browser.FireFox";
    public static final String EDGE = "top.testeru.browser.Edge";

    public static final String[] CHROMEPATH = {"driver/chromedriver"};
    public static final String[] FIREFOXPATH = {"","driver/geckodriver"};
    //https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/
    public static final String[] EDGEPATH = {"driver/msedgedriver"};

}
