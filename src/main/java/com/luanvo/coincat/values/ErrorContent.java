package com.luanvo.coincat.values;

public enum ErrorContent {
    CURRENCY_NOT_FOUND("");

    private String msg;

    ErrorContent(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
