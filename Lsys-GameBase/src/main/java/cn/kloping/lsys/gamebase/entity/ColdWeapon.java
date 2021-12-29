package cn.kloping.lsys.gamebase.entity;

/**
 * @author github-kloping
 */
public interface ColdWeapon extends Weapon {
    /**
     * Whether it can be repaired
     *
     * @return it can be repaired
     */
    @Override
    default boolean isCanFix() {
        return true;
    }
}
