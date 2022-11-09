package org.dog.server.service;

import org.dog.server.common.dto.LoginDTO;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.common.request.LoginRequest;
import org.dog.server.common.request.PasswordRequest;
import org.dog.server.entity.Admin;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/13 00:41
 * @Description:
 */
public interface AdminService {

  List<Admin> list();

  PageInfo<Admin> page(BaseRequest baseRequest);

  Admin getById(Integer id);

  void update(Admin admin);

  void deleteById(Integer id);

  void save(Admin admin);

  LoginDTO login(LoginRequest request);

  void changePass(@RequestBody PasswordRequest request);
}
