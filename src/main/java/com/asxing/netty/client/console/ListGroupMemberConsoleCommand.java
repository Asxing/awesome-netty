package com.asxing.netty.client.console;

import com.asxing.netty.protocol.request.ListGroupMemberRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class ListGroupMemberConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入groupId, 获取群成员列表");
        String groupId = scanner.next();
        ListGroupMemberRequestPacket requestPacket = new ListGroupMemberRequestPacket();
        requestPacket.setGroupId(groupId);
        channel.writeAndFlush(requestPacket);
    }
}
