package com.github.kloping.lsys;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.entitys.Result;
import cn.kloping.lsys.workers.Methods;
import net.mamoe.mirai.console.permission.AbstractPermitteeId;
import net.mamoe.mirai.console.permission.PermissionService;
import net.mamoe.mirai.console.permission.PermitteeId;
import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * @author github.kloping
 */
public class RepeatMethods {
    public static void start() {
        InvokeGroup invokeGroup = new InvokeGroup("repeat");

        invokeGroup.getInvokes().put("跟我念.*", "repeat0");
        invokeGroup.getInvokesAfter().put("跟我念.*", new String[]{"$1"});
        Methods.invokes.put("repeat0", (user, request) -> {
            if (request.getEvent() instanceof GroupMessageEvent) {
                PermitteeId permitteeId = new AbstractPermitteeId.ExactMember(request.getGId().longValue(), request.getSendId().longValue());
                if (PermissionService.hasPermission(permitteeId, Repeat.INSTANCE.getParentPermission().getId())) {
                    int i = request.getOStr().indexOf(".");
                    String str = request.getStr().substring(i);
                    return new Result(new Object[]{str}, 0);
                }
            }
            return null;
        });
        Resource.loadConfAfter.add(() -> {
            if (!Resource.conf.getInvokeGroups().containsKey(invokeGroup.getId()))
                Resource.conf.getInvokeGroups().put(invokeGroup.getId(), invokeGroup);
        });
        Resource.i1();
    }
}
