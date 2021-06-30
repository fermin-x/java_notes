package com.fermin.netty.heartbeat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class EchoClient {
    public void test() throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        //异步操作
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
        while (true) {
            selector.select(100);
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (!key.isValid()) {
                    continue;
                }
                if (key.isConnectable()) {
                    System.out.println("是否链接：" + socketChannel.isConnected());
                }
            }
        }
    }
}
