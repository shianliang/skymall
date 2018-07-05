package com.skymall.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skymall.common.pojo.DataResult;
import com.skymall.common.pojo.SkymallResult;
import com.skymall.dao.TbItemParamDao;
import com.skymall.pojo.TbItemParam;
import com.skymall.pojo.TbItemParamQuery;
import com.skymall.pojo.TbItemParamQuery.Criteria;
import com.skymall.service.ItemParamService;

/** 
* @ClassName: ItemParamServiceImp 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author shianliang
* @date 2018年1月17日 下午10:27:25 
* @version V1.0 
*/
@Service
public class ItemParamServiceImp implements ItemParamService {
	
	@Autowired
	private TbItemParamDao itemParamDao;
	
	/**
	 * 通过商品分类id查询规格
	 */
	@Override
	public SkymallResult getItemParamByCatId(long catId) {
		
		TbItemParamQuery itemParamQuery=new TbItemParamQuery();
		Criteria criteria = itemParamQuery.createCriteria();
		criteria.andItemCatIdEqualTo(catId);
		List<TbItemParam> itemParams=itemParamDao.selectByExample(itemParamQuery);
		if(itemParams!=null&&itemParams.size()>0){
			return SkymallResult.ok(itemParams.get(0));
		}
		return SkymallResult.ok();
	}

	
	@Override
	public SkymallResult saveItemParam(TbItemParam itemParam) {
		
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//执行保存
		itemParamDao.insert(itemParam);
		return SkymallResult.ok();
	}

	@Override
	public DataResult itemParamList(int page,int pageSize) {
		
		TbItemParamQuery itemParamQuery=new TbItemParamQuery();
		
		PageHelper.startPage(page, pageSize);
		
		DataResult result=new DataResult();
		
		List<TbItemParam> itemParams=itemParamDao.selectByExample(itemParamQuery);
		
		PageInfo<TbItemParam> pageInfo=new PageInfo<>(itemParams); 
		
		result.setRows(itemParams);
		result.setTotal((int)pageInfo.getTotal());
		
		return result;
	}

}
