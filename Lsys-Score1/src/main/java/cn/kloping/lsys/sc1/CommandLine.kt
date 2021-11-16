package cn.kloping.lsys.sc1

import cn.kloping.initialize.FileInitializeValue
import cn.kloping.lsys.Resource
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import java.lang.System.err

class CommandLine : SimpleCommand(
    PluginMain.INSTANCE, "Lsys_sc1", "sc1",
    description = "kloping的Lsys_sc1插件指令"
) {

    companion object {
        @JvmField
        val INSTANCE = CommandLine()
    }

    @Handler
    fun CommandSender.handle(arg: String) {
        when {
            arg == "reload" -> {
                sc1.conf = FileInitializeValue.getValue(Resource.rootPath + "/conf/Lsys/sc1.json", sc1.conf, true)
                println("已重新getSc1加载配置")
            }
            arg.startsWith("win") -> {
                try {
                    val cd = java.lang.Long.parseLong(arg.split("=")[1])
                    sc1.conf.setWin(cd.toInt())
                    sc1.conf.apply();
                    println("设置赢的概率为${cd}%")
                } catch (e: Exception) {
                    println("格式错误 示例:win=40 (单位:%)")
                }
            }
            else -> {
                err.println(
                    "已知参数:\n" +
                            "reload\t#重新加载配置\n" +
                            "win=?\t\t#设置猜拳赢的概率\n"
                )
            }
        }
    }
}