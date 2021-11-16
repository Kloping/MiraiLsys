package cn.kloping.lsys.sc1;

import com.sun.istack.internal.NotNull;
import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

import static cn.kloping.lsys.sc1.sc1.start;


public class PluginMain extends JavaPlugin {
    public static final PluginMain INSTANCE = new PluginMain();

    private PluginMain() {
        super(new JvmPluginDescriptionBuilder("cn.kloping.lsys.sco1.PluginMain", "0.1")
                .name("插件_7 Author => HRS LSys sco1 Loaded")
                .info("插件")
                .author("HRS")
                .build());
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage $this$onLoad) {
        if (!Utils.isExits()) {
            getLogger().error("欲使用sco1插件 必须安装 Lsys 插件");
            getLogger().error("欲使用sco1插件 必须安装 Lsys 插件");
            getLogger().error("欲使用sco1插件 必须安装 Lsys 插件");
            PluginManager.INSTANCE.disablePlugin(INSTANCE);
            return;
        }
        try {
            CommandManager.INSTANCE.registerCommand(CommandLine.INSTANCE, false);
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