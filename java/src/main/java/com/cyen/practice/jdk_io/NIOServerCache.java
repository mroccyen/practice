package com.cyen.practice.jdk_io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qingshanpeng
 * @date 2021/7/6 14:36
 * @since 标果工厂-小白杏
 */
public class NIOServerCache {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		List<SocketChannel> cache = new ArrayList<>();
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
					Thread.sleep(2000);
				} else {
					System.out.println("接收客户端连接...");
					//添加到缓存
					cache.add(clientChannel);
				}
				for (SocketChannel channel : cache) {
					//设置为非阻塞
					channel.configureBlocking(false);
					//从客户端读取数据
					int read = channel.read(buffer);
					if (read != 0) {
						//切换模式
						buffer.flip();
						String content = StandardCharsets.UTF_8.decode(buffer).toString();
						System.out.println(content);
						buffer.clear();
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
