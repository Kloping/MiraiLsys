package cn.kloping.lsys.entitys

import net.mamoe.mirai.event.events.MessageEvent

data class Request(
    val str:String,
    val oStr:String,
    val sendId:Number,
    val gId:Number,
    val event:MessageEvent,
    val any:Any?
):Entity{
    override fun apply() {}
}
