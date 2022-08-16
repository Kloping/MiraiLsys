package cn.kloping.lsys.speeches

import cn.kloping.lsys.savers.PutGetter
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand

class CommandLine : CompositeCommand(
    PluginMain.INSTANCE, "Lsys_speeches", "LsysSpeeches",
    description = "kloping的Lsys_speeches插件指令"
) {

    companion object {
        @JvmField
        val INSTANCE = CommandLine()
    }

    @Description("增加今日发言次数,连同累计")
    @SubCommand("addC")
    suspend fun CommandSender.LsysSpeechesaddC(@Name("qq") qid: Long, @Name("值") n: Int) {
        val user = PutGetter.get(qid, true);
        user.addSpeeches(n)
        user.apply()
        sendMessage("OK")
    }

    @Description("设置指定人的发言今日次数")
    @SubCommand("setC")
    suspend fun CommandSender.LsysSpeechesSetC(@Name("qq") qid: Long, @Name("值") n: Int) {
        val user = PutGetter.get(qid, true);
        user.speeches = n.toLong();
        user.apply()
        sendMessage("OK")
    }

    @Description("设置指定人的发言累计次数")
    @SubCommand("setT")
    suspend fun CommandSender.LsysSpeechesSetT(@Name("qq") qid: Long, @Name("值") n: Int) {
        val user = PutGetter.get(qid, true);
        user.speeches_total = n.toLong();
        user.apply()
        sendMessage("OK")
    }

}