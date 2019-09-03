package com.feiyue.cucumber.util;

import com.yfny.utilscommon.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;

/**
 * 统一调用响应格式
 * Created by jisongZhou on 2019/2/18.
 **/
@Component
public class InvokeResult<T> {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    private static InvokeResult invokeResult;

    @PostConstruct
    public void init() {
        invokeResult = this;
    }

    private final static int SUCCESS = 0;
    private final static int FAILURE = 1;
    private final static int EXCEPTION = 2;

    //响应状态
    private String code;

    //响应消息
    private String message;

    //响应数据
    private T data;

    public static <T> InvokeResult success(T data) {
        return success("", data, null);
    }

    public static <T> InvokeResult success(String code, T data, String... params) {
        return getResultInit(code, "", data, SUCCESS, null);
    }

    public static InvokeResult failure() {
        return failure("10002", new String[]{"未知"});
    }

    public static InvokeResult failure(String code, String... params) {
        return failure(code, null, params);
    }

    public static <T> InvokeResult failure(String code, T data, String... params) {
        return getResultInit(code, "", data, FAILURE, params);
    }

    public static InvokeResult exception() {
        return exception("10003", new String[]{"未知"});
    }

    public static InvokeResult exception(String code, String... params) {
        return getResultInit(code, "", null, EXCEPTION, params);
    }

    /**
     * 执行写入时的返回结果
     *
     * @param result       数据库写入结果
     * @param successCode  成功编码
     * @param failureCode1 非正常失败编码
     * @param failureCode2 正常失败编码
     * @return 统一调用响应格式
     */
    public static InvokeResult writeResult(int result, String successCode, String failureCode1, String failureCode2) {
        if (result == 1) {
            return InvokeResult.success(successCode, result);
        } else if (result == -1) {
            return InvokeResult.failure(failureCode1, "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure(failureCode2);
    }

    /**
     * 执行读取时的返回结果
     *
     * @param result       数据库读取结果
     * @param successCode  成功编码
     * @param failureCode1 非正常失败编码
     * @param failureCode2 正常失败编码
     * @return 统一调用响应格式
     */
    public static InvokeResult readResult(Object result, String successCode, String failureCode1, String failureCode2) {
        if (result != null) {
            return InvokeResult.success(successCode, result);
        } else if (result == null) {
            //return InvokeResult.failure(failureCode1, "数据不存在或网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure(failureCode2);
    }

    /**
     * 接口数据统一格式化
     *
     * @param code
     * @param message
     * @param data
     * @param params
     * @return
     */
    private static InvokeResult getResultInit(String code, String message, Object data, int type, String[] params) {
        InvokeResult result = new InvokeResult();
        code = StringUtils.isNotBlank(code) ? code : "10001";
        message = StringUtils.isNotBlank(message) ? message : getMsgFromCfg(code, params);
        data = data != null ? data : StringUtils.isUTF8(message);
        if (type == SUCCESS) {
            code = "10001";
        }
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 从配置文件中获取错误信息
     *
     * @param code
     * @param params
     * @return
     */
    private static String getMsgFromCfg(String code, String[] params) {
        String message = invokeResult.applicationContext.getEnvironment().getProperty(code);
        return params == null ? message : MessageFormat.format(message,
                params);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
