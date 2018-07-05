package com.skymall.service.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skymall.common.utils.FtpUtil;
import com.skymall.service.PictrueService;

/**
 * 图片上传实现
 * @author admin
 *
 */
@Service
public class PictrueServiceImp implements PictrueService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	/**图片上传到ftp保存路径*/
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	/**返回参数用 （图片资源基础路径）*/
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	@Override
	public Map uploadPictrue(MultipartFile imgFile) {
		Map resultMap=new HashMap<>();
		try {
			//获取文件的name
			String oldName=imgFile.getOriginalFilename();
			
			//截取扩展名
			String extendName=oldName.substring(oldName.lastIndexOf("."));
			
			//生成新文件名（唯一）
			String  newName = UUID.randomUUID().toString()+extendName;
	        
			
			//获取上传文件流
			InputStream inputStream=imgFile.getInputStream();
			//实用工具类文件上传
			
			DateTime dateTime=new DateTime();//时间工具类
			String imgPath=dateTime.toString("/yyyy/MM/dd");
			boolean result=FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, imgPath, newName, inputStream);
			if(!result){
				resultMap.put("error", 1);
				resultMap.put("message", "图片上传失败");
				return resultMap;
			}
			
			//将处理后参数返回页面
			resultMap.put("error", 0);
			resultMap.put("url",IMAGE_BASE_URL + imgPath +"/"+ newName );
			return resultMap;
		} catch (IOException e) {
			resultMap.put("error", 1);
			resultMap.put("message", "图片上传发生异常");
			return resultMap;
		}
		
	}


}
