package org.dog.server.service;

import com.github.pagehelper.PageInfo;
import org.dog.server.common.request.BaseRequest;
import org.dog.server.entity.User;

import java.util.List;

/**
 * @Author Odin
 * @Date 2022/10/10 08:46
 * @Description:
 */

public interface UserService {

  List<User> list();

  User getById(Integer id);

  PageInfo<User> page(BaseRequest baseRequest);

  void update(User user);

  void deleteById(Integer id);

  void save(User user);

  void handleAccount(User user);
}
