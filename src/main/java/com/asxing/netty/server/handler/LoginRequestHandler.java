package com.asxing.netty.server.handler;

import com.asxing.netty.protocol.request.LoginRequestPacket;
import com.asxing.netty.protocol.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket)
            throws Exception {
        System.out.println(new Date() + ": 客户端开始登录.......");
        LoginResponsePacket packet = new LoginResponsePacket();
        packet.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            packet.setSuccess(true);
            System.out.println(new Date() + ": 登录成功!");
        } else {
            packet.setReason("账号密码校验失败!");
            packet.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }
        ctx.channel().writeAndFlush(packet);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
