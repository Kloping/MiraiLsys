package cn.kloping.lsys.utils

import net.mamoe.mirai.event.events.MessageEvent

/**
 * @author github.kloping
 */
interface Receiver {
    /**
     * on event
     *
     * @param event
     */
    fun onReceive(event: MessageEvent?)
}