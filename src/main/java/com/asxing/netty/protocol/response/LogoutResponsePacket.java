package com.asxing.netty.protocol.response;

import com.asxing.netty.protocol.command.Packet;

import static com.asxing.netty.protocol.command.Command.LOGOUT_RESPONSE;

public class LogoutResponsePacket extends Packet {
    private boolean success;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
