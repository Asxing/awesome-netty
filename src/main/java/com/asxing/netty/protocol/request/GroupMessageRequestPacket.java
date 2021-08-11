package com.asxing.netty.protocol.request;

import com.asxing.netty.protocol.command.Packet;

import static com.asxing.netty.protocol.command.Command.GROUP_MESSAGE_REQUEST;

public class GroupMessageRequestPacket extends Packet {
    private String groupId;
    private String message;

    public GroupMessageRequestPacket() {}

    public GroupMessageRequestPacket(String groupId, String message) {
        this.groupId = groupId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
