package com.skymall.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.rest.service.RedisService;

@Controller
@RequestMapping("/cache/sync")
public class RedisController {

	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/content/{contentCid}")
	@ResponseBody
	public SkymallResult contentSync(@PathVariable long contentCid){
		return redisService.contentSync(contentCid);
	}
	
	
}
