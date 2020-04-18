package com.meamei.baseConfig;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口数据返回包
 *
 * @param <T> 数据类型
 */
@Data
public final class RestResponse<T> implements Serializable {
    /**
     * 成功
     */
    public static int CODE_SUCCESS = 0;
    /**
     * 一般错误
     */
    public static int CODE_COMMON_ERROR = 1;
    /**
     * 用户未登录
     */
    public static int CODE_USER_NOT_LOGIN = 2;
    /**
     * 警告信息
     */
    public static int WARN = 3;
    /**
     * 未知错误（服务器内部错误，前端看情况提示）
     */
    public static int CODE_UNKNOWN_ERROR = 4;

    /**
     * 重复登陆
     */
    public static int CODE_USER_REPEAT_LOGIN = 5;

    /**
     * 当前手机号未注册（微信）
     */
    public static int CODE_USER_NOT_REGISTER = 6;

    /**
     * 当前用户未绑定手机号（微信）
     */
    public static int CODE_USER_NOT_BIND = 7;

    /**
     * 没有权限访问
     */
    public static int CODE_NOT_AUTHORIZATION = 8;

    /**
     * 用户未登录
     */
    public static String MESSAGE_USER_NOT_LOGIN = "用户未登录";

    /**
     * 用户重复登录
     */
    public static String MESSAGE_USER_REPEAT_LOGIN = "重复登录";

    /**
     * 未知错误
     */
    public static String MESSAGE_UNKNOWN_ERROR = "未知错误";

    /**
     * 当前手机号未注册（微信）
     */
    public static String MESSAGE_USER_NOT_REGISTER = "当前手机号未注册";

    /**
     * 当前用户未绑定手机号（微信）
     */
    public static String MESSAGE_USER_NOT_BIND = "请先绑定手机号码";

    /**
     * 没有权限访问
     */
    public static String MESSAGE_NOT_AUTHORIZATION = "没有权限访问";

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 返回数据
     */
    private T data;

    private RestResponse(Integer code, String errmsg, T data) {
        this.code = code;
        this.errmsg = errmsg;
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data 数据
     * @param <T>  数据类型
     */
    public static <T> RestResponse<T> success(T data) {
        return new RestResponse<>(CODE_SUCCESS, null, data);
    }

    /**
     * 失败
     *
     * @param errmsg 出错消息
     */
    public static <T> RestResponse<T> error(String errmsg) {
        return new RestResponse<>(CODE_COMMON_ERROR, errmsg, null);
    }

    /**
     * 警告
     *
     * @param warnmsg 警告消息
     */
    public static <T> RestResponse<T> warn(String warnmsg) {
        return new RestResponse<>(WARN, warnmsg, null);
    }

    /**
     * 失败
     *
     * @param code   出错码
     * @param errmsg 出错消息
     */
    public static <T> RestResponse<T> error(int code, String errmsg) {
        if (code == 0) {
            return new RestResponse<>(CODE_COMMON_ERROR, errmsg, null);
        }
        return new RestResponse<>(code, errmsg, null);
    }

    /**
     * 失败
     *
     * @param code   出错码
     * @param errmsg 出错消息
     */
    public static <T> RestResponse<T> error(int code, String errmsg, T data) {
        if (code == 0) {
            return new RestResponse<>(CODE_COMMON_ERROR, errmsg, data);
        }
        return new RestResponse<>(code, errmsg, data);
    }

    /**
     * 用户未登录
     */
    public static <T> RestResponse<T> USER_NOT_LOGIN() {
        return error(CODE_USER_NOT_LOGIN, MESSAGE_USER_NOT_LOGIN);
    }

    /**
     * 用户重复登录
     */
    public static <T> RestResponse<T> USER_REPEAT_LOGIN() {
        return error(CODE_USER_REPEAT_LOGIN, MESSAGE_USER_REPEAT_LOGIN);
    }

    /**
     * 未知错误
     */
    public static <T> RestResponse<T> UNKNOWN_ERROR() {
        return error(CODE_UNKNOWN_ERROR, MESSAGE_UNKNOWN_ERROR);
    }

    /**
     * 当前手机号未注册
     */
    public static <T> RestResponse<T> USER_NOT_REGISTER() {
        return error(CODE_USER_NOT_REGISTER, MESSAGE_USER_NOT_REGISTER);
    }
}

