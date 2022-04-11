package com.github.kloping.lsys;

import io.github.kloping.initialize.FileInitializeValue;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.jetbrains.annotations.NotNull;

import static com.github.kloping.lsys.RepeatMethods.start;

/**
 * @author github.kloping
 */
public class Repeat extends JavaPlugin {
    public static final Repeat INSTANCE = new Repeat();

    public Repeat() {
        super(new JvmPluginDescriptionBuilder("com.github.kloping.lsys.Repeat", "0.1")
                .name("lsys Author HRS LSys repeat Loaded")
                .info("plugin-sco1")
                .author("HRS")
                .build());
    }

    public static final Conf CONF_INSTANCE = FileInitializeValue.getValue("./conf/LSys/repeat-conf.json", new Conf(), true);

    @Override
    public void onLoad(@NotNull PluginComponentStorage $this$onLoad) {
        if (!Utils.isExits()) {
            getLogger().error("欲使用repeat插件 必须安装 Lsys 插件");
            getLogger().error("欲使用repeat插件 必须安装 Lsys 插件");
            getLogger().error("欲使用repeat插件 必须安装 Lsys 插件");
            PluginManager.INSTANCE.disablePlugin(INSTANCE);
            return;
        }
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onLoad($this$onLoad);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }
}
