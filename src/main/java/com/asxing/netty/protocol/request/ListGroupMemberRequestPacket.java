package com.asxing.netty.protocol.request;

import com.asxing.netty.protocol.command.Packet;

import static com.asxing.netty.protocol.command.Command.LIST_GROUP_MEMBER_REQUEST;

public class ListGroupMemberRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBER_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
