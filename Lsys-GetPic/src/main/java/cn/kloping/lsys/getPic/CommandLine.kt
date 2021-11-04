package cn.kloping.lsys.getPic

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.SimpleCommand
import java.lang.System.err

class CommandLine : SimpleCommand(
    PluginMain.INSTANCE, "Lsys_get_pic", "getPic",
    description = "kloping的LsysGetPic插件指令"
) {

    companion object {
        @JvmField
        val INSTANCE = CommandLine()
    }

    @Handler
    fun CommandSender.handle(arg: String) {
        when {
            arg == "reload" -> {
                Loader.loadConf()
                println("已重新getPic加载配置")
            }
            arg.startsWith("cd") -> {
                try {
                    val cd = java.lang.Long.parseLong(arg.split("=")[1])
                    Loader.conf.cd = cd
                    Loader.applyConf()
                    println("设置冷却为${cd}毫秒")
                } catch (e: Exception) {
                    println("格式错误 示例:cd=1000 (单位:毫秒)")
                }
            }
            arg.startsWith("num") -> {
                try {
                    val num = java.lang.Long.parseLong(arg.split("=")[1])
                    Loader.conf.num = num.toInt()
                    Loader.applyConf()
                    println("设置张数为${num}")
                } catch (e: Exception) {
                    println("格式错误 示例:num=2")
                }
            }
            else -> {
                err.println(
                    "已知参数:\n" +
                            "reload\t#重新加载配置\n" +
                            "cd=?\t\t#设置冷却\n" +
                            "num=?\t#设置发图张数\n"
                )
            }
        }
    }
}