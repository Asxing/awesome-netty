package com.asxing.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 新建连接服务端给予响应");
        byte[] bytes = "hello, 欢迎首次连接服务!\n".getBytes(StandardCharsets.UTF_8);
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(
                new Date()
                        + ": server receive data -> "
                        + byteBuf.toString(StandardCharsets.UTF_8));
        System.out.println(new Date() + ": server write date");
        byte[] bytes = "hi, 欢迎关注我的微信公众号 布斯行 !".getBytes(StandardCharsets.UTF_8);
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        ctx.channel().writeAndFlush(buffer);
    }
}
