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
public class BIOServerMultiThread {
	public static void main(String[] args) {
		byte[] buffer = new byte[1024];
		ExecutorService executorService = Executors.newFixedThreadPool(5);

		try {
			ServerSocket server = new ServerSocket(7777);
			System.out.println("等待客户端连接");
			while (true) {
				Socket client = server.accept();
				//这样主线程不会阻塞在等待数据上面，每个子线程会阻塞等待客户端发送数据
				//把处理数据交于每个子线程处理，主线程只处理客户端的连接
				executorService.execute(() -> {
					try {
						System.out.println("接收到客户端连接：" + client.getRemoteSocketAddress());

						System.out.println("等待客户端发送数据");
						//接受客户端的数据
						client.getInputStream().read(buffer);
						String content = new String(buffer);
						System.out.println("接收到客户端数据:" + content);

						//发送数据给客户端
						client.getOutputStream().write("你好，我是服务端发送的数据...".getBytes());

						client.close();
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
