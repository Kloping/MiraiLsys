package cn.kloping.lsys.gamebase.entity;

/**
 * @author github kloping
 */
public interface Weapon {

    /**
     * To get the price
     *
     * @return price
     */
    int getPrice();

    /**
     * Returns attack value
     *
     * @return attack value
     */
    int getAtt();

    /**
     * Returns maximum attack value
     *
     * @return maximum attack value
     */
    int getMaxAtt();

    /**
     * Returns the maximum used value
     *
     * @return maximum used value
     */
    int getMaxEndurance();

    /**
     * Gets the currently available value
     *
     * @return currently available value
     */
    int getEnduranceNow();

    /**
     * Whether it can be repaired
     *
     * @return it can be repaired
     */
    boolean isCanFix();

    /**
     * The unique ID of the item
     *
     * @return unique id
     */
    int getIdX();

    /**
     * The ID of the item
     *
     * @return ID
     */
    int getId();

    /**
     * set Item ID
     * and Don't recommend change
     * @param id id
     */
    void setId(int id);

    /**
     * get type
     *
     * @return type
     */
    int getType();

    /**
     * get image position
     *
     * @return path
     */
    String getSrc();

    /**
     * whether complete or not
     *
     * @return complete or not
     */
    boolean isFull();

    /**
     * Returns the ID of the person who owns the item
     *
     * @return ID
     */
    Number getOwner();

    /**
     * Get its description
     *
     * @return description
     */
    String getDesc();
}
