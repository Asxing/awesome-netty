package com.asxing.netty.server.handler;

import com.asxing.netty.protocol.request.ListGroupMemberRequestPacket;
import com.asxing.netty.protocol.response.ListGroupMemberResponsePacket;
import com.asxing.netty.session.Session;
import com.asxing.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.List;
import java.util.stream.Collectors;

public class ListGroupMemberRequestHandler
        extends SimpleChannelInboundHandler<ListGroupMemberRequestPacket> {
    @Override
    protected void channelRead0(
            ChannelHandlerContext ctx, ListGroupMemberRequestPacket requestPacket)
            throws Exception {
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        List<Session> sessionList =
                channelGroup.stream().map(SessionUtil::getSession).collect(Collectors.toList());
        ListGroupMemberResponsePacket responsePacket = new ListGroupMemberResponsePacket();
        responsePacket.setGroupId(groupId);
        responsePacket.setSessionList(sessionList);
        ctx.channel().writeAndFlush(responsePacket);
    }
}
