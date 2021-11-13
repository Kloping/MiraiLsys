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
    var opens: Array<Number>,
    val invokeGroups: ConcurrentHashMap<String, InvokeGroup>,
    var prK: Boolean
) : Entity {

    constructor() : this("", -1, arrayOf(-1), ConcurrentHashMap<String, InvokeGroup>(), false);

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Conf

        if (path != other.path) return false
        if (qq != other.qq) return false
        if (!opens.contentEquals(other.opens)) return false
        if (invokeGroups != other.invokeGroups) return false
        if (prK != other.prK) return false
        if (invokes != other.invokes) return false
        if (invokesAfter != other.invokesAfter) return false

        return true
    }

    override fun hashCode(): Int {
        var result = path.hashCode()
        result = 31 * result + qq.hashCode()
        result = 31 * result + opens.contentHashCode()
        result = 31 * result + invokeGroups.hashCode()
        result = 31 * result + prK.hashCode()
        result = 31 * result + invokes.hashCode()
        result = 31 * result + invokesAfter.hashCode()
        return result
    }
}
