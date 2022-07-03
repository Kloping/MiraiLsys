package cn.kloping.lsys.getPic;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.entitys.Request;
import cn.kloping.lsys.entitys.Result;
import cn.kloping.lsys.entitys.User;
import cn.kloping.lsys.workers.Methods;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.initialize.FileInitializeValue;
import io.github.kloping.url.UrlUtils;
import kotlin.jvm.functions.Function2;
import net.mamoe.mirai.message.data.ForwardMessageBuilder;
import net.mamoe.mirai.message.data.Image;

import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static cn.kloping.lsys.utils.MessageUtils.createImageInGroup;

/**
 * @author github-kloping
 */
public class Loader {
    public static Conf conf = new Conf(0, 12);

    public static final String BASE_URL = "http://kloping.top/api/search/pic?keyword=%s&num=%s&type=%s";

    public static final InvokeGroup INVOKE_GROUP = new InvokeGroup("getPic");

    public static long cd = 0;

    static {
        INVOKE_GROUP.getInvokes().put("发张.*", "getPicOne");
        INVOKE_GROUP.getInvokesAfter().put("发张.*", new String[]{"<Image = $1>", "获取失败"});

        INVOKE_GROUP.getInvokes().put("搜图菜单", "method");
        INVOKE_GROUP.getInvokesAfter().put("搜图菜单", new String[]{"<At = ?>\n搜图菜单\n发张 xx\n百度搜图 xx\n堆糖搜图 xx"});

        INVOKE_GROUP.getInvokes().put("百度搜图.*", "getBaidPics");
        INVOKE_GROUP.getInvokesAfter().put("百度搜图.*", new String[]{"搜索到了$1个结果", "获取失败"});

        INVOKE_GROUP.getInvokes().put("堆糖搜图.*", "getDuitPics");
        INVOKE_GROUP.getInvokesAfter().put("堆糖搜图.*", new String[]{"搜索到了$1个结果", "获取失败"});

        conf = FileInitializeValue.getValue(Resource.ROOT_PATH + "/conf/LSys/lsys-getPic.json", conf, true);
    }

    public static final Function2<User, Request, Result> FUN2 = (user, request) -> {
        try {
            if (cd > 0) {
                System.err.println("冷却中...");
                return null;
            }
            String name = request.getStr().substring(request.getOStr().indexOf("."));
            String names = URLEncoder.encode(name, "utf-8");
            JSONObject jo = JSON.parseObject(UrlUtils.getStringFromHttpUrl(String.format(BASE_URL, names, conf.getNum(), "baidu")));
            startCd();
            ForwardMessageBuilder builder = new ForwardMessageBuilder(request.getEvent().getSubject());
            long id = request.getEvent().getBot().getId();
            String nick = request.getEvent().getBot().getNick();
            int i = 0;
            for (Object s : jo.getJSONArray("data")) {
                try {
                    Image image = createImageInGroup(request.getEvent().getSubject(), s.toString());
                    assert image != null;
                    i++;
                    builder.add(id, nick, image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            request.getEvent().getSubject().sendMessage(builder.build());
            return new Result(new Object[]{i}, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(new Object[]{}, 1);
    };

    public static ExecutorService threads = Executors.newFixedThreadPool(2);

    private static void startCd() {
        if (conf.getCd() <= 0) return;
        threads.execute(() -> {
            try {
                cd = conf.getCd();
                Thread.sleep(cd);
                cd = 0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static final Function2<User, Request, Result> FUN1 = (user, request) -> {
        try {
            if (cd > 0) {
                System.err.println("冷却中...");
                return null;
            }
            String name = request.getStr().substring(request.getOStr().indexOf("."));
            String names = URLEncoder.encode(name, "utf-8");
            JSONObject jo = JSON.parseObject(UrlUtils.getStringFromHttpUrl(String.format(BASE_URL, names, "1", "duit")));
            String picUrl = jo.getJSONArray("data").getString(0);
            startCd();
            return new Result(new Object[]{picUrl}, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(new Object[]{}, 1);
    };

    public static final Function2<User, Request, Result> FUN3 = (user, request) -> {
        try {
            if (cd > 0) {
                System.err.println("冷却中...");
                return null;
            }
            String name = request.getStr().substring(request.getOStr().indexOf(".")).trim();
            String names = URLEncoder.encode(name, "utf-8");
            JSONObject jo = JSON.parseObject(UrlUtils.getStringFromHttpUrl(String.format(BASE_URL, names, conf.getNum(), "duit")));
            startCd();
            ForwardMessageBuilder builder = new ForwardMessageBuilder(request.getEvent().getSubject());
            long id = request.getEvent().getBot().getId();
            String nick = request.getEvent().getBot().getNick();
            int i = 0;
            for (Object s : jo.getJSONArray("data")) {
                try {
                    Image image = createImageInGroup(request.getEvent().getSubject(), s.toString());
                    assert image != null;
                    i++;
                    builder.add(id, nick, image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            request.getEvent().getSubject().sendMessage(builder.build());
            return new Result(new Object[]{i}, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(new Object[]{}, 1);
    };

    public static void loadConf() {
        conf = FileInitializeValue.getValue(Resource.ROOT_PATH + "/conf/LSys/lsys-getPic.json", conf, true);
    }

    public static void applyConf() {
        FileInitializeValue.putValues(Resource.ROOT_PATH + "/conf/LSys/lsys-getPic.json", conf, true);
    }

    public static final Runnable RUNNABLE = () -> {
        if (!Resource.conf.getInvokeGroups().containsKey("getPic"))
            Resource.conf.getInvokeGroups().put("getPic", INVOKE_GROUP);
    };

    public static void load() {
        Resource.loadConfAfter.add(RUNNABLE);

        Methods.invokes.put("getPicOne", FUN1);
        Methods.invokes.put("getBaidPics", FUN2);
        Methods.invokes.put("getDuitPics", FUN3);

        Resource.i1();
    }
}
