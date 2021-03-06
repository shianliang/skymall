package com.skymall.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skymall.common.pojo.DataResult;
import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.IDUtils;
import com.skymall.common.utils.JsonUtils;
import com.skymall.dao.TbItemDao;
import com.skymall.dao.TbItemDescDao;
import com.skymall.dao.TbItemParamItemDao;
import com.skymall.pojo.TbItem;
import com.skymall.pojo.TbItemDesc;
import com.skymall.pojo.TbItemParam;
import com.skymall.pojo.TbItemParamItem;
import com.skymall.pojo.TbItemQuery;
import com.skymall.pojo.TbItemQuery.Criteria;
import com.skymall.service.ItemParamService;
import com.skymall.service.ItemService;


/**
 * 
 * <p>Title: ItemServiceImp</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年4月22日
 */
@Service
public class ItemServiceImp implements ItemService {
	
	@Autowired
	private TbItemDao tbItemDao;
	
	@Autowired
	private TbItemDescDao tbItemDescDao;
	
	@Autowired
	private TbItemParamItemDao itemParamItemDao;

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public TbItem getItemById(Long id) {
		
		/*TbItem tbItem=tbItemDao.selectByPrimaryKey(id);*/
		TbItemQuery tbItemQuery=new TbItemQuery();
		
		Criteria criteria = tbItemQuery.createCriteria();
		criteria.andIdEqualTo(id);
		List<TbItem> items= tbItemDao.selectByExample(tbItemQuery);
		if(items!=null&&items.size()>0){
			return items.get(0);
		}
		return null;
	}

	

	@Override
	public DataResult getItemList(int page, int rows) {
		
		TbItemQuery query=new TbItemQuery();
		//分页插件：mybatis执行sql语句前拦截进行分页处理
		PageHelper.startPage(page, rows);
		
		List<TbItem> items=tbItemDao.selectByExample(query);

		DataResult result=new DataResult();
		PageInfo<TbItem> info=new PageInfo<>(items);
		result.setTotal((int)info.getTotal());
		result.setRows(items);
		return result;
	}

	/**
	 * 
	 * <p>Description: 保存商品 </p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0
	 * @param item
	 * @param desc
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Override
	public SkymallResult saveItme(TbItem item ,String desc,String paramData) throws Exception {
		//生成商品id
		long itemId=IDUtils.genItemId();
		item.setId(itemId);
		// '商品状态，1-正常，2-下架，3-删除',
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		tbItemDao.insert(item);
		//插入商品描述
		SkymallResult result=saveItemDesc(desc,itemId);
		if(result.getStatus()!=200){
			throw new Exception();	
		}
		//插入商品规格
       result=saveItemParamItem(itemId, paramData);
       if(result.getStatus()!=200){
    	   throw new Exception();
       }
       ////将消息发送到rabbitMQ(通过MQ向其他服务器发送消息-添加商品)
       sendMsg(item.getId(),"insert");
       
		return SkymallResult.ok();
	}
	
	/**
	 * 将消息发送到rabbitMQ(通过MQ向其他服务器发送消息-删除商品)
	 * <p>Description: </p> 
	 * @author shianliang  
	 * @date 2018年7月3日  
	 * @version 1.0
	 * @param itemId 商品ID
	 * @param type   操作类型 ：insert、update、delete
	 */
	private void sendMsg(long itemId,String type){
		//消息内容：itemId、type、date
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("itemId",itemId);
			map.put("type", type);
			map.put("date", System.currentTimeMillis());
			this.rabbitTemplate.convertAndSend("item."+type, JsonUtils.objectToJson(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: saveItemDesc 
	* @Description: 保存商品描述
	* @param @param desc  商品描述
	* @param @return    
	* @return SkymallResult    
	* @author shianliang 
	* @throws
	* @date 2018年1月16日 下午9:47:46 
	* @version V1.0
	 */
	public SkymallResult saveItemDesc(String desc,long itemId){
		TbItemDesc itemDesc=new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		int resultInt=tbItemDescDao.insert(itemDesc);
		if(resultInt<1){
			SkymallResult.build(400, "保存商品描述异常");
		}
			return SkymallResult.ok();		
	}

	/**
	 * 
	 * <p>Description: 保存商品规格</p> 
	 * @author shianliang  
	 * @date 2018年4月22日  
	 * @version 1.0
	 * @param itemId
	 * @param paramData
	 * @return
	 */
	public SkymallResult saveItemParamItem(long itemId,String paramData){
		TbItemParamItem itemParamItem=new TbItemParamItem();
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(paramData);
		int resultInt=itemParamItemDao.insert(itemParamItem);
		if(resultInt<1){
			SkymallResult.build(400, "保存商品规格异常");
		}
		return SkymallResult.ok();
	}


	/**
	 * 通过商品ID删除
	 * <p>Description: </p> 
	 * @author shianliang  
	 * @date 2018年7月3日  
	 * @version 1.0
	 * @param itemId
	 * @return
	 */
	@Override
	public boolean deleteItemById(Long itemId) {
		int num=tbItemDao.deleteByPrimaryKey(itemId);
		if(num>0){
			//将消息发送到rabbitMQ(通过MQ向其他服务器发送消息-删除商品)
			sendMsg(itemId,"delete");
		}
		return num>0;
	}
	
}
