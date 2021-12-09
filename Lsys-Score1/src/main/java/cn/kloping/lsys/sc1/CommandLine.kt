package cn.kloping.lsys.sc1

import cn.kloping.lsys.Resource
import io.github.kloping.initialize.FileInitializeValue
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand

class CommandLine : CompositeCommand(
    PluginMain.INSTANCE, "Lsys_sc1", "sc1",
    description = "kloping的Lsys_sc1插件指令"
) {

    companion object {
        @JvmField
        val INSTANCE = CommandLine()
    }

    @Description("重载配置")
    @SubCommand("reload")
    suspend fun CommandSender.LsysSc1Reload() {
        sc1.conf = FileInitializeValue.getValue(Resource.rootPath + "/conf/Lsys/sc1.json", sc1.conf, true)
        sendMessage("已重新getSc1加载配置")
    }

    @Description("设置猜拳赢得概率")
    @SubCommand("reload")
    suspend fun CommandSender.LsysSc1SetWin(arg: String) {
        val cd = java.lang.Long.parseLong(arg.trim())
        sc1.conf.setWin(cd.toInt())
        sc1.conf.apply();
        sendMessage("设置赢的概率为${cd}%")
    }
}