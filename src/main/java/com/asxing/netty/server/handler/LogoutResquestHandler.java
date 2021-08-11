package com.asxing.netty.server.handler;

import com.asxing.netty.protocol.request.LogoutRequestPacket;
import com.asxing.netty.protocol.response.LogoutResponsePacket;
import com.asxing.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LogoutResquestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    public static final LogoutResquestHandler INSTANCE = new LogoutResquestHandler();

    private LogoutResquestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket logoutRequestPacket)
            throws Exception {
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutRequestPacket);
    }
}
