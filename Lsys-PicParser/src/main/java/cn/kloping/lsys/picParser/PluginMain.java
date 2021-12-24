package cn.kloping.lsys.picParser;

import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.jetbrains.annotations.NotNull;

import static cn.kloping.lsys.picParser.picParser.start;

public class PluginMain extends JavaPlugin {
    public static final PluginMain INSTANCE = new PluginMain();

    private PluginMain() {
        super(new JvmPluginDescriptionBuilder("cn.kloping.lsys.picParser.PluginMain", "0.1")
                .name("p_6 Author - HRS LSys picParser Loaded")
                .info("plugin-picP")
                .author("HRS")
                .build());
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage $this$onLoad) {
        if (!Utils.isExits()) {
            getLogger().error("欲使用picParser插件 必须安装 Lsys 插件");
            getLogger().error("欲使用picParser插件 必须安装 Lsys 插件");
            getLogger().error("欲使用picParser插件 必须安装 Lsys 插件");
            PluginManager.INSTANCE.disablePlugin(INSTANCE);
            return;
        }
        try {
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        getLogger().info("");
    }
}
