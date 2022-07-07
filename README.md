# 项目改进
- page包对应的内容封装为yaml文件
- 模块解析
- yaml文件在项目下
# 项目介绍
- 使用关键字进行驱动
- 页面进行PO设计
- 支持多个浏览器：Chrome 火狐 edge
- 可继续添加多个浏览器，有很好的扩展性
- driver根据工厂模式，反射创建
- cookie复用登录
- allure命令行下载最新版本
- driver驱动下载到项目的driver文件夹下，自动匹配
- web、app、接口关键字驱动结合，一套框架多个使用
- selenium最新版本4.1运行报错，解决
# 运行
## 命令行运行
```shell
mvn clean test allure:report
mvn allure:serve
```

