/**
 * @Description: ThreadLocal实现线程封闭
 * @Author: Mark
 * @CreateDate: 2020/5/5 13:46
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 一 使用场景
 * 1.每个线程需要独享一个对象（通常是工具类，eg：SimpleDateFormat和Random） {@link com.mark.csdn.concurrent.threadlocal.ThreadLocalSceneExample1}
 * 2.每个线程内需要保存全局变量（例如在拦截器中获取用户信息），可以让不同的方法直接使用，避免参数传递的麻烦 {@link com.mark.csdn.concurrent.threadlocal.scene2}
 *
 * 二 底层细节
 * 1.使用线性探测法（如果发生了哈希冲突，则继续寻找下一个空位置)，解决Hash冲突问题
 * 2.结合Thread，ThreadLocalMap进行分析 {@link com.mark.csdn.concurrent.threadlocal.ThreadLocalNPE}
 * 3.各个方法源码
 *
 * 三 需要注意的问题
 * 1.NPE
 * 2.内存泄露问题
 */
package com.mark.csdn.concurrent.threadlocal;