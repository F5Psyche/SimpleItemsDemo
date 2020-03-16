package com.zhanghf.enums;

/**
 * @author zhanghf
 * @version 1.0
 * @date 10:35 2020/3/11
 */
public enum BusinessCodeEnum {
    /**
     * 交易请求成功
     */
    SUCCESS("0000", "成功!"),
    TRADE_CODE_NOT_FIND("01", "交易码不存在"),
    PARAMS_ERROR("02", "传入参数有误"),
    COL_MUST_FILL("03", "字段不能为空"),
    MYSQL_DATA_ERROR("04", "联系相关人员"),
    DATA_SAVE_ERROR("05", "数据存储失败"),
    DATA_DELETE_ERROR("06", "数据删除失败"),
    DATA_UPDATE_ERROR("07", "数据更新失败"),
    DATA_QUERY_ERROR("08", "数据查询失败"),
    CHOICE_COL_MUST_FILL("04", "条件性字段不能为空"),
    REQUEST_KEY_FALSE("10", "请求密钥获取失败"),
    IMAGE_NOT_GET("11", "财政版式文件未获取"),
    QZ_TYPE("12", "签章类型"),
    READ_TIMEOUT("13", "连接超时"),


    DATA_QUERY_NULL("90", "没有查询到相关数据"),
    UNKNOWN_ERROR("99", "未知异常"),
    ;

    private String code;
    private String msg;

    BusinessCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getCode(String msg) {
        if (msg != null && msg.length() > 0) {
            for (BusinessCodeEnum type : BusinessCodeEnum.values()) {
                if (msg.contains(type.getMsg())) {
                    return type.getCode();
                }
            }
        }
        return null;
    }

    public static String getMsg(String msg) {
        if (msg != null && msg.length() > 0) {
            for (BusinessCodeEnum type : BusinessCodeEnum.values()) {
                if (msg.contains(type.getMsg())) {
                    return type.getMsg();
                }
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
