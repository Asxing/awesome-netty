package com.asxing.netty.server.handler;

import com.asxing.netty.protocol.request.LoginRequestPacket;
import com.asxing.netty.protocol.response.LoginResponsePacket;
import com.asxing.netty.session.Session;
import com.asxing.netty.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket)
            throws Exception {
        System.out.println(new Date() + ": 客户端开始登录.......");
        LoginResponsePacket packet = new LoginResponsePacket();
        packet.setVersion(loginRequestPacket.getVersion());
        packet.setUserName(loginRequestPacket.getUserName());

        if (valid(loginRequestPacket)) {
            packet.setSuccess(true);
            String userId = randomUserId();
            packet.setUserId(userId);
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
        } else {
            packet.setReason("账号密码校验失败!");
            packet.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }
        ctx.channel().writeAndFlush(packet);
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }


    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
