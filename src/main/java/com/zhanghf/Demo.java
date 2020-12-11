package com.zhanghf;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.zhanghf.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:02 2020/9/3
 */
@Slf4j
public class Demo {


    private static List<Map<String, Object>> getListInfo() {
        List<Map<String, Object>> initList = Lists.newArrayList(
                ImmutableMap.of("AAB301", "330000", "AAB304", "000000", "AAE044", "浙江省", "AAB303", "1"),
                ImmutableMap.of("AAB301", "330100", "AAB304", "330000", "AAE044", "杭州市", "AAB303", "2"),
                ImmutableMap.of("AAB301", "330200", "AAB304", "330000", "AAE044", "宁波市", "AAB303", "2"),
                ImmutableMap.of("AAB301", "330300", "AAB304", "330000", "AAE044", "温州市", "AAB303", "2"),
                ImmutableMap.of("AAB301", "330400", "AAB304", "330000", "AAE044", "嘉兴市", "AAB303", "2"),
                ImmutableMap.of("AAB301", "330101", "AAB304", "330100", "AAE044", "杭州市市本级", "AAB303", "3"),
                ImmutableMap.of("AAB301", "330102", "AAB304", "330100", "AAE044", "上城区", "AAB303", "3"),
                ImmutableMap.of("AAB301", "330103", "AAB304", "330100", "AAE044", "下城区", "AAB303", "3"),
                ImmutableMap.of("AAB301", "330104", "AAB304", "330100", "AAE044", "江干区", "AAB303", "3"),
                ImmutableMap.of("AAB301", "330105", "AAB304", "330100", "AAE044", "拱墅区", "AAB303", "3"),
                ImmutableMap.of("AAB301", "330402", "AAB304", "330400", "AAE044", "南湖区", "AAB303", "3"),
                ImmutableMap.of("AAB301", "330411", "AAB304", "330400", "AAE044", "秀洲区", "AAB303", "3"),
                ImmutableMap.of("AAB301", "330421", "AAB304", "330400", "AAE044", "嘉善县", "AAB303", "3"),
                ImmutableMap.of("AAB301", "330424", "AAB304", "330400", "AAE044", "海盐县", "AAB303", "3"),
                ImmutableMap.of("AAB301", "330481", "AAB304", "330400", "AAE044", "海宁市", "AAB303", "3"),
                ImmutableMap.of("AAB301", "330482", "AAB304", "330400", "AAE044", "平湖市", "AAB303", "3"),
                ImmutableMap.of("AAB301", "330483", "AAB304", "330400", "AAE044", "桐乡市", "AAB303", "3"));
        List<Map<String, Object>> list = initList
                .stream()
                .filter(map -> "330400".equals(map.get("AAB304")))
                .collect(Collectors.toList());
        log.info("initList={}, list={}", initList, list);
        return list;
    }

    public static void step1Test(){
        Map<String, Object> map = null;
        map.get("xx");
    }

    public static void step2Test(){
        step1Test();
    }

    public static void main(String[] args) {
        try {
            step2Test();
        } catch (Exception e) {
            log.error("message={}", CommonUtils.getStackTraceString(e));
        }

    }
}
