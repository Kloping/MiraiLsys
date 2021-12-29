package cn.kloping.lsys.gamebase.database;

import cn.kloping.lsys.Resource;

/**
 * GameDatabase
 *
 * @author github kloping
 * @version 1.0
 * @date 2021/12/28
 */
public class GameDatabase {
    public static final GameDatabase INSTANCE = new GameDatabase(Resource.conf.getPath());

    public GameDatabase(String basePath) {
    }
}
