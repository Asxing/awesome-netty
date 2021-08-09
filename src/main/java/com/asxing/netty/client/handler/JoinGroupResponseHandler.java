package com.asxing.netty.client.handler;

import com.asxing.netty.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket packet) throws Exception {
        if (packet.getSuccess()) {
            System.out.println("加入群[" + packet.getGroupId() + "]成功!");
        } else {
            System.out.println("加入群[" + packet.getGroupId() + "]失败，原因为: " + packet.getReason());
        }
    }
}
