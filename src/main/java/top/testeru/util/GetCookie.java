package top.testeru.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @program: auto
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/7/6 11:10
 */
public class GetCookie {

    public void loginWithCookie(WebDriver webDriver, String preUrl, String domain) {
        List<HashMap<String, Object>> cookies = readCookieYaml();
        List<Long> expiryList = new ArrayList<>();
        cookies.forEach(
                cookie -> {
                    if(cookie.get("expiry") != null){
                        String expiryStr = cookie.get("expiry").toString();
                        Long expireL = Long.valueOf(expiryStr);
                        expiryList.add(expireL);
                    }

                }
        );
        long nowCookieTime = expiryList.get(0) - 31536000000L;//毫秒级别
        long nowTime = System.currentTimeMillis();//毫秒级别
        if((nowTime - nowCookieTime)/1000 < 7200){
            System.out.println("使用cookie文件");
            cookies.stream()
                    .filter(
                            cookie -> cookie.get("domain").toString().contains(domain)
                    )
                    .forEach(cookie -> {
                        //cookie 放入浏览器
                        webDriver.manage().addCookie(
                                new Cookie(cookie.get("name").toString(),cookie.get("value").toString())
                        );
                    });
            //刷新浏览器，刷新的时候会把新的cookie放入，服务器才会返回登录后的页面
            webDriver.navigate().refresh();
        }else {
            System.out.println("扫码登录，cookie失效");
            saomaLogin(webDriver, preUrl);
        }
        String afterURL = webDriver.getCurrentUrl();
        System.out.println(!afterURL.equals(preUrl)?"登录成功":"登录失败");
    }

    public void saomaLogin(WebDriver webDriver, String preUrl) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30),Duration.ofSeconds(2));
        wait.until(webDriver1 -> !webDriver1.getCurrentUrl().equals(preUrl));
        Set<Cookie> cookies1 = webDriver.manage().getCookies();
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try {
            objectMapper.writeValue(
                    Paths.get("cookies.yaml").toFile(),cookies1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HashMap<String, Object>> readCookieYaml() {
        List<HashMap<String, Object>> cookies;
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {
            };
            cookies = objectMapper.readValue(Paths.get("cookies.yaml").toFile(), typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cookies;
    }

}
