package cn.kloping.lsys.gamebase;

import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.workers.Methods;

/**
 * @author github-kloping
 */
public class Game0Loader {

    public static final InvokeGroup INVOKE_GROUP = new InvokeGroup("game0");

    public static void load() {
        String methodName = "game0buy0";
        String invokeName = "购买.*";
        INVOKE_GROUP.getInvokes().put(invokeName, methodName);
        INVOKE_GROUP.getInvokesAfter().put(invokeName, new String[]{

        });
        Methods.invokes.put(methodName, cn.kloping.lsys.gamebase.Methods.GAME0BUY);
    }
}
