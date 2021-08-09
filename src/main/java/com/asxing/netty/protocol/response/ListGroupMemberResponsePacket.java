package com.asxing.netty.protocol.response;

import com.asxing.netty.protocol.command.Packet;
import com.asxing.netty.session.Session;

import java.util.List;

import static com.asxing.netty.protocol.command.Command.LIST_GROUP_MEMBER_RESPONSE;

public class ListGroupMemberResponsePacket extends Packet {
    private String groupId;
    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBER_RESPONSE;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }
}
