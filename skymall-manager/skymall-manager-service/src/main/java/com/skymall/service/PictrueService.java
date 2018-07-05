package com.skymall.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传
 * @author admin
 *
 */
public interface PictrueService {

	Map uploadPictrue(MultipartFile file);
	
}
