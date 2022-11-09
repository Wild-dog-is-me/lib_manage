package org.dog.server.controller;

import cn.hutool.core.collection.CollUtil;
import org.dog.server.common.request.CategoryPageRequest;
import org.dog.server.common.result.Result;
import org.dog.server.entity.Category;
import org.dog.server.service.CategoryService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/18 15:09
 * @Description:
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

  @Resource
  private CategoryService categoryService;

  @PostMapping("/save")
  private Result save(@RequestBody Category category) {
    categoryService.save(category);
    return Result.success();
  }

  @PutMapping("/update")
  public Result update(@RequestBody Category category) {
    categoryService.update(category);
    return Result.success();
  }

  @DeleteMapping("/delete/{id}")
  public Result delete(@PathVariable Integer id) {
    categoryService.deleteById(id);
    return Result.success();
  }

  @GetMapping("/{id}")
  public Result getById(@PathVariable Integer id) {
    return Result.success(categoryService.getById(id));
  }

  @GetMapping("/list")
  public Result list() {
    List<Category> list = categoryService.list();
    return Result.success(list);
  }

  @GetMapping("/tree")
  public Result tree() {
    List<Category> list = categoryService.list();

    // 对list操作即可
//        List<Category> treeList = list.stream().filter(v -> v.getPid() == null).collect(Collectors.toList());  // 第一层
    // 比如 递归实现 children列表的查询

    return Result.success(createTree(null, list));   //  null 表示从第一级开始递归
  }

  // 完全递归的方法来实现递归树
  private List<Category> createTree(Integer pid, List<Category> categories) {
    List<Category> treeList = new ArrayList<>();
    for (Category category : categories) {
      if (pid == null) {
        if (category.getPid() == null) {  // 那这就是第一级节点
          treeList.add(category);
          category.setChildren(createTree(category.getId(), categories));
        }
      } else {
        if (pid.equals(category.getPid())) {
          treeList.add(category);
          category.setChildren(createTree(category.getId(), categories));
        }
      }
      if (CollUtil.isEmpty(category.getChildren())) {
        category.setChildren(null);
      }
    }
    return treeList;
  }

  @GetMapping("/page")
  public Result page(CategoryPageRequest pageRequest) {
    return Result.success(categoryService.page(pageRequest));
  }

}
