package cn.kloping.lsys

import cn.kloping.lsys.Resource.i1
import cn.kloping.lsys.savers.PutGetter
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand

class CommandLine : CompositeCommand(
    PluginMain.INSTANCE, "Lsys", "reload",
    description = "kloping的Lsys插件指令"
) {

    companion object {
        @JvmField
        val INSTANCE = CommandLine()
    }

    @Description("重载配置")
    @SubCommand("reload")
    suspend fun CommandSender.LsysReload() {
        i1()
        histMat.clear()
        MethodName2Ostr.clear()
        sendMessage("OK")
    }

    @Description("清除历史匹配")
    @SubCommand("clearHist")
    suspend fun CommandSender.LsysClearHist() {
        histMat.clear()
        MethodName2Ostr.clear()
        sendMessage("OK")
    }

    @Description("设置主人")
    @SubCommand("setMain")
    suspend fun CommandSender.LsysSetMain(@Name("qid") qid: Long) {
        Resource.conf.qq = qid
        Resource.conf.apply()
        sendMessage("OK")
    }

    @CompositeCommand.Description("增加某人积分")
    @CompositeCommand.SubCommand("addScore")
    suspend fun CommandSender.LsysAddScore(
        @CompositeCommand.Name("qq") qid: Long,
        @CompositeCommand.Name("值") n: Long
    ) {
        val user = PutGetter.get(qid, true);
        user.addP(n)
        user.apply()
        sendMessage("OK")
    }
}