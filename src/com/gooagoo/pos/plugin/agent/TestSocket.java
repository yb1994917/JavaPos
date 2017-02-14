package com.gooagoo.pos.plugin.agent;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import com.gooagoo.pos.plugin.agent.writer.Pencil;

public class TestSocket {
	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(12345);
		} catch (IOException e1) {
			Pencil.writeLog(e1);
		}
		while(true){ //加个循环 出现异常了就继续
		try {
			Socket accept = serverSocket.accept();
			while(true){
			InputStream in = accept.getInputStream();
			byte[] a1=new byte[1024];
			int len;
			while((len=in.read(a1))!=-1){
				System.out.println(new String(a1,0,len,Charset.forName("GBK")));
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			continue;
		}
		}
	}
}
