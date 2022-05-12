package cn.kloping.lsys.sc2

import cn.kloping.lsys.Resource
import io.github.kloping.initialize.FileInitializeValue
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand

class CommandLine : CompositeCommand(
    PluginMain.INSTANCE, "Lsys_sc2", "sc2",
    description = "kloping的Lsys_sc2插件指令"
) {

    companion object {
        @JvmField
        val INSTANCE = CommandLine()
    }

    @Description("重载配置")
    @SubCommand("reload")
    suspend fun CommandSender.LsysSc2Reload() {
        SC2.conf = FileInitializeValue.getValue(Resource.ROOT_PATH + "/conf/Lsys/sc2.json", SC2.conf, true)
        sendMessage("已重新getSc2加载配置")
    }
}