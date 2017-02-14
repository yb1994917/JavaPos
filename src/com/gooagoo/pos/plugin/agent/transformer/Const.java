package com.gooagoo.pos.plugin.agent.transformer;

import java.util.ArrayList;

public class Const {
	
	private static Const constant;
	public static Const gen(){
		if (constant ==null) {
			synchronized (new Object()) {
				if (constant ==null) {
					constant = new Const();
				}
			}
			
		}
		return constant;
	}
	private  String methodName=null;
	private  Object object =null;
	private  ArrayList<String> list =new ArrayList<String>();
	
	private  int i = -1;
	private String jstr;
	private int pid = -1; //-1证明还没找到对应进程
	private String columnName;
	private String InjectClass;
	private String exeName;
	private ArrayList<String> injects=new ArrayList<String>();
	private String userdir;
	public String getMethod(){
		return this.methodName;
	}
	public Object getObject(){
		return this.object;
	}
	
	public String getJstr(){
		return this.jstr;
	}
	public int getI(){
		return this.i;
	}
	public void setMethod(String meth){
		this.methodName = meth;
	}
	public void setJstr(String jstr){
	 this.jstr=jstr;
	}
	public void setI(int t){
		this.i=t;
	}
	public void setPid(int pid){
		 this.pid=pid;
		}
	public int getPid(){
		return pid;
		}
	public void add(String line){
		list.add(line);
	}
	public ArrayList<String> getPids(){
		return list;
	}
	public void setcolumnName(String columnName) {
		this.columnName=columnName;
	}
	public String getcolumnName() {
		return columnName;
	}
	public void setInjectClass(String InjectClass) {
			this.InjectClass=InjectClass;
	}
	public String getInjectClass() {
		return InjectClass;
}
	public void setExeName(String exeName) {
		this.exeName=exeName;
	}
	
	public String getExeName() {
		return this.exeName;
	}
	public void setInjects(ArrayList<String> injects) {
			this.injects=injects;
	}
	public ArrayList<String> getInjects() {
		return this.injects;
}
	public void setUserDir(String property) {
		this.userdir=property;
	}
	
	public String getUserDir() {
		return this.userdir;
	}
}
