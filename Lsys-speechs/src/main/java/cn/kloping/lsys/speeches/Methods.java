package cn.kloping.lsys.speeches;

import cn.kloping.lsys.entitys.Request;
import cn.kloping.lsys.entitys.Result;
import cn.kloping.lsys.entitys.User;
import cn.kloping.lsys.savers.PutGetter;
import kotlin.jvm.functions.Function2;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageSyncEvent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author github-kloping
 */
public class Methods {
    public static final Function2<User, Request, Result> MY_SPEECHES = (user, request) -> new Result(new Object[]{user.getSpeeches(), user.getSpeeches_total()}, 0);
    public static final Function2<User, Request, Result> SPEECHES_PH = (user, request) -> {
        ConcurrentHashMap<Long, User> c0 = new ConcurrentHashMap<>(PutGetter.HIST_USER);
        List<Map.Entry<Long, Long>> q2c = new LinkedList<>();
        c0.forEach((k, v) -> {
            q2c.add(new AbstractMap.SimpleEntry<>(k, v.getSpeeches_total()));
        });
        q2c.sort((o1, o2) -> {
            Long t0 = o2.getValue() - o1.getValue();
            return t0.intValue();
        });
//        if (q2c.size() < 5) return state1;
//        List<Object> list = new LinkedList<>();
//        int i = 5;
//        if (request.getEvent() instanceof GroupMessageEvent || request.getEvent() instanceof GroupMessageSyncEvent) {
//            Group group = (Group) request.getEvent().getSubject();
//            for (Map.Entry<Long, Long> kv : q2c) {
//                if (i == 0) break;
//                list.add(Objects.requireNonNull(group.get(kv.getKey())).getNameCard());
//                list.add(kv.getKey());
//                list.add(kv.getValue());
//                i--;
//            }
//        } else {
//            for (Map.Entry<Long, Long> kv : q2c) {
//                if (i == 0) break;
//                list.add(kv.getKey());
//                list.add(kv.getKey());
//                list.add(kv.getValue());
//                i--;
//            }
//        }
        return new Result(new Object[]{q2c}, 0);
    };

    public static final Function2<User, Request, Result> SPEECHES_PH_DAY = (user, request) -> {
        ConcurrentHashMap<Long, User> c0 = new ConcurrentHashMap<>(PutGetter.HIST_USER);
        List<Map.Entry<Long, Long>> q2c = new LinkedList<>();
        c0.forEach((k, v) -> {
            q2c.add(new AbstractMap.SimpleEntry<>(k, v.getSpeeches()));
        });
        q2c.sort((o1, o2) -> {
            Long t0 = o2.getValue() - o1.getValue();
            return t0.intValue();
        });
//        if (q2c.size() < 5) return state1;
//        List<Object> list = new LinkedList<>();
//        int i = 5;
//        if (request.getEvent() instanceof GroupMessageEvent || request.getEvent() instanceof GroupMessageSyncEvent) {
//            Group group = (Group) request.getEvent().getSubject();
//            for (Map.Entry<Long, Long> kv : q2c) {
//                if (i == 0) break;
//                list.add(Objects.requireNonNull(group.get(kv.getKey())).getNameCard());
//                list.add(kv.getKey());
//                list.add(kv.getValue());
//                i--;
//            }
//        } else {
//            for (Map.Entry<Long, Long> kv : q2c) {
//                if (i == 0) break;
//                list.add(kv.getKey());
//                list.add(kv.getKey());
//                list.add(kv.getValue());
//                i--;
//            }
//        }
//        return new Result(list.toArray(), 0);
        return new Result(new Object[]{q2c}, 0);
    };
}
