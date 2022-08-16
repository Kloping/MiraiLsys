package cn.kloping.lsys.speeches;

import cn.kloping.lsys.entitys.Request;
import cn.kloping.lsys.entitys.Result;
import cn.kloping.lsys.entitys.User;
import kotlin.jvm.functions.Function2;

/**
 * @author github-kloping
 */
public class Methods {
    public static final Function2<User, Request, Result> MY_SPEECHES = (user, request) -> new Result(new Object[]{user.getSpeeches(), user.getSpeeches_total()}, 0);
}
