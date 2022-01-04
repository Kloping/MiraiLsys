package cn.kloping.lsys.gamebase.entity;

/**
 * big knife
 *
 * @author github kloping
 * @version 1.0
 * @date 2021/12/28
 */
public class BigKnife extends Knife {
    private int price = 180;
    private int att = 30;
    private int maxAtt = 30;
    private int maxEndurance = 30;
    private int enduranceNow = 30;
    private int idX = 102;
    private int type = 0;
    private Number owner = -1;
    private String src = "classpath:images/BigKnife.png";
    private String desc = "基础武器大刀";
    private int id;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isFull() {
        return super.isFull();
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getAtt() {
        return att;
    }

    @Override
    public void setAtt(int att) {
        this.att = att;
    }

    @Override
    public int getMaxAtt() {
        return maxAtt;
    }

    @Override
    public int getIdX() {
        return idX;
    }

    @Override
    public void setIdX(int idX) {
        this.idX = idX;
    }

    @Override
    public void setMaxAtt(int maxAtt) {
        this.maxAtt = maxAtt;
    }

    @Override
    public int getMaxEndurance() {
        return maxEndurance;
    }

    @Override
    public void setMaxEndurance(int maxEndurance) {
        this.maxEndurance = maxEndurance;
    }

    @Override
    public int getEnduranceNow() {
        return enduranceNow;
    }

    @Override
    public void setEnduranceNow(int enduranceNow) {
        this.enduranceNow = enduranceNow;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public Number getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Number owner) {
        this.owner = owner;
    }

    @Override
    public String getSrc() {
        return src;
    }

    @Override
    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
