package com.asxing.netty.server.handler;

import com.asxing.netty.protocol.request.CreateGroupRequestPacket;
import com.asxing.netty.protocol.response.CreateGroupResponsePacket;
import com.asxing.netty.utils.IDUtil;
import com.asxing.netty.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket requestPacket) throws Exception {
        List<String> userIdList = requestPacket.getUserIdList();
        ArrayList<String> userNameList = new ArrayList<>();
        DefaultChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (Objects.nonNull(channel)) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(IDUtil.randomId());
        responsePacket.setUserNameList(userNameList);

        channelGroup.writeAndFlush(responsePacket);

        System.out.println("群创建成功, id: " + responsePacket.getGroupId());
        System.out.println("群里有: " + responsePacket.getUserNameList());
        SessionUtil.bindChannelGroup(responsePacket.getGroupId(), channelGroup);
    }
}
