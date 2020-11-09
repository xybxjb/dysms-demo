package cn.slipbend.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RelationshipEnum implements BaseEnum {
    FRIEND(1,"互相关注"),
    MY_FOLLOW(2,"我关注的"),
    MY_FAN(3,"关注我的"),
    NO_RELATIONSHIP(4,"互未关注"),
    FOLLOWED(5,"已关注"),
    NO_FOLLOW(6,"未关注"),
    FOLLOW_BACK(7,"回关")
    ;

    private Integer value;
    private String text;

    private RelationshipEnum(Integer value,String text){
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
