package com.skymall.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.skymall.common.utils.JsonUtils;
import com.skymall.service.PictrueService;

@Controller
public class PictrueController {

	@Autowired
	private PictrueService pictrueService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadPictrue(MultipartFile uploadFile){
		
		Map resultMap=pictrueService.uploadPictrue(uploadFile);
		//为了返回数据的兼容性将java对象转json
		String json = JsonUtils.objectToJson(resultMap);
		
	    return json;
		
	}
	
}
