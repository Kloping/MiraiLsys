package cn.kloping.lsys.picParser;

import cn.kloping.clasz.ClassUtils;
import cn.kloping.file.FileUtils;
import cn.kloping.initialize.FileInitializeValue;
import cn.kloping.lsys.Resource;
import cn.kloping.url.UrlUtils;

import java.io.File;

public class ClassM {
    public static void start() throws Exception {
        String version = UrlUtils.getStringFromHttpUrl(false, "https://gitee.com/kloping/Resource/raw/master/picParser/version");
        String myVersion = FileInitializeValue.getValue(Resource.conf.getPath() + "/picParser/version", version);
        byte[] bytes = FileUtils.getBytesFromFile(Resource.conf.getPath() + "/picParser/Main.class");
        if (bytes == null || !version.trim().equals(myVersion.trim())) {
            bytes = UrlUtils.getBytesFromHttpUrl("https://gitee.com/kloping/Resource/raw/master/picParser/Main.class");
            FileUtils.writeBytesToFile(bytes, new File(Resource.conf.getPath() + "/picParser/Main.class"));
        }
        ClassUtils.ClassEntity entity = ClassUtils.createClassEntity(bytes, "picParser.Main");

    }
}
