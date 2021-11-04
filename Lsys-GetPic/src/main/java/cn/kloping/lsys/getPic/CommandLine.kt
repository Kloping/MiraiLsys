package cn.kloping.lsys.getPic

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import java.lang.System.err

class CommandLine : CompositeCommand(
    PluginMain.INSTANCE, "Lsys", "reload",
    description = "kloping的LsysGetPic插件指令"
) {

    companion object {
        @JvmField
        val INSTANCE = CommandLine()
    }

    @SubCommand("getPic")
    fun CommandSender.handle(arg: String) {
        when  {
            arg == "reload" -> {
                Loader.loadConf()
                println("已重新getPic加载配置")
            }
            arg.startsWith("Cd") -> {
                try {
                    val cd = java.lang.Long.parseLong(arg.split("=")[1])
                    Loader.conf.cd = cd
                    Loader.applyConf()
                    println("设置冷却为${cd}毫秒")
                } catch (e: Exception) {
                    println("格式错误 示例:Cd=1000 (单位:毫秒)")
                }
            }
            arg.startsWith("Num") -> {
                try {
                    val num = java.lang.Long.parseLong(arg.split("=")[1])
                    Loader.conf.num = num.toInt()
                    Loader.applyConf()
                    println("设置张数为${num}")
                } catch (e: Exception) {
                    println("格式错误 示例:Num=2")
                }
            }
            else -> {
                err.println(
                    "已知参数:\n" +
                            "reload\t#重新加载配置\n" +
                            "Cd=?\t\t#设置冷却\n" +
                            "Num=?\t#设置发图张数\n"
                )
            }
        }
    }
}