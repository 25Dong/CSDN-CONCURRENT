/**
 * @Description: final关键字的使用
 * @Author: Mark
 * @CreateDate: 2020/5/2 12:07
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 一 知识点
 * 1.final修饰的类不能被继承
 * 2.final修饰的方法不能重写（注意但是可以重载的）
 * 3.final修饰的变量不能重新赋值(在类的 准备 阶段被赋值)
 * 补充类的生命周期：加载 —— 验证 —— 【准备】 —— 解析 —— 初始化 —— 使用 —— 卸载
 *
 * final变量的赋值时机
 *  修饰全局变量 {@link com.mark.csdn.concurrent.immutable.finalkeywork.FinalVariableExample1}
 *  修饰全局静态变量 {@link com.mark.csdn.concurrent.immutable.finalkeywork.FinalVariableExample2}
 *  修饰局部变量 {@link com.mark.csdn.concurrent.immutable.finalkeywork.FinalVariableExample3}
 *
 * final的注意点
 *  final修饰方法 {@link com.mark.csdn.concurrent.immutable.finalkeywork.FinalMethodDemo}
 *  final修饰类 {@link com.mark.csdn.concurrent.immutable.finalkeywork.FinalClassDemo}
 *
 * 面试题
 * 1.final修饰String {@link com.mark.csdn.concurrent.immutable.finalkeywork.FinalStringExample1}
 * 2.final修饰方法的返回值 {@link com.mark.csdn.concurrent.immutable.finalkeywork.FinalStringExample2}
 */
package com.mark.csdn.concurrent.immutable.finalkeywork;