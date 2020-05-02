package com.mark.csdn.concurrent.immutable.finalkeywork;

/**
 * @Description: 面试题1：final修饰String
 * @Author: Mark
 * @CreateDate: 2020/3/26 9:36
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
public class FinalStringExample1 {

    public static void main(String[] args) {
        String a = "markyi";
        final String b = "mark"; //final 修饰的变量，会被看作常量
        String d = "mark";
        String c = b + "yi"; //c和a会指向同一个地址（常量池）
        String e = d + "yi"; //e的值需要在运行时才能确定，e的值会存放在堆中
        String f = "mark" + "yi";
        System.out.println((a == c)); //true ：a和c指向常量池，此时他们的值是相等的！
        System.out.println((a == e)); //false ：a执行常量池，e指向堆，此时他们的值是不相等的！
        System.out.println((a == f)); //true 思考为什么
    }

}
