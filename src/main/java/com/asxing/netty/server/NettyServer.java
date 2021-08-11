package com.asxing.netty.server;

import com.asxing.netty.codec.PacketCodecHandler;
import com.asxing.netty.codec.Spliter;
import com.asxing.netty.server.handler.AuthHandler;
import com.asxing.netty.server.handler.IMHandler;
import com.asxing.netty.server.handler.LifeCycleHandler;
import com.asxing.netty.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class NettyServer {

    private static final int BEGIN_PORT = 1000;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        final AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");
        final AttributeKey<Object> serverName = AttributeKey.newInstance("serverName");
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .attr(serverName, "nettyServer")
                .childAttr(clientKey, "clientValue")
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .handler(
                        new ChannelInitializer<NioServerSocketChannel>() {
                            @Override
                            protected void initChannel(NioServerSocketChannel ch) {
                                System.out.println("服务启动中");
                                System.out.println(
                                        "handler attr serverName 对应的值："
                                                + ch.attr(serverName).get());
                            }
                        })
                .childHandler(
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) {
                                System.out.println("新连接建立中");
                                System.out.println(
                                        "childHandler attr clientKey 对应的值："
                                                + ch.attr(clientKey).get());
                                ch.pipeline().addLast(new Spliter());
                                ch.pipeline().addLast(LifeCycleHandler.INSTANCE);
                                ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                                ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                                ch.pipeline().addLast(AuthHandler.INSTANCE);
                                ch.pipeline().addLast(IMHandler.INSTANCE);
                            }
                        });
        bind(serverBootstrap, BEGIN_PORT);
    }

    /**
     * 端口绑定
     *
     * @param serverBootstrap bootstrap
     * @param port 端口
     */
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap
                .bind(port)
                .addListener(
                        future -> {
                            if (future.isSuccess()) {
                                System.out.println("端口[" + port + "]绑定成功!");
                            } else {
                                System.err.println("端口[" + port + "]绑定失败!");
                                bind(serverBootstrap, port + 1);
                            }
                        });
    }
}
