package com.robot.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单。
 * 
 * @author 张宝旭
 * @date 2020/9/17
 */
public class Order {
    private String id;
    private Integer uid;
    private Integer aid;
    private BigDecimal count;
    private Date time;
    // 订单状态 0 未付款，1已经付款未发货 2发货待收货 3 收货待评价 4订单完成 5 退货状态
    private Integer state;
    private Address address;

    public Order() {
    }

    public Order(String id, Integer uid, Integer aid, BigDecimal count, Date time, Integer state, Address address) {
        this.id = id;
        this.uid = uid;
        this.aid = aid;
        this.count = count;
        this.time = time;
        this.state = state;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", uid=" + uid +
                ", aid=" + aid +
                ", count=" + count +
                ", time=" + time +
                ", state=" + state +
                ", address=" + address +
                '}';
    }
}
