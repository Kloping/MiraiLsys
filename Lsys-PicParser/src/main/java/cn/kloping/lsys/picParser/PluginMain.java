package cn.kloping.lsys.picParser;

import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.jetbrains.annotations.NotNull;

import static cn.kloping.lsys.picParser.picParser.start;

/**
 * @author github-kloping
 */
public class PluginMain extends JavaPlugin {
    public static final PluginMain INSTANCE = new PluginMain();

    private PluginMain() {
        super(new JvmPluginDescriptionBuilder("cn.kloping.lsys.picParser.PluginMain", "1.0")
                .name("p_6 Author - HRS LSys picParser Loaded")
                .info("plugin-picP")
                .author("HRS")
                .dependsOn("cn.kloping.Lsys", "1.2", true)
                .build());
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage e0) {
        start();
    }

    @Override
    public void onEnable() {
        getLogger().info("");
    }
}
