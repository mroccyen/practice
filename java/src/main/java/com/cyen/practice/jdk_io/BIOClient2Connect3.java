package com.cyen.practice.jdk_io;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author qingshanpeng
 * @date 2021/7/2 17:31
 * @since 标果工厂-小白杏
 */
public class BIOClient2Connect3 {
	public static void main(String[] args) {
		byte[] buffer = new byte[1024];
		try {
			Socket client = new Socket("127.0.0.1", 7777);
			//发送数据给服务端
			client.getOutputStream().write(scanner().getBytes());
			client.close();
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
