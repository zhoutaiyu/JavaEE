package com.sell.mapper;

import com.sell.po.Content;
import com.sell.po.ContentExample;
import com.sell.po.ContentWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContentMapper {
    int countByExample(ContentExample example);

    int deleteByExample(ContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ContentWithBLOBs record);

    int insertSelective(ContentWithBLOBs record);

    List<ContentWithBLOBs> selectByExampleWithBLOBs(ContentExample example);

    List<Content> selectByExample(ContentExample example);

    ContentWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ContentWithBLOBs record, @Param("example") ContentExample example);

    int updateByExampleWithBLOBs(@Param("record") ContentWithBLOBs record, @Param("example") ContentExample example);

    int updateByExample(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByPrimaryKeySelective(ContentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ContentWithBLOBs record);

    int updateByPrimaryKey(Content record);
}