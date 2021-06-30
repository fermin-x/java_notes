package com.fermin.netty.echo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class EchoServer {
    //1打开serversocket
    //2启动selector

    @Test
    public void openServer(int point) throws IOException {
        ServerSocketChannel serversocketChannel = ServerSocketChannel.open();
        //在注册之前设置成非阻塞
        serversocketChannel.configureBlocking(false);
        serversocketChannel.bind(new InetSocketAddress(8080));

        Selector selector = Selector.open();
        serversocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        new Thread(() -> {
            try {
                startSelector(selector);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "selector").start();


        //线程
        while (true) {

        }
    }

    private void startSelector(Selector selector) throws IOException {
        while (true) {
//            selector.select(1000);
            int count = selector.select();
            if (count == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isValid() && selectionKey.isAcceptable()) {
                    accept(selectionKey);
                }
                if (selectionKey.isValid() && selectionKey.isReadable()) {
                    new Thread(() -> {
                        try {
                            request(selectionKey);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
                iterator.remove();
            }
        }
    }

    private void request(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        channel.read(byteBuffer);

        byteBuffer.flip();
        channel.write(byteBuffer);
    }

    private void accept(SelectionKey selectionKey) {
        SocketChannel channel = (SocketChannel) selectionKey.channel();

    }
}
