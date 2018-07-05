package com.skymall.dao;

import com.skymall.pojo.TbItemDesc;
import com.skymall.pojo.TbItemDescQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemDescDao {
    int countByExample(TbItemDescQuery example);

    int deleteByExample(TbItemDescQuery example);

    int deleteByPrimaryKey(Long itemId);

    int insert(TbItemDesc record);

    int insertSelective(TbItemDesc record);

    List<TbItemDesc> selectByExampleWithBLOBs(TbItemDescQuery example);

    List<TbItemDesc> selectByExample(TbItemDescQuery example);

    TbItemDesc selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") TbItemDesc record, @Param("example") TbItemDescQuery example);

    int updateByExampleWithBLOBs(@Param("record") TbItemDesc record, @Param("example") TbItemDescQuery example);

    int updateByExample(@Param("record") TbItemDesc record, @Param("example") TbItemDescQuery example);

    int updateByPrimaryKeySelective(TbItemDesc record);

    int updateByPrimaryKeyWithBLOBs(TbItemDesc record);

    int updateByPrimaryKey(TbItemDesc record);
}