package com.gooagoo.pos.plugin.agent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import com.gooagoo.pos.plugin.agent.writer.Pencil;

public class Log {

	

	public static void main(String[] args) {
		Pencil.writeLocalFile("\0", Charset.forName("GBK"));
	}
}
