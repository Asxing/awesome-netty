package com.asxing.netty.client.console;

import com.asxing.netty.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {
    public static final String USER_ID_SPLIT = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        System.out.println("[拉人群聊] 输入 UserId 列表，UserId 之间英文逗号隔开: ");
        String userIds = scanner.next();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLIT)));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
