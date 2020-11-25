package com.zhanghf;

import com.google.common.collect.Lists;
import com.zhanghf.util.HttpConnectionUtils;
import com.zhanghf.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author zhanghf
 * @version 1.0
 * @date 11:32 2020/9/29
 */
@Slf4j
public class FutureStudy {

    private static ExecutorService executorService = Executors.newFixedThreadPool(20);


    public ResultVo<String> postUse(String nodeId) {
        try {
            Map<String, String> bizMapTemp = new HashMap<>(16);
            bizMapTemp.put("workorderno", "8df56f81-c0bc-480d-a883-145f2bd8e8a3");
            bizMapTemp.put("nodeid", nodeId);
            return HttpConnectionUtils.managePost("http://10.85.94.57:9099/insiis/interfaceGmApi.do", bizMapTemp, "6014");
        } catch (Exception e) {
            return new ResultVo<>();
        }
    }

    public List<String> nodeIdsQuery(List<String> nodeIds) {
        List<Future<String>> futures = new ArrayList<>();
        for (String nodeId : nodeIds) {
            Future<String> future = executorService.submit(new FutureStudy.FutureDemoClass(nodeId));
            futures.add(future);
        }
        List<String> results = futures.stream().map((i) -> {
            try {
                return i.get();
            } catch (Exception var2) {
                log.error("批量上传任务执行异常", var2);
                return "批量上传任务执行异常";
            }
        }).collect(Collectors.toList());
        executorService.shutdown();
        return results;
    }

    private class FutureDemoClass implements Callable {
        private String nodeId;

        FutureDemoClass(String nodeId) {
            this.nodeId = nodeId;
        }

        @Override
        public String call() {
            try {
                return new FutureStudy().postUse(nodeId).toString();
            } catch (Exception e) {
                return null;
            }
        }
    }


    public static void main(String[] args) {

        List<String> nodeIds = Lists.newArrayList("clcs", "sbztxx", "smcl", "dyhzd", "ywsl", "xcbdj");
        System.out.println(nodeIds);
        long step1 = System.currentTimeMillis();
        List<String> list = new FutureStudy().nodeIdsQuery(nodeIds);
        long step2 = System.currentTimeMillis();
        System.out.println(step2 - step1);
        System.out.println(list);
        List<ResultVo<String>> s = new ArrayList<>();
        for (String nodeId : nodeIds) {
            ResultVo<String> resultVo = new FutureStudy().postUse(nodeId);
            s.add(resultVo);
        }
        System.out.println(System.currentTimeMillis() - step2);
    }
}
