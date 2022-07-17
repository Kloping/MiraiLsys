package cn.kloping.lsys.getPic;

import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author github-kloping
 */
public class PluginMain extends JavaPlugin {
    public static final PluginMain INSTANCE = new PluginMain();

    private PluginMain() {
        super(new JvmPluginDescriptionBuilder("cn.kloping.lsys.getPic.PluginMain", "1.0")
                .name("plugin_4 Author - HRS LSys GetPic Loaded")
                .info("plugin-pic")
                .author("HRS")
                .dependsOn("cn.kloping.Lsys","1.3",true)
                .build());
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage load) {
        Loader.load();
    }

    @Override
    public void onEnable() {
        getLogger().info("");
        CommandManager.INSTANCE.registerCommand(CommandLine.INSTANCE, false);
    }
}
