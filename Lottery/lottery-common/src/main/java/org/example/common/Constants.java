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
        INDEX_DUP("0003", "主键冲突"),
        NO_UPDATE("0004", "SQL操作被更新"),
        LOSING_DRAW("D0001", "未中奖");

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

    /**
     * 发奖的状态
     */
    public enum AwardState{

        /**
         * 等待发奖
         */
        WAIT(0, "等待发奖"),

        /**
         * 发奖成功
         */
        SUCCESS(1, "发奖成功"),

        /**
         * 发奖失败
         */
        FAIL(2, "发奖失败");

        private Integer code;
        private String info;

        AwardState(Integer code, String info){
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
     * 发奖类型
     */
    public enum AwardType{
        /**
         * 文字描述
         */
        DESC(1, "文字描述"),

        /**
         * 兑换码
         */
        RedeemCodeGoods(2, "兑换码"),

        /**
         * 优惠券
         */
        CouponGoods(3, "优惠券"),

        /**
         * 实物奖品
         */
        PhysicalGoods(4, "实物奖品");

        private Integer code;
        private String info;

        AwardType(Integer code, String info) {
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
     * 活动状态：
     */
    public enum ActivityState{

        /** 编辑 */
        EDIT(1, "编辑"),

        ARRAIGNMENT(2, "提审"),

        REVOKE(3, "撤审"),

        PASS(4, "通过"),

        /** 活动运行中 */
        DOING(5, "运行"),

        REFUSE(6, "拒绝"),

        CLOSE(7, "关闭"),

        OPEN(8, "打开");

        private Integer code;
        private String info;

        ActivityState(Integer code, String info){
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
     * Ids生成策略的枚举
     */
    public enum Ids{

        SnowFlake,
        RandomNumeric,
        ShortCode;
    }

    /**
     * 活动单使用状态：0 未被使用  1 使用中
     */
    public enum TakeState{
        /** 未必使用 */
        NO_USED(0, "未被使用"),
        USED(1, "使用中");

        TakeState(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        private Integer code;
        private String info;

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
     * 奖品发放状态：0 初始，1 发放成功 2 发放失败
     */
    public enum GrantState{
        /** 初始 */
        INIT(0, "初始"),
        COMPLETE(1, "发放成功"),
        FAIL(2, "发放失败");

        private Integer code;
        private String info;

        GrantState(Integer code, String info) {
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
     * 全局属性类
     */
    public static final class Global{
        public static final Long TREE_NULL_NODE = 0L;
    }

    public static final class NodeType{
        public static final Integer STEM = 1;
        public static final Integer FRUIT = 2;
    }

    public static final class RuleLimitType{
        public static final int EQ = 1;
        public static final int GT = 2;
        public static final int LT = 3;
        public static final int GE = 4;
        public static final int LE = 5;
        public static final int ENUM = 6;
    }


    public static void main(String[] args) {
        for(ResponseCode responseCode: ResponseCode.values()){
            System.out.println("[" + responseCode.getCode() + ", " + responseCode.getInfo() + "]");
        }
    }



}
