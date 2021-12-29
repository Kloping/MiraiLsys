package cn.kloping.lsys.gamebase;

import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author github-kloping
 */
public class Game0 extends JavaPlugin {
    public static final Game0 INSTANCE = new Game0();

    public Game0() {
        super(new JvmPluginDescriptionBuilder("o.github.lsys.gameBase.game0", "1.0")
                .name("plugin_10 Author-HRS-LSys-game-Loaded")
                .info("plugin-pic")
                .author("HRS")
                .build());
    }


    @Override
    public void onLoad(@NotNull PluginComponentStorage this$onLoad) {
        if (!Utils.isExits()) {
            getLogger().error("欲使用game0插件 必须安装 Lsys 插件");
            getLogger().error("欲使用game0插件 必须安装 Lsys 插件");
            getLogger().error("欲使用game0插件 必须安装 Lsys 插件");
            PluginManager.INSTANCE.disablePlugin(INSTANCE);
            return;
        }
        Game0Loader.load();
    }

    @Override
    public void onEnable() {
        getLogger().info("");
    }
}
