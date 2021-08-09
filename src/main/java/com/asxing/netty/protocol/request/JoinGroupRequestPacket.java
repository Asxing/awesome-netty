package com.asxing.netty.protocol.request;

import com.asxing.netty.protocol.command.Packet;

import static com.asxing.netty.protocol.command.Command.JOIN_GROUP_REQUEST;

public class JoinGroupRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
