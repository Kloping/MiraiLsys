package cn.kloping.lsys.entitys

import net.mamoe.mirai.event.events.MessageEvent

/**
 * str: 原话
 * oStr: 匹配到的语句
 * sendId: 发送人Id
 * gId: 群Id 若存在
 */
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
