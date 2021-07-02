package com.cyen.practice.io;

import java.io.IOException;
import java.net.Socket;

/**
 * @author qingshanpeng
 * @date 2021/7/2 17:31
 * @since 标果工厂-小白杏
 */
public class BIOClient {
	public static void main(String[] args) {
		byte[] buffer = new byte[1024];
		try {
			Socket client = new Socket("127.0.0.1", 7777);

			//发送数据给服务端
			client.getOutputStream().write("你好，我是客户端发送的数据...".getBytes());

			//接受服务端数据
			client.getInputStream().read(buffer);
			String content = new String(buffer);
			System.out.println("接收到服务端数据:" + content);

			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
