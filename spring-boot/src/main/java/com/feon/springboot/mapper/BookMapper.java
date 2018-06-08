package com.feon.springboot.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.feon.springboot.model.Book;
import com.feon.springboot.model.dto.BookDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper extends BaseMapper<Book> {
    List<Book> showList(Pagination page);

    List<Book> showListWithParamMap(Pagination page, @Param("type") String type, @Param("name") String name);

    List<Book> showListWithParamObj(Pagination page, BookDTO bookDTO);
}