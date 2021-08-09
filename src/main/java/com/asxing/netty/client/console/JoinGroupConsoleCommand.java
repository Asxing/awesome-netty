package com.asxing.netty.client.console;

import com.asxing.netty.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket requestPacket = new JoinGroupRequestPacket();
        System.out.println("输入 groupId, 加入群聊: ");
        String groupId = scanner.next();
        requestPacket.setGroupId(groupId);
        channel.writeAndFlush(requestPacket);
    }
}
