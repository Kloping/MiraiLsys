
package cn.kloping.lsys.speeches;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.workers.Methods;

import static cn.kloping.lsys.speeches.Methods.MY_SPEECHES;

/**
 * @author github-kloping
 */
public class Speeches {
    public static void start() {

        InvokeGroup invokeGroup = new InvokeGroup("speeches");

        invokeGroup.getInvokes().put("我的发言", "mySpeeches");
        invokeGroup.getInvokesAfter().put("我的发言", new String[]{"<At = ?>\n您今天发言了$1次\n累计发言$2次"});
        Methods.invokes.put("mySpeeches", MY_SPEECHES);

        Resource.loadConfAfter.add(() -> {
            if (!Resource.conf.getInvokeGroups().containsKey(invokeGroup.getId()))
                Resource.conf.getInvokeGroups().put(invokeGroup.getId(), invokeGroup);
        });
        Resource.i1();
    }
}