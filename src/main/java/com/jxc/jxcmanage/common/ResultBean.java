package com.jxc.jxcmanage.common;

import lombok.Data;

@Data
public class ResultBean {
    //操作成功默认返回值
    public final static String SUCCESS_CODE = "200";
    public final static String SUCCESS_MESSAGE = "操作成功";

    //操作成功默认返回值
    public final static String FAIL_CODE = "201";
    public final static String FAIL_MESSAGE = "操作失败";


    private String code;
    private String message;
    private Object data;


    public ResultBean(){
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }


    public ResultBean(String code,String message){
        this(code,message,null);
    }

    public ResultBean(String code,String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultBean success(){
        return success(null);
    }

    public static ResultBean success(Object data){
        return success(SUCCESS_MESSAGE,data);
    }

    public static ResultBean success(String message,Object data){
        return new ResultBean(SUCCESS_CODE,message,data);
    }

    public static ResultBean fail(){
        return fail(null);
    }

    public static ResultBean fail(Object data){
        return fail(FAIL_MESSAGE,data);
    }

    public static ResultBean fail(String message,Object data){
        return new ResultBean(FAIL_CODE,message,data);
    }


}
