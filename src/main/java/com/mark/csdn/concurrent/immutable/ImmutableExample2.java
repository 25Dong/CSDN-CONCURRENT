package com.mark.csdn.concurrent.immutable;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Collections 声明不可变对象
 * @Author: Mark
 * @CreateDate: 2020/3/26 9:31
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
@Slf4j
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = new HashMap<>();

    static{
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        // 抛出 java.lang.UnsupportedOperationException 异常
        map.put(4, 2);
        log.info("{}",map.get(4));
    }
}
