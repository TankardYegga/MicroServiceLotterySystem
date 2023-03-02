package org.example.common;

import java.io.Serializable;

/**
 * Description: 返回一个可序列化的结果对象, 该结果对象由Code码和info来定义
 * Creator: levin
 * Date: 2/25/2023
 * Time: 8:16 PM
 * Email: levinforward@163.com
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -3826891916021780628L;;
    private String code;
    private String info;

    public Result(String code, String info){
        this.code = code;
        this.info = info;
    }

    public static Result buildResult(String code, String info){
        return new Result(code, info);
    }

    public static Result buildResult(Constants.ResponseCode code, Constants.ResponseCode info){
        return new Result(code.getCode(), info.getInfo());
    }

    public static Result buildResult(Constants.ResponseCode code, String info){
        return new Result(code.getCode(), info);
    }

    public static Result buildSuccessResult(){
        return new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
    }

    public static Result buildErrorResult(){
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
    }

    public static Result buildErrorResult(String info){
        return new Result(Constants.ResponseCode.UN_ERROR.getCode(), info);
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
