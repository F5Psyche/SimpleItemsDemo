package com.zhanghf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.gov.api.response.AtgBizMatterqueryMatterQuerydetailResponse;
import com.zhanghf.po.AddressInfo;
import com.zhanghf.util.XmlMapUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Map;

@Slf4j
public class AppTest {

    private static final String dtos = "[{\"materialNecessityDesc\":\"\",\"instructions\":\"\",\"gmtModified\":\"\",\"bizCode\":\"\",\"sourceChannel\":\"1\",\"memo\":\"\",\"mtIssueUnit\":\"\",\"sampleForm\":\"\",\"sourceChannelDesc\":\"\",\"blankForm\":\"\",\"materialForm\":\"4\",\"materialNecessity\":\"1\",\"materialExtMap\":{},\"id\":\"0\",\"blankFormName\":\"\",\"paperMaterialSpecific\":\"原件\",\"requestMaterialBasis\":\"\",\"materialType\":\"1\",\"sort\":\"1\",\"gmtCreate\":\"\",\"materialName\":\"社会保障卡\",\"materialDetailRequires\":\"\",\"impleCode\":\"bb4a9da36852d21c0168925f9cb519672331619995000\",\"paperMaterialNums\":\"1\",\"acceptCriteria\":\"\",\"sampleFormName\":\"\",\"localMaterialId\":\"7bd81477-e039-4d91-82e8-9a0cb2a92ace\",\"rq\":\"0\"}]";

    private static final String ACCEPTADDRESS = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>\n" +
            "<DATAAREA>\n" +
            "<ACCEPT_ADDRESSS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>义乌市行政服务中心（义乌市北苑街道望道路300号）</ADDRESS>\n" +
            "<ADDRESS_KIND>1</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>上午8：30-11：30；下午13：00-17：00</ACCEPT_TIMEDESC>\n" +
            "<PHONE/>\n" +
            "<UUID>e65221e1-6e8a-4768-abe5-f296469c7d2d</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "</ACCEPT_ADDRESSS>\n" +
            "</DATAAREA>";

    private static final String ACCEPTADDRESS2 = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?>\n" +
            "<DATAAREA>\n" +
            "<ACCEPT_ADDRESSS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>杭州市萧山区社会保障管理中心（杭州市萧山区城厢街道萧然南路373号）</ADDRESS>\n" +
            "<ADDRESS_KIND>2</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>法定工作日，冬令时8:30-11:45 13:30-17:15 夏令时8:30-11:45 14:00-17:45</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-12333</PHONE>\n" +
            "<UUID>ad354b5f-cea2-4d76-b05a-6cfb628c156b</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>杭州市萧山区行政服务中心（杭州市萧山区北干街道市心中路1069号（科技创新中心B楼））</ADDRESS>\n" +
            "<ADDRESS_KIND>1</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>法定工作日，冬令时8:30-11:45 13:00-16:45 夏令时8:30-11:45 13:30-17:15</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-12333</PHONE>\n" +
            "<UUID>55e46577-2fcf-4419-ac28-023d1ea96225</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>萧山区办事服务中心浦阳江（临浦）分中心（杭州市萧山区临浦镇元宝山路1号）</ADDRESS>\n" +
            "<ADDRESS_KIND>2</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>法定工作日，冬令时8:30-11:30 13:00-16:30 夏令时8:30-11:30 13:30-17:00</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82287007</PHONE>\n" +
            "<UUID>0d457047-d4d8-480b-92ca-9f672c4f1e63</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>萧山区办事服务中心瓜沥分中心（杭州市萧山区瓜沥镇政通路100号）</ADDRESS>\n" +
            "<ADDRESS_KIND>2</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>法定工作日，冬令时8:30-11:45 13:00-16:15 夏令时8:30-11:45 13:30-16:45</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-57152666</PHONE>\n" +
            "<UUID>bdc01f5c-a790-4b36-a526-f2c35e30845a</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>萧山区南阳街道社会服务管理中心（杭州市萧山区南阳街道向阳路762号）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>夏季工作时间上午8:15—11:15 下午13:30—16:30。 冬季时间上午8:15—11:15 下午13:00—16:15（周一到周五法定节假日除外）</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82188527</PHONE>\n" +
            "<UUID>2b535983-88a2-4b03-a01b-d269016c611c</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>杭州市萧山区新街街道办事服务中心（杭州市萧山区新街街道北塘东路798号（萧山新街派出所旁边））</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>夏季：8：00－11：00，13:30-17:00；冬季：8：30－11：30，13:00-16:30；</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82613768</PHONE>\n" +
            "<UUID>c32a33c7-7b04-4782-a81e-777752e74042</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>萧山区靖江街道办事服务中心（杭州市萧山区靖江街道申达路255号）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>夏令8:15-11:00 13:30-16:45 冬令8:30-11:30 13:00-16:15</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82912790</PHONE>\n" +
            "<UUID>56125202-d56f-4d7b-9d6e-023ae503d801</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>萧山区浦阳镇办事服务中心（杭州市萧山区浦阳镇振浦路328号）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>上午8:00--11:30，下午13:30--16:00</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82279055</PHONE>\n" +
            "<UUID>2897f692-ae6f-4b4c-8535-1cf5a1f43c72</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>萧山区益农镇办事服务中心（杭州市萧山区益农镇兴贸路88号）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>周一至周五（法定节假日除外）夏令时：上午8:15—11:15；下午13:30—16:30；冬令时：上午8:15—11:15；下午13:00—16:00；</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-83529991</PHONE>\n" +
            "<UUID>edb39adc-0d48-4511-b2af-27a8e98fb8e1</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>萧山区戴村镇办事服务中心（杭州市萧山区戴村镇人民政府旁（桥戴线与03省道交叉口））</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>冬季上午8:30—11:30，下午13:15—16:15。夏季上午8:30—11:30，下午13:30—4:45</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82238216</PHONE>\n" +
            "<UUID>aeedc766-f43a-4df4-888f-a532d0940658</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>杭州市萧山区蜀山街道办事服务中心（鲁公桥社区126号）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>夏季就8:00-11:00 13:30-17:00 冬季8:30-11:15 13:30-17:00（法定节假日除外）</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-83527306</PHONE>\n" +
            "<UUID>无</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>杭州市萧山区城厢街道文化路181号</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>夏季工作日：上午8:30-11:30，下午14:00-17:30；冬季工作日：上午8:30-11:30，下午13:30-17:00</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82725543</PHONE>\n" +
            "<UUID>无</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>萧山区河上镇办事服务中心（杭州市萧山区河上镇胜达路农商银行对面）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>周一至周五（法定节假日除外），夏季 上午8:30—11:15，下午13:30—16:30，冬季 上午8:30—11:30，下午13:00—16:00。电话：0571-82616852。</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82206973</PHONE>\n" +
            "<UUID>7458449f-fa86-4f12-8f21-ecdf1e51168d</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>杭州市萧山区所前镇党群服务中心（缪家村大治桥南17号）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>夏季：工作日上午8:00-11:00，下午13:30-17:00；冬季：工作日上午8:00-11:00，下午13:00-16:30;公休假日休息</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82772327</PHONE>\n" +
            "<UUID>无</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>杭州市萧山区党湾镇办事服务中心（杭州市萧山区党湾镇镇中路55号）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>夏季工作日：上午8:15-11:15，下午13:30-16:30；冬季工作日：上午8:30-11:30，下午13:15-16:15</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82104972</PHONE>\n" +
            "<UUID>1a2dbd90-8806-4ef0-9736-ff89f7851665</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>萧山区楼塔镇办事服务中心（杭州市萧山区楼塔镇洲口路（老卫生院））</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>周一至周五（法定节假日除外），夏季 上午8:30—11:30，下午13:30—16:30，冬季 上午8:30—11:30，下午13:00—16:00</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82202141</PHONE>\n" +
            "<UUID>b4cb4f7f-9966-4bb4-ad09-9c04f3816662</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>萧山区闻堰街道办事服务中心（杭州市萧山区湘湖路3388号湘湖艺术中心东大楼）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>周一至周五（法定节假日除外），夏季 上午8:30—11:30，下午13:30—17:00，冬季 上午8:30—11:30，下午13:00—16:30</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82300083</PHONE>\n" +
            "<UUID>27a9c04e-f2bd-47e3-9172-b6568b5c2de6</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>杭州市萧山区衙前镇人民政府（杭州市萧山区衙前镇衙前路350号）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>夏季：上午8:00-11:00，下午13:30-17:00冬季：上午8:00-11:00，下午13:00-16:30（法定节假日除外）</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82151702</PHONE>\n" +
            "<UUID>e7e2c128-8258-4ae7-9958-75c21470d434</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>萧山区新塘街道办事处（杭州市萧山区南秀路2699号）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>春8:00-11:00；1：30-5:00 秋8:00-11:00；1：00-4:30</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-83713018</PHONE>\n" +
            "<UUID>598303b8-28d9-487d-b8ed-d277c86d18a1</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>杭州市萧山区进化镇办事服务中心（葛云飞路768号）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>夏冬均为 上午 8:00-11:00，下午13:30-16:30；公休假日休息</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82453783</PHONE>\n" +
            "<UUID>无</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>杭州市萧山区义桥镇人民政府一楼</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>夏季：上午08:00-11:00，下午13:30-17:00；冬季：上午08:00-11:00，下午13:00-16:30;公休假日休息</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82172331</PHONE>\n" +
            "<UUID>无</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>宁围街道公共服务中心 地址：杭州市萧山区利华路558号（宁围派出所马路正对面，宁围市民公园内西侧）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>夏令时：上午08：30-11：20下午13：30-17：00；冬令时：上午08：30-11：20下午13:00-16:30;公休假日休息</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82564992</PHONE>\n" +
            "<UUID>无</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "<ACCEPT_ADDRESS>\n" +
            "<ADDRESS>萧山区北干街道办事服务中心（杭州市萧山区育才北路517号）</ADDRESS>\n" +
            "<ADDRESS_KIND>6</ADDRESS_KIND>\n" +
            "<ACCEPT_TIMEDESC>夏季：上午8:30-11:00，下午13:30-17:30；冬季：上午8:30-11:00，下午13:00-17:00</ACCEPT_TIMEDESC>\n" +
            "<PHONE>0571-82817619</PHONE>\n" +
            "<UUID>d57de7d1-1fae-439d-8a7e-645b94260ef6</UUID>\n" +
            "</ACCEPT_ADDRESS>\n" +
            "</ACCEPT_ADDRESSS>\n" +
            "</DATAAREA>";

    @Test
    public void httpTest() {
        //atg.biz.matterquery.matter.querydetail
        AtgBizMatterqueryMatterQuerydetailResponse response = null;
        Map<String, Object> map = XmlMapUtils.xmlToMap(ACCEPTADDRESS2);
        String dataArea = JSON.toJSONString(map.get("DATAAREA"));
        JSONObject json = JSON.parseObject(dataArea);
        JSONObject acceptAddresss = json.getJSONObject("ACCEPT_ADDRESSS");
        String key = "ACCEPT_ADDRESS";
        String simpleName = acceptAddresss.get(key).getClass().getSimpleName();
        try {
            if ("JSONObject".equals(simpleName)) {
                AddressInfo adressInfo = acceptAddresss.getObject(key, AddressInfo.class);
                System.out.println(adressInfo);
            } else if ("JSONArray".equals(simpleName)) {
                List<AddressInfo> adressInfos = JSON.parseArray(acceptAddresss.getString(key), AddressInfo.class);
                for (AddressInfo adressInfo : adressInfos) {
                    System.out.println(adressInfo);
                }
            }
        } catch (Exception e) {
            log.error("uuid={}, acceptAddresss={}, errMsg={}", "", acceptAddresss, e.toString());
        }
    }

}
