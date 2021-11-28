package cn.kloping.lsys.sc2;

import cn.kloping.lsys.entitys.Request;
import cn.kloping.lsys.entitys.Result;
import cn.kloping.lsys.entitys.User;
import cn.kloping.lsys.utils.MessageUtils;
import io.github.kloping.judge.Judge;
import io.github.kloping.number.NumberUtils;
import kotlin.jvm.functions.Function2;

import static cn.kloping.lsys.savers.PutGetter.get;
import static cn.kloping.lsys.workers.Methods.*;

public class Methods {

    public static final Function2<User, Request, Result> transTo = (user, request) -> {
        long q2 = MessageUtils.getAtFromRequest(request);
        if (q2 == -1) return state3;
        User u2 = get(q2);
        if (u2 == null) return state2;
        String nStr = NumberUtils.findNumberFromString(request.getStr()).replace(q2+"","");
        if (Judge.isEmpty(nStr)) return state4;
        long n = Long.parseLong(nStr);
        if (user.getP() < n) return state1;
        u2.addP(n);
        user.addP(-n);
        return state0;
    };
    public static final Function2<User, Request, Result> LongTimeWork = (user, request) -> {
        long k1 = user.getK1();
        if (k1 >= System.currentTimeMillis()) {
            String tips = (k1 - System.currentTimeMillis()) / 1000 / 60 + "分钟之后";
            return new Result(new Object[]{tips}, 0);
        }
        int r = MessageUtils.random.nextInt(20) + 40;
        user.addP(r);
        int rt = (MessageUtils.random.nextInt(5) + 6);
        user.setK1(System.currentTimeMillis() + rt * 1000 * 60);
        user.apply();
        return new Result(new Object[]{rt, r}, 1);
    };
    public static final Function2<User, Request, Result> lookSc = (user, request) -> {
        long q2 = MessageUtils.getAtFromRequest(request);
        if (q2 == -1) return state2;
        User u2 = get(q2);
        if (u2 == null) return state1;
        return new Result(new Object[]{u2.getP()}, 0);
    };

}
