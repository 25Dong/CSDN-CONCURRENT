package com.mark.csdn.concurrent.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * @Description: 使用com.google.common.collect下的类保证不可变对象
 * @Author: Mark
 * @CreateDate: 2020/3/26 9:32
 * @Version: 3.00.001
 * @Copyright : 豆浆油条个人非正式工作室
 */
public class ImmutableExample3 {

    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);
    private final static ImmutableSet<Integer> set = ImmutableSet.copyOf(list);
    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);
    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer,Integer>builder()
            .put(1, 2)
            .put(3, 4)
            .put(5, 6)
            .build();


    public static void main(String[] args) {
        System.out.println(map.get(1));
    }
}
