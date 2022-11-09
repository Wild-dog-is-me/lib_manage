package org.dog.server.controller;

import org.dog.server.common.result.Result;
import org.dog.server.common.request.UserPageRequest;
import org.dog.server.entity.User;
import org.dog.server.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/10 08:48
 * @Description:
 */


@RestController
@RequestMapping("/user")
public class UserController {

  @Resource
  private UserService userService;

  @PostMapping("/account")
  public Result account(@RequestBody User user) {
    userService.handleAccount(user);
    return Result.success();
  }

  @GetMapping("/list")
  public Result list() {
    List userList = userService.list();
    return Result.success(userList);
  }

  @GetMapping("/{id}")
  public Result getById(@PathVariable Integer id) {
    User user = userService.getById(id);
    return Result.success(user);
  }

  @GetMapping("/page")
  public Result page(UserPageRequest userPageRequest) {
    return Result.success(userService.page(userPageRequest));
  }

  @PutMapping("/update")
  public Result update(@RequestBody User user) {
    userService.update(user);
    return Result.success();
  }

  @PostMapping("/save")
  public Result save(@RequestBody User user) {
    userService.save(user);
    String username = user.getUsername();
    return Result.success(username);
  }

  @DeleteMapping("/delete/{id}")
  public Result delete(@PathVariable Integer id) {
    userService.deleteById(id);
    return Result.success();
  }

}
