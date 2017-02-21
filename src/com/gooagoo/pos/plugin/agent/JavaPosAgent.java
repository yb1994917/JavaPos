package com.gooagoo.pos.plugin.agent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.gooagoo.pos.plugin.agent.transformer.Const;
import com.gooagoo.pos.plugin.agent.transformer.Constants;
import com.gooagoo.pos.plugin.agent.transformer.JPosTransformer;
import com.gooagoo.pos.plugin.agent.writer.Pencil;
import com.gooagoo.pos.plugin.agent.writer.WriterFactory.WriterFactoryProperties;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import sun.tools.java.ClassDeclaration;

public class JavaPosAgent {
	//注入的到收银软件,收银软件的进程里没有tools.jar,找不到类,单独抽取出来注入
	 private static volatile Instrumentation globalInstr;
	private static VirtualMachine select(String jvmkey, String jvmvalue) {
		VirtualMachine jvm = null;
		
			List<VirtualMachineDescriptor> vms = VirtualMachine.list();
	
			for (VirtualMachineDescriptor vm : vms) {
				try {		
				jvm = VirtualMachine.attach(vm.id());
				System.out.println(vm.id());
				Properties ps = jvm.getSystemProperties();
				Iterator<Entry<Object, Object>> it = ps.entrySet().iterator();
				while (it.hasNext()) {
					Entry<Object, Object> kv = it.next();
					if (kv.getKey().toString().contains(jvmkey) && kv.getValue().toString().contains(jvmvalue)) {
						System.out.println(kv.getKey().toString()+"-----"+kv.getValue().toString());
						return jvm;
				}
				}
				} catch (Exception e) {
					continue;
				}
			}
		return null;
	}

	private static WriterFactoryProperties writerFactorySettings = new WriterFactoryProperties();
	private static String inipa;
		
	public static void main(String... args) {
		System.out.println("main");
		String agent = null;
		String iniPath = null;
		String vmc = null;
		String server = null;
		int port = 12345;

		VirtualMachine jvm = null;
		if (args.length < 1) {
			System.out.println("step 1/5. 请指定代理.");
			return;
		} else {
			
			//判断inipath是否存在
			if (args.length==1) {//如果只传了一个参数,默认会从当前路劲下找config.ini,脚本的路径
				String property = System.getProperty("user.dir");
				System.out.println(System.getProperty("user.dir"));
				iniPath="./javapos.ini";
			}else{
				iniPath=args[1];
			}
			File iniFile = new File(iniPath);
			if (!iniFile.exists()) {
				System.out.println("step 2/5. 配置文件:[" + iniPath + "]不存在!");
				return;
			} else {
				System.out.println("step 2/5. 配置文件:[" + iniPath + "]");
			}
			
			
			//判断jar包是否存在
			agent = args[0];
			File agentFile = new File(agent);
			if (!agentFile.exists()) {
				System.out.println("step 3/5. 代理:[" + agent + "]不存在!");
				return;
			} else {
				System.out.println("step 3/5. 代理:[" + agent + "]");
			}
		}
		
		if (args.length==2 || args.length==1) {
			System.out.println("args==2");
			List<VirtualMachineDescriptor> vms = VirtualMachine.list();
				
			for (VirtualMachineDescriptor vm : vms) {
				String pid=vm.id();
				try {
					jvm = VirtualMachine.attach(pid);
					Properties ps = jvm.getSystemProperties();
					Iterator<Entry<Object, Object>> it = ps.entrySet().iterator();
					while (it.hasNext()) {
						Entry<Object, Object> kv = it.next();
						if (kv.getKey().toString().contains("user.dir")) {
							System.out.println(vm.id()+"-user.dir-"+kv.getValue().toString());
						}
					}
					
					try {
						File agentFile = new File(agent);
						File iniFile=new File(iniPath);
						if (jvm != null) {
							
							if (agentFile.exists()) {
								if (iniFile.exists()) {
									jvm.loadAgent(agent,iniFile.getAbsolutePath());
									jvm.detach();
								}else{
									System.out.println("step 4/5. 文件:[" + iniPath + "]不存在!");	
								}
							} else {
								System.out.println("step 4/5. 文件:[" + agent + "]不存在!");
							}
						}
						System.out.println("step 5/5. 加载java代理成功!");
					} catch (Exception e) {
						System.out.println("step 5/5. 加载java代理失败!");
						continue;
					}
					
				} catch (Exception e) {
					System.out.println("pid:"+pid+e.toString());
					continue;
				}
			}
			return;
		}
	
	
		if (args.length > 2 && args[2].contains("-")) {
			String[] split = args[2].split("-");
			jvm = select(split[0],split[1]);
			if (jvm != null) {
				System.out.println("step 3/5. 虚拟机pid:[" + jvm.id() + "]");
			} else {
				System.out.println("step 3/5. 根据[" + vmc + "]没有找到java虚拟机.");
				return;
			}
		}
		
		if (args.length > 3) {
			server = args[3];
			writerFactorySettings.setServer(server);
			System.out.println("step 4/5. 数据接收机:" + "[" + server + "]");
		}
		
		if (args.length > 4) {
			String p = args[4];
			try {
				port = Integer.parseInt(p);
				System.out.println("step 4/5. 数据接收机端口号:" + "[" + port + "]");
			} catch (Exception e) {
			}
			writerFactorySettings.setPort(port);
		}
		try {
			File agentFile = new File(agent);
			File iniFile =new File(iniPath);
			if (jvm != null) {
				if (iniFile.exists()) {
					StringBuilder sb = new StringBuilder();
					sb.append(iniPath);
					if (server!=null) {//sever 没有  port就没有
						sb.append(",");
						sb.append(server);
						if (port!=-1) {
							sb.append(",");
							sb.append(port);
						}
					}
					
					if (agentFile.exists() ) {
						System.out.println(sb.toString());
						jvm.loadAgent(agent,sb.toString());
						jvm.detach();
					} else {
						System.out.println("step 5/5. agent文件:[" + agent + "]不存在!");
						return;
					}
				}else{
					System.out.println("step 5/5. ini文件:[" + iniPath + "]不存在!");
					return;
				}
			}
			System.out.println("step 5/5. 加载java代理成功!");
		} catch (Exception e) {
			System.out.println("step 5/5. 加载java代理失败!");
			e.printStackTrace();
		}

	}

	public static void premain(String args, Instrumentation inst) {
		globalInstr=inst;
		deleteLastLogs();
		Pencil.writeLog("Premain loaded...");
		Pencil.writeLog("premain"+args);
		if (args!=null && !"".equals(args)) {
			File file=new File(args);
			if (!file.exists()) {
				Pencil.writeLog(new RuntimeException("配置文件不存在"));
			}else{
				iniToConst(file);	
			}
		}
		ClassFileTransformer transformer = new JPosTransformer();
		inst.addTransformer(transformer);
	}

	private static void deleteLastLogs() {
		File file = new File("./logs");
		if(file.exists()) {
			File[] listFiles = file.listFiles();
			for (File file2 : listFiles) {
				file2.delete();
			}
		}	  
	}

	private static void iniToConst(File file) {
		BufferedReader reader =null;
		try {
			 reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),Charset.forName("GBK")));
			String line=null;
			Map<String, String> map = new HashMap<String, String>();
			// 分行读取 1 没有:号 不会有这个key  2 冒号右边没内容也没有这个key
			while ((line = reader.readLine()) != null) {
				if (line!=null && line.contains(":")) {
					String[] split = line.split(":");
					if (split!=null && split.length>1) {
						map.put(split[0], split[1]);
					}
				}
			}
			
			Pencil.writeLog("配置文件的行数"+map.size());
			
			if (map.containsKey("columnName")) {
				Pencil.writeLog("配置了columnName,值为:"+map.get("columnName"));
				Const.gen().setcolumnName(map.get("columnName"));
			}else{
				Pencil.writeLog("没有配置columnName");
				Const.gen().setcolumnName("");
			}
			
			ArrayList<String> al = new ArrayList<String>();
			if (map.containsKey("InjectClass")) {
				Pencil.writeLog("配置了InjectClass,值为:"+map.get("InjectClass"));
				String key = map.get("InjectClass");
					if (key.contains(",")) {
						String[] split2 = key.split(",");
						System.out.println("split2:"+split2.length);
						for (String str : split2) {
							Pencil.writeLog1("conf--->"+str);
							al.add(str);
						}
					}else{
						al.add(key);
					}
					Const.gen().setInjects(al);
					Pencil.writeLog("injects的size:"+al.size());
				}else{
					Pencil.writeLog(new RuntimeException("没配置Inject的类,默认改变所有类"));
					Const.gen().setInjectClass("");
					Const.gen().setInjects(al);
				}	
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (reader!=null) {
					reader.close();
				}
			} catch (Exception e2) {
			}
		}
	}
	

	public static void agentmain(String args, final Instrumentation inst){
		globalInstr=inst;
		deleteLastLogs();
		Pencil.writeLog("args:"+args);
		 inipa = null;
		 WriterFactoryProperties writerFactorySettings = new WriterFactoryProperties();
		//args支持配置多个  ,号分开
		if (args!=null) {  //config.ini ip port
			if (args.contains(",")) {
				String[] split = args.split(",");
				String ip;
				String port;
				
				switch (split.length) {
				case 1:
					inipa = split[0];
					break;
				case 2:
					inipa=split[0];
					 ip=split[1];
					writerFactorySettings.setServer(ip); 
					break;
				case 3:
					inipa=split[0];
					ip=split[1];
					port=split[2];
					writerFactorySettings.setServer(ip);
					writerFactorySettings.setPort(Integer.parseInt(port.trim()));
					Pencil.writeLog("ip,port已设置port:"+port);
					break;

				default:
					break;
				}
			}else{
				inipa=args;
			}
		}else{
			Pencil.writeLog(new RuntimeException("请在脚本中指定配置文件的路径"));
		}
		
		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();  
        String name = runtime.getName();  // format: "pid@hostname"  
        final int pid =Integer.parseInt(name.substring(0, name.indexOf('@'))); 
        Const.gen().setPid(pid);
		Pencil.writeLog("agent loaded...");
		ClassFileTransformer transformer = new JPosTransformer();
		inst.addTransformer(transformer,true);
		 final Class[] allLoadedClasses = inst.getAllLoadedClasses(); 
		 Pencil.writeLog2("allLoadedClasses.length:"+":"+allLoadedClasses.length);
			new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Pencil.writeLog("inipa--->"+inipa);
					File file=new File(inipa);
					if (!file.exists()) {
						Pencil.writeLog(new RuntimeException("config.ini不存在!!!"));
					}else{
						iniToConst(file);
					}
					
					getExeName(String.valueOf(pid));
					getUserDir();
				Pencil.writeServer("{\"pid\":"+Const.gen().getPid()+",\"user.dir\":"+"\""+Const.gen().getUserDir()+"\"}", Charset.forName("GBK"));
					Thread.sleep(6000);
				} catch (InterruptedException e1) {
					Pencil.writeLog(e1);
				}
				
					for (int i = 0 ; i < allLoadedClasses.length ; i++) {
						boolean skip = false;
						for (String useless : Constants.UselessPackagePreix) {
							skip = allLoadedClasses[i].getName().startsWith(useless);
							if(skip){
								break;
							}
						}
						if(skip){
							continue;
						}
						Pencil.writeLog2(allLoadedClasses[i].getName());
								try {
									//把inst存起来 需要一个字节数组 ,包含新的类文件的 byte 数组。到时候就在transform中调用inst.redefineClass
									inst.retransformClasses(allLoadedClasses[i]);
									Pencil.writeLog2(allLoadedClasses[i]+"执行了retransformClasses");
								} catch (UnmodifiableClassException e) {
									Pencil.writeLog(e);
									continue;
								}
					}
			}
		}).start();
		}

	protected static void getUserDir() {
			String property = System.getProperty("user.dir");
			Pencil.writeLog(property);
			Const.gen().setUserDir(property);
			
	}

	protected static void getExeName(String id) {
		try {
			Process p = Runtime.getRuntime().exec("cmd.exe /c tasklist");
			InputStream ins = p.getInputStream();
			InputStream errins = p.getErrorStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins, Charset.forName("utf-8")));
			BufferedReader errreader = new BufferedReader(new InputStreamReader(errins, Charset.forName("utf-8")));
			String line = null;
			  while ((line=reader.readLine())!=null) {
//	        	  System.out.println(line);
	        	  if (line.contains(".exe")&&line.contains(id)) {
	        		  int x = line.indexOf(".");
	        		  String substr = line.substring(0,x);
	        		  String exeName=substr.concat(".exe");
	        		  Pencil.writeLog("exeName"+exeName);
	        		  Const.gen().setExeName(exeName);
					}
	          }
			  while ((line=errreader.readLine())!=null) {
	        	  System.out.println(line);
	        	  System.out.println("tasklist命令执行失败");
	          }
		} catch (Exception e) {
			Pencil.writeLog(e);
		}
	}
    public static Instrumentation getInstrumentation()
    {
        return globalInstr;
    }
}
