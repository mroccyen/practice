package com.cyen.practice.jdk_io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author qingshanpeng
 * @date 2021/7/6 14:36
 * @since 标果工厂-小白杏
 */
public class NIOServerSimple {
    public static void main(String[] args) {
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        ByteBuffer writeBuff = ByteBuffer.allocate(1024);
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(7777));

            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (selector.select() > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel clientChannel = serverSocketChannel.accept();
                        clientChannel.configureBlocking(false);
                        System.out.println("接收客户端连接...");
                        //注册读事件
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    }
                    if (selectionKey.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                        //从客户端读取数据
                        clientChannel.read(readBuffer);
                        //切换模式
                        readBuffer.flip();
                        System.out.println("接收客户端数据 : " + new String(readBuffer.array()));
                        selectionKey.interestOps(SelectionKey.OP_WRITE);

                        readBuffer.clear();
                    }
                    if (selectionKey.isWritable()) {
                        writeBuff.rewind();
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        socketChannel.write(writeBuff);
                        selectionKey.interestOps(SelectionKey.OP_READ);

                        writeBuff.clear();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
