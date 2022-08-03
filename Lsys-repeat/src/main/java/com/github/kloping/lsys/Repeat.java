package com.github.kloping.lsys;

import io.github.kloping.initialize.FileInitializeValue;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
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
        super(new JvmPluginDescriptionBuilder("com.github.kloping.lsys.Repeat", "1.0")
                .name("lsys Author HRS LSys repeat Loaded")
                .info("plugin-sco1")
                .author("HRS")
                .dependsOn("cn.kloping.Lsys", "1.4", true)
                .build());
    }

    public static final Conf CONF_INSTANCE = FileInitializeValue.getValue("./conf/LSys/repeat-conf.json", new Conf(), true);

    @Override
    public void onLoad(@NotNull PluginComponentStorage $this$onLoad) {
        start();
        super.onLoad($this$onLoad);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }
}
