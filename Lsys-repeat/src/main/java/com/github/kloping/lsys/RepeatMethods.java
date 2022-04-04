package com.github.kloping.lsys;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.entitys.Result;
import cn.kloping.lsys.workers.Methods;

/**
 * @author github.kloping
 */
public class RepeatMethods {
    public static void start() {
        InvokeGroup invokeGroup = new InvokeGroup("repeat");

        invokeGroup.getInvokes().put("跟我念.*", "repeat0");
        invokeGroup.getInvokesAfter().put("跟我念.*", new String[]{"$1"});
        Methods.invokes.put("repeat0", (user, request) -> {
            if (user.getQq().longValue() == Resource.conf.getQq().longValue()) {
                int i = request.getOStr().indexOf(".");
                String str = request.getStr().substring(i);
                return new Result(new Object[]{str}, 0);
            } else {
                return null;
            }
        });
        Resource.loadConfAfter.add(() -> {
            if (!Resource.conf.getInvokeGroups().containsKey(invokeGroup.getId()))
                Resource.conf.getInvokeGroups().put(invokeGroup.getId(), invokeGroup);
        });
        Resource.i1();
    }
}
