package com.example.demo.entity;

import java.io.Serializable;

public class ResultVO implements Serializable {
	private int status;
    private Object result;
    private String msg;

    public ResultVO(){}

    public ResultVO(int status,Object result,String msg){
        this.status = status;
        this.result = result;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
