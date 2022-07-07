package top.testeru.page;

import top.testeru.key.WebKeyWords;
import top.testeru.util.Driver;
import org.openqa.selenium.By;

/**
 * @program: auto
 * @author: testeru.top
 * @description: 企业微信Web首页
 * @Version 1.0
 * @create: 2022/7/7 10:56
 */
public class MainPage {

    WebKeyWords webKeyWords = WebKeyWords.getInstance();

    public ContactPage loginToContact(){

        //1、打开浏览器
        webKeyWords.openBrowserWithURL(Driver.CHROME, Driver.CHROMEPATH,"https://work.weixin.qq.com/wework_admin/loginpage_wx");
        //2、cookie登录
        webKeyWords.cookieLogin(".weixin.qq.com");

        //3、跳转到通讯录页面
        webKeyWords.click(By.id("menu_contacts"));
        //确定跳转
        webKeyWords.waitUntil().until(webDriver -> webDriver.findElement(By.id("memberSearchInput")));
        return new ContactPage();
    }
    public void quite(){
        webKeyWords.close();

    }

}
