package com.asxing.netty.client.handler;

import com.asxing.netty.protocol.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

public class HeartBeatTimehandler extends ChannelInboundHandlerAdapter {
    public static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor()
                .schedule(
                        () -> {
                            if (ctx.channel().isActive()) {
                                ctx.writeAndFlush(new HeartBeatRequestPacket());
                                scheduleSendHeartBeat(ctx);
                            }
                        },
                        HEARTBEAT_INTERVAL,
                        TimeUnit.SECONDS);
    }
}
