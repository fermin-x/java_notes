package com.fermin.netty.channel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Arrays;
import java.util.Iterator;

/**
 * UDP 数据流管道
 */
public class DatagramChannelTest {

    @Test
    public static void test1() throws IOException {
        // step 1 open channel
        DatagramChannel channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(8090));

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //step 2 write data to buffer
        channel.read(byteBuffer);

        //step 3 get ready to read from buffer to JVM
        byteBuffer.flip();

        //step 4 read
        byte[] content = new byte[byteBuffer.remaining()];
        while (byteBuffer.hasRemaining()) {
            for (int i = 0; i < content.length; i++) {
                content[i] = byteBuffer.get();
            }
        }

        System.out.println(Arrays.toString(content));
    }

    @Test
    //通过选择器 实现UDP
    public void test2() throws IOException {
        Selector selector = Selector.open();
        DatagramChannel channel =  DatagramChannel.open();
        channel.bind(new InetSocketAddress(8080));

        channel.configureBlocking(false);

        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);
        while (true) {
            //刷新key
            int count = selector.select();
            if (count >0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                   SelectionKey key = iterator.next();
                    handle(key);
                    iterator.remove();
                }
            }
        }
    }

    private void handle(SelectionKey key) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        DatagramChannel channel = (DatagramChannel)  key.channel();
        buffer.clear();
        channel.receive(buffer);
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        key.cancel();
    }
}
