package com.feon.springboot.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.feon.springboot.model.Book;
import com.feon.springboot.model.dto.BookDTO;

/**
 * 书籍表业务层接口
 */
public interface IBookService extends IService<Book> {
    Book findObjByCondition(Book book);

    Page<Book> pageFind(Page<Book> page);

    Page<Book> pageFindWithParamMap(Page<Book> page, String type, String name);

    Page<Book> pageFindWithParamObj(Page<Book> page, BookDTO bookDTO);
}