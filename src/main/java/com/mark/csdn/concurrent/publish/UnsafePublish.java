package com.mark.csdn.concurrent.publish;

import com.mark.csdn.concurrent.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Description: 不安全发布对象的演示
 * @Author: Mark
 * @CreateDate: 2020/4/22 22:54
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {


    private String[] states = {"a", "b", "c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.asList(unsafePublish.getStates()));

        //外部调用这修改了，发布对象的内容
        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.asList(unsafePublish.getStates()));
    }
}
