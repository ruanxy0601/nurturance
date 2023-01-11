package com.ruanxy.common.base;

import com.ruanxy.common.constant.ResultConstant;

import java.io.Serializable;

/**
 * 统一返回结果类
 *
 * @author ruanxiangyu
 * @date 2022/11/1.
 */
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务错误
     */
    public static final int FAILED = 0;

    /**
     * 成功
     */
    public static final int SUCCESS = 1;

    /**
     * 验证报错返回2
     */
    public static final int VALIDATOR = 2;

    // 状态码：1成功，其他为失败
    private int code;

    // 成功为success，其他为失败原因
    private String message;

    // 数据结果集
    private Object data;

    /**
     * 空构造器(增加原因：在dubbo使用Hessian反序列化时，使用此构造器，避免参数非默认类型引发空指针异常）
     */
    public BaseResult() {
    }

    public BaseResult(String message) {
        this.message = message;
    }

    public BaseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static BaseResult failResultCreate(String message) {
        return new BaseResult(FAILED, message, null);
    }

    /**
     * @param message 错误提示信息
     * @param data    错误详细信息（具体数据信息）
     * @return BaseResult
     */
    public static BaseResult failResultCreate(String message, Object data) {
        return new BaseResult(FAILED, message, data);
    }

    /**
     * 返回成功信息：
     *
     * @param data Get：传入的是查询业务数据
     * @return BaseResult
     */
    public static BaseResult successResultCreate(Object data) {
        return new BaseResult(SUCCESS, ResultConstant.SUCCESS.getMessage(), data);
    }

    /**
     * 返回成功信息
     *
     * @param message 自定义前台显示成功信息
     * @param data    业务数据
     * @return BaseResult
     */
    public static BaseResult successResultCreate(String message, Object data) {
        return new BaseResult(SUCCESS, message, data);
    }

    /**
     * 验证返回数据是否为1
     *
     * @param didCount 处理数据量
     * @return BaseResult
     */
    public static BaseResult checkCountOneResult(int didCount) {
        return checkCountResult(didCount, 1);
    }

    /**
     * 验证返回值是否一致
     *
     * @param didCount    处理数据
     * @param willDoCount 期望数据条数
     * @return BaseResult
     */
    public static BaseResult checkCountResult(int didCount, int willDoCount) {
        BaseResult result = new BaseResult();
        if (didCount <= 0) {
            result.setCode(FAILED);
            result.setMessage("处理失败");
        } else if (didCount < willDoCount) {
            result.setCode(SUCCESS);
            result.setMessage("批量操作部分成功！");
        } else {
            result.setCode(SUCCESS);
            result.setMessage("操作成功");
        }

        result.setData(didCount);
        return result;
    }

    /**
     * 验证返回值是否为true
     *
     * @param flag boolean返回值
     * @return BaseResult
     */
    public static BaseResult checkBooleanResult(boolean flag) {
        return checkBooleanResult(flag, null);
    }

    /**
     * 验证返回值是否为true
     *
     * @param flag boolean返回值
     * @param data 返回对象
     * @return BaseResult
     */
    public static BaseResult checkBooleanResult(boolean flag, Object data) {
        BaseResult result = new BaseResult();
        if (flag) {
            result.setCode(SUCCESS);
            result.setMessage("操作成功");
        } else {
            result.setCode(FAILED);
            result.setMessage("操作失败");
        }
        result.setData(data);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 获取返回结果状态是否成功
     *
     * @return boolean
     */
    public boolean isSuccess() {
        return SUCCESS == code;
    }

    @Override
    public String toString() {
        return "BaseResult{" + "code=" + code + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}
