package cn.kloping.lsys.gamebase.entity;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.gamebase.Game0Loader;
import cn.kloping.lsys.gamebase.conf.Game0Conf;
import com.alibaba.fastjson.JSON;
import io.github.kloping.file.FileUtils;
import io.github.kloping.serialize.HMLObject;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author github-kloping
 * @version 1.0
 */
public class Bag {
    private Long qId = -1L;
    private Map<Integer, Integer> items = new ConcurrentHashMap<>();

    public Long getQId() {
        return qId;
    }

    public void setQId(Long qId) {
        this.qId = qId;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }

    public static synchronized Bag getInstance(long id) {
        File file = new File(Resource.conf.getPath(), id + "/bg.hml");
        String hmlStr = FileUtils.getStringFromFile(file.getAbsolutePath());
        if (hmlStr == null || hmlStr.isEmpty()) {
            Bag bag = new Bag();
            bag.setQId(id);
            FileUtils.putStringInFile(HMLObject.toHMLString(bag), file);
            return bag;
        } else {
            return HMLObject.parseObject(hmlStr, Bag.class);
        }
    }

    public Bag apply() {
        File file = new File(Resource.conf.getPath(), qId + "/bg.hml");
        FileUtils.putStringInFile(HMLObject.toHMLString(this), file);
        return this;
    }
}
