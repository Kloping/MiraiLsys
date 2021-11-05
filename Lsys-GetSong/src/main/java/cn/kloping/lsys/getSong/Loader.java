package cn.kloping.lsys.getSong;

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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Loader {
    public static final String baseUrl = "http://49.232.209.180:20041/api/search/song?keyword=%s&type=%s";
    private static final Function2<User, Request, Result> pointKugou = (user, request) -> {
        try {
            int i = request.getOStr().indexOf(".");
            String name = request.getStr().substring(i).trim();
            name = URLEncoder.encode(name, "utf-8");
            JSONObject jo = getSong(name, "kugou");
            return new Result(new Object[]{
                    "MusicShare",
                    "KugouMusic",
                    jo.getString("media_name"),
                    jo.getString("author_name"),
                    jo.getString("songUrl"),
                    jo.getString("imgUrl"),
                    jo.getString("songUrl")
            }, 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new Result(new Object[]{}, 1);
        }
    };
    private static final Function2<User, Request, Result> pointQQ = (user, request) -> {
        try {
            int i = request.getOStr().indexOf(".");
            String name = request.getStr().substring(i).trim();
            name = URLEncoder.encode(name, "utf-8");
            JSONObject jo = getSong(name, "kugou");
            return new Result(new Object[]{
                    "MusicShare",
                    "QQMusic",
                    jo.getString("media_name"),
                    jo.getString("author_name"),
                    jo.getString("songUrl"),
                    jo.getString("imgUrl"),
                    jo.getString("songUrl")
            }, 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new Result(new Object[]{}, 1);
        }
    };
    private static final Function2<User, Request, Result> pointWy = (user, request) -> {
        try {
            int i = request.getOStr().indexOf(".");
            String name = request.getStr().substring(i).trim();
            name = URLEncoder.encode(name, "utf-8");
            JSONObject jo = getSong(name, "kugou");
            return new Result(new Object[]{
                    "MusicShare",
                    "NeteaseCloudMusic",
                    jo.getString("media_name"),
                    jo.getString("author_name"),
                    jo.getString("songUrl"),
                    jo.getString("imgUrl"),
                    jo.getString("songUrl")
            }, 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new Result(new Object[]{}, 1);
        }
    };

    public static final InvokeGroup invokeGroup = new InvokeGroup("getSong");

    static {
        invokeGroup.getInvokes().put("酷狗点歌.*", "pointKugou");
        invokeGroup.getInvokes().put("QQ点歌.*", "pointQQ");
        invokeGroup.getInvokes().put("网易点歌.*", "pointWy");
        invokeGroup.getInvokes().put("点歌系统", "method");
        //========================
        invokeGroup.getInvokesAfter().put("酷狗点歌.*", new String[]{"<$1 = $2, $3, $4, http://49.232.209.180:20041/, $6, $7>", "<At = ?>点歌失败"});
        invokeGroup.getInvokesAfter().put("QQ点歌.*", new String[]{"<$1 = $2, $3, $4, http://49.232.209.180:20041/, $6, $7>", "<At = ?>点歌失败"});
        invokeGroup.getInvokesAfter().put("网易点歌.*", new String[]{"<$1 = $2, $3, $4, http://49.232.209.180:20041/, $6, $7>", "<At = ?>点歌失败"});
        invokeGroup.getInvokesAfter().put("点歌系统", new String[]{"<At = ?>\n点歌系统\n酷狗点歌 歌名\n网易点歌 歌名\nQQ点歌 歌名"});
    }

    public static final Runnable runnable = () -> {
        if (!Resource.conf.getInvokeGroups().containsKey(invokeGroup.getId()))
            Resource.conf.getInvokeGroups().put(invokeGroup.getId(), invokeGroup);
    };

    public static void load() {
        Resource.loadConfAfter.add(runnable);

        Methods.invokes.put("pointKugou", pointKugou);
        Methods.invokes.put("pointQQ", pointQQ);
        Methods.invokes.put("pointWy", pointWy);

        Resource.i1();
    }

    public static JSONObject getSong(String keyword, String type) {
        String urlStr = String.format(baseUrl, keyword, type);
        String jsonStr = UrlUtils.getStringFromHttpUrl(urlStr);
        return JSON.parseObject(jsonStr).getJSONArray("data").getJSONObject(0);
    }
}
