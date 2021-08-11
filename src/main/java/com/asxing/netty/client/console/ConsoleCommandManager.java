package com.asxing.netty.client.console;

import com.asxing.netty.utils.SessionUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {
    private static final Map<String, ConsoleCommand> CONSOLE_COMMAND_MAP;

    static {
        CONSOLE_COMMAND_MAP = new HashMap<>();
        CONSOLE_COMMAND_MAP.put("sendToUser", new SendToUserConsoleCommand());
        CONSOLE_COMMAND_MAP.put("logout", new LogoutConsoleCommand());
        CONSOLE_COMMAND_MAP.put("createGroup", new CreateGroupConsoleCommand());
        CONSOLE_COMMAND_MAP.put("joinGroup", new JoinGroupConsoleCommand());
        CONSOLE_COMMAND_MAP.put("quitGroup", new QuitGroupConsoleCommand());
        CONSOLE_COMMAND_MAP.put("listGroup", new ListGroupMemberConsoleCommand());
        CONSOLE_COMMAND_MAP.put("sendToGroup", new SendToGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String commandName = scanner.next();
        if (!SessionUtil.hasLogin(channel)) {
            return;
        }
        ConsoleCommand command = CONSOLE_COMMAND_MAP.get(commandName);
        if (Objects.nonNull(command)) {
            command.exec(scanner, channel);
        } else {
            System.out.println("无法识别[" + command + "]指令, 请重新输入!");
        }
    }
}
