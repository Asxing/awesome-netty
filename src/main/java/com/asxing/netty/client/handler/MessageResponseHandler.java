package com.asxing.netty.client.handler;

import com.asxing.netty.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(
            ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket)
            throws Exception {
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUserName();
        System.out.println(
                fromUserId + ":" + fromUserName + "-> " + messageResponsePacket.getMessage());
    }
}
