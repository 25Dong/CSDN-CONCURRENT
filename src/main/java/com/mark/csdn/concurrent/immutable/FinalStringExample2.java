package com.mark.csdn.concurrent.immutable;

/**
 * @Description: 面试题2：final修饰变量是根据方法赋值
 * @Author: Mark
 * @CreateDate: 2020/3/26 9:38
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
public class FinalStringExample2 {

    public static void main(String[] args) {
        String a = "mark2";
        final String b = getDashixiong(); //final修饰方法时，编译期无法确定b的值
        String c = b + 2;
        System.out.println(a == c); //false
    }

    private static String getDashixiong() {
        return "mark";
    }
}
