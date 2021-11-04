package cn.kloping.lsys

import cn.kloping.lsys.Resource.i1
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import java.lang.System.err

class CommandLine : SimpleCommand(
    PluginMain.INSTANCE, "Lsys", "reload",
    description = "kloping的Lsys插件指令"
) {

    companion object {
        @JvmField
        val INSTANCE = CommandLine()
    }

    @Handler
    fun CommandSender.handle(arg: String) {
        when (arg) {
            "reload" -> {
                i1()
                println("已重新加载配置")
            }
            "clearHist" -> {
                histMat.clear()
                histMatO.clear()
                println("已清除历史匹配")
            }
            "update" -> {
                println("正在更新插件")
            }
            else -> {
                err.println(
                    "已知参数:\n" +
                            "reload\t#重新加载配置\n" +
                            "clearHist\t#清除历史匹配\n"
                )
            }
        }
    }
}