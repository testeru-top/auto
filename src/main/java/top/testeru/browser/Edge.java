package top.testeru.browser;

import org.openqa.selenium.edge.EdgeDriver;

/**
 * @program: auto
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/7/6 20:30
 */
public class Edge extends Browser{
    public Edge(String driverpath) {
        // 设置 chrome driver 的路径
        System.setProperty("webdriver.edge.driver", driverpath);

        try {
            // 创建浏览器实例
            this.driver = new EdgeDriver();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("log--error：创建driver失败！！");
            System.out.println(e.getMessage());
        }

    }
}
