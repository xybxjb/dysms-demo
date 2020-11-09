package cn.slipbend.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EachSlipEnum implements BaseEnum {
    MY_SLIDE(1, "我右滑了对方"),
    EACH_SLIDE(2,"互相右滑"),
    ;

    private Integer value;
    private String text;

    private EachSlipEnum(Integer value,String text){
        this.value = value;
        this.text = text;
    }

    @Override
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
