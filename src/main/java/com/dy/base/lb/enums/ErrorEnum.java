package com.dy.base.lb.enums;


/**
 * 错误码
 *
 * @author: saiwei2
 * @date: 2020-6-21 18:02:55
 */
public enum ErrorEnum {

    /**
     * 错误码分内外两种
     * 对内使用最细粒度错误吗，对外使用统一错误码
     * 对外统一使用本类型第一个错误码。
     */

    RES_CODE_SUCCESS(0, 0, "成功"),
    RES_CODE_FAIL(-1, 900001, "失败"),
    RES_CODE_SYS_INVALID_TOKEN(101412, 21101, "用户身份过期,请重新登录。"),
    RES_CODE_SYS_DISABLED_TOKEN(101413, 21102, "账户在其他地方登录,请重新登录。"),
    RES_CODE_SYS_WHITE_REFER_ERROR(101413, 21103, "未通过白名单检查"),
    ;

    private final int errorCode;
    private final int parentCode;
    private final String errorMessage;

    ErrorEnum(int parentCode, int errorCode, String errorMessage) {
        this.parentCode = parentCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getParentCode() {
        if (String.valueOf(errorCode).startsWith("1")) {
            return errorCode;
        }

        return parentCode;
    }

    public ErrorEnum getOutError() {
        return getErrorByCode(getParentCode());
    }

    public static ErrorEnum getErrorByCode(int code) {
        for (ErrorEnum errorEnum : values()) {
            if (errorEnum.getErrorCode() == code) {
                return errorEnum;
            }
        }
        return null;
    }
}
