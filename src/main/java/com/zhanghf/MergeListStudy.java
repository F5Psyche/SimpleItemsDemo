package com.zhanghf;

import com.beust.jcommander.internal.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * list合并学习
 *
 * @author zhanghf
 * @version 1.0
 * @date 14:02 2020/4/15
 */
public class MergeListStudy {

    public List<Map<String, String>> listMerge(List<Map<String, String>> list1, List<Map<String, String>> list2, String fieldName) {
        if (fieldName.isEmpty()) {
            return Collections.emptyList();
        }
        Map<String, String> maps = new HashMap<>();
        List<Map<String, String>> list3 = list1.stream()
                .map(map -> list2.stream()
                        .filter(m -> StringUtils.equals(m.get(fieldName), map.get(fieldName)))
                        .findAny().map(m -> {
                            System.out.println("1");
                            if (StringUtils.equals(m.get(fieldName), map.get(fieldName))) {
                                maps.put(fieldName, m.get(fieldName));
                            } else {
                                return null;
                            }
                            return maps;
                        }).orElse(null))
                .filter(Objects::nonNull).collect(Collectors.toList());
        return list3;

    }

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("one","two");
        Map<String, String> maps = new HashMap<>();
        System.out.println(list.stream().map(String::toUpperCase).collect(Collectors.toList()));

        List<Map<String, String>> list1 = list.stream().map(map -> {
            maps.put("ss", "ss");
            return maps;
        }).collect(Collectors.toList());
        System.out.println(list1);
    }

}
