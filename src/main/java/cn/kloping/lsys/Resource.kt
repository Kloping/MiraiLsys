package cn.kloping.lsys

import cn.kloping.lsys.entitys.Conf
import cn.kloping.lsys.entitys.InvokeGroup
import cn.kloping.lsys.workers.Methods
import io.github.kloping.initialize.FileInitializeValue
import net.mamoe.mirai.console.ConsoleFrontEndImplementation
import net.mamoe.mirai.console.MiraiConsoleImplementation
import java.io.File
import java.util.concurrent.ConcurrentHashMap

object Resource {

    @JvmField
    var conf: Conf = Conf();

    @JvmField
    val loadConfAfter = ArrayList<Runnable>();

    private fun before() {
        val invokeS = LinkedHashMap<String, String>().apply {
            put("积分查询", "m1")
            put("查询积分", "m1")
            put("取积分.*", "m2")
            put("存积分.*", "m3")
            put("开启", "mOpen")
            put("关闭", "mClose")
            put("开启群.*?", "mOpen0")
            put("关闭.*?", "mClose0")
            put("开启私聊", "mpOpen")
            put("关闭私聊", "mpClose")
            put("菜单", "menuN")
        }

        val invokesAfter = ConcurrentHashMap<String, Array<String>>().apply {
            put("积分查询", arrayOf("<At = ?>\n剩的积分:$1\n存的积分$2"))
            put("查询积分", arrayOf("<At = ?>\n剩的积分:$1\n存的积分$2"))
            put("取积分.*", arrayOf("<At = ?>\n取积分成功", "<At = ?>\n取积分失败,存的积分不足", "<At = ?>\n格式错误"))
            put("存积分.*", arrayOf("<At = ?>\n存积分成功", "<At = ?>\n存积分失败,剩余积分不足", "<At = ?>\n格式错误"))
            put("开启", arrayOf("<At = ?>\n开启成功", "<At = ?>\n本就是开启"))
            put("关闭", arrayOf("<At = ?>\n关闭成功", "<At = ?>\n本就是关闭"))
            put("开启私聊", arrayOf("<At = ?>\n开启成功"))
            put("关闭私聊", arrayOf("<At = ?>\n关闭成功"))
            put("菜单", arrayOf("<At = ?>\n$1"))
        }

        val invokeGroup = InvokeGroup("main").apply {
            this.invokes.putAll(invokeS)
            this.invokesAfter.putAll(invokesAfter)
        }

        val invokeGroupMap = ConcurrentHashMap<String, InvokeGroup>().apply {
            put(invokeGroup.id, invokeGroup)
        }
        conf = Conf(File(rootPath, "/data/LSys").absolutePath, -1, HashSet(), invokeGroupMap, false)
    }

    @OptIn(ConsoleFrontEndImplementation::class)
    @JvmField
    val rootPath: String = MiraiConsoleImplementation.getInstance().rootPath.toFile().absolutePath

    @JvmStatic
    fun i1() {
        if (!File("$rootPath/", "conf/LSys/conf.json").exists()) {
            try {
                File("$rootPath/", "conf/LSys/conf.json").parentFile.mkdirs()
                File("$rootPath/", "conf/LSys/conf.json").createNewFile()
                before()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else conf = FileInitializeValue.getValue(
            File("$rootPath/", "conf/LSys/conf.json").absolutePath, conf, true
        )
        for (r in loadConfAfter) r.run()
        conf.load()
        conf.apply()
        Methods.init()
    }
}
