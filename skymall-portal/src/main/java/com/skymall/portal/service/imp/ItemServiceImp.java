/**  
 * <p>Title: ItemServiceImpl.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年4月22日  
 * @version 1.0  
 */ 
package com.skymall.portal.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.skymall.common.pojo.SkymallResult;
import com.skymall.common.utils.HttpClientUtil;
import com.skymall.common.utils.JsonUtils;
import com.skymall.pojo.TbItem;
import com.skymall.pojo.TbItemDesc;
import com.skymall.pojo.TbItemParam;
import com.skymall.pojo.TbItemParamItem;
import com.skymall.portal.pojo.Item;
import com.skymall.portal.pojo.ItemInfo;
import com.skymall.portal.service.ItemService;

/**  
 * <p>Title: ItemServiceImpl</p>  
 * <p>Description: 商品信息service </p>  
 * @author shianliang 
 * @date 2018年4月22日  
 */
@Service
public class ItemServiceImp implements ItemService{
	/**rest服务的基础路径*/
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	/**查询商品信息的url*/
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;

	/**查询商品描述的url*/
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;
	
	/**查询商品规格参数url*/
	@Value("${ITEM_PARAM_URL}")
	private String ITEM_PARAM_URL;
	
	@Override
	public ItemInfo getItemInfoByItemId(long itemId) {
		try {
			String result=HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
			if(StringUtils.isNotBlank(result)){
				SkymallResult skymallResult = SkymallResult.formatToPojo(result, ItemInfo.class);
				if(skymallResult.getStatus()==200){
					ItemInfo item=(ItemInfo)skymallResult.getData();
					return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (非 Javadoc) 
	* <p>Title: getItemDescByItemId</p> 
	* <p>Description: </p> 
	* @param itemId
	* @return 
	* @see com.skymall.portal.service.ItemService#getItemDescByItemId(long) 
	*/
	@Override
	public String getItemDescByItemId(long itemId) {
		try {
			String resultStr=HttpClientUtil.doGet(REST_BASE_URL+ITEM_DESC_URL+itemId);
			if(StringUtils.isNotBlank(resultStr)){
				SkymallResult result=SkymallResult.formatToPojo(resultStr, TbItemDesc.class);
				if(result.getStatus()==200){
					TbItemDesc itemDesc=(TbItemDesc)result.getData();
					String desc=itemDesc.getItemDesc();
					return desc;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/* (非 Javadoc) 
	* <p>Title: getItemParamItem</p> 
	* <p>Description: </p> 
	* @param itemId
	* @return 
	* @see com.skymall.portal.service.ItemService#getItemParamItem(long) 
	*/
	@Override
	public String getItemParamItem(long itemId) {
		
		try {
			String jsonData=HttpClientUtil.doGet(REST_BASE_URL+ITEM_PARAM_URL+itemId);
			//把json转成java对象
			if(StringUtils.isNotBlank(jsonData)){
				SkymallResult result=SkymallResult.formatToPojo(jsonData, TbItemParamItem.class);
				if(result.getStatus()==200){
					TbItemParamItem itemParamItem=(TbItemParamItem) result.getData();
					String paramData=itemParamItem.getParamData();
					List<Map> jsonList=JsonUtils.jsonToList(paramData,Map.class);
					StringBuffer sb = new StringBuffer();
					sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
					sb.append("    <tbody>\n");
					for(Map m1:jsonList) {
						sb.append("        <tr>\n");
						sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
						sb.append("        </tr>\n");
						List<Map> list2 = (List<Map>) m1.get("params");
						for(Map m2:list2) {
							sb.append("        <tr>\n");
							sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
							sb.append("            <td>"+m2.get("v")+"</td>\n");
							sb.append("        </tr>\n");
						}
					}
					sb.append("    </tbody>\n");
					sb.append("</table>");
					
					return sb.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}

	
	
	
	
	
}
