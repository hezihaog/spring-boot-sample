package com.feon.springboot.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.feon.springboot.mapper.BookMapper;
import com.feon.springboot.model.Book;
import com.feon.springboot.model.dto.BookDTO;
import com.feon.springboot.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 书籍表业务层实现类
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book findObjByCondition(Book book) {
        return this.bookMapper.selectOne(book);
    }

    @Override
    public Page<Book> pageFind(Page<Book> page) {
        page.setRecords(this.bookMapper.showList(page));
        return page;
    }

    @Override
    public Page<Book> pageFindWithParamMap(Page<Book> page, String type, String name) {
        page.setRecords(this.bookMapper.showListWithParamMap(page, type, name));
        return page;
    }

    @Override
    public Page<Book> pageFindWithParamObj(Page<Book> page, BookDTO bookDTO) {
        page.setRecords(this.bookMapper.showListWithParamObj(page, bookDTO));
        return page;
    }
}
