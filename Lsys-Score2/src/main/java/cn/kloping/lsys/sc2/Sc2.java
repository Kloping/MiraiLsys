package cn.kloping.lsys.sc2;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.workers.Methods;
import io.github.kloping.initialize.FileInitializeValue;

import static cn.kloping.lsys.sc2.Methods.*;

public class Sc2 {
    public static Conf conf = new Conf();

    public static void start() {
        conf = FileInitializeValue.getValue(Resource.ROOT_PATH + "/conf/LSys/sc2.json", conf, true);

        InvokeGroup invokeGroup = new InvokeGroup("sco2");

        invokeGroup.getInvokes().put("积分转让.*", "transTo");
        invokeGroup.getInvokesAfter().put("积分转让.*", new String[]{"<At = ?>\n积分转让成功"
                , "<At = ?>\n积分不足"
                , "<At = ?>\nta还没有注册呢"
                , "<At = ?>\nta转给谁呢"
                , "<At = ?>\n转多少呢"
        });
        Methods.invokes.put("transTo", transTo);
        //=打劫end
        invokeGroup.getInvokes().put("积分侦查.*", "lookSc");
        invokeGroup.getInvokesAfter().put("积分侦查.*", new String[]{"<At = ?>\nta剩的积分:$1"
                , "<At = ?>\nta还没有注册"
                , "<At = ?>\n侦查谁"
        });
        Methods.invokes.put("lookSc", lookSc);
        //=签到end
        invokeGroup.getInvokes().put("打工.*", "LongTimeWork");
        invokeGroup.getInvokesAfter().put("打工.*", new String[]{"<At = ?>\n冷却时间未到:$1"
                , "<At = ?>\n您花费了$1分钟,打工赚了 $2 积分"
        });
        Methods.invokes.put("LongTimeWork", LongTimeWork);
        //=猜拳end

        Resource.loadConfAfter.add(() -> {
            if (!Resource.conf.getInvokeGroups().containsKey(invokeGroup.getId()))
                Resource.conf.getInvokeGroups().put(invokeGroup.getId(), invokeGroup);
        });
        Resource.i1();
    }
}