package com.gooagoo.pos.plugin.agent.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.swing.JTable;

import com.alibaba.fastjson.JSON;
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
//			if (className.equals("javax.swing.JTable")) {
			if (obj instanceof JTable) {
//				Pencil.writeLog("有Jtable对象 不为null");
				Pencil.writeLog("javax.swing.JTable");
				JTable jt=(JTable)obj;
				if (jt.getRowCount()<= 0 || jt.getColumnCount()<=0) {
					Pencil.writeLog("count<=0");
					return "";
				}
//				if (jt.getColumnCount()!=12) {
//					return "";
//				}
		
	
//		File file = new File(".\\config.ini");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),Charset.forName("GBK")));
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i <  jt.getColumnCount(); i++) {
			builder.append(jt.getColumnName(i));
		}
		Pencil.writeLog("columnName"+Const.gen().getcolumnName());
		if ("".equals(Const.gen().getcolumnName())) { //config没有配置columnName
			return "";
		}else{ //配置了
			if (builder.toString().contains(Const.gen().getcolumnName())) {
				Pencil.writeLog("配置了columnName");
				StringBuilder sb = new StringBuilder();
				sb.append("{");
				sb.append("\"detailinfo\":");
				sb.append("[");
				for (int i = 0; i < jt.getRowCount(); i++) {
					sb.append("{");
					for (int j = 0; j < jt.getColumnCount(); j++) {
						if (i == jt.getRowCount() - 1
								&& j == jt.getColumnCount() - 1) {
							sb.append("\"").append(jt.getColumnName(j))
									.append("\"").append(": ").append("\"")
									.append(String.valueOf(jt.getValueAt(i, j)).replace('\n',' ')).append("\"");
						} else if (i != jt.getRowCount() - 1
								&& j == jt.getColumnCount() - 1) { //jt.getColumnName(j)取得列名
							sb.append("\"").append(jt.getColumnName(j))
									.append("\"").append(": ").append("\"")
									.append(String.valueOf(jt.getValueAt(i, j)).replace('\n',' ')).append("\"");
						} else {
							sb.append("\"").append(jt.getColumnName(j))
									.append("\"").append(": ").append("\"")
									.append(String.valueOf(jt.getValueAt(i, j)).replace('\n',' ')).append("\"")
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
	//	["1","1","阿尔卑斯香橙硬糖150g袋装","袋","1","8.90","0.00","8.90"]
	}else{
		try {
			String json =  JSON.toJSONString(obj);
			if (json.startsWith("[")) {
				 json = "{\"detailinfo\":".concat(json).concat("}");
			}
			return json;
		} catch (Exception e) {
			write("发生异常的类" + obj.getClass().getName());
			Pencil.writeLog(e);
//			write("JSONUtil" + e.toString());
			return null;
		}
	}
	} catch (Exception e) {
	Pencil.writeLog(e);
	return "";
	}
	return null;	
	}

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
}
