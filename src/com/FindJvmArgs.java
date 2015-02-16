package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.gooagoo.pos.plugin.agent.transformer.Const;
import com.gooagoo.pos.plugin.agent.utils.CMDUtils;
import com.gooagoo.pos.plugin.agent.writer.Test;

//java.io.IOException: Cannot run program "cmdx.exe CD D:/ProgramFile/Java11/java7.0_51/bin": CreateProcess error=2,
//error2表示路径找不到
//Class-Path: lib/tools.jar
public class FindJvmArgs {

	
	public static void main(String[] args) {
		String dir=null;
		if (args!=null && args.length>0) {
			dir=args[0];
		}
//		s="tasklist";
//		CMDUtils.CMD("tasklist",true);
//		CMDUtils.CMD("jps", true);
		try {
			Runtime rn = Runtime.getRuntime();
			File file=new File("D:/ProgramFile/Java11/jdk1.7.0_45/bin");
			Process p = rn.exec("cmd.exe /c jps",null,file); //第三个参数指定运行的目录
			InputStream ins = p.getInputStream();
			InputStream errins = p.getErrorStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins, Charset.forName("utf-8")));
			BufferedReader errreader = new BufferedReader(new InputStreamReader(errins, Charset.forName("utf-8")));
			String line = null;
          while ((line=reader.readLine())!=null) {
        	  System.out.println(line);
        	  if (!(line.contains("FindJvmArgs") || line.contains("Jps") || line.contains("JCmd") )) {
					
        		  line=line.replaceAll("[a-zA-Z]", "");
        		  Const.gen().add(line.trim()); 
//					CMDUtils.doNext();真傻 在遍历啊 肯定执行多次
				}
          }
          if (Const.gen().getPids()!=null && Const.gen().getPids().size()>0) {
        	  CMDUtils.doNext(dir);
          }else{
        	  System.out.println("进程集合为空!!!");
          }
         
          while ((line=errreader.readLine())!=null) {
        	  System.out.println(line);
        	  System.out.println("jps命令执行失败");
          }
		} catch (Exception e) {
		}
		
	}
	
}
