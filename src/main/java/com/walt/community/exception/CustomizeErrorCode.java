package com.walt.community.exception;

/**
 * @author: walt1012
 * @date: 2020/7/8
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("要找的问题不存在"),
    ;

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
