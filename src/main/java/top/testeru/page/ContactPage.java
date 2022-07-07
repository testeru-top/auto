package top.testeru.page;

import top.testeru.key.WebKeyWords;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @program: auto
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/7/7 11:08
 */
public class ContactPage{
    WebKeyWords webKeyWords = WebKeyWords.getInstance();
    public ContactPage addMember(String name, String acctid, String mail, String mobile) {
        By addMemberButton = By.linkText("添加成员");
        webKeyWords.waitUntil().until(ExpectedConditions.visibilityOfElementLocated(addMemberButton));
        webKeyWords.click(addMemberButton);
        webKeyWords.send(By.name("username"), name);
        webKeyWords.send(By.name("acctid"), acctid);
        webKeyWords.send(By.name("biz_mail"), mail);
        webKeyWords.send(By.name("mobile"), mobile);
        webKeyWords.click(By.linkText("保存"));
        webKeyWords.refresh();
        return this;
    }
    public ProfilePage searchMemeber(String username) {
        By memberSearchInput = By.id("memberSearchInput");
        webKeyWords.send(memberSearchInput, username);
        webKeyWords.waitUntil().until(webDriver -> webDriver.getPageSource().contains("成员详情"));
        return new ProfilePage();
    }
}
