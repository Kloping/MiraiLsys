package cn.kloping.lsys

import cn.kloping.lsys.Resource.conf
import cn.kloping.lsys.Resource.i1
import cn.kloping.lsys.savers.PutGetter
import cn.kloping.lsys.utils.Receiver
import cn.kloping.lsys.utils.toText
import net.mamoe.mirai.console.ConsoleFrontEndImplementation
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.extension.PluginComponentStorage
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.EventHandler
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.SimpleListenerHost
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.GroupMessageSyncEvent
import net.mamoe.mirai.event.events.MessageEvent
import kotlin.coroutines.CoroutineContext

/**
 * @Author hrs 3474006766@qq.com
 */
class PluginMain() : KotlinPlugin(
    JvmPluginDescriptionBuilder("cn.kloping.Lsys", "1.6")
        .name("p_0-Author-HRS-LSys-Loaded")
        .info("Lsys-main")
        .author("HRS")
        .build().apply {
        }
) {

    @OptIn(ConsoleFrontEndImplementation::class)
    override fun onEnable() {
        logger.info("HRS's LSys Plugin loaded! @作者:qq-3474006766")
        i1();
        CommandManager.registerCommand(CommandLine.INSTANCE)
        GlobalEventChannel.registerListenerHost(object : SimpleListenerHost() {
            override fun handleException(context: CoroutineContext, exception: Throwable) {
                super.handleException(context, exception)
            }

            @EventHandler
            suspend fun handleMessage(event: GroupMessageEvent) {
                val text = toText(event.message).trim();
                handMessage(text, event)
            }

            @EventHandler
            suspend fun handleMessage(event: FriendMessageEvent) {
                val text = toText(event.message).trim();
                if (conf.prK)
                    handMessage(text, event)
            }

            @EventHandler
            suspend fun handleMessage(event: GroupMessageSyncEvent) {
                val text = toText(event.message).trim();
                handMessage(text, event)
            }
        })

        val r0 = object : Receiver {
            override fun onReceive(event: MessageEvent?) {
                val user = event?.sender?.let { PutGetter.get(it.id, true) }
                user?.addSpeeches()?.apply()
            }
        }
        Resource.receivers.add(r0);
    }

    companion object {
        @JvmField
        var INSTANCE = PluginMain()
    }
}