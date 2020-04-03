package com.mark.csdn.concurrent.immutable;



import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: final修饰基础变量和应用类型变量的区别
 * @Author: Mark
 * @CreateDate: 2020/3/26 9:28
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 总结：
 * 1.final修饰基础数据类型的变量时：变量的值不能改变；当修饰引用类型变量时，变量执行的地址不能改变，但是变量的属性的内容可以修改；
 * 2.final修饰类：类不可以别继承（该类中的方法也被隐式被final修饰）
 * 3.final修饰方法时：方法不可以被重写。（补充：static修改的方法的也不能被重写，但是不同的的是，子类可以声明一个和父类一样的static方法）
 */
@Slf4j
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "1";
    private final static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
    }

    /**
     * final 也可以用来修饰形参
     * @param a 形参
     */
    private void test(final int a){
//        a = 1;
    }

    public static void main(String[] args) {
        //Cannot assign a value to final variable 'a'
//        a = 2;
//        b = "2";
//        map = Maps.newHashMap();

        //虽然final声明的引用类型，不能指向别的引用，但是可以
        //改变指向内容的属性
        map.put(1, 3);
        log.info("{}",map.get(1));
    }
}
