package cn.kloping.lsys.speeches;

import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.jetbrains.annotations.NotNull;


/**
 * @author github-kloping
 */
public class PluginMain extends JavaPlugin {
    public static final PluginMain INSTANCE = new PluginMain();

    private PluginMain() {
        super(new JvmPluginDescriptionBuilder("cn.kloping.lsys.speeches.PluginMain", "1.0")
                .name("p_8 Author - HRS LSys speeches Loaded")
                .info("plugin-speeches")
                .author("HRS")
                .dependsOn("cn.kloping.Lsys", "1.5", true)
                .build());
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage e0) {
        Speeches.start();
    }

    @Override
    public void onEnable() {
        getLogger().info("");
        CommandManager.INSTANCE.registerCommand(CommandLine.INSTANCE, false);
    }
}