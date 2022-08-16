package cn.kloping.lsys.speeches

import net.mamoe.mirai.console.command.CompositeCommand

class CommandLine : CompositeCommand(
    PluginMain.INSTANCE, "Lsys_speeches", "speeches",
    description = "kloping的Lsys_speeches插件指令"
) {

    companion object {
        @JvmField
        val INSTANCE = CommandLine()
    }

}