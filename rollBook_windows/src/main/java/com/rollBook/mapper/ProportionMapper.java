package com.rollBook.mapper;

import com.rollBook.po.Proportion;
import com.rollBook.po.ProportionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProportionMapper {
    int countByExample(ProportionExample example);

    int deleteByExample(ProportionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Proportion record);

    int insertSelective(Proportion record);

    List<Proportion> selectByExample(ProportionExample example);

    Proportion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Proportion record, @Param("example") ProportionExample example);

    int updateByExample(@Param("record") Proportion record, @Param("example") ProportionExample example);

    int updateByPrimaryKeySelective(Proportion record);

    int updateByPrimaryKey(Proportion record);
}