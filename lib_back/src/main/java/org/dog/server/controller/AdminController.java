package org.dog.server.controller;

import org.dog.server.common.result.Result;
import org.dog.server.common.dto.LoginDTO;
import org.dog.server.common.request.AdminPageRequest;
import org.dog.server.common.request.LoginRequest;
import org.dog.server.common.request.PasswordRequest;
import org.dog.server.entity.Admin;
import org.dog.server.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/13 00:40
 * @Description:
 */

@RestController
@RequestMapping("/admin")
public class AdminController {


  @Resource
  private AdminService adminService;

  @PostMapping("/login")
  public Result login(@RequestBody LoginRequest request) {
    LoginDTO login = adminService.login(request);
    return Result.success(login);
  }

  @PutMapping("/password")
  public Result password(@RequestBody PasswordRequest request) {
    adminService.changePass(request);
    return Result.success();
  }

  @PostMapping("/save")
  public Result save(@RequestBody Admin obj) {
    adminService.save(obj);
    return Result.success();
  }

  @PutMapping("/update")
  public Result update(@RequestBody Admin obj) {
    adminService.update(obj);
    return Result.success();
  }

  @DeleteMapping("/delete/{id}")
  public Result delete(@PathVariable Integer id) {
    adminService.deleteById(id);
    return Result.success();
  }

  @GetMapping("/{id}")
  public Result getById(@PathVariable Integer id) {
    Admin obj = adminService.getById(id);
    return Result.success(obj);
  }

  @GetMapping("/list")
  public Result list() {
    List<Admin> list = adminService.list();
    return Result.success(list);
  }

  @GetMapping("/page")
  public Result page(AdminPageRequest pageRequest) {
    return Result.success(adminService.page(pageRequest));
  }

}
