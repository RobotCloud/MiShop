package com.robot.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品类。
 *
 * @author 张宝旭
 * @date 2020/9/16
 */
public class Goods {
    private Integer id;
    private Integer typesId;
    private String name;
    private Date time;
    private String image;
    private BigDecimal price;
    private int state;
    private String  info;

    public Goods() {
    }

    public Goods(Integer id, Integer typesId, String name, Date time, String image, BigDecimal price, int state, String info) {
        this.id = id;
        this.typesId = typesId;
        this.name = name;
        this.time = time;
        this.image = image;
        this.price = price;
        this.state = state;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypesId() {
        return typesId;
    }

    public void setTypesId(Integer typesId) {
        this.typesId = typesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", typesId=" + typesId +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", state=" + state +
                ", info='" + info + '\'' +
                '}';
    }
}
