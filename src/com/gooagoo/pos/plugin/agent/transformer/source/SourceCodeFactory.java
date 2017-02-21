package com.gooagoo.pos.plugin.agent.transformer.source;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import com.gooagoo.javassist.CtClass;
import com.gooagoo.pos.plugin.agent.transformer.Const;
import com.gooagoo.pos.plugin.agent.writer.Pencil;

public class SourceCodeFactory {

	public static String createBefore(String methodName, CtClass[] parameterTypes) {
//		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();  
//        String name = runtime.getName(); // format: "pid@hostname"  
//        Pencil.writeLog("name--->"+name);
//        int pid =Integer.parseInt(name.substring(0, name.indexOf('@'))); 
//		String exeName=Const.gen().getExeName();
//		int len = parameterTypes.length;
//		StringBuilder cup = new StringBuilder(512);
//		cup.append("{\n").append("	   try{\n");
//		boolean hasCode = false;
//		for (int i = 1; i <= len; i++) {
//			CtClass c = parameterTypes[i - 1];
//			
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//			String date = format.format(new Date());
//			boolean skip = false;
//			for (String useless : Constants1.UselessPackagePreix) {
//				if (c.getName().startsWith(useless)) {
//					skip = true;
//					break;
//				}
//			}
////			com.gooagoo.pos.plugin.agent.AttachAgent
//			//JSON  JSONUtil
//			if (!skip) {
//				hasCode = true;
//				cup.append("	   		Pencil.writeServer(\""  + date + "   " + methodName + ":P[" + i + "]=\"+JSON.toString($" + i + "),Charset.forName(\"GBK\"));\n");
//			}
//		}
//		cup.append("	   }catch(Exception e){\n").append(" Pencil.writeLog(e); }\n").append("	}\n");
		int pid = Const.gen().getPid();
		StringBuilder cup = new StringBuilder(512);
		cup.append("{\n").append("	   try{\n");
		cup.append("MyTask.pool.execute(new MyTask(\""+methodName+"\",$args,"+pid+"));");
		cup.append("	   }catch(Exception e){\n").append("}\n").append("	}\n");
		return cup.toString();
		
	}

	public static String createAfter() throws Exception {
		throw new Exception("not implements yet!");
	}
}
