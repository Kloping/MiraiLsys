package cn.kloping.lsys.gamebase.entity;

/**
 * @author github-kloping
 * @version 1.0
 */
public class Item {
    private int id;
    private Number baseMoney;
    private Number nowMoney;
    private int num = 1;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Number getBaseMoney() {
        return baseMoney;
    }

    public void setBaseMoney(Number baseMoney) {
        this.baseMoney = baseMoney;
    }

    public Number getNowMoney() {
        return nowMoney;
    }

    public void setNowMoney(Number nowMoney) {
        this.nowMoney = nowMoney;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


