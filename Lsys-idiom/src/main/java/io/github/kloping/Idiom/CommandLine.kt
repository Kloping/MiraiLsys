package io.github.kloping.Idiom

import io.github.kloping.Idiom.entity.Conf
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand

/**
 * @author github-kloping
 */
class CommandLine : CompositeCommand(
    PluginMain.INSTANCE, "Lsys_idiom",
    "lsys-idiom", description = "lsys的 idiom 成语接龙 插件指令"
) {

    companion object {
        @JvmField
        val INSTANCE = CommandLine()
    }

    @Description("设置积分值")
    @SubCommand("setS1")
    suspend fun CommandSender.lsysIdiomSetS1(@Name("值") i1: Int) {
        Conf.INSTANCE.setS1(i1)
        Conf.INSTANCE.apply()
        sendMessage("设置 积分值为: $i1")
    }

    @Description("设置最大答错次数")
    @SubCommand("setMaxError")
    suspend fun CommandSender.lsysIdiomSetMaxError(@Name("值") i1: Int) {
        Conf.INSTANCE.setMaxError(i1)
        Conf.INSTANCE.apply()
        sendMessage("设置 最大答错次数: $i1")
    }
}