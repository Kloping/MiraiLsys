package io.github.kloping.Idiom;

import com.alibaba.fastjson.JSON;
import io.github.kloping.Idiom.entity.Response;
import io.github.kloping.url.UrlUtils;

import java.net.URLEncoder;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author HRS-Computer
 */
public abstract class Idiom {
    public Set<String> hist = new LinkedHashSet<>();
    private String upWord;
    private String upPinyin;

    public Idiom() {
        upWord = getRandom();
        upPinyin = getEndPinyin(upWord);
        hist.add(upWord);
    }

    public Idiom(int maxFailed) {
        this.maxFailed = maxFailed;
        upWord = getRandom();
        upPinyin = getEndPinyin(upWord);
        hist.add(upWord);
    }

    private int maxFailed = 5;
    private int failed = 0;

    public int meet(String s) {
        try {
            failed++;
            Response response = request(s);
            if (s.trim().length() != 4) return -1;
            if (hist.contains(s)) return -2;
            if (response.getState().intValue() == -1)
                return -3;
            if (!upPinyin.equals(getStartPinyin(response)))
                return -4;
            upWord = s;
            upPinyin = getEndPinyin(response);
            hist.add(upWord);
            failed = 0;
            return 0;
        } finally {
            if (failed >= maxFailed)
                fail(s);
        }
    }

    public abstract void fail(String s);

    public Set<String> getHist() {
        return hist;
    }

    public String getUpWord() {
        return upWord;
    }

    public String getUpPinyin() {
        return upPinyin;
    }

    public int getMaxFailed() {
        return maxFailed;
    }

    public void setMaxFailed(int maxFailed) {
        this.maxFailed = maxFailed;
    }

    static Response request(String arg) {
        String str = UrlUtils.getStringFromHttpUrl("http://kloping.top/api/get/idiom?word=" + URLEncoder.encode(arg));
        return JSON.parseObject(str).toJavaObject(Response.class);
    }

    static String getRandom() {
        return UrlUtils.getStringFromHttpUrl("http://kloping.top/api/get/idiom/random");
    }

    static String getStartPinyin(Response response) {
        String s = response.getPinyin()[0];
        return s.replaceAll("[0-9]", "");
    }

    static String getStartPinyin(String arg) {
        String str = UrlUtils.getStringFromHttpUrl("http://kloping.top/api/get/idiom?word=" + URLEncoder.encode(arg));
        Response response = JSON.parseObject(str).toJavaObject(Response.class);
        String s = response.getPinyin()[0];
        return s.replaceAll("[0-9]", "");
    }

    static String getEndPinyin(String arg) {
        String str = UrlUtils.getStringFromHttpUrl("http://kloping.top/api/get/idiom?word=" + URLEncoder.encode(arg));
        Response response = JSON.parseObject(str).toJavaObject(Response.class);
        String s = response.getPinyin()[3];
        return s.replaceAll("[0-9]", "");
    }

    static String getEndPinyin(Response response) {
        String s = response.getPinyin()[3];
        return s.replaceAll("[0-9]", "");
    }
}
