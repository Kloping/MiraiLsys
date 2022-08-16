package cn.kloping.lsys.speechs;

import cn.kloping.lsys.Resource;
import io.github.kloping.initialize.FileInitializeValue;

/**
 * @author github-kloping
 */
public class Conf {
    private int win = 40;
    private int ob = 18;
    private int signNormalGet = 100;
    private int signFirstGet = 220;
    private int signSecondGet = 160;
    private int signThirdGet = 120;
    private int moraMax = 2000;
    private int moraMin = 5;

    public int getMoraMax() {
        return moraMax;
    }

    public void setMoraMax(int moraMax) {
        this.moraMax = moraMax;
    }

    public int getMoraMin() {
        return moraMin;
    }

    public void setMoraMin(int moraMin) {
        this.moraMin = moraMin;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public void setOb(int ob) {
        this.ob = ob;
    }

    public int getWin() {
        return win;
    }

    public int getOb() {
        return ob;
    }

    public int getSignNormalGet() {
        return signNormalGet;
    }

    public void setSignNormalGet(int signNormalGet) {
        this.signNormalGet = signNormalGet;
    }

    public int getSignFirstGet() {
        return signFirstGet;
    }

    public void setSignFirstGet(int signFirstGet) {
        this.signFirstGet = signFirstGet;
    }

    public int getSignSecondGet() {
        return signSecondGet;
    }

    public void setSignSecondGet(int signSecondGet) {
        this.signSecondGet = signSecondGet;
    }

    public int getSignThirdGet() {
        return signThirdGet;
    }

    public void setSignThirdGet(int signThirdGet) {
        this.signThirdGet = signThirdGet;
    }

    public void apply() {
        FileInitializeValue.putValues(Resource.ROOT_PATH + "/conf/LSys/sc1.json", this, true);
    }
}
