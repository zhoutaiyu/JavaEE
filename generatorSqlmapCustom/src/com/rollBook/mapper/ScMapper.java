package com.rollBook.mapper;

import com.rollBook.po.Sc;
import com.rollBook.po.ScExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScMapper {
    int countByExample(ScExample example);

    int deleteByExample(ScExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Sc record);

    int insertSelective(Sc record);

    List<Sc> selectByExample(ScExample example);

    Sc selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Sc record, @Param("example") ScExample example);

    int updateByExample(@Param("record") Sc record, @Param("example") ScExample example);

    int updateByPrimaryKeySelective(Sc record);

    int updateByPrimaryKey(Sc record);
}