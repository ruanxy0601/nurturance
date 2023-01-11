package com.ruanxy.common.base;

import java.io.Serializable;

/**
 * 用于验证重复返回值
 *
 * @author ruanxiangyu on 20221101
 */
public class ResultValidator implements Serializable {
    private String errorMsg;

    public ResultValidator(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
