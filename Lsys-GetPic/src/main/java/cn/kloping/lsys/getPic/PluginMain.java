package cn.kloping.lsys.getPic;

import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.jetbrains.annotations.NotNull;

public class PluginMain extends JavaPlugin {
    public static final PluginMain INSTANCE = new PluginMain();

    private PluginMain() {
        super(new JvmPluginDescriptionBuilder("cn.kloping.lsys.getPic.PluginMain", "0.1")
                .name("plugin_4 Author - HRS LSys GetPic Loaded")
                .info("plugin-pic")
                .author("HRS")
                .build());
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage $this$onLoad) {
        if (!Utils.isExits()) {
            getLogger().error("欲使用GetPic插件 必须安装 Lsys 插件");
            getLogger().error("欲使用GetPic插件 必须安装 Lsys 插件");
            getLogger().error("欲使用GetPic插件 必须安装 Lsys 插件");
            PluginManager.INSTANCE.disablePlugin(INSTANCE);
            return;
        }
        Loader.load();
    }

    @Override
    public void onEnable() {
        getLogger().info("");
        CommandManager.INSTANCE.registerCommand(CommandLine.INSTANCE, false);
    }
}
