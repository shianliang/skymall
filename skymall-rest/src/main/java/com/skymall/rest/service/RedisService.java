package com.skymall.rest.service;

import com.skymall.common.pojo.SkymallResult;

public interface RedisService {
	/**缓存同步根据内容分类id*/
	SkymallResult contentSync(long contentCid);

}
