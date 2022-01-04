package cn.kloping.lsys.gamebase;

import cn.kloping.lsys.gamebase.conf.WeaponConf;
import cn.kloping.lsys.gamebase.entity.Weapon;

/**
 * WeaponFactory
 *
 * @author github kloping
 * @version 1.0
 * @date 2021/12/29-16:08
 */
public class WeaponFactory {
    private static final WeaponFactory INSTANCE = new WeaponFactory();

    private WeaponFactory() {
    }

    public static <T extends Weapon> T newWeapon(Class<T> type, int id) throws IllegalAccessException {
        if (WeaponConf.INSTANCE.getWeaponMap().containsKey(type.getName())) {
            T oWeapon = (T) WeaponConf.INSTANCE.getWeaponMap().get(type.getName());
            T weapon = io.github.kloping.clasz.ClassUtils.copyAllField(oWeapon);
            weapon.setId(id);
            return weapon;
        } else {
            throw new RuntimeException("unknown type " + type);
        }
    }

    public static Weapon newWeapon(String typeName, int id) throws ClassNotFoundException, IllegalAccessException {
        Class<?> class0 = Class.forName(typeName);
        if (Weapon.class.isAssignableFrom(class0)) {
            Class<? extends Weapon> class1 = (Class<? extends Weapon>) class0;
            return newWeapon(class1, id);
        }
        throw new RuntimeException("this type not is weapon");
    }
}
