package cn.kloping.lsys.entitys

import cn.kloping.initialize.FileInitializeValue
import cn.kloping.lsys.Resource
import cn.kloping.lsys.Resource.rootPath
import com.alibaba.fastjson.annotation.JSONField
import java.io.File
import java.util.concurrent.ConcurrentHashMap

data class Conf(
    val path: String,
    var qq: Number,
    var opens: java.util.HashSet<Number>,
    val invokeGroups: ConcurrentHashMap<String, InvokeGroup>,
    var prK: Boolean
) : Entity {

    constructor() : this("", -1, HashSet(), ConcurrentHashMap<String, InvokeGroup>(), false);

    @JSONField(serialize = false)
    val invokes = ConcurrentHashMap<String, String>();

    @JSONField(serialize = false)
    val invokesAfter = ConcurrentHashMap<String, Array<String>>();

    fun load() {
        for (ig in invokeGroups.values) {
            invokes.putAll(ig.invokes)
            invokesAfter.putAll(ig.invokesAfter)
        }
    }

    override fun apply() {
        FileInitializeValue.putValues(File("$rootPath/", "conf/LSys/conf.json").absolutePath, Resource.conf, true)
    }
}
