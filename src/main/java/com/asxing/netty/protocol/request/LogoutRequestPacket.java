package com.asxing.netty.protocol.request;

import com.asxing.netty.protocol.command.Packet;

import static com.asxing.netty.protocol.command.Command.LOGOUT_REQUEST;

public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
