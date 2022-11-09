package org.dog.server.controller;

import org.dog.server.common.request.BorrowPageRequest;
import org.dog.server.common.result.Result;
import org.dog.server.entity.Borrow;
import org.dog.server.entity.Retur;
import org.dog.server.service.BorrowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 借控制器
 *
 * @Author Odin
 * @Date 2022/10/22 02:02
 * @Description:
 */

@RestController
@RequestMapping("/borrow")
public class BorrowController {

  @Resource
  private BorrowService borrowService;

  @PostMapping("/save")
  public Result save(@RequestBody Borrow borrow) {
    borrowService.save(borrow);
    return Result.success();
  }
  @PutMapping("/update")
  public Result update(@RequestBody Borrow obj) {
    borrowService.update(obj);
    return Result.success();
  }

  @DeleteMapping("/delete/{id}")
  public Result delete(@PathVariable Integer id) {
    borrowService.deleteById(id);
    return Result.success();
  }

  @GetMapping("/{id}")
  public Result getById(@PathVariable Integer id) {
    Borrow obj = borrowService.getById(id);
    return Result.success(obj);
  }

  @GetMapping("/list")
  public Result list() {
    List<Borrow> list = borrowService.list();
    return Result.success(list);
  }

  @GetMapping("/page")
  public Result page(BorrowPageRequest pageRequest) {
    return Result.success(borrowService.page(pageRequest));
  }

  @GetMapping("/pageRetur")
  public Result pageRetur(BorrowPageRequest pageRequest) {
    return Result.success(borrowService.pageRetur(pageRequest));
  }

  @PostMapping("/saveRetur")
  public Result saveRetur(@RequestBody Retur obj) {
    borrowService.saveRetur(obj);
    return Result.success();
  }

  @DeleteMapping("/deleteRetur/{id}")
  public Result deleteRetur(@PathVariable Integer id) {
    borrowService.deleteReturById(id);
    return Result.success();
  }
}
