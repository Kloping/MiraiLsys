
package cn.kloping.lsys.speeches;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.workers.Methods;

import static cn.kloping.lsys.speeches.Methods.*;

/**
 * @author github-kloping
 */
public class Speeches {
    public static void start() {

        InvokeGroup invokeGroup = new InvokeGroup("speeches");

        invokeGroup.getInvokes().put("我的发言", "mySpeeches");
        invokeGroup.getInvokesAfter().put("我的发言", new String[]{"<At = ?>\n您今天发言了$1次\n累计发言$2次"});
        Methods.invokes.put("mySpeeches", MY_SPEECHES);

        invokeGroup.getInvokes().put("发言排行", "SPEECHES_PH");
        invokeGroup.getInvokesAfter().put("发言排行", new String[]{"<At = ?>\n" +
                "1. $1($2)发言了$3次\n" +
                "2. $4($5)发言了$6次\n" +
                "3. $7($8)发言了$9次\n" +
                "4. $10($11)发言了$12次\n" +
                "5. $13($14)发言了$15次", "等待更多发言者"
        });
        Methods.invokes.put("SPEECHES_PH", SPEECHES_PH);

        invokeGroup.getInvokes().put("今日发言排行", "SPEECHES_PH_DAY");
        invokeGroup.getInvokesAfter().put("今日发言排行", new String[]{"<At = ?>\n" +
                "1. $1($2)发言了$3次\n" +
                "2. $4($5)发言了$6次\n" +
                "3. $7($8)发言了$9次\n" +
                "4. $10($11)发言了$12次\n" +
                "5. $13($14)发言了$15次", "等待更多发言者"
        });
        Methods.invokes.put("SPEECHES_PH_DAY", SPEECHES_PH_DAY);

        Resource.loadConfAfter.add(() -> {
            if (!Resource.conf.getInvokeGroups().containsKey(invokeGroup.getId()))
                Resource.conf.getInvokeGroups().put(invokeGroup.getId(), invokeGroup);
        });
        Resource.i1();
    }
}