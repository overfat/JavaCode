package com.hfx.dao;

import com.hfx.pojo.Books;

import java.util.List;

public interface BookMapper {

    // 增加一个book
    int addBook(Books book);

    //根据id删除book
    int deleteBookById(int id);

    // 更新book
    int updateBook(Books books);

    // 根据id查询，返回一个book
    Books queryBookById(int id);

    //查询全部书，返回一个List
    List<Books> queryAllBook();


}
