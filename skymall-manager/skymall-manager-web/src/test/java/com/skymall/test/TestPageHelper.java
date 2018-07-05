package com.skymall.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skymall.dao.TbItemDao;
import com.skymall.pojo.TbItem;
import com.skymall.pojo.TbItemQuery;
import com.skymall.pojo.TbItemQuery.Criteria;
import com.skymall.service.ItemService;

public class TestPageHelper {

	@Test
	public void pageHelperTest(){
		//获取spring容器
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		//从spring容器中获取dao
		TbItemDao bean = applicationContext.getBean(TbItemDao.class);
		//创建查询对象
		TbItemQuery itemQuery=new TbItemQuery();
		//分页处理：分页插件（在执行sql语句前拦截处理（添加limit语句））
		PageHelper.startPage(1, 10);
		List<TbItem> items=bean.selectByExample(itemQuery);
		for(TbItem item:items){
			System.out.println(item.getTitle());
		}
		//pageInfo对象中有所有分页数据
		PageInfo<TbItem> itemInfo=new PageInfo<>(items);
		System.err.println("总条数："+itemInfo.getTotal());
		System.err.println("总条数2："+itemInfo.getPageNum());
		System.err.println("总条数3："+itemInfo.getPageSize());
	}
	
}
