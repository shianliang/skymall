package com.skymall.rest.service.imp;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.JsonUtils;
import com.skymall.dao.TbItemDao;
import com.skymall.dao.TbItemDescDao;
import com.skymall.dao.TbItemParamDao;
import com.skymall.dao.TbItemParamItemDao;
import com.skymall.pojo.TbItem;
import com.skymall.pojo.TbItemDesc;
import com.skymall.pojo.TbItemParamItem;
import com.skymall.pojo.TbItemParamItemQuery;
import com.skymall.pojo.TbItemParamItemQuery.Criteria;
import com.skymall.rest.dao.JedisClient;
import com.skymall.rest.service.ItemService;
/**
 * 商品信息管理Service
* @ClassName: ItemServiceImp 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author (作者)  
* @date 2018年4月7日 下午9:20:38 
* @version V1.0
 */
@Service
public class ItemServiceImp implements ItemService {

	@Autowired
	private TbItemDao itemDao;
	
	@Autowired
	private TbItemDescDao itemDescDao;
	
	@Autowired
	private TbItemParamItemDao itemParamItemDao;
	
	/**jedis客户端*/
	@Autowired
	private JedisClient jedisClient;
	
	//#商品存入redis key定义
	@Value("${ITEM_REDIS_KEY}")
	private String ITEM_REDIS_KEY;
	
	//#商品key的有效时间(秒)
	@Value("${EXPIRE}")
	private Integer EXPIRE;
	
	/**
	 * 根据商品id查询商品信息
	 */
	@Override
	public SkymallResult findItemInfoById(long id) {
		TbItem item=null;
		
		
		try {
			//缓存中取数据并将过期时间重置
			String redisJson=jedisClient.get(ITEM_REDIS_KEY+":"+id+":base");
			//重置过期时间（未测试）
			jedisClient.expire(ITEM_REDIS_KEY+":"+id+":base",EXPIRE);
			//如果有直接返回数据
			if(StringUtils.isNotBlank(redisJson)){
				item=JsonUtils.jsonToPojo(redisJson, TbItem.class);
				return SkymallResult.ok(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
			
		//根据id查询商品信息
		item = itemDao.selectByPrimaryKey(id);
		
		//添加缓存到redis并设置有效时间
		try {
			jedisClient.set(ITEM_REDIS_KEY+":"+id+":base",JsonUtils.objectToJson(item));
			//设置有效时间
			jedisClient.expire(ITEM_REDIS_KEY+":"+id+":base", EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return SkymallResult.ok(item);
	}


	/**
	 * 通过商品ID查询商品描述
	 */
	@Override
	public SkymallResult findItemDescByItemId(long itemId) {
		
		try {
			String resultJson=jedisClient.get(ITEM_REDIS_KEY+":"+itemId+":desc");
			jedisClient.expire(ITEM_REDIS_KEY+":"+itemId+":desc",EXPIRE);
			if(StringUtils.isNotBlank(resultJson)){
				TbItemDesc itemDesc=JsonUtils.jsonToPojo(resultJson, TbItemDesc.class);
				return SkymallResult.ok(itemDesc);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItemDesc itemDesc = itemDescDao.selectByPrimaryKey(itemId);
		
		
		try {
			
			jedisClient.set(ITEM_REDIS_KEY+":"+itemId+":desc", JsonUtils.objectToJson(itemDesc));
			jedisClient.expire(ITEM_REDIS_KEY+":"+itemId+":desc", EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return SkymallResult.ok(itemDesc);
	}


	/**
	 * 通过商品ID查询商品规格参数
	 */
	@Override
	public SkymallResult findItemParamByItmId(long itemId) {
		
		
		try {
			//缓存中取数据并将过期时间重置
			String redisJson=jedisClient.get(ITEM_REDIS_KEY+":"+itemId+":param");
			//重置过期时间（未测试）
			jedisClient.expire(ITEM_REDIS_KEY+":"+itemId+":param",EXPIRE);
			//如果有直接返回数据
			if(StringUtils.isNotBlank(redisJson)){
			TbItemParamItem	itemParam=JsonUtils.jsonToPojo(redisJson, TbItemParamItem.class);
				return SkymallResult.ok(itemParam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//创建查询条件
		TbItemParamItemQuery query=new TbItemParamItemQuery();
		Criteria criteria=query.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		//执行查询
		List<TbItemParamItem> list=itemParamItemDao.selectByExampleWithBLOBs(query);
		//处理返回结果
		TbItemParamItem itemParam=null;
		if(list!=null&& list.size()>0){
			 itemParam=list.get(0);
				//添加缓存到redis并设置有效时间
				try {
					jedisClient.set(ITEM_REDIS_KEY+":"+itemId+":param",JsonUtils.objectToJson(itemParam));
					//设置有效时间
					jedisClient.expire(ITEM_REDIS_KEY+":"+itemId+":param", EXPIRE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			 
			 return SkymallResult.ok(itemParam);
		}
		
		return SkymallResult.build(400, "无此商品规格");
	}

}
