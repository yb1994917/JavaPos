package com.gooagoo.pos.plugin.agent.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.gooagoo.pos.plugin.agent.transformer.Const;

public class CMDUtils {
    public static Process CMD(String cmd,boolean isNeedMsg){
        Process p = null;
        try {
            cmd = "cmd.exe /c "+cmd;
            System.out.println(cmd);
            p = Runtime.getRuntime().exec(cmd);
            if (isNeedMsg) {
            	 new Thread(new cmdResult(p.getInputStream(),"success")).start();
                 new Thread(new cmdResult(p.getErrorStream(),"error")).start();
			}
            p.getOutputStream().close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return p;
    }
     
    public static Process CMD(String cmd,String ...args){
        return CMD(String.format(cmd, args));
    }
    public static Process runCMD(String cmd,boolean isNeedMsg){
        Process p = null;
        try {
            cmd = "cmd.exe /c start "+cmd;
            System.out.println(cmd);
            p = Runtime.getRuntime().exec(cmd);
            if (isNeedMsg) {
            	new Thread(new cmdResult(p.getInputStream(),"success")).start();
                new Thread(new cmdResult(p.getErrorStream(),"error")).start();
			}
            
            p.getOutputStream().close();
        } catch (Exception e) {
            System.out.println("命令行出错！");
            e.printStackTrace();
        }
        return p;
    }
     
    public static Process runCMD(String cmd,String ...args){
        return runCMD(String.format(cmd, args));
    }
     
    static class cmdResult implements Runnable{
        private InputStream ins;
		private String isSuccess;
		private String pid;
         
        public cmdResult(InputStream ins, String isSuccess, String pid){
        	this.isSuccess=isSuccess;
            this.ins = ins;
            this.pid=pid;
        }
        public cmdResult(InputStream ins, String isSuccess){
        	this.isSuccess=isSuccess;
            this.ins = ins;
        }
        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(ins, Charset.forName("utf-8")));
                String line = null;

                if ("success".equals(isSuccess)) {
                while ((line=reader.readLine())!=null) {
                	System.out.println(line);
						if (line.startsWith("pos.exe")) {
//							System.out.println(line);
							String[] split = line.split("Console");
							String[] split2 = split[0].split("pos.exe");
							String	pid = split2[1].replace(" ", "");
//							Const.gen().setPid(pid);
							System.out.println("pid--->"+pid);
							break;
						}
						if (line.startsWith("java.version")) {
							String[] split = line.split("=");
							System.out.println("进程号"+pid+"运行jdk版本:"+split[1]);
						}
						if (line.startsWith("sun.arch.data.model")) {
							String[] split = line.split("=");
							System.out.println("进程号"+pid+"运行jdk位数:"+split[1]+"位");
						}
						if (line.startsWith("java.home")) {
							String[] split = line.split("=");
							System.out.println("进程号"+pid+"jre的位置:"+split[1]);
						}
						if (line.startsWith("exe4j.launchName")) {
							String[] split = line.split("=");
							System.out.println("进程号"+pid+"launchName:"+split[1]);
						}
						if (line.startsWith("user.dir")) {
							String[] split = line.split("=");
							System.out.println("进程号"+pid+"user.dir:"+split[1]);
						}
						

					}
				System.out.println("--------------------------------------------------分 割 线 ------------------------------------------");	
                }else{
                	  while ((line=reader.readLine())!=null) {
//  							System.out.println(line);
  							if (line.contains("AttachNotSupportedException: Unable to attach to 32-bit process running under WOW64")) {
								System.out.println("请用对应位数的jdk再次尝试获取"+pid+"进程的java环境");
							}else if (line.contains("java.io.IOException: Command failed in target VM")) {
								System.out.println("进程号"+pid+":jre6");
							}else if (line.contains("com.sun.tools.attach.AttachNotSupportedException: jvm.dll not loaded by target process")) {
								System.out.println("进程号"+pid+"不是个java进程");
							}else if (line.contains("com.sun.tools.attach.AttachNotSupportedException: Unable to attach to 64-bit process")) {
								System.out.println("进程号"+pid+"是64位进程,请选择64bit的jdk(7以上)");
							}
  						}
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         
    }

    //args String pid
	public static void doNext() {
		try {
//			 String cmd = "cmd.exe CD D:\\ProgramFile\\Java11\\java7.0_51\\bin";
//			Runtime.getRuntime().exec(cmd);
//			cmd = "cmd.exe CD D:";
//			Runtime.getRuntime().exec(cmd);
			Runtime rn = Runtime.getRuntime();
			rn.exec("cmd.exe /k cd /" );
			rn.exec("cmd.exe /k cd d:/ProgramFile/Java11/java7.0_51/bin");  //进入jdk7-32的目录,一般的收银都是32位的
			//jcmd在jdk7才出现,对jdk8的环境也能使用jcmd,jdk6就不行了
			rn.exec("cmd.exe /k D:");
			ArrayList<String> pids = Const.gen().getPids();
			for (int i = 0; i < pids.size(); i++) {
				String pid = pids.get(i);
				Process p = rn.exec("jcmd "+String.valueOf(pid)+ " VM.system_properties");
				new Thread(new cmdResult(p.getInputStream(),"success",pid)).start();
	            new Thread(new cmdResult(p.getErrorStream(),"error",pid)).start();
			}
			
			
//			String[] arr=new String[]{"cmd.exe","/k","cd /","cd D:/ProgramFile/Java11/java7.0_51/bin","D:","jcmd "+pid+ " VM.system_properties"};
//			Process p = Runtime.getRuntime().exec(arr);
//			new Thread(new cmdResult(p.getInputStream(),"success")).start();
//            new Thread(new cmdResult(p.getErrorStream(),"error")).start();
//			CMD("jcmd "+pid+ " VM.system_properties",true);
//			Runtime.getRuntime().exec("jcmd "+pid+ "VM.system_properties");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		 
		
		
		
	}
 
}
