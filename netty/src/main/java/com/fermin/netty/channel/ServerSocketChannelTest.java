package com.fermin.netty.channel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelTest {

    @Test
    public void test1() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));

        while (true) {
            // 建立链接
            handle(serverSocketChannel.accept());
        }

    }

    public void handle(final SocketChannel socketChannel) {
        // 通信
        Thread thread = new Thread(() -> {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                try {
                    buffer.clear();
                    //阻塞状态，读取数据
                    socketChannel.read(buffer);
                    //准备读取缓存中的数据
                    buffer.flip();

                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String message = new String(bytes);
                    System.out.println(message);

                    buffer.rewind();
                    socketChannel.write(buffer);
                    if (message.trim().equals("exit")) {
                        break;
                    }
                } catch (IOException e) {

                }
            }
        });
        thread.start();
    }
}