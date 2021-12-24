package io.github.kloping.Idiom;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.entitys.Request;
import cn.kloping.lsys.entitys.Result;
import cn.kloping.lsys.entitys.User;
import cn.kloping.lsys.workers.Methods;
import io.github.kloping.Idiom.entity.Conf;
import kotlin.jvm.functions.Function2;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Worker {
    public static void start() {
        InvokeGroup invokeGroup = new InvokeGroup("idiom");
        String touchKey = "";
        String methodName = "";

        touchKey = "开始成语接龙";
        methodName = "startIdiomGame";

        invokeGroup.getInvokes().put(touchKey, methodName);
        invokeGroup.getInvokesAfter().put(touchKey, new String[]{
                "<At = ?>\n游戏已经开始了哦~",
                "游戏开始\n第$1次接龙\n当前词语:$2\n末尾音节:$3\n",
        });
        Methods.invokes.put(methodName, m1);
        //==========
        touchKey = "我接.*";
        methodName = "meetIdiomGame";

        invokeGroup.getInvokes().put(touchKey, methodName);
        invokeGroup.getInvokesAfter().put(touchKey, new String[]{
                "<At = ?>\n游戏还未开始\n请说开始成语接龙"
                , "<At = ?>\n必须是四字成语呢\n扣除$1积分"
                , "<At = ?>\n这个成语被用过了哦\n扣除$1积分"
                , "<At = ?>\n这好像不是一个成语哦\n扣除$1积分"
                , "<At = ?>\n\"$1\"的末尾音节是$2\n\"$3\"的开始尾音节是$4\n好像不对呢\n扣除$5积分"
                , "<At = ?>\n\"$1\"接上了\n奖励$2积分\n第$3次接龙\n当前词语:$4\n末尾音节:$5\n"
                , "<At = ?>\n积分不足...."
        });
        Methods.invokes.put(methodName, m2);
        //==========


        Resource.loadConfAfter.add(() -> {
            if (!Resource.conf.getInvokeGroups().containsKey(invokeGroup.getId()))
                Resource.conf.getInvokeGroups().put(invokeGroup.getId(), invokeGroup);
        });
        Resource.i1();
    }

    public static final Map<Long, Idiom> games = new ConcurrentHashMap<>();

    public static final Function2<User, Request, Result> m1 = (user, request) -> {
        long gid = request.getGId().longValue();
        if (games.containsKey(gid)) {
            return Methods.state0;
        } else {
            Idiom idiom = new Idiom(Conf.INSTANCE.getMaxError()) {
                @Override
                public void fail(String s) {
                    games.remove(gid);
                    MessageChainBuilder builder = new MessageChainBuilder();
                    builder.append("成语接龙结束\n");
                    builder.append("共接了").append(String.valueOf(this.getHist().size())).append("次\n");
                    for (String s2 : this.getHist()) {
                        builder.append(s2).append("-");
                    }
                    builder.append("end");
                    request.getEvent().getSubject().sendMessage(builder.build());
                }
            };
            games.put(gid, idiom);
            return new Result(new Object[]{
                    idiom.getHist().size()
                    , idiom.getUpWord().trim()
                    , idiom.getUpPinyin()
            }, 1);
        }
    };

    public static final Function2<User, Request, Result> m2 = (user, request) -> {
        long gid = request.getGId().longValue();
        int s1 = Conf.INSTANCE.getS1();
        if (!games.containsKey(gid))
            return Methods.state0;
        if (user.getP() < s1)
            return Methods.state6;
        Idiom idiom = games.get(gid);
        int i = request.getOStr().indexOf(".");
        String i1 = request.getStr().substring(i).trim();
        int st = -999;
        switch (st = idiom.meet(i1)) {
            case -1:
            case -2:
            case -3:
                user.addP(-s1);
                user.apply();
                return new Result(new Object[]{s1}, (-st));
            case -4:
                user.addP(-s1);
                user.apply();
                return new Result(new Object[]{
                        idiom.getUpWord(),
                        idiom.getUpPinyin(),
                        i1,
                        Idiom.getStartPinyin(i1),
                        s1
                }, (-st));
            default:
                user.addP(s1 * 2);
                user.apply();
                return new Result(
                        new Object[]{
                                i1
                                , s1 * 2
                                , idiom.getHist().size()
                                , idiom.getUpWord()
                                , idiom.getUpPinyin()
                        }, 5
                );
        }
    };

    /*public static void main(String[] args) {
        String json = UrlUtils.getStringFromHttpUrl("http://49.232.209.180:20041/api/get/idiom?word=" + URLEncoder.encode("为所欲为"));
        System.out.println(json);
        JsonSJC.parse(JSON.parseObject("{\"state\":1,\"word\":\"为所欲为\",\"pinyin\":[\"wei2\",\"suo3\",\"yu4\",\"wei2\"]}")
                , "D:\\Projects\\OwnProjects\\MiraiLSys\\Lsys-idiom\\src\\main\\java\\io\\github\\kloping\\Idiom\\entity"
                , "response"
                , " io.github.kloping.Idiom.entity"
        );
    }*/
}