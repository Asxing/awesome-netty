package com.asxing.netty.client.handler;

import com.asxing.netty.protocol.response.ListGroupMemberResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ListGroupMemberReponseHandler extends SimpleChannelInboundHandler<ListGroupMemberResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberResponsePacket msg) throws Exception {
        System.out.println("群[" + msg.getGroupId() + "]中的人包括: " + msg.getSessionList());
    }
}
