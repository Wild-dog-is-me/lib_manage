package org.dog.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.entity.Category;
import org.dog.server.mapper.CategoryMapper;
import org.dog.server.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/18 14:54
 * @Description:
 */

@Service
public class CategoryServiceImpl implements CategoryService {

  @Resource
  private CategoryMapper categoryMapper;

  @Override
  public List<Category> list() {
    return categoryMapper.list();
  }

  @Override
  public PageInfo<Category>  page(BaseRequest baseRequest) {
    PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
    List<Category> categories = categoryMapper.listByCondition(baseRequest);
    System.out.println(categories.toArray());
    return new PageInfo<>(categories);
  }

  @Override
  public void save(Category category) {
    categoryMapper.save(category);
  }

  @Override
  public Category getById(Integer id) {
    return categoryMapper.getById(id);
  }

  @Override
  public void update(Category category) {
    category.setUpdatetime(LocalDate.now());
    categoryMapper.updateById(category);
  }

  @Override
  public void deleteById(Integer id) {
    categoryMapper.deleteById(id);
  }
}
