package com.example.demo.core;

import com.example.demo.entity.ResultVO;

public class ResultUtil {
	public static final int SUCCESS_CODE = 1;
    public static final int FAIL_CODE = 0;

    public static ResultVO getResult(int status, String msg, Object result) {
        ResultVO vo = new ResultVO();
        vo.setStatus(status);
        vo.setMsg(msg);
        vo.setResult(result);
        return vo;
    }

    public static ResultVO getSuccessResult() {
        return getSuccessResult(null);
    }

    public static ResultVO getSuccessResult(Object result) {
        return getSuccessResult(null, result);
    }

    public static ResultVO getSuccessResult(String msg, Object result) {
        return getResult(SUCCESS_CODE, msg, result);
    }

    public static ResultVO getFailResult(String msg) {
        return getResult(FAIL_CODE, msg, null);
    }

    public static ResultVO getFailResult(String msg, int code) {
        return getResult(code, msg, null);
    }
}
