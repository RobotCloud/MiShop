package com.robot.pojo;

/**
 * 收货地址。
 *
 * @author 张宝旭
 * @date 2020/9/17
 */
public class Address {
    private Integer aid;
    private Integer uid;
    private String name;
    private String phone;
    private String detail;
    private Integer state;

    public Address() {
    }

    public Address(Integer aid, Integer uid, String name, String phone, String detail, Integer state) {
        this.aid = aid;
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.detail = detail;
        this.state = state;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + aid +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", detail='" + detail + '\'' +
                ", state=" + state +
                '}';
    }
}
