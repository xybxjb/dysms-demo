package cn.slipbend.util;

/**
 */
public enum RespCode {

    SUCCESS(0,"success"),
    ERROR(1,"error");


    Integer code;
    String desc;
    private RespCode(Integer code , String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
