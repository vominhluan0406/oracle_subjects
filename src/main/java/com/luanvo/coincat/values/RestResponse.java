package com.luanvo.coincat.values;

import lombok.Data;
import org.json.simple.JSONObject;

@SuppressWarnings("ALL")
@Data
public class RestResponse {

    private static final String MESSAGE = "message";
    private static final String DATA = "data";
    private static final String CODE = "code";

    private static final int SUCCESS_CODE = 1;
    private static final int ERROR_CODE = 0;

    private static final String MESSAGE_SUCESS = "sucsess";


    public static JSONObject success(JSONObject data){
        JSONObject rs = new JSONObject();
        rs.put(CODE,SUCCESS_CODE);
        rs.put(DATA,data);
        rs.put(MESSAGE,MESSAGE_SUCESS);
        return rs;
    }

    public static JSONObject error(String message){
        JSONObject rs = new JSONObject();
        rs.put(CODE,SUCCESS_CODE);
        rs.put(DATA,new JSONObject());
        rs.put(MESSAGE,message);
        return rs;
    }
}
