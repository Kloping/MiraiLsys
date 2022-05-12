package cn.kloping.lsys.getSong;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.entitys.Request;
import cn.kloping.lsys.entitys.Result;
import cn.kloping.lsys.entitys.User;
import cn.kloping.lsys.workers.Methods;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.url.UrlUtils;
import kotlin.jvm.functions.Function2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author github-kloping
 */
public class Loader {

    public static final String BASE_URL = "http://kloping.life/api/search/song?keyword=%s&type=%s";

    private static final Function2<User, Request, Result> POINT_KUGOU = (user, request) -> {
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

    private static final Function2<User, Request, Result> POINT_QQ = (user, request) -> {
        try {
            int i = request.getOStr().indexOf(".");
            String name = request.getStr().substring(i).trim();
            name = URLEncoder.encode(name, "utf-8");
            JSONObject jo = getSong(name, "qq");
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

    private static final Function2<User, Request, Result> POINT_WY = (user, request) -> {
        try {
            int i = request.getOStr().indexOf(".");
            String name = request.getStr().substring(i).trim();
            name = URLEncoder.encode(name, "utf-8");
            JSONObject jo = getSong(name, "wy");
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

    public static final InvokeGroup INVOKE_GROUP = new InvokeGroup("getSong");

    static {
        INVOKE_GROUP.getInvokes().put("酷狗点歌.*", "pointKugou");
        INVOKE_GROUP.getInvokes().put("QQ点歌.*", "pointQQ");
        INVOKE_GROUP.getInvokes().put("网易点歌.*", "pointWy");
        INVOKE_GROUP.getInvokes().put("点歌系统", "method");
        //========================
        INVOKE_GROUP.getInvokesAfter().put("酷狗点歌.*", new String[]{"<$1 = $2, $3, $4, https://www.kugou.com/, $6, $7>", "<At = ?>点歌失败"});
        INVOKE_GROUP.getInvokesAfter().put("QQ点歌.*", new String[]{"<$1 = $2, $3, $4, https://y.qq.com/, $6, $7>", "<At = ?>点歌失败"});
        INVOKE_GROUP.getInvokesAfter().put("网易点歌.*", new String[]{"<$1 = $2, $3, $4, https://music.163.com/, $6, $7>", "<At = ?>点歌失败"});
        INVOKE_GROUP.getInvokesAfter().put("点歌系统", new String[]{"<At = ?>\n点歌系统\n酷狗点歌 歌名\n网易点歌 歌名\nQQ点歌 歌名"});
    }

    public static final Runnable runnable = () -> {
        if (!Resource.conf.getInvokeGroups().containsKey(INVOKE_GROUP.getId()))
            Resource.conf.getInvokeGroups().put(INVOKE_GROUP.getId(), INVOKE_GROUP);
    };

    public static void load() {
        Resource.loadConfAfter.add(runnable);

        Methods.invokes.put("pointKugou", POINT_KUGOU);
        Methods.invokes.put("pointQQ", POINT_QQ);
        Methods.invokes.put("pointWy", POINT_WY);

        Resource.i1();
    }

    public static JSONObject getSong(String keyword, String type) {
        String urlStr = String.format(BASE_URL, keyword, type);
        String jsonStr = UrlUtils.getStringFromHttpUrl(urlStr);
        return JSON.parseObject(jsonStr).getJSONArray("data").getJSONObject(0);
    }
}
