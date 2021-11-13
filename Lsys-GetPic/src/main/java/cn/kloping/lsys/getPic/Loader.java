package cn.kloping.lsys.getPic;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.entitys.Request;
import cn.kloping.lsys.entitys.Result;
import cn.kloping.lsys.entitys.User;
import cn.kloping.lsys.workers.Methods;
import cn.kloping.url.UrlUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import kotlin.jvm.functions.Function2;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.ForwardMessageBuilder;
import net.mamoe.mirai.message.data.Image;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static cn.kloping.lsys.utils.MessageUtils.createImageInGroup;

public class Loader {
    public static Conf conf = new Conf(0, 12);

    public static final String baseUrl = "http://49.232.209.180:20041/api/search/pic?keyword=%s&num=%s&type=%s";

    public static final InvokeGroup invokeGroup = new InvokeGroup("getPic");

    public static long cd = 0;

    static {
        invokeGroup.getInvokes().put("发张.*", "getPicOne");
        invokeGroup.getInvokesAfter().put("发张.*", new String[]{"<Image = $1>", "获取失败"});

        invokeGroup.getInvokes().put("搜图菜单", "method");
        invokeGroup.getInvokesAfter().put("搜图菜单", new String[]{"<At = ?>\n搜图菜单\n发张 xx\n百度搜图 xx\n堆糖搜图 xx"});

        invokeGroup.getInvokes().put("百度搜图.*", "getBaidPics");
        invokeGroup.getInvokesAfter().put("百度搜图.*", new String[]{"搜索到了$1个结果", "获取失败"});

        invokeGroup.getInvokes().put("堆糖搜图.*", "getDuitPics");
        invokeGroup.getInvokesAfter().put("堆糖搜图.*", new String[]{"搜索到了$1个结果", "获取失败"});

        conf = cn.kloping.initialize.FileInitializeValue.getValue(Resource.rootPath+"/conf/Lsys/lsys-getPic.json", conf, true);
    }

    public static final Function2<User, Request, Result> fun2 = (user, request) -> {
        try {
            if (cd > 0) {
                System.err.println("冷却中...");
                return null;
            }
            String name = request.getStr().substring(request.getOStr().indexOf("."));
            String names = URLEncoder.encode(name, "utf-8");
            JSONObject jo = JSON.parseObject(UrlUtils.getStringFromHttpUrl(String.format(baseUrl, names, conf.getNum(), "baidu")));
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

    public static final Function2<User, Request, Result> fun1 = (user, request) -> {
        try {
            if (cd > 0) {
                System.err.println("冷却中...");
                return null;
            }
            String name = request.getStr().substring(request.getOStr().indexOf("."));
            String names = URLEncoder.encode(name, "utf-8");
            JSONObject jo = JSON.parseObject(UrlUtils.getStringFromHttpUrl(String.format(baseUrl, names, "1", "duit")));
            String picUrl = jo.getJSONArray("data").getString(0);
            startCd();
            return new Result(new Object[]{picUrl}, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(new Object[]{}, 1);
    };

    public static final Function2<User, Request, Result> fun3 = (user, request) -> {
        try {
            if (cd > 0) {
                System.err.println("冷却中...");
                return null;
            }
            String name = request.getStr().substring(request.getOStr().indexOf("."));
            String names = URLEncoder.encode(name, "utf-8");
            JSONObject jo = JSON.parseObject(UrlUtils.getStringFromHttpUrl(String.format(baseUrl, names, conf.getNum(), "duit")));
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


//    public static synchronized Image createImageInGroup(Contact group, String path) {
//        try {
//            if (path.startsWith("http")) {
//                return Contact.uploadImage(group, new URL(path).openStream());
//            } else if (path.startsWith("{")) {
//                return Image.fromId(path);
//            } else {
//                Image image = null;
//                image = Contact.uploadImage(group, new File(path));
//                return image;
//            }
//        } catch (IOException e) {
//            System.err.println(path + "加载重试");
//            try {
//                return Contact.uploadImage(group, new URL(path).openStream());
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//                return null;
//            }
//        }
//    }

    public static final Runnable runnable = () -> {
        if (!Resource.conf.getInvokeGroups().containsKey("getPic"))
            Resource.conf.getInvokeGroups().put("getPic", invokeGroup);
    };

    public static void loadConf() {
        conf = cn.kloping.initialize.FileInitializeValue.getValue("./conf/Lsys/lsys-getPic.json", conf, true);
    }

    public static void applyConf() {
        cn.kloping.initialize.FileInitializeValue.putValues("./conf/Lsys/lsys-getPic.json", conf, true);
    }

    public static void load() {
        Resource.loadConfAfter.add(runnable);

        Methods.invokes.put("getPicOne", fun1);
        Methods.invokes.put("getBaidPics", fun2);
        Methods.invokes.put("getDuitPics", fun3);

        Resource.i1();
    }
}
