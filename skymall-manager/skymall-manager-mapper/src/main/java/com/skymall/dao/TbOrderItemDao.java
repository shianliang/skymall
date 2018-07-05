package com.skymall.dao;

import com.skymall.pojo.TbOrderItem;
import com.skymall.pojo.TbOrderItemQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbOrderItemDao {
    int countByExample(TbOrderItemQuery example);

    int deleteByExample(TbOrderItemQuery example);

    int deleteByPrimaryKey(String id);

    int insert(TbOrderItem record);

    int insertSelective(TbOrderItem record);

    List<TbOrderItem> selectByExample(TbOrderItemQuery example);

    TbOrderItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TbOrderItem record, @Param("example") TbOrderItemQuery example);

    int updateByExample(@Param("record") TbOrderItem record, @Param("example") TbOrderItemQuery example);

    int updateByPrimaryKeySelective(TbOrderItem record);

    int updateByPrimaryKey(TbOrderItem record);
}