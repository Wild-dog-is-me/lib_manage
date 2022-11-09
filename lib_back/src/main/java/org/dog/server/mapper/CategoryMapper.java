package org.dog.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.entity.Category;

import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/18 14:03
 * @Description:
 */

@Mapper
public interface CategoryMapper {

  List<Category> list();

  List<Category> listByCondition(BaseRequest baseRequest);

  void save(Category category);

  Category getById(Integer id);

  void updateById(Category user);

  void deleteById(Integer id);
}
