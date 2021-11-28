package io.github.kloping.Idiom;

import io.github.kloping.Idiom.entity.Conf;
import net.mamoe.mirai.console.command.java.JSimpleCommand;

public class CommandLine extends JSimpleCommand {
    public CommandLine() {
        super(PluginMain.INSTANCE, "Lsys_idiom", "lsys-idiom");
        setDescription("lsys的 idiom 成语接龙 插件指令");
    }

    public static final CommandLine INSTANCE = new CommandLine();

    @Handler
    public String m1(String arg) {
        String[] ss = arg.split("=");
        try {
            Integer i1 = Integer.parseInt(ss[1].trim());
            switch (ss[0]) {
                case "setS1":
                    Conf.INSTANCE.setS1(i1);
                    Conf.INSTANCE.apply();
                    System.out.println("设置 积分值为: " + i1);
                    return "设置 积分值为: " + i1;
                case "setMaxError":
                    Conf.INSTANCE.setMaxError(i1);
                    Conf.INSTANCE.apply();
                    System.out.println("设置 最大答错次数: " + i1);
                    return "设置 最大答错次数: " + i1;
            }
        } catch (Exception e) {
        }
        System.err.println("未知命令: \n\t已知: setS1=2\t#设置答对答错获得的积分\n\tsetMaxError=5\t#设置最大打错次数");
        return "";
    }
}
