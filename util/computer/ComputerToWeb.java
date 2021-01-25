package com.sun.day02;

import java.io.IOException;

public class ComputerToWeb {
	 
	/**
	 * 通过指定的浏览器打开指定的网页
	 * @param path   path:浏览器的存储地址
	 * @param web    web:指定打开的地址
	 * @return
	 */
	public static boolean getScan(String path,String web) {
		try {
			ProcessBuilder proc = new ProcessBuilder(path, web);
            proc.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("没有运行启动页面：");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
