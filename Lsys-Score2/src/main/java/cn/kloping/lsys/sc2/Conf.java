package cn.kloping.lsys.sc2;

import cn.kloping.lsys.Resource;
import io.github.kloping.initialize.FileInitializeValue;

/**
 * @author github-kloping
 */
public class Conf {
    private int workCdMin = 5;
    private int workCdMax = 12;
    private int workGetMin = 30;
    private int workGetMax = 60;

    public int getWorkCdMin() {
        return workCdMin;
    }

    public void setWorkCdMin(int workCdMin) {
        this.workCdMin = workCdMin;
    }

    public int getWorkCdMax() {
        return workCdMax;
    }

    public void setWorkCdMax(int workCdMax) {
        this.workCdMax = workCdMax;
    }

    public int getWorkGetMin() {
        return workGetMin;
    }

    public void setWorkGetMin(int workGetMin) {
        this.workGetMin = workGetMin;
    }

    public int getWorkGetMax() {
        return workGetMax;
    }

    public void setWorkGetMax(int workGetMax) {
        this.workGetMax = workGetMax;
    }


    public void apply() {
        FileInitializeValue.putValues(Resource.ROOT_PATH + "/conf/LSys/sc2.json", this, true);
    }
}
