package com.walt.community.enums;

/**
 * @author: walt1012
 * @date: 2020/7/14
 */
public enum NotificationStatusEnum {
    UNREAD(0),READ(1),
    ;
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
