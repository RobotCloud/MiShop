package com.robot.util;

/**
 * 响应信息。
 *
 * @author 张宝旭
 * @date 2020/9/17
 */
public class Message {
    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;

    private Object object1;
    private Object object2;
    private Object object3;
    private Object object4;
    private Object object5;
    private Object object6;

    public Message() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject1() {
        return object1;
    }

    public void setObject1(Object object1) {
        this.object1 = object1;
    }

    public Object getObject2() {
        return object2;
    }

    public void setObject2(Object object2) {
        this.object2 = object2;
    }

    public Object getObject3() {
        return object3;
    }

    public void setObject3(Object object3) {
        this.object3 = object3;
    }

    public Object getObject4() {
        return object4;
    }

    public void setObject4(Object object4) {
        this.object4 = object4;
    }

    public Object getObject5() {
        return object5;
    }

    public void setObject5(Object object5) {
        this.object5 = object5;
    }

    public Object getObject6() {
        return object6;
    }

    public void setObject6(Object object6) {
        this.object6 = object6;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", object1=" + object1 +
                ", object2=" + object2 +
                ", object3=" + object3 +
                ", object4=" + object4 +
                ", object5=" + object5 +
                ", object6=" + object6 +
                '}';
    }
}
