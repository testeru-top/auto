package top.testeru.key;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.nio.file.Files;

/**
 * @program: auto
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/7/6 21:23
 */
public class EleKeyWords {
    public EleKeyWords() {
    }

    //所有方法都基于driver
    WebDriver webDriver;
    WebElement currentElement;
    public EleKeyWords(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    @Step("查找元素：{by}")
    public WebElement find(By by) {
        return webDriver.findElement(by);
    }
    @Step("元素 {by} 点击")
    public void click(By by) {
        find(by).click();
    }
    @Step("元素 {by} 输入 {text}")
    public void send(By by, String text) {
        find(by).clear();
        find(by).sendKeys(text);
    }
    @Step("元素 {by} 文本获取")
    public String getText(By by) {
        return find(by).getText();
    }
    public String getAttribute(By by, String name) {
        return find(by).getAttribute(name);
    }
    public String getPageSource() {
        return webDriver.getPageSource();
    }

    //通过value值来选择option元素
    @Step("通过 {value} 选择元素 {by}")
    public void selectWithValue(By by, String value) {
        Select select = new Select(find(by));
        select.selectByValue(value);
    }
    //截图
//    public void takeScreenshot(String filename) {
//        try {
//            filename += dateTimeStr("MMdd-HHmmss") + ".png";
//            filename = "Logs\\pic\\" + filename;
//            //截图保存为jvm内存中的文件
//            File screenshotAs = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
//            //在硬盘中创建一个文件，将截图复制过去。
//            File picFile = new File(filename);
//            Files.copy(screenshotAs, picFile);
//        } catch (IOException e) {
//            log.error(e.fillInStackTrace());
//            log.error("截图失败");
//        }
//    }
}
