package com.zhanghf.service;

import com.zhanghf.annotation.LoginInfo;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhanghf
 * @version 1.0
 * @date 17:23 2020/4/15
 */
@Slf4j
@Service
public class AnnotationService {

    @LoginInfo(loginName = "admin")
    public ResultVo loginUserName1(Map<String, String> map) {
        log.info("userName={}", "admin");
        return null;
    }

    @LoginInfo(loginName = "user")
    public ResultVo loginUserName2(Map<String, String> map) {
        log.info("userName={}", "user");
        return null;
    }
}
