package cn.kloping.lsys.sc1;

import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.jetbrains.annotations.NotNull;

import static cn.kloping.lsys.sc1.Sc1.start;


/**
 * @author github-kloping
 */
public class PluginMain extends JavaPlugin {
    public static final PluginMain INSTANCE = new PluginMain();

    private PluginMain() {
        super(new JvmPluginDescriptionBuilder("cn.kloping.lsys.sc1.PluginMain", "1.0")
                .name("p_7 Author - HRS LSys sco1 Loaded")
                .info("plugin-sco1")
                .author("HRS")
                .dependsOn("cn.kloping.Lsys", "1.6", true)
                .build());
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage e0) {
        start();
    }

    @Override
    public void onEnable() {
        getLogger().info("");
        CommandManager.INSTANCE.registerCommand(CommandLine.INSTANCE, false);
    }
}