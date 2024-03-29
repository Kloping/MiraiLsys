package cn.kloping.lsys.sc2;

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
        super(new JvmPluginDescriptionBuilder("cn.kloping.lsys.sco2.PluginMain", "0.1")
                .name("p_8 Author-HRS-LSys-sco2-Loaded")
                .info("plugin-sco2")
                .author("HRS")
                .dependsOn("cn.kloping.Lsys", "1.6", true)
                .build());
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage e0) {
        Sc2.start();
    }

    @Override
    public void onEnable() {
        getLogger().info("");
    }
}