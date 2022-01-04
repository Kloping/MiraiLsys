package cn.kloping.lsys.gamebase.conf;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.gamebase.entity.BigKnife;
import cn.kloping.lsys.gamebase.entity.Knife;
import cn.kloping.lsys.gamebase.entity.Weapon;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.github.kloping.clasz.ClassUtils;
import io.github.kloping.initialize.FileInitializeValue;
import io.ktor.util.collections.ConcurrentSet;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Weapon Config
 *
 * @author github kloping
 * @version 1.0
 * @date 2021/12/28
 */
public class WeaponConf {

    public static final WeaponConf INSTANCE = new WeaponConf();
    private static final String PATH = "/conf/LSys/game0/weaponConf.json";
    private static File dataFile = null;

    static {
        dataFile = new File(Resource.rootPath, PATH);
        WeaponConf wc = FileInitializeValue.getValue(dataFile.getAbsolutePath(), INSTANCE, true);
        wc.weaponJo.forEach((k, v) -> {
            try {
                Class<? extends Weapon> cla = (Class<? extends Weapon>) Class.forName(k);
                INSTANCE.weaponMap.put(k, v.toJavaObject(cla));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        Set<Class<? extends Weapon>> cs = new ConcurrentSet<>();
        cs.add(Knife.class);
        cs.add(BigKnife.class);
        for (Class<? extends Weapon> c : cs) {
            INSTANCE.putIfNotExists(c.getName(), ClassUtils.newInstance(c));
        }
        INSTANCE.apply();
    }

    @JSONField(serialize = false, name = "weapons")
    public Map<String, JSONObject> weaponJo = new ConcurrentHashMap<>();

    @JSONField(deserialize = false, name = "weapons")
    public Map<String, Weapon> weaponMap = new ConcurrentHashMap<>();

    public Map<String, Weapon> getWeaponMap() {
        return weaponMap;
    }

    public WeaponConf setWeaponMap(Map<String, Weapon> weaponMap) {
        this.weaponMap = weaponMap;
        return this;
    }

    public synchronized void putIfNotExists(String key, Weapon v) {
        if (!this.weaponMap.containsKey(key)) {
            this.weaponMap.put(key.trim(), v);
        }
    }

    public Weapon get(String key) {
        return this.weaponMap.get(key);
    }

    public void apply() {
        FileInitializeValue.putValues(dataFile.getAbsolutePath(), this);
    }
}
