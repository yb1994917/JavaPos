package com;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.gooagoo.pos.plugin.agent.transformer.Const;
import com.gooagoo.pos.plugin.agent.utils.CMDUtils;
import com.gooagoo.pos.plugin.agent.writer.Test;

//java.io.IOException: Cannot run program "cmdx.exe CD D:/ProgramFile/Java11/java7.0_51/bin": CreateProcess error=2,
//error2表示路径找不到
//Class-Path: lib/tools.jar
public class FindJvmArgs {
	public static void main(String[] args) {
		String s=null;
//		s="tasklist";
//		CMDUtils.CMD("tasklist",true);
//		CMDUtils.CMD("jps", true);
		try {
			Runtime rn = Runtime.getRuntime();
			rn.exec("cmd.exe /k cd /" );
			rn.exec("cmd.exe /k cd d:/ProgramFile/Java11/java7.0_51/bin"); 
			rn.exec("cmd.exe /k D:");
			Process p = rn.exec("cmd.exe /c jps");
			InputStream ins = p.getInputStream();
			InputStream errins = p.getErrorStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins, Charset.forName("utf-8")));
			BufferedReader errreader = new BufferedReader(new InputStreamReader(errins, Charset.forName("utf-8")));
			String line = null;
          while ((line=reader.readLine())!=null) {
//        	  System.out.println(line);
        	  if (!(line.contains("FindJvmArgs") || line.contains("Jps") || line.contains("JCmd") )) {
					Const.gen().add(line.trim()); 
//					CMDUtils.doNext();真傻 在遍历啊 肯定执行多次
				}
          }
          if (Const.gen().getPids()!=null && Const.gen().getPids().size()>0) {
        	  CMDUtils.doNext();
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
	
	public void doMy(){
		
	}
}
