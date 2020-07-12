package com.walt.community.dto;

import com.walt.community.exception.CustomizeErrorCode;
import com.walt.community.exception.CustomizeException;

/**
 * @author: walt1012
 * @date: 2020/7/10
 */
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDTO errOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errOf(CustomizeErrorCode customizeErrorCode) {
        return errOf(customizeErrorCode.getCode(), customizeErrorCode.getMessage());
    }

    public static ResultDTO errOf(CustomizeException e) {
        return errOf(e.getCode(), e.getMessage());
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static <T> ResultDTO<T> okOf(T t) {
        ResultDTO<T> resultDTO = new ResultDTO<T>();
        resultDTO.setData(t);
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
