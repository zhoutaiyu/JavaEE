package com.book.mapper;

import com.book.po.Bookta;
import com.book.po.Bookta;
import com.book.po.BooktaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BooktaMapper {
    int countByExample(BooktaExample example);

    int deleteByExample(BooktaExample example);

    int deleteByPrimaryKey(String isbn);

    int insert(Bookta record);

    int insertSelective(Bookta record);

    List<Bookta> selectByExample(BooktaExample example);

    Bookta selectByPrimaryKey(String isbn);

    int updateByExampleSelective(@Param("record") Bookta record, @Param("example") BooktaExample example);

    int updateByExample(@Param("record") Bookta record, @Param("example") BooktaExample example);

    int updateByPrimaryKeySelective(Bookta record);

    int updateByPrimaryKey(Bookta record);
}