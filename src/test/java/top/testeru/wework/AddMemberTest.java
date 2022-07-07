package top.testeru.wework;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.testeru.entity.User;
import top.testeru.page.MainPage;
import top.testeru.util.FakerUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @program: auto
 * @author: testeru.top
 * @description: 企业微信-web页面添加用户
 * @Version 1.0
 * @create: 2022/7/7 14:13
 */
@DisplayName("添加成员测试用例")
public class AddMemberTest {
    //删除log日志，allure报告已经加载进去了
    @BeforeAll
    @AfterAll
    static void cleanText(){
        File file = Paths.get("jpg").toFile();
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("通讯录添加成员")
    @Description("企业微信的通讯录通过cookie登录添加成员，成功后搜索添加的结果是否正确")
    @Step("add Member")
    void test1(){
        MainPage mainPage = new MainPage();
        String name = FakerUtil.get_name();
        String acctid = FakerUtil.get_acctid();
        String phone = FakerUtil.get_zh_phone();

        User user = mainPage.loginToContact().addMember(name, acctid, acctid, phone).searchMemeber(name).get();
        mainPage.quite();
        assertAll(()-> assertThat(name,equalTo(user.getName())),
                ()-> assertThat(phone,equalTo(user.getMobile())));
    }
}
