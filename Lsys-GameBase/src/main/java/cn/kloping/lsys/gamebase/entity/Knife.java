package cn.kloping.lsys.gamebase.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author github kloping
 * @version 1.0
 * @date 2021/12/28
 */
public class Knife implements ColdWeapon {
    private int price = 10;
    private int att = 10;
    private int maxAtt = 10;
    private int maxEndurance = 10;
    private int enduranceNow = 10;
    private int idX = 101;
    private int type = 0;
    private Number owner = -1;
    private String src = "classpath:images/Knife.png";
    private String desc = "基础物品小刀";

    private int id = -1;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @JSONField(deserialize = false, serialize = false)
    @Override
    public boolean isFull() {
        return getMaxAtt() == getAtt() && getMaxEndurance() == getEnduranceNow();
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public Number getOwner() {
        return owner;
    }

    public void setOwner(Number owner) {
        this.owner = owner;
    }

    @Override
    public int getMaxAtt() {
        return maxAtt;
    }

    public void setMaxAtt(int maxAtt) {
        this.maxAtt = maxAtt;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getAtt() {
        return att;
    }

    public void setAtt(int att) {
        this.att = att;
    }

    @Override
    public int getMaxEndurance() {
        return maxEndurance;
    }

    public void setMaxEndurance(int maxEndurance) {
        this.maxEndurance = maxEndurance;
    }

    @Override
    public int getEnduranceNow() {
        return enduranceNow;
    }

    public void setEnduranceNow(int enduranceNow) {
        this.enduranceNow = enduranceNow;
    }

    @Override
    public int getIdX() {
        return idX;
    }

    public void setIdX(int idX) {
        this.idX = idX;
    }

    @Override
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
