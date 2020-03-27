package com.zhanghf;

import com.alibaba.fastjson.JSON;
import com.zhanghf.po.WfMenuConfigInfo;
import com.zhanghf.util.CommonUtils;
import com.zhanghf.util.HttpConnectionUtils;
import com.zhanghf.vo.InnerMatterMaterialInfoVO;
import com.zhanghf.vo.MatterProcessMenuVO;
import com.zhanghf.vo.MenuValueVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author zhanghf
 * @version 1.0
 * @date 21:20 2020/3/6
 */
@Slf4j
public class App {

    private static String text = "[{\"areaCode\":\"330100\",\"children\":[{\"areaCode\":\"330102\",\"children\":[],\"menuValueList\":[{\"channelType\":\"01\",\"menuId\":\"1453\",\"menuName\":\"\",\"menuUrl\":\"\"},{\"channelType\":\"01\",\"menuId\":\"1453\",\"menuName\":\"\",\"menuUrl\":\"\"}],\"systemName\":\"上城区经办系统\",\"systemId\":\"tjhjffjjd\"}],\"menuValueList\":[{\"channelType\":\"01\",\"menuId\":\"1453\",\"menuName\":\"\",\"menuUrl\":\"\"},{\"channelType\":\"01\",\"menuId\":\"1453\",\"menuName\":\"\",\"menuUrl\":\"\"}],\"systemName\":\"杭州市经办系统\",\"systemId\":\"askfhsf\"}]";


    private static String text1 = "{\n" +
            "    \"addressInfo\": [\n" +
            "        {\n" +
            "            \"id\": \"54915\",\n" +
            "            \"localInnerCode\": \"cae90e51-11b4-4057-b529-8f720514804d\",\n" +
            "            \"address\": \"杭州市体育场路538号金祝大厦三楼省医保中心服务大厅\",\n" +
            "            \"addressKind\": \"2\",\n" +
            "            \"acceptTimeDesc\": \"工作日上午8:30-12:00、下午13:30-17:00\",\n" +
            "            \"phone\": \"\",\n" +
            "            \"uuid\": \"无\",\n" +
            "            \"isInner\": \"0\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"materialInfos\": [\n" +
            "        {\n" +
            "            \"extInfo\": \"\",\n" +
            "            \"gmtCreate\": \"\",\n" +
            "            \"gmtModified\": \"\",\n" +
            "            \"imageMaterialNums\": \"3\",\n" +
            "            \"isFile\": \"1\",\n" +
            "            \"isNeed\": \"1\",\n" +
            "            \"matId\": \"\",\n" +
            "            \"materialCode\": \"c05b4512-f46a-4df5-8b25-16e4eecd777f\",\n" +
            "            \"materialForm\": \"5\",\n" +
            "            \"materialId\": \"80\",\n" +
            "            \"materialName\": \"一就很难米糠粕\",\n" +
            "            \"materialSource\": \"1\",\n" +
            "            \"paperMaterialNums\": \"3\",\n" +
            "            \"userCreate\": \"\",\n" +
            "            \"userModified\": \"\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"matterInfo\": {\n" +
            "        \"acptCond\": \"\",\n" +
            "        \"chargeBasis\": \"\",\n" +
            "        \"chargeStandard\": \"\",\n" +
            "        \"hndlKind\": \"\",\n" +
            "        \"matId\": \"\",\n" +
            "        \"isProv\": \"0\",\n" +
            "        \"localInnerCode\": \"cae90e51-11b4-4057-b529-8f720514804d\",\n" +
            "        \"matName\": \"享受规定（特殊慢性）病种待遇备案\",\n" +
            "        \"matCode\": \"331619996000\",\n" +
            "        \"serviceCode\": \"联办事项-19996-000\",\n" +
            "        \"serviceCodeId\": \"16-19996-000\",\n" +
            "        \"matSettingBasis\": \"待补充\\r\\n浙江省人力资源和社会保障厅浙江省财政厅关于印发《浙江省省级单位职工医疗保险办法》的通知（浙人社发﹝2016﹞113号）：“第三十八条参保人员罹患疾病属于省级基本医疗保险规定病种的，应及时向省医保中心备案，门诊治疗（含异地就医及转外门诊治疗）规定病种疾病发生的医疗费用按照住院医疗费用结算，但不设住院起付标准。\\n规定病种指各类恶性肿瘤、系统性红斑狼疮、血友病、再生障碍性贫血、儿童孤独症、精神分裂症、情感性精神病，以及慢性肾功能衰竭的透析治疗和器官移植后的抗排异治疗。”\\r\\n\\r\\n\",\n" +
            "        \"statutoryDeadlineUnit\": \"1\",\n" +
            "        \"consultMethod\": \"0571-85112893\",\n" +
            "        \"superviseMethod\": \"0571-81051032\",\n" +
            "        \"outProcess\": \"http://zjqlk.oss-cn-hangzhou.aliyuncs.com/6/2/256ef705-bb89-44fa-8f62-a0f515ece43d.png\",\n" +
            "        \"entityName\": \"省医疗保障局\",\n" +
            "        \"ouGuid\": \"001003148\",\n" +
            "        \"jurisCode\": \"330000\",\n" +
            "        \"gmtCreate\": \"1585099191000\",\n" +
            "        \"gmtModified\": \"1585099191000\",\n" +
            "        \"isStatus\": \"1\",\n" +
            "        \"isBlockchain\": \"1\",\n" +
            "        \"isRepeat\": \"1\",\n" +
            "        \"otMatAllow\": \"\"\n" +
            "    },\n" +
            "    \"matterUserInfos\": [\n" +
            "        {\n" +
            "            \"extInfo\": \"\",\n" +
            "            \"gmtCreate\": \"\",\n" +
            "            \"gmtModified\": \"\",\n" +
            "            \"id\": 0,\n" +
            "            \"matId\": \"\",\n" +
            "            \"userCard\": \"\",\n" +
            "            \"userCreate\": \"\",\n" +
            "            \"userEmail\": \"3\",\n" +
            "            \"userModified\": \"\",\n" +
            "            \"userName\": \"3\",\n" +
            "            \"userPhone\": \"3\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private static List<Long> menuConfigInfosSave(String uuid, Long matProcNodeId, String parentAreaCode, List<MatterProcessMenuVO> vos) {
        for (MatterProcessMenuVO vo : vos) {
            List<MatterProcessMenuVO> children = vo.getChildren();
            if (CommonUtils.entityNotEmpty(uuid, children)) {
                menuConfigInfosSave(uuid, matProcNodeId, vo.getAreaCode(), children);
            }
            List<MenuValueVO> menuValueList = vo.getMenuValueList();
            for (MenuValueVO menuValueVO : menuValueList) {
                WfMenuConfigInfo menuConfigInfo = new WfMenuConfigInfo();
                menuConfigInfo.setMatProcNodeId(matProcNodeId);
                menuConfigInfo.setSystemId(vo.getSystemId());
                menuConfigInfo.setSystemName(vo.getSystemName());
                menuConfigInfo.setMenuId(menuValueVO.getMenuId());
                menuConfigInfo.setMenuName(menuValueVO.getMenuName());
                menuConfigInfo.setMenuUrl(menuValueVO.getMenuUrl());
                menuConfigInfo.setChannelType(menuValueVO.getChannelType());
                menuConfigInfo.setOuGuid(vo.getOuGuid());
                menuConfigInfo.setAreaCode(vo.getAreaCode());
                menuConfigInfo.setParentAreaCode(parentAreaCode);
                log.info("uuid={}, menuConfigInfo={}", uuid, menuConfigInfo);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        InnerMatterMaterialInfoVO vo = JSON.parseObject(text1, InnerMatterMaterialInfoVO.class);
        Object object = HttpConnectionUtils.httpConnectionPost(UUID.randomUUID().toString(),"http://localhost:8080/annotation/test",JSON.parseObject(text1));
        System.out.println(object);

    }












}
