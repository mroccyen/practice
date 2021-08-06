package com.cyen.practice.jdk_io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author qingshanpeng
 * @date 2021/7/6 14:36
 * @since 标果工厂-小白杏
 */
public class NIOServerSimple {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(7777));

			//设置为非阻塞
			serverSocketChannel.configureBlocking(false);

			while (true) {
				SocketChannel clientChannel = serverSocketChannel.accept();
				//没有客户端连接
				if (clientChannel == null) {
					System.out.println("等待客户端连接...");
					Thread.sleep(5000);
				} else {
					System.out.println("接收客户端连接...");

					//切换模式
					buffer.flip();
					//从客户端读取数据
					int read = clientChannel.read(buffer);
					if (read != 0) {
						String content = StandardCharsets.UTF_8.decode(buffer).toString();
						System.out.println(content);
					} else {
						System.out.println("未收到客户端消息...");
					}
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
