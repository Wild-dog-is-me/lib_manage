package org.dog.server.controller;

import org.dog.server.common.request.BookPageRequest;
import org.dog.server.common.result.Result;
import org.dog.server.entity.Book;
import org.dog.server.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/21 01:53
 * @Description:
 */

@RestController
@RequestMapping("/book")
public class BookController {

  @Resource
  private BookService bookService;

  @PostMapping("/save")
  public Result save(@RequestBody Book book) {
    bookService.save(book);
    return Result.success();
  }

  @PutMapping("/update")
  public Result update(@RequestBody Book book) {
    bookService.update(book);
    return Result.success();
  }

  @GetMapping("/{id}")
  public Result getById(@PathVariable Integer id) {
    Book book = bookService.getById(id);
    return Result.success(book);
  }

  @DeleteMapping("/delete/{id}")
  public Result delete(@PathVariable Integer id) {
    bookService.deleteById(id);
    return Result.success();
  }

  @GetMapping("/list")
  public Result list() {
    List<Book> list = bookService.list();
    return Result.success(list);
  }

  @GetMapping("/page")
  public Result page(BookPageRequest pageRequest) {
    return Result.success(bookService.page(pageRequest));
  }
}
