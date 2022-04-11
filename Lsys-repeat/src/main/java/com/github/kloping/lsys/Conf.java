package com.github.kloping.lsys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author github.kloping
 */
public class Conf {
    private List<Long> fathers = new ArrayList<>();

    public List<Long> getFathers() {
        return fathers;
    }

    public void setFathers(List<Long> fathers) {
        this.fathers = fathers;
    }
}
