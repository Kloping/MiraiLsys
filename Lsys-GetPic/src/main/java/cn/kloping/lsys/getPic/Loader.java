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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Loader {
    public static final String baseUrl = "http://49.232.209.180:20041/api/search/pic?keyword=%s&num=2&type=duit";

    public static final InvokeGroup invokeGroup = new InvokeGroup("getPic");

    static {
        invokeGroup.getInvokes().put("发张.*", "getPicOne");
        invokeGroup.getInvokesAfter().put("发张.*", new String[]{"<Image = $1>", "获取失败"});
    }

    public static final Function2<User, Request, Result> fun1 = (user, request) -> {
        String name = request.getStr().substring(request.getOStr().indexOf("."));
        try {
            String names = URLEncoder.encode(name, "utf-8");
            JSONObject jo = JSON.parseObject(UrlUtils.getStringFromHttpUrl(false, String.format(baseUrl, names)));
            String picUrl = jo.getJSONArray("data").getString(0);
            return new Result(new Object[]{picUrl}, 0);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new Result(new Object[]{}, 1);
    };

    public static final Runnable runnable = () -> {
        if (!Resource.conf.getInvokeGroups().containsKey("getPic"))
            Resource.conf.getInvokeGroups().put("getPic", invokeGroup);
    };

    public static void load() {
        Resource.loadConfAfter.add(runnable);

        Methods.invokes.put("getPicOne", fun1);

        Resource.i1();
    }
}
