package com.asxing.netty.client;

import com.asxing.netty.protocol.command.PacketCodeC;
import com.asxing.netty.protocol.request.MessageRequestPacket;
import com.asxing.netty.utils.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");
        bootstrap
                .group(workGroup)
                .channel(NioSocketChannel.class)
                .attr(clientKey, "clientValue")
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(
                        new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) {
                                System.out.println(
                                        "handler attr clientKey 对应的值：" + ch.attr(clientKey).get());
                                ch.pipeline().addLast(new FirstClientHandler());
                            }
                        });
        connect(bootstrap, "localhost", 1000, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap
                .connect(host, port)
                .addListener(
                        future -> {
                            if (future.isSuccess()) {
                                System.out.println("连接成功!");
                                Channel channel = ((ChannelFuture) future).channel();
                                startConsoleThread(channel);
                            } else if (retry == 0) {
                                System.out.println("重试次数已用完，放弃连接！");
                            } else {
                                int order = (MAX_RETRY - retry) + 1;
                                int delay = 1 << order;
                                System.out.println(new Date() + ": 连接失败, 第" + order + "次重连....");
                                bootstrap
                                        .config()
                                        .group()
                                        .schedule(
                                                () -> connect(bootstrap, host, port, retry - 1),
                                                delay,
                                                TimeUnit.SECONDS);
                            }
                        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(
                        () -> {
                            while (!Thread.interrupted()) {
                                if (LoginUtil.hasLogin(channel)) {
                                    System.out.println("发送消息到服务器");
                                    Scanner scanner = new Scanner(System.in);
                                    String line = scanner.nextLine();
                                    MessageRequestPacket messageRequestPacket =
                                            new MessageRequestPacket();
                                    messageRequestPacket.setMessage(line);
                                    ByteBuf buffer =
                                            PacketCodeC.INSTANCE.encode(
                                                    channel.alloc(), messageRequestPacket);
                                    channel.writeAndFlush(buffer);
                                }
                            }
                        })
                .start();
    }
}
