package com.feon.springboot;

import com.baomidou.mybatisplus.plugins.Page;
import com.feon.springboot.model.Book;
import com.feon.springboot.model.dto.BookDTO;
import com.feon.springboot.service.IBookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDaoTest {

    @Autowired
    private IBookService bookService;

    @Test
    public void testFindById() {
        //根据id查找
        Book book = this.bookService.selectById(1);
        assertNotNull(book);
        System.out.println(book);
    }

    @Test
    public void testFindByIds() {
        //根据多个id查询
        List<Book> books = this.bookService.selectBatchIds(Arrays.asList(1, 2, 4));
        assertNotNull(books);
        books.forEach(System.out :: println);
    }

    @Test
    public void testFindOneByObjCondition() {
        //将查询条件封装在bean进行查询
        Book paramBook = new Book();
        paramBook.setName("三国演义");
        Book book = this.bookService.findObjByCondition(paramBook);
        assertNotNull(book);
        System.out.println(book);
    }

    @Test
    public void testFindByMapCondition() {
        //将查询条件封装到map进行查询
        Map<String, Object> param = new HashMap<>();
        param.put("type", "B");
        List<Book> books = this.bookService.selectByMap(param);
        assertNotNull(books);
        books.forEach(System.out :: println);
    }

    @Test
    public void testPageFind() {
        //分页查询
        Page<Book> page = new Page<Book>();
        page.setCurrent(1);
        page.setSize(3);
//        page.setDescs(Arrays.asList("id", "name"));

        Map<String, Object> condition = new HashMap<>();
        condition.put("type", "B");
        page.setCondition(condition);

        Page<Book> result = this.bookService.selectPage(page);
        List<Book> books = null;
        if(result != null) {
            books = result.getRecords();
        }
        assertNotNull(books);
        books.forEach(System.out :: println);
    }

    @Test
    public void testInsert() {
        Book book = new Book();
        book.setName("龙珠");
        book.setMessage("漫画");
        book.setType("M");
        book.setPostDate(new Date());
        assertTrue(this.bookService.insertAllColumn(book)); //生成所有字段的insert语句（如果没有值，字段值为null）
    }

    @Test
    public void testInsertAllColumns() {
        Book book = new Book();
        book.setName("情深深雨蒙蒙");
        assertTrue(this.bookService.insert(book)); //生成对应字段的insert语句
    }

    @Test
    public void testInsertBatch() {
        Book book1 = new Book();
        book1.setName("春之梦幻");
        book1.setMessage("漫画");
        book1.setType("M");
        book1.setPostDate(new Date());

        Book book2 = new Book();
        book2.setName("城市猎人");
        book2.setMessage("漫画");
        book2.setType("M");
        book2.setPostDate(new Date());

        Book book3 = new Book();
        book3.setName("IQ博士");
        book3.setMessage("漫画");
        book3.setType("M");
        book3.setPostDate(new Date());

        Book book4 = new Book();
        book4.setName("龙虎门");
        book4.setMessage("漫画");
        book4.setType("M");
        book4.setPostDate(new Date());

//        assertTrue(this.bookService.insertBatch(Arrays.asList(book1, book2, book3, book4)));
        //设置批量数为2
        assertTrue(this.bookService.insertBatch(Arrays.asList(book1, book2, book3, book4), 2));
    }

    @Test
    public void testInsertOrUpdate() {
        Book book = new Book();
        book.setId(9);
        book.setName("IQ博士");
        assertTrue(bookService.insertOrUpdate(book));
    }

    @Test
    public void testInsertOrUpdateAllColumns() {
        Book book = new Book();
        book.setId(9);
        book.setName("龙珠");
        book.setMessage("漫画");
        book.setType("M");
        book.setPostDate(new Date());
        assertTrue(bookService.insertOrUpdateAllColumn(book));
    }

    @Test
    public void testDeleteById() {
        assertTrue(this.bookService.deleteById(7));
    }

    @Test
    public void testDeleteByIds() {
        assertTrue(this.bookService.deleteBatchIds(Arrays.asList(6, 8)));
    }

    @Test
    public void testDeleteByMap() {
        //将条件字段封装到map，批量删除
        Map<String, Object> param = new HashMap<>();
        param.put("name", "还珠格格");
        assertTrue(this.bookService.deleteByMap(param));
    }

    @Test
    public void testUpdateById() {
        //根据id更新数据
        Book book = new Book();
        book.setId(21);
        book.setName("《龙虎门》");
        assertTrue(this.bookService.updateById(book));
    }

    @Test
    public void testUpdateBatch() {
        //根据id批量更新
        Book book1 = new Book();
        book1.setId(18);
        book1.setName("《春之梦幻》");
        book1.setMessage("漫画");
        book1.setType("M");
        book1.setPostDate(new Date());

        Book book2 = new Book();
        book2.setId(19);
        book2.setName("《城市猎人》");
        book2.setMessage("漫画");
        book2.setType("M");
        book2.setPostDate(new Date());

        Book book3 = new Book();
        book3.setId(20);
        book3.setName("《IQ博士》");
        book3.setMessage("漫画");
        book3.setType("M");
        book3.setPostDate(new Date());

        assertTrue(this.bookService.updateBatchById(Arrays.asList(book1, book2, book3)));
    }

    @Test
    public void testUpdateBatchAllColumns() {
        //批量更新全部字段
        Book book1 = new Book();
        book1.setId(18);
        book1.setName("春之梦幻");
        book1.setMessage("漫画");
        book1.setType("M");
        book1.setPostDate(new Date());

        Book book2 = new Book();
        book2.setId(19);
        book2.setName("城市猎人");
        book2.setMessage("漫画");
        book2.setType("M");
        book2.setPostDate(new Date());

        Book book3 = new Book();
        book3.setId(20);
        book3.setName("IQ博士");
        book3.setMessage("漫画");
        book3.setType("M");
        book3.setPostDate(new Date());

        Book book4 = new Book();
        book4.setId(21);
        book4.setName("IQ博士");
        book4.setMessage("漫画");
        book4.setType("M");
        book4.setPostDate(new Date());

        //设置批量数为2
        assertTrue(this.bookService.updateAllColumnBatchById(Arrays.asList(book1, book2, book3, book4), 2));
    }

    @Test
    public void testPageFindByXml() {
        //传入当前页，每页多少条
        Page page = new Page(1, 2);
        Page<Book> books = this.bookService.pageFind(page);
        assertNotNull(books);
        assertNotNull(books.getRecords());
        books.getRecords().forEach(System.out :: println);
    }

    @Test
    public void testPageFindWithParamMapByXml() {
        Page page = new Page(1, 3);
        Page<Book> books = this.bookService.pageFindWithParamMap(page, "B", "红楼梦");
        assertNotNull(books);
        assertNotNull(books.getRecords());
        books.getRecords().forEach(System.out :: println);
    }

    @Test
    public void testPageFindWithParamObjByXml() {
        Page page = new Page(1, 10);
        BookDTO bookDTO = new BookDTO();
        bookDTO.setType("M");
        Page<Book> books = this.bookService.pageFindWithParamObj(page, bookDTO);
        assertNotNull(books);
        assertNotNull(books.getRecords());
        books.getRecords().forEach(System.out :: println);
    }
}
