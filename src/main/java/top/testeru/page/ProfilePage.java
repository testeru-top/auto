package top.testeru.page;

import top.testeru.entity.User;
import top.testeru.key.WebKeyWords;
import org.openqa.selenium.By;

/**
 * @program: auto
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/7/7 11:41
 */
//个人资料页
public class ProfilePage {
    WebKeyWords webKeyWords = WebKeyWords.getInstance();

    public User get(){
        String name = webKeyWords.getText(
                By.cssSelector(".member_display_cover_detail_name"));
        String mobile= webKeyWords.getText(
                By.cssSelector(".member_display_item_Phone .member_display_item_right"));

        User user=new User();
        user.setName(name);
        user.setMobile(mobile);
        return user;
    }

}
