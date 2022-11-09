package org.dog.server.service;

import com.github.pagehelper.PageInfo;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.entity.Category;

import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/18 14:53
 * @Description:
 */
public interface CategoryService {

  List<Category> list();

  PageInfo<Category> page(BaseRequest baseRequest);

  void save(Category category);

  Category getById(Integer id);

  void update(Category category);

  void deleteById(Integer id);
}
