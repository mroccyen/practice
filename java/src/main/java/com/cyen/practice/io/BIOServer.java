package com.cyen.practice.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author qingshanpeng
 * @date 2021/7/2 17:22
 * @since 标果工厂-小白杏
 */
public class BIOServer {
	public static void main(String[] args) {
		byte[] buffer = new byte[1024];
		try (ServerSocket server = new ServerSocket(7777)) {
			while (true) {
				System.out.println("等待客户端连接");
				Socket client = server.accept();
				System.out.println("接收到客户端连接：" + client.getRemoteSocketAddress());

				System.out.println("等待客户端发送数据");
				//接受客户端的数据，这时会阻塞服务器
				client.getInputStream().read(buffer);
				String content = new String(buffer);
				System.out.println("接收到客户端数据:" + content);

				//发送数据给客户端
				client.getOutputStream().write("你好，我是服务端发送的数据...".getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
