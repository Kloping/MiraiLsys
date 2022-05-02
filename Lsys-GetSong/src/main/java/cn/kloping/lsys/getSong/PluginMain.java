package cn.kloping.lsys.getSong;

import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.jetbrains.annotations.NotNull;

public class PluginMain extends JavaPlugin {
    public static final PluginMain INSTANCE = new PluginMain();

    private PluginMain() {
        super(new JvmPluginDescriptionBuilder("cn.kloping.lsys.getSong.PluginMain", "0.2")
                .name("plugin_5 Author - HRS LSys GetSong Loaded")
                .info("plugin-song")
                .author("HRS").dependsOn("cn.kloping.Lsys", "0.3", true)
                .build());
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage $this$onLoad) {
        if (!Utils.isExits()) {
            getLogger().error("欲使用GetSong插件 必须安装 Lsys 插件");
            getLogger().error("欲使用GetSong插件 必须安装 Lsys 插件");
            getLogger().error("欲使用GetSong插件 必须安装 Lsys 插件");
            PluginManager.INSTANCE.disablePlugin(INSTANCE);
            return;
        }
        Loader.load();
    }

    @Override
    public void onEnable() {
        getLogger().info("");
    }
}
