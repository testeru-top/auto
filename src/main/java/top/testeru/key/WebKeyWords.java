package top.testeru.key;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import top.testeru.factory.DriverFactory;
import top.testeru.util.GetCookie;

import java.nio.file.Paths;
import java.time.Duration;

/**
 * @program: auto
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/7/6 17:37
 */
public class WebKeyWords extends EleKeyWords {
    By currentBy;

    private volatile static WebKeyWords webKeyWords ;

    private WebKeyWords() {

    }
    //3.提供对外方法,返回对象实例，运行时加载对象
    public static synchronized WebKeyWords getInstance()
    {
        if (webKeyWords == null) {
            synchronized (WebKeyWords.class) {
                if (webKeyWords == null) {
                    webKeyWords = new WebKeyWords();
                }
            }
        }
        return webKeyWords;
    }


//    public WebKeyWords(WebDriver webDriver) {
//        super(webDriver);
//    }

    /**
     * 封装浏览器启动driver，其实就是打开浏览器
     * 兼容多个浏览器调用
     * webDriver = DriverFactory.getDriver(Driver.EDGE, Driver.EDGEPATH);
     */
    @Step("打开：{URL}")
    public void openBrowserWithURL(String browserType, String[] driverPath, String URL) {

        try {
            webDriver = DriverFactory.getDriver(browserType, driverPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //浏览器最大化
        maxWindow();
        //打开网站
        toURL(URL);
        //隐式等待
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    /**
     * 浏览器窗口最大化
     */
    public void maxWindow() {
        webDriver.manage().window().maximize();
    }

    /**
     * 打开网站
     * @param url
     * @return
     */
    public void toURL(String url) {
        try {
            webDriver.get(url);
        } catch (Exception e) {
            System.out.println("URL输入有误");
            e.printStackTrace();
        }
    }

    /**
     * 关闭浏览器
     */
    public void close(){
        webDriver.quit();
    }


    /**
     * ".weixin.qq.com"
     * cookie登录
     */
    @Step("cookie登录：{domain}")
    public void cookieLogin(String domain) {
        GetCookie getCookie = new GetCookie();
        String preUrl = getUrl();
        if(!Paths.get("cookies.yaml").toFile().exists()){
            getCookie.saomaLogin(webDriver, preUrl);
        }else {
            getCookie.loginWithCookie(webDriver, preUrl, domain);
        }

    }

    private String getUrl() {
        return webDriver.getCurrentUrl();
    }

    //显示等待
    @Step("最多等待30s")
    public WebDriverWait waitUntil(){
       return new WebDriverWait(webDriver, Duration.ofSeconds(30), Duration.ofSeconds(2));
    }
    //页面刷新
    public void refresh() {
        webDriver.navigate().refresh();
    }

    //

}
