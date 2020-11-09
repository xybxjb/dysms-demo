package cn.slipbend.util;

import java.io.Serializable;

/**

 **/
public class ServerResponse<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Integer code;   // 状态码
    String msg;     // 消息
    T data;         // 数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getdata() {
        return data;
    }

    public void setdata(T data) {
        this.data = data;
    }

    /**
     *      操作成功的时候调用以下重载方法
     */
    public static<T> ServerResponse<T> getSuccess(String msg) {
        return new ServerResponse<>(RespCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse<T> getSuccess(T data) {
        return new ServerResponse<>(RespCode.SUCCESS.getCode(),data);
    }

    public static <T> ServerResponse<T> getSuccess(String msg, T data) {
        System.out.println(data);
        return new ServerResponse<>(RespCode.SUCCESS.getCode(), msg, data);
    }


    /**
     *      操作失败的时候调用以下重载方法
     */
    public static ServerResponse getError(String msg) {
        return new ServerResponse(RespCode.ERROR.getCode(), msg);
    }

    public static <T> ServerResponse getError(T data) {
        return new ServerResponse(RespCode.ERROR.getCode(),data);
    }

    public static <T> ServerResponse getError(String msg, T data) {
        return new ServerResponse(RespCode.ERROR.getCode(), msg, data);
    }

    /**
     *@Description:
     *      返回操作成功与否状态
     */
    public Boolean isSuccess() {
        return this.code == RespCode.SUCCESS.getCode();
    }



    /**
     *@Description:
     *      私有化构造方法,保证安全.
     */
    private ServerResponse(Integer code) {
        this.code = code;
    }

    private ServerResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ServerResponse(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private ServerResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

