package cn.kloping.lsys.entitys

import cn.kloping.lsys.PluginMain
import cn.kloping.lsys.Resource
import cn.kloping.lsys.Resource.ROOT_PATH
import com.alibaba.fastjson.annotation.JSONField
import io.github.kloping.initialize.FileInitializeValue
import net.mamoe.mirai.console.permission.AbstractPermitteeId
import net.mamoe.mirai.console.permission.PermissionService.Companion.hasPermission
import net.mamoe.mirai.console.permission.PermitteeId
import java.io.File
import java.util.concurrent.ConcurrentHashMap


data class Conf(
    var path: String,
    var qq: Number,
    var opens: java.util.HashSet<Long>,
    var invokeGroups: ConcurrentHashMap<String, InvokeGroup>,
    var prK: Boolean
) : Entity {

    constructor() : this("", -1, LinkedHashSet(), ConcurrentHashMap<String, InvokeGroup>(), false);

    @JSONField(serialize = false, deserialize = false)
    val invokes = ConcurrentHashMap<String, String>();

    @JSONField(serialize = false, deserialize = false)
    val invokesAfter = ConcurrentHashMap<String, Array<String>>();

    fun load() {
        for (ig in invokeGroups.values) {
            invokes.putAll(ig.invokes)
            invokesAfter.putAll(ig.invokesAfter)
        }
    }

    fun hasPerm(qid: Long): Boolean {
        val permitee: PermitteeId = AbstractPermitteeId.ExactUser(qid);
        return qid == qq || permitee.hasPermission(PluginMain.INSTANCE.parentPermission.id)
    }

    override fun apply() {
        FileInitializeValue.putValues(File("$ROOT_PATH/", "conf/LSys/conf.json").absolutePath, Resource.conf, true)
    }
}
