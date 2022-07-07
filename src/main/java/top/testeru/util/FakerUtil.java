package top.testeru.util;

import com.github.javafaker.Faker;

import java.util.Locale;

/**
 * @program: auto
 * @author: testeru.top
 * @description:
 * @Version 1.0
 * @create: 2022/7/6 11:38
 */
public class FakerUtil {
    static Faker faker = new Faker(Locale.SIMPLIFIED_CHINESE);
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    //名字
    public static String get_name(){
        String name = faker.name().fullName();
        return name;
    }
    public static String get_zh_phone(){
        //随机生成8位的电话号
        String s = faker.phoneNumber().subscriberNumber(8);
        //随机生成手机号开端的下标
        int index= getNum(0,telFirst.length-1);
        //获取手机号开头三位数
        String first=telFirst[index];
        //返回手机号
        return first + s;
    }
    public static String get_phone(){
        return faker.phoneNumber().phoneNumber();
    }
    public static String get_acctid(){
        return faker.phoneNumber().subscriberNumber(12);
    }
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }


}

