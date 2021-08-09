package com.asxing.netty.protocol.response;

import com.asxing.netty.protocol.command.Packet;

import static com.asxing.netty.protocol.command.Command.JOIN_GROUP_RESPONSE;

public class JoinGroupResponsePacket extends Packet {
    private Boolean success;
    private String groupId;
    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
