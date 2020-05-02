/**
 * @Description: synchronized
 * @Author: Mark
 * @CreateDate: 2019/6/30 11:27
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 *
 * 特点 ：
 *  1.拥有锁重入，也就是说当一个线程的得到一个对象锁后，再次请求对象锁的时候还是可以得到对象锁的；
 *  2.不具有继承性；
 *  3.出现异常，自动释放锁；
 *  4.是非公平锁：在释放锁的时候，任何一个等待的线程都有机会获得锁，而不是按照申请获得锁的时间顺序来获得锁;
 *  5.锁的实现是依靠JVM。
 *
 *  作用范围：
 *  1.修饰一个代码块，作用于调用对象；
 *  2.修饰一个方法，作用于调用的对象；
 *  3.修饰一个静态的方法，作用于该类的所有对象；
 *  4.修饰一个类，作用于该类的所有对象；
 *  补充：synchronized修饰代码块时，而该方法的所有都声明在代码块时 和  synchronized修饰方法时等价的
 */
package com.mark.csdn.concurrent.sync;