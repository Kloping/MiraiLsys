package cn.kloping.lsys.sc1;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.workers.Methods;
import io.github.kloping.initialize.FileInitializeValue;

import static cn.kloping.lsys.sc1.Methods.*;

/**
 * @author github-kloping
 */
public class Sc1 {
    public static Conf conf = new Conf();
    public static void start() {
        conf = FileInitializeValue.getValue(Resource.ROOT_PATH +"/conf/LSys/sc1.json", conf, true);

        InvokeGroup invokeGroup = new InvokeGroup("sco1");

        invokeGroup.getInvokes().put("打劫.*", "rob");
        invokeGroup.getInvokesAfter().put("打劫.*", new String[]{"<At = ?>\n您成功打劫了ta$1积分"
                , "<At = ?>\n您要打劫谁呢?"
                , "<At = ?>\nta还没有注册呢"
                , "<At = ?>\nta没有积分能被您抢了"
                , "<At = ?>\n您必须有一点积分才能去打劫哦"
                , "<At = ?>\n犯罪指数最大"});
        Methods.invokes.put("rob", ROB);
        //=打劫end
        invokeGroup.getInvokes().put("签到", "sign");
        invokeGroup.getInvokesAfter().put("签到", new String[]{"<At = ?>\n签到成功!!!\n<Image = $1>\n犯罪指数清除\n今日第$2签\n获得$3积分"
                , "<At = ?>\n签到失败,您今天已经签到了~"});
        Methods.invokes.put("sign", SIGN);
        //=签到end
        invokeGroup.getInvokes().put("猜拳.*", "mora");
        invokeGroup.getInvokesAfter().put("猜拳.*", new String[]{"<At = ?>\n你赢了 我出的是$1 你获得了$2积分"
                , "<At = ?>\n你输了 我出的是$1 你输掉了$2积分"
                , "<At = ?>\n平局 我出的是$1"
                , "<At = ?>\n积分不足"
                , "<At = ?>\n积分范围在$1~$2之间"
                , "<At = ?>\n没有数值 例: 猜拳 石头 10"
        });
        Methods.invokes.put("mora", MORA_METHOD);
        //=猜拳end

        Resource.loadConfAfter.add(() -> {
            if (!Resource.conf.getInvokeGroups().containsKey(invokeGroup.getId()))
                Resource.conf.getInvokeGroups().put(invokeGroup.getId(), invokeGroup);
        });
        Resource.i1();
    }
}