package com.gooagoo.pos.plugin.agent.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.swing.JTable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gooagoo.pos.plugin.agent.transformer.Const;
import com.gooagoo.pos.plugin.agent.transformer.Constants1;
import com.gooagoo.pos.plugin.agent.writer.Pencil;

public class JSONUtil {
	private static File f = new File("d:/test.txt");

	public static String toString(Object obj) {

		if (obj == null) {
			return null;
		}
		String className = obj.getClass().getName();
//		Pencil.writeLog("className"+className);
//		if (className.equals("java.lang.String")) {
//			return "[\"".concat(obj.toString()).concat("\"]");
//		}
		for (String useless : Constants1.UselessPackagePreix) {
			if (className.startsWith(useless)) {
				return "";
			}
		}
   try {
			if (obj instanceof JTable) { // if
			// (className.equals("javax.swing.JTable"))
			// {
				String forJtable = forJtable(obj);
				return forJtable;
				// else if (obj instanceof Table) {
			
			} else {
					
				try {
					String json = JSON.toJSONString(obj);
					parseJson(json);
					json = json.replaceAll("(\r\n|\r|\n|\n\r|\0)", ""); // \0是NUL
						if (json.startsWith("[")) {
						json = "{\"detailinfo\":".concat(json).concat("}");
						}
						parseJson(json);
					return json;
				} catch (Exception e) {
					write("发生异常的类" + obj.getClass().getName());
					// Pencil.writeLog(e); 会报错!!注释掉
					// 需要再打开org.eclipse.swt.SWTException: Invalid thread access
					// write("JSONUtil" + e.toString());
					return null;
				}
	}
		} catch (Exception e) {
			// Pencil.writeLog(e);
			return "";
		}
	}
//		/**
//		*解析org.eclipse.swt.widgets.Table对象  		
//		*/
//	private static String forTable(Object obj) {  //不是每个收银 选一个商品 就传一次table的 ,这个方法都没走过
////		Pencil.writeLog("有Jtable对象 不为null");
//		StringBuilder sb=new StringBuilder();
//				Pencil.writeLog("org.eclipse.swt.widgets.Table");
//				Table table=(Table)obj;
//				if (table.getItemCount()<= 0 || table.getColumnCount()<=0) {
//					Pencil.writeLog("count<=0");
//					return "";
//				}
//			    int columnCount = table.getColumnCount();
//		  		int itemCount = table.getItemCount();
//		  		for (int i = 0; i < columnCount; i++) {
//		  			for (int j = 0; j < itemCount; j++) {
//		  				String ts = table.getItem(new Point(i, j)).getText();
//		  				Pencil.writeLog("i:"+i+","+"j:"+j+" data:"+ts);
//		  				sb.append(ts);
//		  			}
//		  		}
//		    return sb.toString();
//	}


	public static String toString(int i) {
		try {
			return Integer.toString(i);
		} catch (Exception e) {
			return null;
		}
	}

	public static String toString(long lng) {
		try {
			return Long.toString(lng);
		} catch (Exception e) {
			return null;
		}
	}

	public static String toString(char c) {
		return new String(new char[] { c });
	}

	public static String toString(double d) {
		return Double.toString(d);
	}

	public static String toString(float f) {
		return Double.toString(f);
	}

	public static String toString(short s) {
		return Short.toString(s);
	}

	public static String toString(boolean b) {
		return Boolean.toString(b);
	}

	public static String toString(byte bt) {
		return Byte.toString(bt);
	}

	public static void write(String str) {

		OutputStream out = null;
		try {
			out = new FileOutputStream(f);
			byte b[] = str.getBytes();
			out.write(b);
			out.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	/**
	 * 手动解析JTable对象
	 * */
	private static String forJtable(Object obj) {
		Pencil.writeLog("javax.swing.JTable");
		JTable jt = (JTable) obj;
		if (jt.getRowCount() <= 0 || jt.getColumnCount() <= 0) {
			Pencil.writeLog("count<=0");
			return "";
		}
		// if (jt.getColumnCount()!=12) {
		// return "";
		// }
		// File file = new File(".\\config.ini");
		// BufferedReader reader = new BufferedReader(new InputStreamReader(new
		// FileInputStream(file),Charset.forName("GBK")));
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < jt.getColumnCount(); i++) {
			builder.append(jt.getColumnName(i));
		}
		Pencil.writeLog("columnName" + Const.gen().getcolumnName());
		if ("".equals(Const.gen().getcolumnName())) { // config没有配置columnName
			return "";
		} else { // 配置了
			if (builder.toString().contains(Const.gen().getcolumnName())) {
				Pencil.writeLog("配置了columnName");
				StringBuilder sb = new StringBuilder();
				sb.append("{");
				sb.append("\"detailinfo\":");
				sb.append("[");
				for (int i = 0; i < jt.getRowCount(); i++) {
					sb.append("{");
					for (int j = 0; j < jt.getColumnCount(); j++) {
						if (i == jt.getRowCount() - 1 && j == jt.getColumnCount() - 1) {
							sb.append("\"").append(jt.getColumnName(j)).append("\"").append(": ").append("\"")
									.append(String.valueOf(jt.getValueAt(i, j)).replace('\n', ' ')).append("\"");
						} else if (i != jt.getRowCount() - 1 && j == jt.getColumnCount() - 1) { // jt.getColumnName(j)取得列名
							sb.append("\"").append(jt.getColumnName(j)).append("\"").append(": ").append("\"")
									.append(String.valueOf(jt.getValueAt(i, j)).replace('\n', ' ')).append("\"");
						} else {
							sb.append("\"").append(jt.getColumnName(j)).append("\"").append(": ").append("\"")
									.append(String.valueOf(jt.getValueAt(i, j)).replace('\n', ' ')).append("\"")
									.append(",");
						}
					}
					if (i == jt.getRowCount() - 1) {
						sb.append("}");
					} else {
						sb.append("},");
					}
				}
				
				sb.append("]");
				sb.append("}");
				return sb.toString();
			}
		}
		return null;
	}
	
	/**
	 * 过滤不需要的数据  待完善
	 * */
	private static void parseJson(String json) {
		String[] arr=new String[]{"rowno","barcode","name","unit","sl","hjje","hjzk"};
			if (!json.contains("detailinfo")) {
				return;
			}
			for (int i = 0; i < arr.length; i++) {
				if (!json.contains(arr[i])) {
					return ;
				}
			}
			
			JSONObject jsonobj = JSON.parseObject(json);
			JSONArray jsonArray = jsonobj.getJSONArray("detailinfo");
			StringBuilder sb=new StringBuilder();
			
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject object = (JSONObject) jsonArray.get(i);
				for (int j = 0; j < arr.length; j++) {
					try {
						String str = String.valueOf(object.get(arr[j]));
						sb.append(str).append("  ");
					} catch (Exception e) {
						Pencil.writeLog(jsonArray.get(i).toString()+"--->"+e.toString());
					//jsonarray.get(i)得到的竟然是zszke zsdjbh hyzklje等 为啥???
					}
				}
				Pencil.writeLog(sb.toString());
			}	
	}
}
