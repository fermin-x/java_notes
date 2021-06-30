package com.fermin.netty;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

public class SelectorReadTest {
    @Test
    public void test() throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        System.out.println(channel.validOps() & SelectionKey.OP_READ);
        System.out.println(channel.validOps() & SelectionKey.OP_WRITE);
        System.out.println(channel.validOps() & SelectionKey.OP_CONNECT);
        System.out.println(channel.validOps() & SelectionKey.OP_WRITE);
    }
}
