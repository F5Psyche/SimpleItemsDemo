package com.zhanghf.po;

import lombok.Data;

/**
 * @author zhanghf
 * @version 1.0
 * @date 16:18 2020/2/10
 */
@Data
public class PowerMatterInfo {
    //
//    private String adminCounterPart;//行政相对人性质
//
//    private String attachment;//附件信息
//
//    private Map<String, String> basicExtMap;//基本扩展信息
//    private String bizLineCode;//业务条线代码
//    private String consultMethod;//咨询方式
//    private String csjApply;//长三角异地通办
//
//    private String discretion;//自由裁量
//    private String entityCode;//实施主体代码

//    private String entityNature;//实施主体性质
//    private String entityOid;//实施主体唯一代码
//    private String entrust;//委托下放
//    private String entrustedDept;//委托部门
//    private String gmtCreate;//创建时间
//    private String gmtModified;//修改时间
//    private Long id;

//    private String industrySubjectClassify;//行业主题分类

//    private String lbsxCode;//联办事项编码

//    private String legalConcern;//法人关注点
//    private String legalObjectClassify;//面向法人的对象分类

//

//    private String matSettingBasis;//设定依据
//
//    private String otherRightSubType;//其他行政权力子类型

//
//
//    private String personalConcern;//个人关注点
//    private String personalObjectClassify;//面向个人的对象分类
//    private String planEndTime;//计划取消时间
//    private String planStartTime;//计划生效时间
//    private String publicServiceSubType;//公共服务事项子类型
//    private String pyc;//最多跑一次
//    private String rightAttribute;//权力属性
//    private String rightSource;//权力来源
//    private String rightSourceMethod;//权力来源方式

    private String entityName;//实施主体名称

    private String matCode;//事项编码
    private String matCommonName;//事项通俗名称
    private String matName;//事项名称
    private String impleCode;//实施编码
    private String impleLevel;//行使层级
    private String impleStatus;//实施状态
    private String impleType;//实施类型
    private String impleVersion;//实施版本
    private String mainItem;//是否主项
    private String mainItemCode;//主项代码
    private String subItem;//是否子项
    private String subItemCode;//子项代码
    private String detailItem;//是否细项outProcess
    private String detailItemCode;//细项代码

    private String localInnerCode;//地方内部编码
    private String localRowGuid;//权力事项库ROWGUID

    private String adCode;//区域编码
    private String jurisCode;//辖区代码
    private String ouGuid;//权力事项库部门编码
    private String ouTypeCode;//事项所属业务类型

    private String statutoryDeadline;//法定办结时限
    private String statutoryDeadlineUnit;//法定办结时限单位

    private String acceptAddress;//受理地点
    private String leadDept;//实施或牵头的处（科）室名称
    private String outProcess;//外部流程图

    /**
     * 监督投诉方式
     */
    private String superviseMethod;

//    private String onlineAppoint;//在线预约
//    private String appOnlineAppointment;//是否APP端在线预约，1表示可以，0表示不可以
//    private String appOnlineTransact;//移动端是否网上办理
//    private String pcOnlineAppointment;//是否PC端在线预约，1表示可以在线预约，0表示不可以
//    private String pcOnlineTransact;//电脑端是否网上办理
//    private String bakNote;//备注
}
