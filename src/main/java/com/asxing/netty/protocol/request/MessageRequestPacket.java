package com.asxing.netty.protocol.request;

import com.asxing.netty.protocol.command.Packet;

import static com.asxing.netty.protocol.command.Command.MESSAGE_REQUEST;

public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String message;

    public MessageRequestPacket() {
    }

    public MessageRequestPacket(String toUserId, String line) {
        this.toUserId = toUserId;
        this.message = line;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }
}
