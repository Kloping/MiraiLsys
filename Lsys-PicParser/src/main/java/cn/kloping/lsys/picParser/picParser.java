package cn.kloping.lsys.picParser;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.entitys.Result;
import cn.kloping.lsys.workers.Methods;
import com.alibaba.fastjson.JSON;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.ForwardMessageBuilder;
import net.mamoe.mirai.message.data.Image;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cn.kloping.url.UrlUtils.getStringFromHttpUrl;

public class picParser {

    public static void start() {
        InvokeGroup invokeGroup = new InvokeGroup("picParser");

        invokeGroup.getInvokes().put("解析快手图片.*", "picParseGiftshow");
        invokeGroup.getInvokesAfter().put("解析快手图片.*", new String[]{"<At = ?>\n解析到$1个结果", "解析失败"});

        invokeGroup.getInvokes().put("解析抖音图片.*", "picParseDouy");
        invokeGroup.getInvokesAfter().put("解析抖音图片.*", new String[]{"<At = ?>\n解析到$1个结果", "解析失败"});

        invokeGroup.getInvokes().put("发送第.*", "picParseGet");
        invokeGroup.getInvokesAfter().put("发送第.*", new String[]{"<At = ?>\n已发送$1", "您没有进行相关的搜索", "<At = ?>\n<Image = $1>"});

        Runnable runnable = () -> {
            if (!Resource.conf.getInvokeGroups().containsKey(invokeGroup.getId()))
                Resource.conf.getInvokeGroups().put(invokeGroup.getId(), invokeGroup);
        };
        Resource.loadConfAfter.add(runnable);

        final Result result = new Result(null, 1);

        Methods.invokes.put("picParseGiftshow", (user, request) -> {
            try {
                String url = request.getStr().substring(request.getOStr().indexOf("."));
                String[] strings = parseKsImgs(url);
                searched.put(user.getQq().longValue(), strings);
                return new Result(new Object[]{strings.length}, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });
        Methods.invokes.put("picParseDouy", (user, request) -> {
            try {
                String url = request.getStr().substring(request.getOStr().indexOf("."));
                String[] strings = parseDyImgs(url);
                searched.put(user.getQq().longValue(), strings);
                return new Result(new Object[]{strings.length}, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });
        Methods.invokes.put("picParseGet", (user, request) -> {
            if (!searched.containsKey(user.getQq().longValue()))
                return result;
            String sts = request.getStr().substring(request.getOStr().indexOf("."));
            long id = user.getQq().longValue();
            if (sts.equals("全部")) {
                ForwardMessageBuilder builder = new ForwardMessageBuilder(request.getEvent().getSubject());
                long myid = request.getEvent().getBot().getId();
                String nick = request.getEvent().getBot().getNick();
                int i = 0;
                for (String s : searched.get(id)) {
                    try {
                        Image image = createImageInGroup(request.getEvent().getSubject(), s.toString());
                        assert image != null;
                        i++;
                        builder.add(myid, nick, image);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                request.getEvent().getSubject().sendMessage(builder.build());
                return new Result(new Object[]{i}, 0);
            } else {
                try {
                    int i = Integer.parseInt(sts);
                    return new Result(new Object[]{searched.get(id)[i]}, 2);
                } catch (Exception e) {
                    return new Result(new Object[]{searched.get(id)[0]}, 2);
                }
            }
        });

        Resource.i1();
    }

    public static final Map<Long, String[]> searched = new LinkedHashMap<>();

    public static final String urlParse = "http://49.232.209.180:20041/api/search/parseImgs?url=%s&type=%s";

    public static final Map<String, String[]> his = new ConcurrentHashMap<>();

    public static String[] parseKsImgs(String url) {
        try {
            String urlStr = String.format(urlParse, url.trim(), "ks");
            if (his.containsKey(urlStr)) return his.get(urlStr);
            String jsonStr = getStringFromHttpUrl(urlStr);
            String[] urls = JSON.parseObject(jsonStr, String[].class);
            his.put(urlStr, urls);
            return urls;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] parseDyImgs(String url) {
        try {
            String urlStr = String.format(urlParse, url.trim(), "dy");
            if (his.containsKey(urlStr)) return his.get(urlStr);
            String jsonStr = getStringFromHttpUrl(urlStr);
            String[] urls = JSON.parseObject(jsonStr, String[].class);
            his.put(urlStr, urls);
            return urls;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static synchronized Image createImageInGroup(Contact group, String path) {
        try {
            if (path.startsWith("http")) {
                return Contact.uploadImage(group, new URL(path).openStream());
            } else if (path.startsWith("{")) {
                return Image.fromId(path);
            } else {
                Image image = null;
                image = Contact.uploadImage(group, new File(path));
                return image;
            }
        } catch (IOException e) {
            System.err.println(path + "加载重试");
            try {
                return Contact.uploadImage(group, new URL(path).openStream());
            } catch (IOException ioException) {
                ioException.printStackTrace();
                return null;
            }
        }
    }
}
