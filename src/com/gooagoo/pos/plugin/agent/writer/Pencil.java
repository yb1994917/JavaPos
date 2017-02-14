package com.gooagoo.pos.plugin.agent.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.gooagoo.pos.plugin.agent.writer.WriterFactory.WriterFactoryProperties;

public class Pencil {
	//这里的FactorySettings给他设置ip,端口才有用
	private static WriterFactoryProperties FactorySettings = new WriterFactoryProperties();
	private final static Object Lock = new Object();

	public static void writeLocalFile(String content, Charset charset) {
		if (content == null) {
			return ;
		}
	
		WriterFactory factory = WriterFactory.getInstance(FactorySettings);
		final OutputStream fos = factory.getFileOutputStream();
		if(fos==null){
			return;
		}
		 byte[] data = (content + "\n").getBytes(charset != null ? charset : Charset.forName("GBK"));
		write(data, fos);
	}

	public static void writeServer(String content,Charset charset) {
		if (content == null) {
			return ;
		}
//		FactorySettings.setPort(33333); 测试有效!
		WriterFactory factory = WriterFactory.getInstance(FactorySettings);
		OutputStream os = factory.getServerOutputStream();


		if (os == null) {
			writeLocalFile(content,charset);
			return;
		} else {
			try {
			synchronized (Lock) {
					byte[] data = content.getBytes(charset != null ? charset : Charset.forName("GBK"));
					int len = data.length;
					byte[] header = toBytes(len);
					if (os!=null) {
						  os.write(header);
				          os.write(data);
					}
				} 
				}catch (Exception e) {
			}
			
		}
	}

	public  static boolean write(byte[] data, OutputStream os) {
		if (os != null) {
			try {
				if (data != null) {
					os.write(data);
				}
			} catch (IOException e) {
//				e.printStackTrace(); //error.log java.net.SocketException: Connection reset by peer: socket write error 
				return false;
			}
		}
		return true;
	}

	public static byte[] toBytes(int i) {
		byte[] cup = new byte[4];
			int g = i%10;
	        int sw = i/10%10;
	        int b = i/100%10;
	        int q = i/1000%10;
		cup[0] = (byte) (q+48);
		cup[1] = (byte) (b+48);
		cup[2] = (byte) (sw+48);
		cup[3] = (byte) (g+48);

		return cup;
	}


	public static int toInteger(byte[] cup) {
		int i = (int) ((cup[0] & 0xFF) << 24);

		i += (int) ((cup[1] & 0XFF) << 16);

		i += (int) ((cup[2] & 0XFF) << 8);

		i += (int) (cup[3] & 0XFF);

		return i;
	}

	// java 合并两个byte数组
	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}
	
	
	//C:/Users/gag/Desktop
	public static void writeLog(Exception e) {
		File f1 = new File("./logs/");
		if (!f1.exists()) {
			f1.mkdir();
		}
		File f = new File("./logs/exception.log");
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(f,true);
				PrintWriter writer = new PrintWriter(fos);
				writer.append(Thread.currentThread().getName());
				e.printStackTrace(writer);
				writer.flush();
				writer.close();
			} catch (Exception ex) {
			}
		} catch (Exception exc) {
		
		}
	}
	

	//C:/Users/gag/Desktop
	public static void writeLog(String content){
		File f1 = new File("./logs/");
		if (!f1.exists()) {
			f1.mkdir();
		}
		File f = new File("./logs/insert.log");
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(f,true);
				PrintWriter writer = new PrintWriter(fos);
				writer.append(content + "\n");
				writer.flush();
				writer.close();
			} catch (Exception ex) {
			}
		} catch (Exception exc) {
		}
	}
	
	public static void main(String...args){
//		byte[] bytes = toBytes(223);
//		for (byte b : bytes) {
//			System.out.println(b);
//		}
		try{
			throw new IllegalArgumentException("cccc");
		}catch(Exception e){
			writeLog(e);
		}
	}

	public static void writeLog1(String content) {
		File f1 = new File("./logs/");
		if (!f1.exists()) {
			f1.mkdir();
		}
		File f = new File("./logs/transform.log");
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(f,true);
				PrintWriter writer = new PrintWriter(fos);
				writer.append(content + "\n");
				writer.flush();
				writer.close();
			} catch (Exception ex) {
			}
		} catch (Exception exc) {
		}
	}
	
	public static void writeLog2(String content) {
		File f1 = new File("./logs/");
		if (!f1.exists()) {
			f1.mkdir();
		}
		File f = new File("./logs/AllLoded.log");
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(f,true);
				PrintWriter writer = new PrintWriter(fos);
				writer.append(content + "\n");
				writer.flush();
				writer.close();
			} catch (Exception ex) {
				
			}
		} catch (Exception exc) {
		}
	}
}
