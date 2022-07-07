package top.testeru.key;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        WebElement element = webDriver.findElement(by);
        //通过可变参数 args，传递参数数组，在js语句中用arguments[0]调用。
//        jsDriver.executeScript("arguments[0]." + "style.border='3px solid red'", element);
        HighlightElement(webDriver,element);
        screen(by.toString());
        UnhighlightElement(webDriver,element);
        return element;
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
    public void screen(String message) {

        //生成时间戳
        long nowTime = System.currentTimeMillis();
        //进行截图操作
        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        //在硬盘中创建一个文件，将截图复制过去
        Path jpgPath = Paths.get("jpg", nowTime + ".jpg");
        try {
            FileUtils.copyFile(screenshot, jpgPath.toFile());
            //添加到报告中
            Allure.addAttachment(message, "image/jpg", new FileInputStream(jpgPath.toFile()), ".jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //元素执行js语句
    public void runJsWithEle(String jsScript, By by) {
        JavascriptExecutor jsDriver = (JavascriptExecutor) webDriver;
        //通过可变参数 args，传递参数数组，在js语句中用arguments[0]调用。
        jsDriver.executeScript("arguments[0]." + jsScript, find(by));
//        jse.executeScript("arguments[0].style.border='3px solid red'", element_node);
    }
    //元素选中
    private WebDriver HighlightElement(WebDriver driver, WebElement element){
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        return driver;
    }
    //去掉选中
    private WebDriver UnhighlightElement(WebDriver driver, WebElement element){
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border=''", element);
        }
        return driver;
    }
    //获取元素轴坐标
    public Point getLocation(By by) {
        //获取元素x,y轴坐标
        return find(by).getLocation();
    }
    //获取元素长度
    public Dimension getSize(By by) {
        //获取元素x,y轴坐标
        return find(by).getSize();
    }
}
