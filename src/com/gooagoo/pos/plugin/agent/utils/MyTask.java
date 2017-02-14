package com.gooagoo.pos.plugin.agent.utils;


import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JTable;

import com.gooagoo.javassist.ClassPool;
import com.gooagoo.pos.plugin.agent.transformer.Const;
import com.gooagoo.pos.plugin.agent.transformer.Constants1;
import com.gooagoo.pos.plugin.agent.writer.Pencil;
public class MyTask implements Runnable{
	public static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
	public static ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 25, 10L, TimeUnit.SECONDS, queue);
	ClassPool pool1=ClassPool.getDefault();
	private String methodName;
	private Object[] objs;
	private int pid;
//	private String exeName;

	public MyTask(String methodName,Object[] objs,int pid) {
		this.methodName=methodName;
		this.objs=objs;
//		this.exeName=exeName;
		this.pid=pid;
		 pool.allowCoreThreadTimeOut(true);
		 pool1.appendSystemPath();
	}

	@Override
	public void run() {
//		int len = parameterTypes.length;
//		StringBuilder cup = new StringBuilder(512);
//		cup.append("{\n").append("	   try{\n");
//		for (int i = 1; i <= len; i++) {
//			CtClass c = parameterTypes[i - 1];
//			boolean skip = false;
		int len = objs.length;
		for (int i = 1; i <= len; i++) {
			Object obj = objs[i - 1];
			if (obj==null) {
				continue;
			}
			boolean skip = false;
			for (String useless : Constants1.UselessPackagePreix) {
				
				if (obj.getClass().getName().startsWith(useless)) {
					skip = true;
					break;
				}
			}
			if (!skip) {
				
				Pencil.writeServer("pid:"+pid+" "+getTime() +"	"+ methodName + ":P[" + i
						+ "]=" + JSONUtil.toString(objs[i - 1]),
						Charset.forName("GBK"));
			}
		}
		}
		public static String getTime(){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			String date = format.format(System.currentTimeMillis());
			return date;
		}
}
