package com.asxing.netty.client.handler;

import com.asxing.netty.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        System.out.println("群创建成功, Id: " + createGroupResponsePacket.getGroupId());
        System.out.println("群里有: " + createGroupResponsePacket.getUserNameList());
    }
}
