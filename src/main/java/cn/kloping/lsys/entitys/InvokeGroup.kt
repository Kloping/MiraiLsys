package cn.kloping.lsys.entitys

import cn.kloping.lsys.Resource
import java.util.concurrent.ConcurrentHashMap

class InvokeGroup(val id: String) : Entity {
    val invokes = LinkedHashMap<String, String>();
    val invokesAfter = ConcurrentHashMap<String, Array<String>>();

    override fun apply() {
        Resource.conf.invokeGroups[id] = this
    }
}