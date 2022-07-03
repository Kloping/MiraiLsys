package cn.kloping.lsys.picParser;

import cn.kloping.lsys.Resource;
import cn.kloping.lsys.entitys.InvokeGroup;
import cn.kloping.lsys.entitys.Request;
import cn.kloping.lsys.entitys.Result;
import cn.kloping.lsys.workers.Methods;
import com.alibaba.fastjson.JSON;
import net.mamoe.mirai.message.data.ForwardMessageBuilder;
import net.mamoe.mirai.message.data.Image;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.kloping.lsys.utils.MessageUtils.createImageInGroup;
import static io.github.kloping.url.UrlUtils.getStringFromHttpUrl;

public class picParser {

    public static void start() {
        InvokeGroup invokeGroup = new InvokeGroup("picParser");

        invokeGroup.getInvokes().put("解析快手图片.*", "picParseGiftshow");
        invokeGroup.getInvokesAfter().put("解析快手图片.*", new String[]{"<At = ?>\n解析到$1个结果", "只能解析图集或解析失败"});

        invokeGroup.getInvokes().put("解析抖音图片.*", "picParseDouy");
        invokeGroup.getInvokesAfter().put("解析抖音图片.*", new String[]{"<At = ?>\n解析到$1个结果", "只能解析图集或解析失败"});

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
                String url = getUrl(request);
                String[] strings = parseKsImgs(url);
                SEARCHED.put(user.getQq().longValue(), strings);
                return new Result(new Object[]{strings.length}, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });
        Methods.invokes.put("picParseDouy", (user, request) -> {
            try {
                String url = getUrl(request);
                String[] strings = parseDyImgs(url);
                SEARCHED.put(user.getQq().longValue(), strings);
                return new Result(new Object[]{strings.length}, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });
        Methods.invokes.put("picParseGet", (user, request) -> {
            if (!SEARCHED.containsKey(user.getQq().longValue()))
                return result;
            String sts = request.getStr().substring(request.getOStr().indexOf("."));
            long id = user.getQq().longValue();
            if (sts.equals("全部")) {
                ForwardMessageBuilder builder = new ForwardMessageBuilder(request.getEvent().getSubject());
                long myid = request.getEvent().getBot().getId();
                String nick = request.getEvent().getBot().getNick();
                int i = 0;
                for (String s : SEARCHED.get(id)) {
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
                    return new Result(new Object[]{SEARCHED.get(id)[i]}, 2);
                } catch (Exception e) {
                    return new Result(new Object[]{SEARCHED.get(id)[0]}, 2);
                }
            }
        });

        Resource.i1();
    }

    public static final Pattern pattern = Pattern.compile("http[a-zA-Z0-9/:.]*");

    private static String getUrl(Request request) {
        String url = request.getStr().substring(request.getOStr().indexOf("."));
        try {
            Matcher matcher = pattern.matcher(url);
            if (matcher.find())
                url = matcher.group().trim();
        } catch (Exception e) {
        }
        return url.trim();
    }

    public static final Map<Long, String[]> SEARCHED = new LinkedHashMap<>();

    public static final String URL_PARSE = "http://kloping.top/api/search/parseImgs?url=%s&type=%s";

    public static final Map<String, String[]> HIS = new ConcurrentHashMap<>();

    public static String[] parseKsImgs(String url) {
        try {
            String urlStr = String.format(URL_PARSE, url.trim(), "ks");
            if (HIS.containsKey(urlStr)) return HIS.get(urlStr);
            String jsonStr = getStringFromHttpUrl(urlStr);
            String[] urls = JSON.parseObject(jsonStr, String[].class);
            HIS.put(urlStr, urls);
            return urls;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] parseDyImgs(String url) {
        try {
            String urlStr = String.format(URL_PARSE, url.trim(), "dy");
            if (HIS.containsKey(urlStr)) return HIS.get(urlStr);
            String jsonStr = getStringFromHttpUrl(urlStr);
            String[] urls = JSON.parseObject(jsonStr, String[].class);
            HIS.put(urlStr, urls);
            return urls;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(parseKsImgs("https://v.kuaishouapp.com/s/Gbua6Ivl")));
    }
}
