package cn.kloping.lsys.gamebase;

import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.gamebase.conf.Game0Conf;
import cn.kloping.lsys.workers.Methods;

import static cn.kloping.lsys.gamebase.Methods.GAME0BUY;

/**
 * @author github-kloping
 */
public class Game0Loader {

    public static Game0Conf conf = new Game0Conf();

    public static final InvokeGroup INVOKE_GROUP = new InvokeGroup("game0");

    public static void load() {
        String methodName = "game0buy0";
        String invokeName = "购买.*";
        INVOKE_GROUP.getInvokes().put(invokeName, methodName);
        INVOKE_GROUP.getInvokesAfter().put(invokeName, new String[]{

        });
        Methods.invokes.put(methodName, GAME0BUY);
    }
}
