package com.gooagoo.pos.plugin.agent.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.gooagoo.pos.plugin.agent.transformer.Const;

/**
 * 
 * writer工厂类,用于输出文本到本地文件或者服务器
 * 
 * @author Xue Liang on 2016-10-08
 *
 */

public class WriterFactory {
	private WriterFactoryProperties properties = new WriterFactoryProperties();
	private static Socket ServerConnection = null;
	private static final Object Lock = new Object();

	/**
	 * 工厂属性
	 * 
	 * @author Xue Liang on 2016-10-09
	 *
	 */
	//C:/Users/gag/Desktop
	public static class WriterFactoryProperties {
		private String outputDirectory = "./home/gos/";
		private String server = "127.0.0.1";
		private int port = 12345;
		private Charset charset = Charset.forName("GBK");

		public String getOutputDirectory() {
			return outputDirectory;
		}

		public void setOutputDirectory(String outputDirectory) {
			this.outputDirectory = outputDirectory;
		}

		public String getServer() {
			return server;
		}

		public void setServer(String server) {
			this.server = server;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public Charset getCharset() {
			return charset;
		}

		public void setCharset(Charset charset) {
			this.charset = charset;
		}

	}

	private static WriterFactory Factory = null;

	/**
	 * 获取writer工厂实例
	 * 
	 * @param properties
	 * @return
	 */
	public static WriterFactory getInstance(WriterFactoryProperties properties) {
		if (Factory == null) {
			synchronized (Lock) {
				if (Factory == null) {
					Factory = new WriterFactory(properties);
				} else if (properties != null) {
					Factory.properties = properties;
				
				}
			}
		}
		return Factory;
	}

	/**
	 * 连接到服务器
	 */
	private final Runnable connectRunnable = new Runnable() {
		public void run() {
			while (true) {
				try {
					ServerConnection = new Socket(properties.getServer(), properties.getPort());//这句话可能会出异常
					synchronized (Lock) {
						try {
							//没出异常,就走到这里,就证明链接上了,测试过,每次连上都会发
							Pencil.writeServer("{\"pid\":"+Const.gen().getPid()+",\"user.dir\":"+"\""+Const.gen().getUserDir()+"\"}", Charset.forName("GBK"));
							Lock.wait();
						} catch (InterruptedException ex) {
						}
					}
				} catch (Exception e) {
					synchronized (Lock) {
						try {
							ServerConnection.close();
						} catch (Exception ex) {
							ServerConnection = null;
						}
						try {
							Lock.wait(2000);
						} catch (InterruptedException ex) {
						}
					}
				}
			}
		}
	};

	private void connectServer() {
		Thread t = new Thread(connectRunnable);
		t.start();
	}

	/**
	 * 检查与服务器是否连接
	 */
	private final Runnable monitorRunnable = new Runnable() {
		public void run() {
			while (true) {
				synchronized (Lock) {
					try {
						ServerConnection.sendUrgentData(1);
					} catch (Exception e) {
						Lock.notifyAll();
					}
					
					try {
						Lock.wait(5000);
					} catch (Exception e) {
					}
				}
			}
		}
	};

	private void monitor() {
		Thread t = new Thread(monitorRunnable);
		t.start();
	}

	private WriterFactory(WriterFactoryProperties properties) {
		if (properties != null) {
			this.properties = properties;
		}
		this.connectServer();

		this.monitor();

	}

	/**
	 * 创建文件
	 * 
	 * @return
	 */
	private File createFile() {

		DateFormat format = new SimpleDateFormat("yyyy/MM/dd/");

		Calendar calendar = Calendar.getInstance();

		Date now = calendar.getTime();

		String path = format.format(now);

		String dataFileFolder = this.properties.getOutputDirectory() + path;

		File dir = new File(dataFileFolder);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		String dataFile = dataFileFolder + calendar.get(Calendar.HOUR_OF_DAY) + "-" + calendar.get(Calendar.MINUTE)+ ".txt";

		File file = new File(dataFile);

		return file;
	}

	/**
	 * 得到文件writer
	 * 
	 * @return
	 */
	private OutputStream FOS = null;

	public OutputStream getFileOutputStream() {
		File dataFile = createFile();
		try {
			if (FOS == null) {
				synchronized (Lock) {
					if (FOS == null)
						FOS = new FileOutputStream(dataFile);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return FOS;
	}

	public OutputStream getServerOutputStream() {
		OutputStream os = null;
		if (ServerConnection != null) {
			try {
				os = ServerConnection.getOutputStream();
			} catch (Exception e) {
//				e.printStackTrace();  // error.log--java.net.SocketException: Socket is closed
				synchronized (Lock) {
					Lock.notifyAll();
				}
			}
		}
		return os;
	}

}
