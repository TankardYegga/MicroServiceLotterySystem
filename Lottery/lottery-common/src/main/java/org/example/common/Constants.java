package org.example.common;

/**
 * Description:
 * Creator: levin
 * Date: 2/25/2023
 * Time: 8:02 PM
 * Email: levinforward@163.com
 */
public class Constants {


    /**
     * 响应码
     */
    public enum ResponseCode{
        /**
         * 成功响应
         */
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


    /**
     * 策略模式
     */
    public enum StrategyMode{

        /**
         * 单项概率
         */
        SINGLE(1, "单项概率"),

        /**
         * 总体概率
         */
        ENTIRETY(2, "总体概率");

        private Integer code;
        private String info;

        StrategyMode(Integer code, String info){
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }


    /**
     * 抽奖状态
     * @author: tankard
     */
    public enum DrawState{

        /**
         * 未中奖
         */
        FAIL(0, "未中奖"),

        SUCCESS(1, "中奖"),

        COVER(2, "兜底奖");

        private Integer code;
        private String info;

        DrawState(Integer code, String info){
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }



    public static void main(String[] args) {
        for(ResponseCode responseCode: ResponseCode.values()){
            System.out.println("[" + responseCode.getCode() + ", " + responseCode.getInfo() + "]");
        }
    }
}
