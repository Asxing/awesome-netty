package com.asxing.netty.client.console;

import com.asxing.netty.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket requestPacket = new QuitGroupRequestPacket();
        System.out.println("输入groupId, 退出群聊: ");
        String groupId = scanner.next();
        requestPacket.setGroupId(groupId);
        channel.writeAndFlush(requestPacket);
    }
}
