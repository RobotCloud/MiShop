package com.robot.pojo;

import java.math.BigDecimal;

/**
 * 购物车。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public class Cart {
    private Integer cid;
    private Integer uid;
    private Integer gid;
    private BigDecimal count;
    private Integer num;
    private Goods goods;

    public Cart() {
    }

    public Cart(Integer cid, Integer uid, Integer gid, BigDecimal count, Integer num, Goods goods) {
        this.cid = cid;
        this.uid = uid;
        this.gid = gid;
        this.count = count;
        this.num = num;
        this.goods = goods;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", gid=" + gid +
                ", count=" + count +
                ", num=" + num +
                ", goods=" + goods +
                '}';
    }
}
