package com.skymall.search.service.imp;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.ExceptionUtil;
import com.skymall.search.mapper.ItemDao;
import com.skymall.search.pojo.Item;
import com.skymall.search.service.ItemService;

@Service
public class ItemServiceImp implements ItemService {

	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private SolrServer solrServer;
	
	/**
	 * 将数据库中的所有商品读出并插入solr索引库中
	 */
	@Override
	public SkymallResult importAllItems() {
		
		try {	
				//将数据库中的所有商品读出
				List<Item> list=itemDao.getItemList();
				for (Item item : list) {
					SolrInputDocument document=new SolrInputDocument();
					document.addField("id", item.getId());
					document.addField("item_title", item.getTitle());
					document.addField("item_sell_point",item.getSell_point());
					document.addField("item_price", item.getPrice());
					document.addField("item_image", item.getImage());
					document.addField("item_category_name", item.getCeagory_name());
					document.addField("item_desc", item.getItem_des());	
					solrServer.add(document);
				}
				solrServer.commit();
				return SkymallResult.ok();
			} catch (Exception e) {
				e.printStackTrace();
				return SkymallResult.build(500, ExceptionUtil.getStackTrace(e));
				
			}
		
	}
	/**
	 * 通过itemId查询商品信息
	 * <p>Description: </p> 
	 * @author shianliang  
	 * @date 2018年7月3日  
	 * @version 1.0
	 * @param ItemId
	 * @return
	 */
	public Item getItemByItemId(Long itemId){
		try {
			return itemDao.getItemByItemId(itemId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
