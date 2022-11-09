package org.dog.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.entity.Book;
import org.dog.server.exception.ServiceException;
import org.dog.server.mapper.BookMapper;
import org.dog.server.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/21 01:54
 * @Description:
 */

@Service
public class BookServiceImpl implements BookService {

  @Resource
  private BookMapper bookMapper;

  @Override
  public List<Book> list() {
    return bookMapper.list();
  }

  @Override
  public PageInfo<Book> page(BaseRequest baseRequest) {
    PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
    return new PageInfo<>(bookMapper.listByCondition(baseRequest));
  }

  @Override
  public void save(Book book) {
    try {
      book.setCategory(category(book.getCategories()));
      bookMapper.save(book);
    } catch (Exception e) {
      throw new ServiceException("数据插入错误", e);
    }
  }

  @Override
  public Book getById(Integer id) {
    return bookMapper.getById(id);
  }

  @Override
  public void update(Book book) {
    try {
      book.setCategory(category(book.getCategories()));
      book.setUpdatetime(LocalDate.now());
      bookMapper.updateById(book);
    } catch (Exception e) {
      throw new ServiceException("数据更新错误", e);
    }

  }

  @Override
  public void deleteById(Integer id) {
    bookMapper.deleteById(id);
  }


  private String category(List<String> categories) {
    StringBuilder sb = new StringBuilder();
    if (CollUtil.isNotEmpty(categories)) {
      categories.forEach(v -> sb.append(v).append(" > "));
      return sb.substring(0, sb.lastIndexOf(" > "));
    }
    return sb.toString();
  }
}
