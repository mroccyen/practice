package com.cyen.practice.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qingshanpeng
 * @date 2021/7/2 17:22
 * @since 标果工厂-小白杏
 */
public class BIOMultiThreadServer {
	public static void main(String[] args) {
		byte[] buffer = new byte[1024];
		ExecutorService executorService = Executors.newFixedThreadPool(5);

		try {
			ServerSocket server = new ServerSocket(7777);
			System.out.println("等待客户端连接");
			while (true) {
				Socket client = server.accept();
				executorService.execute(() -> {
					try {
						System.out.println("接收到客户端连接：" + client.getRemoteSocketAddress());

						//发送数据给客户端
						client.getOutputStream().write("你好，我是服务端发送的数据...".getBytes());

						//接受客户端的数据
						client.getInputStream().read(buffer);
						String content = new String(buffer);
						System.out.println("接收到客户端数据:" + content);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
