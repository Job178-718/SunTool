package computerTool;

public class closeComputer{



//  定时关机
	public static void Closeable(int time) throws IOException{
		// time代表秒
		Runtime.getRuntime().exec("shutdown -s -t "+time);
	}


	//取消关机
	public static void stopClose() throws IOException {
		Runtime.getRuntime().exec("shutdown -a");
	}
}