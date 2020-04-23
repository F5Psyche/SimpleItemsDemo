package com.zhanghf;

import com.google.common.collect.Lists;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:35 2020/4/23
 */
@Slf4j
public class PropertyUtilsTest {

    @Test
    public void test() {
        try {
            Map<String, Object> map = new HashMap<>(5);
            map.put("success", true);
            map.put("result", Lists.newArrayList("1", "2"));
            ResultVo resultVo = new ResultVo();
            log.info("map={}, resultVo={}", map, resultVo);
            PropertyUtils.copyProperties(resultVo, map);
            log.info("map={}, resultVo={}", map, resultVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
