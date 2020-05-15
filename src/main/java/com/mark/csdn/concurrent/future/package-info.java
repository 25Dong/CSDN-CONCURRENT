/**
 * @Description: 获取子线程的执行结果 future
 * @Author: Mark
 * @CreateDate: 2020/2/5 14:51
 * @Version: 1.0
 * @Copyright : 豆浆油条个人非正式工作室
 *
 *  Runnable无返回值，不能抛出异常
 *  -{@link com.mark.csdn.concurrent.future.RunnableCantThrowsException}
 *
 * 简单演示通过线程池返回Future对象
 * -{@link com.mark.csdn.concurrent.future.OneFuture}
 *
 *
 * 通过Lambda 声明Callable实例
 * -{@link com.mark.csdn.concurrent.future.OneFutureLambda}
 *
 * 批量提交任务时，用List接收返回的Future
 * -{@link com.mark.csdn.concurrent.future.MultiFutures}
 *
 * 演示get方法过程中抛出异常
 * -{@link com.mark.csdn.concurrent.future.GetException}
 *
 * 演示get的超时方法
 * -{@link com.mark.csdn.concurrent.future.Timeout}
 *
 * 用FutureTask创建Future
 * -{@link com.mark.csdn.concurrent.future.FutureTaskDemo}
 *
 */
package com.mark.csdn.concurrent.future;