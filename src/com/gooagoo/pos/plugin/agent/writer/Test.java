package com.gooagoo.pos.plugin.agent.writer;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class Test {
	public static void main(String[] args) throws AttachNotSupportedException, IOException {
////		List<VirtualMachineDescriptor> vms = VirtualMachine.list();
//		File file=new File("./config.ini");
//		if (!file.exists()) {
//			System.out.println("不存在");
//		}
//		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();  
//        String name = runtime.getName(); // format: \"pid@hostname\"  
//        System.out.println(name);
//           int pid =Integer.parseInt(name.substring(0, name.indexOf('@')));  
//			VirtualMachine jvm = VirtualMachine.attach("1564");
//			Properties ps = jvm.getSystemProperties();
//			Iterator<Entry<Object, Object>> it = ps.entrySet().iterator();
//			while (it.hasNext()) {
//				Entry<Object, Object> kv = it.next();
//				
//				System.out.println("key==="+kv.getKey().toString()+"---->value=="+kv.getValue().toString());
//				System.out.println(kv.getValue().toString());
////				if (kv.getKey().toString().contains(jvmCharactor)) {
////					return jvm;
////				} else if (kv.getValue().toString().contains(jvmCharactor)) {
////					return jvm;
////				}
////				for (VirtualMachineDescriptor vm : vms) {
////			}
//			System.out.println("-------------------------------------------------------------------------------");
////		}
//			}	
		
		
//		Properties props=System.getProperties(); //系统属性 
//		System.out.println(\"Java的运行环境版本：\"+props.getProperty(\"java.version\")); 
//		System.out.println(\"Java的运行环境供应商：\"+props.getProperty(\"java.vendor\")); 
//		System.out.println(\"Java供应商的URL：\"+props.getProperty(\"java.vendor.url\")); 
//		System.out.println(\"Java的安装路径：\"+props.getProperty(\"java.home\")); 
//		System.out.println(\"Java的虚拟机规范版本：\"+props.getProperty(\"java.vm.specification.version\")); 
//		System.out.println(\"Java的虚拟机规范供应商：\"+props.getProperty(\"java.vm.specification.vendor\")); 
//		System.out.println(\"Java的虚拟机规范名称：\"+props.getProperty(\"java.vm.specification.name\")); 
//		System.out.println(\"Java的虚拟机实现版本：\"+props.getProperty(\"java.vm.version\")); 
//		System.out.println(\"Java的虚拟机实现供应商：\"+props.getProperty(\"java.vm.vendor\")); 
//		System.out.println(\"Java的虚拟机实现名称：\"+props.getProperty(\"java.vm.name\")); 
//		System.out.println(\"Java运行时环境规范版本：\"+props.getProperty(\"java.specification.version\")); 
//		System.out.println(\"Java运行时环境规范供应商：\"+props.getProperty(\"java.specification.vender\")); 
//		System.out.println(\"Java运行时环境规范名称：\"+props.getProperty(\"java.specification.name\")); 
//		System.out.println(\"Java的类格式版本号：\"+props.getProperty(\"java.class.version\")); 
//		System.out.println(\"Java的类路径：\"+props.getProperty(\"java.class.path\")); 
//		System.out.println(\"加载库时搜索的路径列表：\"+props.getProperty(\"java.library.path\")); 
//		System.out.println(\"默认的临时文件路径：\"+props.getProperty(\"java.io.tmpdir\")); 
//		System.out.println(\"一个或多个扩展目录的路径：\"+props.getProperty(\"java.ext.dirs\")); 
//		System.out.println(\"操作系统的名称：\"+props.getProperty(\"os.name\")); 
//		System.out.println(\"操作系统的构架：\"+props.getProperty(\"os.arch\")); 
//		System.out.println(\"操作系统的版本：\"+props.getProperty(\"os.version\")); 
//		System.out.println(\"文件分隔符：\"+props.getProperty(\"file.separator\"));   //在 unix 系统中是＂／＂ 
//		System.out.println(\"路径分隔符：\"+props.getProperty(\"path.separator\"));   //在 unix 系统中是＂:＂
//		System.out.println(\"行分隔符：\"+props.getProperty(\"line.separator\"));   //在 unix 系统中是＂/n＂ 
//		System.out.println(\"用户的账户名称：\"+props.getProperty(\"user.name\")); 
//		System.out.println(\"用户的主目录：\"+props.getProperty(\"user.home\")); 
//		System.out.println(\"用户的当前工作目录：\"+props.getProperty(\"user.dir\"));
//		
//			String s="{\"detailinfo\":[{\"activeflag\":0,\"badflag\":0,\"barcode\":\"1\",\"batch\":\"\",\"bzhl\":1,\"calc_mode\":\" \",\"catid\":\"12130105\",\"cjzke\":0,\"code\":\"1\",\"costfactor\":0,\"dblMaxYhSl\":0,\"fhdd\":\"\",\"fk_sysy\":0,\"flag\":\"4\",\"fph\":\"\",\"fphm\":30,\"goodsstatus\":0,\"gz\":\"12130105\",\"hjje\":8.9,\"hjzk\":0,\"hyzke\":0,\"hyzkfd\":1,\"hyzklje\":0,\"inputbarcode\":\"1\",\"isPopExc\":\" \",\"isSMPopCalced\":\" \",\"isdzc\":\" \",\"isvipzk\":\"Y\",\"jg\":8.9,\"lsj\":8.9,\"lszke\":0,\"lszkfd\":1,\"lszre\":0,\"lszzk\":0,\"lszzr\":0,\"ltzke\":0,\"memo\":\"\",\"memonum1\":0,\"mjzke\":0,\"mjzkfd\":0,\"name\":\"阿尔卑斯香橙硬糖150g袋装\",\"num1\":0,\"num10\":0,\"num11\":0,\"num12\":0,\"num13\":0,\"num14\":0,\"num15\":0,\"num2\":0,\"num3\":0,\"num4\":0,\"num5\":0,\"num6\":0,\"num7\":0,\"num8\":0,\"num9\":0,\"plzke\":0,\"plzkfd\":0,\"ppcode\":\"2018\",\"qtzke\":0,\"qtzre\":0,\"rowno\":1,\"rulezke\":0,\"rulezkfd\":0,\"sl\":1,\"spzkfd\":0,\"sqkh\":\"\",\"sqktype\":\" \",\"sqkzkfd\":0,\"sswr_sysy\":0,\"str1\":\"\",\"str2\":\"\",\"syjh\":\"0002\",\"type\":\"1\",\"uid\":\"00\",\"unit\":\"袋\",\"xxtax\":17,\"yfphm\":0,\"yhdjbh\":\"\",\"yhzke\":0,\"yhzkfd\":0,\"ypopzke\":0,\"yrowno\":0,\"ysl\":0,\"ysyjh\":\"\",\"yyyh\":\"超市\",\"zszke\":0,\"zszkfd\":0},{\"activeflag\":0,\"badflag\":0,\"barcode\":\"1\",\"batch\":\"\",\"bzhl\":1,\"calc_mode\":\" \",\"catid\":\"12130105\",\"cjzke\":0,\"code\":\"1\",\"costfactor\":0,\"dblMaxYhSl\":0,\"fhdd\":\"\",\"fk_sysy\":0,\"flag\":\"4\",\"fph\":\"\",\"fphm\":30,\"goodsstatus\":0,\"gz\":\"12130105\",\"hjje\":8.9,\"hjzk\":0,\"hyzke\":0,\"hyzkfd\":1,\"hyzklje\":0,\"inputbarcode\":\"1\",\"isPopExc\":\" \",\"isSMPopCalced\":\" \",\"isdzc\":\" \",\"isvipzk\":\"Y\",\"jg\":8.9,\"lsj\":8.9,\"lszke\":0,\"lszkfd\":1,\"lszre\":0,\"lszzk\":0,\"lszzr\":0,\"ltzke\":0,\"memo\":\"\",\"memonum1\":0,\"mjzke\":0,\"mjzkfd\":0,\"name\":\"阿尔卑斯香橙硬糖150g袋装\",\"num1\":0,\"num10\":0,\"num11\":0,\"num12\":0,\"num13\":0,\"num14\":0,\"num15\":0,\"num2\":0,\"num3\":0,\"num4\":0,\"num5\":0,\"num6\":0,\"num7\":0,\"num8\":0,\"num9\":0,\"plzke\":0,\"plzkfd\":0,\"ppcode\":\"2018\",\"qtzke\":0,\"qtzre\":0,\"rowno\":1,\"rulezke\":0,\"rulezkfd\":0,\"sl\":1,\"spzkfd\":0,\"sqkh\":\"\",\"sqktype\":\" \",\"sqkzkfd\":0,\"sswr_sysy\":0,\"str1\":\"\",\"str2\":\"\",\"syjh\":\"0002\",\"type\":\"1\",\"uid\":\"00\",\"unit\":\"袋\",\"xxtax\":17,\"yfphm\":0,\"yhdjbh\":\"\",\"yhzke\":0,\"yhzkfd\":0,\"ypopzke\":0,\"yrowno\":0,\"ysl\":0,\"ysyjh\":\"\",\"yyyh\":\"超市\",\"zszke\":0,\"zszkfd\":0},{\"activeflag\":0,\"badflag\":0,\"barcode\":\"2\",\"batch\":\"\",\"bzhl\":1,\"calc_mode\":\" \",\"catid\":\"12130105\",\"cjzke\":0,\"code\":\"2\",\"costfactor\":0,\"dblMaxYhSl\":0,\"fhdd\":\"\",\"fk_sysy\":0,\"flag\":\"4\",\"fph\":\"\",\"fphm\":30,\"goodsstatus\":0,\"gz\":\"12130105\",\"hjje\":8.9,\"hjzk\":0,\"hyzke\":0,\"hyzkfd\":1,\"hyzklje\":0,\"inputbarcode\":\"2\",\"isPopExc\":\" \",\"isSMPopCalced\":\" \",\"isdzc\":\" \",\"isvipzk\":\"Y\",\"jg\":8.9,\"lsj\":8.9,\"lszke\":0,\"lszkfd\":1,\"lszre\":0,\"lszzk\":0,\"lszzr\":0,\"ltzke\":0,\"memo\":\"\",\"memonum1\":0,\"mjzke\":0,\"mjzkfd\":0,\"name\":\"阿尔卑斯精选水果硬糖150g袋装\",\"num1\":0,\"num10\":0,\"num11\":0,\"num12\":0,\"num13\":0,\"num14\":0,\"num15\":0,\"num2\":0,\"num3\":0,\"num4\":0,\"num5\":0,\"num6\":0,\"num7\":0,\"num8\":0,\"num9\":0,\"plzke\":0,\"plzkfd\":0,\"ppcode\":\"2018\",\"qtzke\":0,\"qtzre\":0,\"rowno\":2,\"rulezke\":0,\"rulezkfd\":0,\"sl\":1,\"spzkfd\":0,\"sqkh\":\"\",\"sqktype\":\" \",\"sqkzkfd\":0,\"sswr_sysy\":0,\"str1\":\"\",\"str2\":\"\",\"syjh\":\"0002\",\"type\":\"1\",\"uid\":\"00\",\"unit\":\"袋\",\"xxtax\":17,\"yfphm\":0,\"yhdjbh\":\"\",\"yhzke\":0,\"yhzkfd\":0,\"ypopzke\":0,\"yrowno\":0,\"ysl\":0,\"ysyjh\":\"\",\"yyyh\":\"超市\",\"zszke\":0,\"zszkfd\":0},{\"activeflag\":0,\"badflag\":0,\"barcode\":\"3\",\"batch\":\"\",\"bzhl\":1,\"calc_mode\":\" \",\"catid\":\"12130105\",\"cjzke\":0,\"code\":\"3\",\"costfactor\":0,\"dblMaxYhSl\":0,\"fhdd\":\"\",\"fk_sysy\":0,\"flag\":\"4\",\"fph\":\"\",\"fphm\":30,\"goodsstatus\":0,\"gz\":\"12130105\",\"hjje\":8.9,\"hjzk\":5.9,\"hyzke\":0,\"hyzkfd\":1,\"hyzklje\":0,\"inputbarcode\":\"3\",\"isPopExc\":\" \",\"isSMPopCalced\":\" \",\"isdzc\":\" \",\"isvipzk\":\"Y\",\"jg\":8.9,\"lsj\":8.9,\"lszke\":0,\"lszkfd\":1,\"lszre\":5.9,\"lszzk\":0,\"lszzr\":0,\"ltzke\":0,\"memo\":\"\",\"memonum1\":0,\"mjzke\":0,\"mjzkfd\":0,\"name\":\"阿尔卑斯树莓味硬糖150g袋装\",\"num1\":0,\"num10\":0,\"num11\":0,\"num12\":0,\"num13\":0,\"num14\":0,\"num15\":0,\"num2\":0,\"num3\":0,\"num4\":0,\"num5\":0,\"num6\":0,\"num7\":0,\"num8\":0,\"num9\":0,\"plzke\":0,\"plzkfd\":0,\"ppcode\":\"2018\",\"qtzke\":0,\"qtzre\":0,\"rowno\":3,\"rulezke\":0,\"rulezkfd\":0,\"sl\":1,\"spzkfd\":0,\"sqkh\":\"019\",\"sqktype\":\"1\",\"sqkzkfd\":0,\"sswr_sysy\":0,\"str1\":\"\",\"str2\":\"\",\"syjh\":\"0002\",\"type\":\"1\",\"uid\":\"00\",\"unit\":\"袋\",\"xxtax\":17,\"yfphm\":0,\"yhdjbh\":\"\",\"yhzke\":0,\"yhzkfd\":0,\"ypopzke\":0,\"yrowno\":0,\"ysl\":0,\"ysyjh\":\"\",\"yyyh\":\"超市\",\"zszke\":0,\"zszkfd\":0},{\"activeflag\":0,\"badflag\":0,\"barcode\":\"4\",\"batch\":\"\",\"bzhl\":1,\"calc_mode\":\" \",\"catid\":\"12130105\",\"cjzke\":0,\"code\":\"4\",\"costfactor\":0,\"dblMaxYhSl\":0,\"fhdd\":\"\",\"fk_sysy\":0,\"flag\":\"4\",\"fph\":\"\",\"fphm\":30,\"goodsstatus\":0,\"gz\":\"12130105\",\"hjje\":8.9,\"hjzk\":0,\"hyzke\":0,\"hyzkfd\":1,\"hyzklje\":0,\"inputbarcode\":\"4\",\"isPopExc\":\" \",\"isSMPopCalced\":\" \",\"isdzc\":\" \",\"isvipzk\":\"Y\",\"jg\":8.9,\"lsj\":8.9,\"lszke\":0,\"lszkfd\":1,\"lszre\":0,\"lszzk\":0,\"lszzr\":0,\"ltzke\":0,\"memo\":\"\",\"memonum1\":0,\"mjzke\":0,\"mjzkfd\":0,\"name\":\"阿尔卑斯原味硬糖150g袋装\",\"num1\":0,\"num10\":0,\"num11\":0,\"num12\":0,\"num13\":0,\"num14\":0,\"num15\":0,\"num2\":0,\"num3\":0,\"num4\":0,\"num5\":0,\"num6\":0,\"num7\":0,\"num8\":0,\"num9\":0,\"plzke\":0,\"plzkfd\":0,\"ppcode\":\"2018\",\"qtzke\":0,\"qtzre\":0,\"rowno\":4,\"rulezke\":0,\"rulezkfd\":0,\"sl\":1,\"spzkfd\":0,\"sqkh\":\"\",\"sqktype\":\" \",\"sqkzkfd\":0,\"sswr_sysy\":0,\"str1\":\"\",\"str2\":\"\",\"syjh\":\"0002\",\"type\":\"1\",\"uid\":\"00\",\"unit\":\"袋\",\"xxtax\":17,\"yfphm\":0,\"yhdjbh\":\"\",\"yhzke\":0,\"yhzkfd\":0,\"ypopzke\":0,\"yrowno\":0,\"ysl\":0,\"ysyjh\":\"\",\"yyyh\":\"超市\",\"zszke\":0,\"zszkfd\":0},{\"activeflag\":0,\"badflag\":0,\"barcode\":\"5\",\"batch\":\"\",\"bzhl\":1,\"calc_mode\":\" \",\"catid\":\"12130105\",\"cjzke\":0,\"code\":\"5\",\"costfactor\":0,\"dblMaxYhSl\":0,\"fhdd\":\"\",\"fk_sysy\":0,\"flag\":\"4\",\"fph\":\"\",\"fphm\":30,\"goodsstatus\":0,\"gz\":\"12130105\",\"hjje\":8.9,\"hjzk\":0,\"hyzke\":0,\"hyzkfd\":1,\"hyzklje\":0,\"inputbarcode\":\"5\",\"isPopExc\":\" \",\"isSMPopCalced\":\" \",\"isdzc\":\" \",\"isvipzk\":\"Y\",\"jg\":8.9,\"lsj\":8.9,\"lszke\":0,\"lszkfd\":1,\"lszre\":0,\"lszzk\":0,\"lszzr\":0,\"ltzke\":0,\"memo\":\"\",\"memonum1\":0,\"mjzke\":0,\"mjzkfd\":0,\"name\":\"阿尔卑斯草本清凉硬糖150g袋装\",\"num1\":0,\"num10\":0,\"num11\":0,\"num12\":0,\"num13\":0,\"num14\":0,\"num15\":0,\"num2\":0,\"num3\":0,\"num4\":0,\"num5\":0,\"num6\":0,\"num7\":0,\"num8\":0,\"num9\":0,\"plzke\":0,\"plzkfd\":0,\"ppcode\":\"2018\",\"qtzke\":0,\"qtzre\":0,\"rowno\":5,\"rulezke\":0,\"rulezkfd\":0,\"sl\":1,\"spzkfd\":0,\"sqkh\":\"\",\"sqktype\":\" \",\"sqkzkfd\":0,\"sswr_sysy\":0,\"str1\":\"\",\"str2\":\"\",\"syjh\":\"0002\",\"type\":\"1\",\"uid\":\"00\",\"unit\":\"袋\",\"xxtax\":17,\"yfphm\":0,\"yhdjbh\":\"\",\"yhzke\":0,\"yhzkfd\":0,\"ypopzke\":0,\"yrowno\":0,\"ysl\":0,\"ysyjh\":\"\",\"yyyh\":\"超市\",\"zszke\":0,\"zszkfd\":0},{\"activeflag\":0,\"badflag\":0,\"barcode\":\"6\",\"batch\":\"\",\"bzhl\":1,\"calc_mode\":\" \",\"catid\":\"12130105\",\"cjzke\":0,\"code\":\"6\",\"costfactor\":0,\"dblMaxYhSl\":0,\"fhdd\":\"\",\"fk_sysy\":0,\"flag\":\"4\",\"fph\":\"\",\"fphm\":30,\"goodsstatus\":0,\"gz\":\"12130105\",\"hjje\":8.9,\"hjzk\":0,\"hyzke\":0,\"hyzkfd\":1,\"hyzklje\":0,\"inputbarcode\":\"6\",\"isPopExc\":\" \",\"isSMPopCalced\":\" \",\"isdzc\":\" \",\"isvipzk\":\"Y\",\"jg\":8.9,\"lsj\":8.9,\"lszke\":0,\"lszkfd\":1,\"lszre\":0,\"lszzk\":0,\"lszzr\":0,\"ltzke\":0,\"memo\":\"\",\"memonum1\":0,\"mjzke\":0,\"mjzkfd\":0,\"name\":\"阿尔卑斯特浓硬糖150g袋装\",\"num1\":0,\"num10\":0,\"num11\":0,\"num12\":0,\"num13\":0,\"num14\":0,\"num15\":0,\"num2\":0,\"num3\":0,\"num4\":0,\"num5\":0,\"num6\":0,\"num7\":0,\"num8\":0,\"num9\":0,\"plzke\":0,\"plzkfd\":0,\"ppcode\":\"2018\",\"qtzke\":0,\"qtzre\":0,\"rowno\":6,\"rulezke\":0,\"rulezkfd\":0,\"sl\":1,\"spzkfd\":0,\"sqkh\":\"\",\"sqktype\":\" \",\"sqkzkfd\":0,\"sswr_sysy\":0,\"str1\":\"\",\"str2\":\"\",\"syjh\":\"0002\",\"type\":\"1\",\"uid\":\"00\",\"unit\":\"袋\",\"xxtax\":17,\"yfphm\":0,\"yhdjbh\":\"\",\"yhzke\":0,\"yhzkfd\":0,\"ypopzke\":0,\"yrowno\":0,\"ysl\":0,\"ysyjh\":\"\",\"yyyh\":\"超市\",\"zszke\":0,\"zszkfd\":0},{\"activeflag\":0,\"badflag\":0,\"barcode\":\"7\",\"batch\":\"\",\"bzhl\":1,\"calc_mode\":\" \",\"catid\":\"12130105\",\"cjzke\":0,\"code\":\"7\",\"costfactor\":0,\"dblMaxYhSl\":0,\"fhdd\":\"\",\"fk_sysy\":0,\"flag\":\"4\",\"fph\":\"\",\"fphm\":30,\"goodsstatus\":0,\"gz\":\"12130105\",\"hjje\":8.9,\"hjzk\":0,\"hyzke\":0,\"hyzkfd\":1,\"hyzklje\":0,\"inputbarcode\":\"7\",\"isPopExc\":\" \",\"isSMPopCalced\":\" \",\"isdzc\":\" \",\"isvipzk\":\"Y\",\"jg\":8.9,\"lsj\":8.9,\"lszke\":0,\"lszkfd\":1,\"lszre\":0,\"lszzk\":0,\"lszzr\":0,\"ltzke\":0,\"memo\":\"\",\"memonum1\":0,\"mjzke\":0,\"mjzkfd\":0,\"name\":\"阿尔卑斯草莓硬糖150g袋装\",\"num1\":0,\"num10\":0,\"num11\":0,\"num12\":0,\"num13\":0,\"num14\":0,\"num15\":0,\"num2\":0,\"num3\":0,\"num4\":0,\"num5\":0,\"num6\":0,\"num7\":0,\"num8\":0,\"num9\":0,\"plzke\":0,\"plzkfd\":0,\"ppcode\":\"2018\",\"qtzke\":0,\"qtzre\":0,\"rowno\":7,\"rulezke\":0,\"rulezkfd\":0,\"sl\":1,\"spzkfd\":0,\"sqkh\":\"\",\"sqktype\":\" \",\"sqkzkfd\":0,\"sswr_sysy\":0,\"str1\":\"\",\"str2\":\"\",\"syjh\":\"0002\",\"type\":\"1\",\"uid\":\"00\",\"unit\":\"袋\",\"xxtax\":17,\"yfphm\":0,\"yhdjbh\":\"\",\"yhzke\":0,\"yhzkfd\":0,\"ypopzke\":0,\"yrowno\":0,\"ysl\":0,\"ysyjh\":\"\",\"yyyh\":\"超市\",\"zszke\":0,\"zszkfd\":0},{\"activeflag\":0,\"badflag\":0,\"barcode\":\"8\",\"batch\":\"\",\"bzhl\":1,\"calc_mode\":\" \",\"catid\":\"12130102\",\"cjzke\":0,\"code\":\"8\",\"costfactor\":0,\"dblMaxYhSl\":0,\"fhdd\":\"\",\"fk_sysy\":0,\"flag\":\"4\",\"fph\":\"\",\"fphm\":30,\"goodsstatus\":0,\"gz\":\"12130102\",\"hjje\":8.9,\"hjzk\":0,\"hyzke\":0,\"hyzkfd\":1,\"hyzklje\":0,\"inputbarcode\":\"8\",\"isPopExc\":\" \",\"isSMPopCalced\":\" \",\"isdzc\":\" \",\"isvipzk\":\"Y\",\"jg\":8.9,\"lsj\":8.9,\"lszke\":0,\"lszkfd\":1,\"lszre\":0,\"lszzk\":0,\"lszzr\":0,\"ltzke\":0,\"memo\":\"\",\"memonum1\":0,\"mjzke\":0,\"mjzkfd\":0,\"name\":\"阿尔卑斯亦式咖啡硬糖150g袋装\",\"num1\":0,\"num10\":0,\"num11\":0,\"num12\":0,\"num13\":0,\"num14\":0,\"num15\":0,\"num2\":0,\"num3\":0,\"num4\":0,\"num5\":0,\"num6\":0,\"num7\":0,\"num8\":0,\"num9\":0,\"plzke\":0,\"plzkfd\":0,\"ppcode\":\"2018\",\"qtzke\":0,\"qtzre\":0,\"rowno\":8,\"rulezke\":0,\"rulezkfd\":0,\"sl\":1,\"spzkfd\":0,\"sqkh\":\"\",\"sqktype\":\" \",\"sqkzkfd\":0,\"sswr_sysy\":0,\"str1\":\"\",\"str2\":\"\",\"syjh\":\"0002\",\"type\":\"1\",\"uid\":\"00\",\"unit\":\"袋\",\"xxtax\":17,\"yfphm\":0,\"yhdjbh\":\"\",\"yhzke\":0,\"yhzkfd\":0,\"ypopzke\":0,\"yrowno\":0,\"ysl\":0,\"ysyjh\":\"\",\"yyyh\":\"超市\",\"zszke\":0,\"zszkfd\":0}]}";
//			byte[] data = s.getBytes(Charset.forName("GBK"));
//			int len = data.length;
//			byte[] header = toBytes(len);
//			System.out.println(len);
			
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
	
}
