package com.cyen.practice.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author qingshanpeng
 * @date 2021/7/2 17:31
 * @since 标果工厂-小白杏
 */
public class NIOClient2Connect2 {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		try {
			SocketChannel channel = SocketChannel.open();
			channel.connect(new InetSocketAddress("127.0.0.1", 7777));
			while (!channel.finishConnect()) {
				System.out.println("等待连接服务器...");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			while (true) {
				//发送数据给服务端
				ByteBuffer put = buffer.put(scanner().getBytes());
				//切换模式
				put.flip();

				channel.write(put);

				buffer.clear();
				put.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String scanner() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入：");
		if (scanner.hasNext()) {
			return scanner.next();
		}
		throw new NullPointerException();
	}
}
