package cn.kloping.lsys.sc1;

import cn.kloping.lsys.Resource;
import io.github.kloping.initialize.FileInitializeValue;

public class Conf {
    private int win = 40;
    private int ob = 18;

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

    public void apply() {
        FileInitializeValue.putValues(Resource.rootPath + "/conf/Lsys/sc1.json", this, true);
    }
}
