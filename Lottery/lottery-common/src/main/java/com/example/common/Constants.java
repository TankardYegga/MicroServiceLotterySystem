package com.example.common;

/**
 * Description:
 * Creator: levin
 * Date: 2/25/2023
 * Time: 8:02 PM
 * Email: levinforward@163.com
 */
public class Constants {

    //定义了一个枚举类型ResponseCode
    public enum ResponseCode{
        SUCCESS("0000", "成功"),
        UN_ERROR("0001", "未知失败"),
        ILLEGAL_PARAMETER("0002", "非法参数"),
        INDEX_DUP("0003", "主键冲突");

        private String code;
        private String info;

        ResponseCode(String code, String info){
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    public static void main(String[] args) {
        for(ResponseCode responseCode: ResponseCode.values()){
            System.out.println("[" + responseCode.getCode() + ", " + responseCode.getInfo() + "]");
        }
    }
}
