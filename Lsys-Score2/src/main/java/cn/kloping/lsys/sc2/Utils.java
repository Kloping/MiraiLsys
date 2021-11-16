package cn.kloping.lsys.sc2;

import net.mamoe.mirai.console.plugin.Plugin;
import net.mamoe.mirai.console.plugin.PluginManager;
import net.mamoe.mirai.console.plugin.description.PluginDescription;
import net.mamoe.mirai.console.plugin.loader.PluginLoader;

public class Utils {
    public static boolean isExits() {
        for (Plugin plugin : PluginManager.INSTANCE.getPlugins()) {
            PluginLoader<Plugin, PluginDescription> loader = (PluginLoader<Plugin, PluginDescription>) plugin.getLoader();
            String id = loader.getPluginDescription(plugin).getId();
            if (id.equals("cn.kloping.Lsys")) {
                return true;
            }
        }
        return false;
    }
}
