package cn.kloping.lsys.sc2;

import com.sun.istack.internal.NotNull;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;


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
                .dependsOn("cn.kloping.Lsys", "1.0", true)
                .build());
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage e0) {
        SC2.start();
    }

    @Override
    public void onEnable() {
        getLogger().info("");
    }
}