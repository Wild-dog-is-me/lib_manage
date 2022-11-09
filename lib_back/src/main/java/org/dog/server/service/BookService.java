package org.dog.server.service;

import com.github.pagehelper.PageInfo;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.entity.Book;

import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/21 01:54
 * @Description:
 */
public interface BookService {

  List<Book> list();

  PageInfo<Book> page(BaseRequest baseRequest);

  void save(Book book);

  Book getById(Integer id);

  void update(Book book);

  void deleteById(Integer id);

}
