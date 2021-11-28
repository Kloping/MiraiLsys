package cn.kloping.lsys

import cn.kloping.lsys.Resource.conf
import cn.kloping.lsys.Resource.i1
import cn.kloping.lsys.utils.toText
import net.mamoe.mirai.console.ConsoleFrontEndImplementation
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.EventHandler
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.SimpleListenerHost
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import kotlin.coroutines.CoroutineContext

/**
 * @Author hrs 3474006766@qq.com
 */
class PluginMain : KotlinPlugin {

    constructor() : super(
        JvmPluginDescriptionBuilder("cn.kloping.Lsys", "0.2.6")
            .name("插件_ Author => HRS LSys Loaded")
            .info("插件")
            .author("HRS")
            .build().apply {
            }
    )

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
        })
    }

    companion object {
        @JvmField
        var INSTANCE = PluginMain()
    }
}