package io.github.kloping.Idiom.entity;

import cn.kloping.lsys.Resource;
import io.github.kloping.initialize.FileInitializeValue;

public class Conf {
    /**
     * 答对/获得 的 积分
     */
    public int s1 = 2;

    /**
     * 最大答错次数
     */
    public int maxError = 5;

    public int getS1() {
        return s1;
    }

    public void setS1(int s1) {
        this.s1 = s1;
    }

    public int getMaxError() {
        return maxError;
    }

    public void setMaxError(int maxError) {
        this.maxError = maxError;
    }

    public static Conf INSTANCE = new Conf();

    public void load() {
        INSTANCE = FileInitializeValue.getValue(Resource.ROOT_PATH + "/conf/LSys/lsys-idiom.json", INSTANCE, true);
    }

    public void apply() {
        FileInitializeValue.putValues(Resource.ROOT_PATH + "/conf/LSys/lsys-idiom.json", INSTANCE, true);
    }
}
