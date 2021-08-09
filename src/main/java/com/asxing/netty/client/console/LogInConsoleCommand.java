package com.asxing.netty.client.console;

import com.asxing.netty.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LogInConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        System.out.println("输入用户名登录: ");
        loginRequestPacket.setUserName(scanner.nextLine());
        loginRequestPacket.setPassword("pwd");
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    private void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
