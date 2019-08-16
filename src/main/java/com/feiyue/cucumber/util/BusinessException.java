package com.feiyue.cucumber.util;

/**
 * 自定义系统业务异常处理
 * Author auto
 */
public class BusinessException extends Exception {

    private String code;
    private String[] params;


    public BusinessException() {
        super();
    }

    public BusinessException(String code, String... params) {
        super(code);
        this.code = code;
        this.params = params;
    }

    public String getCode() {
        return code;
    }

    public String[] getParams() {
        return params;
    }

}
