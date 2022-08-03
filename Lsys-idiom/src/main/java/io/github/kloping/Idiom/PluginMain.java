package io.github.kloping.Idiom;

import com.sun.istack.internal.NotNull;
import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

import static io.github.kloping.Idiom.Worker.start;

/**
 * @author github-kloping
 */
public class PluginMain extends JavaPlugin {
    public static final PluginMain INSTANCE = new PluginMain();

    private PluginMain() {
        super(new JvmPluginDescriptionBuilder("cn.kloping.lsys.idiom.PluginMain", "1.0")
                .name("plugin_9 Author - HRS LSys idiom Loaded")
                .info("plugin-idiom")
                .author("HRS")
                .dependsOn("cn.kloping.Lsys", "1.4", true)
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
