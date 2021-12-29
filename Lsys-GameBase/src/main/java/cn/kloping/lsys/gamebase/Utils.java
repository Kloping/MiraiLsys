package cn.kloping.lsys.gamebase;

import net.mamoe.mirai.console.plugin.Plugin;
import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.plugin.description.PluginDescription;
import net.mamoe.mirai.console.plugin.loader.PluginLoader;

/**
 * @author github-kloping
 */
public class Utils {
    public static boolean isExits() {
        for (Plugin plugin : PluginManager.INSTANCE.getPlugins()) {
            PluginLoader<Plugin, PluginDescription> loader =
                    (PluginLoader<Plugin, PluginDescription>) plugin.getLoader();
            String id = loader.getPluginDescription(plugin).getId();
            if ("cn.kloping.Lsys".equals(id)) {
                return true;
            }
        }
        return false;
    }
}
