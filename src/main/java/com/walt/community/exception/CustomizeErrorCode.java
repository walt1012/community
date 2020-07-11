package com.walt.community.exception;

/**
 * @author: walt1012
 * @date: 2020/7/8
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001, "要找的问题不存在"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题进行评论或回复"),
    NO_LOGIN(2003, "当前操作需要登录,请登录后重试"),
    SYS_ERROR(2004, "服务器冒烟了,稍后再试"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "你找的评论不存在"),
    ;

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
