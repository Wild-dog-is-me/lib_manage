package org.dog.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.entity.Book;

import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/21 01:54
 * @Description:
 */

@Mapper
public interface BookMapper {

  List<Book> list();

  List<Book> listByCondition(BaseRequest baseRequest);

  void save(Book obj);

  Book getById(Integer id);

  void updateById(Book user);

  void deleteById(Integer id);

  Book getByNo(String bookNo);

}
