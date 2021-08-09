package com.asxing.netty.client.handler;

import com.asxing.netty.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class QuitGroupResponseHanlder extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket msg) throws Exception {
        if (msg.getSuccess()) {
            System.out.println("退出群聊[" + msg.getGroupId() + "]成功!");
        } else {
            System.out.println("退出群聊[" + msg.getGroupId() + "]失败!");
        }
    }
}
