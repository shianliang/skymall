package com.skymall.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.skymall.common.utils.FtpUtil;

/**
 * 上传图片服务器测试
 * @author admin
 *
 */
public class FtpTest {

	@Test
	public void testFtpClient()throws Exception {
		
		//创建FtpClient对象
		FTPClient ftpClient=new FTPClient();
		
		//创建FtpClient链接，默认端口21
		ftpClient.connect("192.168.203.128",21);
		
		
		//登陆ftp服务器，输入账号密码
		ftpClient.login("ftpuser", "shi1988");
		
		//设置上传地址
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");

		//上传
		//读取本地文件
		FileInputStream inputStream=new FileInputStream(new File("C:\\Users\\admin\\Pictures\\Camera Roll\\5744341324711660010.jpg"));
		//修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		
		//第一 个参数 ：服务器文件名
		//第二参数： 要上传的文件流
		ftpClient.storeFile("test.jpg", inputStream);
		
		//关闭
		ftpClient.logout();
	}
	
	/**
	 * 利用工具类实现图片上传图片服务器
	 */
	@Test
	public void testFtpClient2(){
		try {
			FileInputStream inputStream=new FileInputStream(new File("C:\\Users\\admin\\Pictures\\Camera Roll\\5744341324711660010.jpg"));
			FtpUtil.uploadFile("192.168.203.128",21, "ftpuser","shi1988","/home/ftpuser/www/images" ,"2018/01/13" , "yes.jpg",inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}
}
