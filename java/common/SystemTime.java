package com.zy.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * <pre>
 * 项目:
 * 描述:
 * 作者:ZhangYi
 * 时间:2015年3月31日 上午8:19:30
 * 版本:wsm_v3.1
 * JDK:1.7.65
 * </pre>
 */
public class SystemTime {
	/**
	 * <pre>
	 * 描述:
	 * 作者:ZhangYi
	 * 时间:2015年3月31日 上午8:19:44
	 * 参数：(参数列表)
	 * http://www.bjtime.cn
	 * http://www.beijing-time.org/time.asp
	 * http://open.baidu.com/static/time/beijingtime.html
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static Date net_date1() throws Exception{
//		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8")); // 时区设置  
		URL url=new URL("http://open.baidu.com/static/time/beijingtime.html");//取得资源对象  
		URLConnection conn=url.openConnection();//生成连接对象  
//		conn.connect(); //发出连接  
		long time=conn.getDate(); //取得网站日期时间（时间戳）  
		Date date=new Date(time); //转换为标准时间对象  
		return date;
	}
	/**
	 * <pre>
	 * 描述:
	 * 作者:ZhangYi
	 * 时间:2015年3月31日 上午8:16:35
	 * 参数：(参数列表)
	 * 中国大概能用的NTP时间服务器 
	 * server 133.100.11.8 prefer 
     * server 210.72.145.44 
     * server 203.117.180.36 //程序中所用的 
     * server 131.107.1.10 
     * server time.asia.apple.com 
     * server 64.236.96.53 
     * server 130.149.17.21 
     * server 66.92.68.246 
     * server www.freebsd.org 
     * server 18.145.0.30 
     * server clock.via.net 
     * server 137.92.140.80 
     * server 133.100.9.2 
     * server 128.118.46.3 
     * server ntp.nasa.gov 
     * server 129.7.1.66 
     * server ntp-sop.inria.frserver 210.72.145.44(国家授时中心服务器IP地址) 
     * ntpdate 131.107.1.10 
     * ntpdate -s time.asia.apple.com 
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static Date net_date3() throws Exception{
//		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8")); // 时区设置  
        long differenceBetweenEpochs = 2208988800L; 
        Socket socket = new Socket("time-nw.nist.gov", 37); 
        InputStream raw = socket.getInputStream(); 
        long secondsSince1900 = 0; 
        for (int i = 0; i < 4; i++) { 
            secondsSince1900 = (secondsSince1900 << 8) | raw.read(); 
        } 
        if (raw != null){
        	raw.close();
        	socket.close();
        }
        long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs; 
        long time = secondsSince1970 * 1000; 
		Date date=new Date(time); //转换为标准时间对象  
		return date;
	}
	/**
	 * <pre>
	 * 描述:
	 * 作者:ZhangYi
	 * 时间:2015年3月31日 上午8:02:38
	 * 参数：(参数列表)
	 * @param args
	 * @throws IOException
	 * </pre>
	 */
	public static void main(String[] args){
		try {
			Date date = null;
//			date = net_date1();
//			System.out.println("--当前时间1---"+date.toLocaleString());
//			date = net_date2();
//			System.out.println("--当前时间2---"+date.toLocaleString());
			date = net_date3();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("--当前时间3---"+format.format(date));
			String[] dtime = format.format(date).split("\\ ");
			Runtime.getRuntime().exec("  cmd /c date " + dtime[0]);
			Runtime.getRuntime().exec("  cmd /c time " + dtime[1]);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("----------IO异常--------------");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("----------异常--------------");
		}finally{
			System.out.println("----------finish--------------");
		}
	}

}
