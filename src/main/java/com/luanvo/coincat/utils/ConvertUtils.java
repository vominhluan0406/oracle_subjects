package com.luanvo.coincat.utils;

public class ConvertUtils {

    public static int toInt(Object value){
        try{
            return Integer.parseInt(value.toString());
        }catch (Exception e){
            return 0;
        }
    }

    public static double toDouble(Object value){
        try{
            return Double.parseDouble(value.toString());
        }catch (Exception e){
            return 0;
        }
    }
}
