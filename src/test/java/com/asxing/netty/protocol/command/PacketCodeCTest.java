package com.asxing.netty.protocol.command;

import com.asxing.netty.protocol.request.LoginRequestPacket;
import com.asxing.netty.serialize.Serializer;
import com.asxing.netty.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class PacketCodeCTest {

    private PacketCodeC packetCodeCUnderTest;

    @Before
    public void setUp() {
        packetCodeCUnderTest = new PacketCodeC();
    }

    @Test
    public void testEncode() {
        final Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserName("Thomas");
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setPassword("pwd");

        final ByteBuf result =
                packetCodeCUnderTest.encode(ByteBufAllocator.DEFAULT, loginRequestPacket);
        final Packet packet = packetCodeCUnderTest.decode(result);

        Assert.assertArrayEquals(
                serializer.serialize(loginRequestPacket), serializer.serialize(packet));
    }
}
